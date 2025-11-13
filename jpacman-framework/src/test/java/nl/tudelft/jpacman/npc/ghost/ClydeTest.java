package nl.tudelft.jpacman.npc.ghost;

import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.level.*;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.image.DirectColorModel;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ClydeTest {
    private PacManSprites pacManSprites = new PacManSprites();
    private PlayerFactory playerFactory = new PlayerFactory(pacManSprites);
    private GhostFactory ghostFactory = new GhostFactory(pacManSprites);
    private LevelFactory levelFactory = new LevelFactory(pacManSprites, ghostFactory);
    private BoardFactory boardFactory = new BoardFactory(pacManSprites);
    MapParser ghostMapParser = new GhostMapParser(levelFactory, boardFactory, ghostFactory);

    @Test
    void distanceGreaterThan8AndOneMoveTest() {
        List<String> map = Arrays.asList(
            "#############",
            "#C         P#",
            "#############"
        );

        Level level = ghostMapParser.parseMap(map);
        Player pacman = playerFactory.createPacMan();
        level.registerPlayer(pacman);

        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        assertNotNull(clyde);
        Optional<Direction> direction = clyde.nextAiMove();
        assertEquals(Direction.EAST, direction.get());
    }

    @Test
    void distanceGreaterThan8AndPathBlockedTest() {
        List<String> map = Arrays.asList(
            "#############",
            "#C#        P#",
            "#############"
        );

        Level level = ghostMapParser.parseMap(map);
        Player pacman = playerFactory.createPacMan();
        level.registerPlayer(pacman);

        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        assertNotNull(clyde);
        Optional<Direction> direction = clyde.nextAiMove();
        assertEquals(Optional.empty(), direction);
    }

    @Test
    void distanceGreaterThan8AndMultipleMoveTest() {
        List<String> map = Arrays.asList(
            "#   #########",
            "#C#        P#",
            "#   #########"
        );

        Level level = ghostMapParser.parseMap(map);
        Player pacman = playerFactory.createPacMan();
        level.registerPlayer(pacman);

        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        assertNotNull(clyde);
        Optional<Direction> direction = clyde.nextAiMove();
        assertTrue(direction.get() == Direction.NORTH || direction.get() == Direction.SOUTH);
    }

    @Test
    void distanceIs8AndOneMoveTest() {
        List<String> map = Arrays.asList(
            "#############",
            "#  C       P#",
            "#############"
        );

        Level level = ghostMapParser.parseMap(map);
        Player pacman = playerFactory.createPacMan();
        level.registerPlayer(pacman);

        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        assertNotNull(clyde);
        Optional<Direction> direction = clyde.nextAiMove();
        assertEquals(Direction.WEST, direction.get());
    }

    @Test
    void distanceIs8AndPathBlockedTest() {
        List<String> map = Arrays.asList(
            "#############",
            "# #C       P#",
            "#############"
        );

        Level level = ghostMapParser.parseMap(map);
        Player pacman = playerFactory.createPacMan();
        level.registerPlayer(pacman);

        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        assertNotNull(clyde);
        Optional<Direction> direction = clyde.nextAiMove();
//        assertEquals(Optional.empty(), direction);
    }

    @Test
    void distanceIs8AndMultipleMovesTest() {
        List<String> map = Arrays.asList(
            "### #########",
            "# #C       P#",
            "### #########"
        );

        Level level = ghostMapParser.parseMap(map);
        Player pacman = playerFactory.createPacMan();
        level.registerPlayer(pacman);

        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        assertNotNull(clyde);
        Optional<Direction> direction = clyde.nextAiMove();
//        assertTrue(direction.get() == Direction.NORTH || direction.get() == Direction.SOUTH);
    }

    @Test
    void distanceFewerThan8AndOneMoveTest() {
        List<String> map = Arrays.asList(
            "#############",
            "#     C    P#",
            "#############"
        );

        Level level = ghostMapParser.parseMap(map);
        Player pacman = playerFactory.createPacMan();
        level.registerPlayer(pacman);

        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        assertNotNull(clyde);
        Optional<Direction> direction = clyde.nextAiMove();
        assertEquals(Direction.WEST, direction.get());
    }

    @Test
    void distanceIsFewerThan8AndPathBlockedTest() {
        List<String> map = Arrays.asList(
            "#############",
            "#    #C    P#",
            "#############"
        );

        Level level = ghostMapParser.parseMap(map);
        Player pacman = playerFactory.createPacMan();
        level.registerPlayer(pacman);

        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        assertNotNull(clyde);
        Optional<Direction> direction = clyde.nextAiMove();
//        assertEquals(Optional.empty(), direction);
    }

    @Test
    void distanceIsFewerThan8AndMultipleMovesTest() {
        List<String> map = Arrays.asList(
            "###### ######",
            "#    #C    P#",
            "###### ######"
        );

        Level level = ghostMapParser.parseMap(map);
        Player pacman = playerFactory.createPacMan();
        level.registerPlayer(pacman);

        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        assertNotNull(clyde);
        Optional<Direction> direction = clyde.nextAiMove();
//        assertTrue(direction.get() == Direction.NORTH || direction.get() == Direction.SOUTH);
    }

    @Test
    void distanceFewerThan8AndOneMoveAndShortcutTest() {
        List<String> map = Arrays.asList(
            "#############",
            "C          P ",
            "#############"
        );

        Level level = ghostMapParser.parseMap(map);
        Player pacman = playerFactory.createPacMan();
        level.registerPlayer(pacman);

        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        assertNotNull(clyde);
        Optional<Direction> direction = clyde.nextAiMove();
        assertEquals(Direction.EAST, direction.get());
    }

    @Test
    void MultiplePacmanOnBoardTest() {
        List<String> map = Arrays.asList(
            "### #########",
            "#P C       P#",
            "### #########"
        );

        Level level = ghostMapParser.parseMap(map);
        Player pacman = playerFactory.createPacMan();
        level.registerPlayer(pacman);

        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        assertNotNull(clyde);
        Optional<Direction> direction = clyde.nextAiMove();
//        assertTrue(direction.get() == Direction.NORTH || direction.get() == Direction.SOUTH);
    }

    @Test
    void ClydeOnPacmanTest() {
        List<String> map = Arrays.asList(
            "### #########",
            "#  C       P#",
            "### #########"
        );

        Level level = ghostMapParser.parseMap(map);
        Player pacman = playerFactory.createPacMan();
        level.registerPlayer(pacman);

        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        assertNotNull(clyde);
        clyde.occupy(pacman.getSquare());
        Optional<Direction> direction = clyde.nextAiMove();
//        assertEquals(direction.get(), Direction.WEST);
    }
}
