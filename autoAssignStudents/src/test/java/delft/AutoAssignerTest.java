package delft;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.within;
import java.time.temporal.ChronoUnit;

import java.util.*;
import java.util.stream.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import java.time.*;
import static org.assertj.core.api.Assertions.*;


class AutoAssignerTest {

    private ZonedDateTime date(int year, int month, int day, int hour, int minute) {
        return ZonedDateTime.of(year, month, day, hour, minute, 0, 0, ZoneId.systemDefault());
    }

    private AutoAssigner assigner = new AutoAssigner();
    List<Student> students = new ArrayList<>();
    List<Workshop> workshops = new ArrayList<>();




    @Test
    void basicTest() {
        students.add(new Student(1, "Robin", "rob1@mail"));

        Map<ZonedDateTime, Integer> spotsPerDate1 = new HashMap<>();
        spotsPerDate1.put(date(2025, 3, 6, 3, 50), 2);
        workshops.add(new Workshop(1, "workshop1", spotsPerDate1));

        AssignmentsLogger log = assigner.assign(students, workshops);

        assertThat(log.getAssignments()).containsExactlyInAnyOrder("workshop1,Robin,06/03/2025 03:50");
        assertThat(log.getErrors()).isEmpty();

    }

    @Test
    void NotEnoughStudentsTest() {
        students.add(new Student(1, "Robin", "rob1@mail"));

        Map<ZonedDateTime, Integer> spotsPerDate1 = new HashMap<>();
        spotsPerDate1.put(date(2025, 3, 6, 3, 50), 2);
        workshops.add(new Workshop(1, "workshop1", spotsPerDate1));

        AssignmentsLogger log = assigner.assign(students, workshops);

        assertThat(log.getAssignments()).containsExactlyInAnyOrder("workshop1,Robin,06/03/2025 03:50");
        assertThat(log.getErrors()).isEmpty();
    }

    @Test
    void MultipleNotOverlappingWorkShopsTest() {
        students.add(new Student(1, "Robin", "rob1@mail"));
        students.add(new Student(2, "Rob2", "rob2@mail"));
        students.add(new Student(3, "Rob3", "rob3@mail"));


        Map<ZonedDateTime, Integer> spotsPerDate1 = new HashMap<>();
        spotsPerDate1.put(date(2025, 3, 6, 3, 50), 2);
        workshops.add(new Workshop(1, "workshop1", spotsPerDate1));

        Map<ZonedDateTime, Integer> spotsPerDate2 = new HashMap<>();
        spotsPerDate2.put(date(2025, 3, 7, 2, 0), 2);
        workshops.add(new Workshop(2, "workshop2", spotsPerDate2));

        AssignmentsLogger log = assigner.assign(students, workshops);

        assertThat(log.getAssignments()).containsExactlyInAnyOrder("workshop1,Robin,06/03/2025 03:50",
                "workshop1,Rob2,06/03/2025 03:50", "workshop2,Robin,07/03/2025 02:00",
                "workshop2,Rob2,07/03/2025 02:00");

    }

    @Test
    void MultipleOverlappingWorkShopsTest() {
        students.add(new Student(1, "Robin", "rob1@mail"));
        students.add(new Student(2, "Rob2", "rob2@mail"));
        students.add(new Student(3, "Rob3", "rob3@mail"));


        Map<ZonedDateTime, Integer> spotsPerDate1 = new HashMap<>();
        spotsPerDate1.put(date(2025, 3, 6, 3, 50), 2);
        workshops.add(new Workshop(1, "workshop1", spotsPerDate1));

        Map<ZonedDateTime, Integer> spotsPerDate2 = new HashMap<>();
        spotsPerDate2.put(date(2025, 3, 6, 3, 50), 2);
        workshops.add(new Workshop(2, "workshop2", spotsPerDate1));

        AssignmentsLogger log = assigner.assign(students, workshops);

        assertThat(log.getAssignments()).containsExactlyInAnyOrder("workshop1,Robin,06/03/2025 03:50",
                "workshop1,Rob2,06/03/2025 03:50", "workshop2,Rob3,06/03/2025 03:50");

    }

    @Test
    void NotEnoughSpotTest() {
        students.add(new Student(1, "Robin", "rob1@mail"));
        students.add(new Student(2, "Rob2", "rob2@mail"));
        students.add(new Student(3, "Rob3", "rob3@mail"));

        Map<ZonedDateTime, Integer> spotsPerDate1 = new HashMap<>();
        spotsPerDate1.put(date(2025, 3, 6, 3, 50), 2);
        workshops.add(new Workshop(1, "workshop1", spotsPerDate1));

        AssignmentsLogger log = assigner.assign(students, workshops);

        assertThat(log.getAssignments()).containsExactlyInAnyOrder("workshop1,Robin,06/03/2025 03:50",
                "workshop1,Rob2,06/03/2025 03:50");
        assertThat(log.getErrors()).containsExactlyInAnyOrder("workshop1,Rob3");

    }

    @Test
    void NotEnoughSpotMultipleWorkshopsTest() {
        students.add(new Student(1, "Robin", "rob1@mail"));
        students.add(new Student(2, "Rob2", "rob2@mail"));
        students.add(new Student(3, "Rob3", "rob3@mail"));

        Map<ZonedDateTime, Integer> spotsPerDate1 = new HashMap<>();
        spotsPerDate1.put(date(2025, 3, 6, 3, 50), 2);
        workshops.add(new Workshop(1, "workshop1", spotsPerDate1));

        AssignmentsLogger log = assigner.assign(students, workshops);

        assertThat(log.getAssignments()).containsExactlyInAnyOrder("workshop1,Robin,06/03/2025 03:50",
                "workshop1,Rob2,06/03/2025 03:50");
        assertThat(log.getErrors()).containsExactlyInAnyOrder("workshop1,Rob3");

    }

    @Test
    void NoSpotTest() {
        students.add(new Student(1, "Robin", "rob1@mail"));
        students.add(new Student(2, "Rob2", "rob2@mail"));
        students.add(new Student(3, "Rob3", "rob3@mail"));


        Map<ZonedDateTime, Integer> spotsPerDate1 = new HashMap<>();
        spotsPerDate1.put(date(2025, 3, 6, 3, 50), 1);
        workshops.add(new Workshop(1, "workshop1", spotsPerDate1));

        Map<ZonedDateTime, Integer> spotsPerDate2 = new HashMap<>();
        spotsPerDate2.put(date(2025, 3, 6, 3, 50), 2);
        workshops.add(new Workshop(2, "workshop2", spotsPerDate2));

        AssignmentsLogger log = assigner.assign(students, workshops);

//        assertThat(log.getAssignments()).containsExactlyInAnyOrder("workshop2,Robin,06/03/2025 03:50",
//                "workshop2,Rob2,06/03/2025 03:50");
        assertThat(log.getErrors()).containsExactlyInAnyOrder("workshop1,Rob2",
                "workshop1,Rob3", "workshop2,Rob3");

    }

}
