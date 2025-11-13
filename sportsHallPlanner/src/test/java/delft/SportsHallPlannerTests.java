package delft;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import java.util.*;
import java.util.stream.*;

import static java.util.Collections.*;
import static org.assertj.core.api.Assertions.*;
import static delft.Field.*;
import static delft.Property.*;
import static delft.SportsHallPlanner.planHalls;

class SportsHallPlannerTests {

    List<SportsHall> halls = new ArrayList<>();
    List<Request> requests = new ArrayList<>();

    @Test
    void happyPathTest() {
        Set<Property> properties = new HashSet<>();
        properties.add(NEAR_CITY_CENTRE);
        Request request = new Request(properties, BADMINTON, 1);
        requests.add(request);

        Set<Property> properties2 = new HashSet<>();
        properties2.add(NEAR_CITY_CENTRE);
        Map<Field, Integer> fields = new HashMap<>();
        fields.put(BADMINTON, 2);
        SportsHall hall = new SportsHall(properties, fields);
        halls.add(hall);

        Map<SportsHall, Request> planning = SportsHallPlanner.planHalls(requests, halls);

        assertThat(planning.get(hall)).isEqualTo(request);
    }

    @Test
    void TwoRequestsTest() {
        Set<Property> properties = new HashSet<>();
        properties.add(NEAR_CITY_CENTRE);
        Request request1 = new Request(properties, BADMINTON, 2);
        Request request2 = new Request(properties, BADMINTON, 1);
        requests.add(request1);
        requests.add(request2);

        Set<Property> properties2 = new HashSet<>();
        properties2.add(NEAR_CITY_CENTRE);
        Map<Field, Integer> fields = new HashMap<>();
        fields.put(BADMINTON, 1);
        SportsHall hall1 = new SportsHall(properties, fields);
        SportsHall hall2 = new SportsHall(properties, fields);
        halls.add(hall2);

        Map<SportsHall, Request> planning = SportsHallPlanner.planHalls(requests, halls);

        assertThat(planning.get(hall1)).isEqualTo(request2);
        assertThat(planning.get(hall2)).isEqualTo(request1);
    }
}
