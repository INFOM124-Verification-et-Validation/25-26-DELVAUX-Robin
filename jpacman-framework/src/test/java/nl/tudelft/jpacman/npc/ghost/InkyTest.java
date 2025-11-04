package nl.tudelft.jpacman.npc.ghost;

import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.level.*;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class InkyTest {
    private PacManSprites pacManSprites = new PacManSprites();
    private PlayerFactory playerFactory = new PlayerFactory(pacManSprites);
    private GhostFactory ghostFactory = new GhostFactory(pacManSprites);
    private LevelFactory levelFactory = new LevelFactory(pacManSprites, ghostFactory);
    private BoardFactory boardFactory = new BoardFactory(pacManSprites);
    MapParser ghostMapParser = new GhostMapParser(levelFactory, boardFactory, ghostFactory);

    @Test
    void BlinkyBetweenInkiAndPacManTest() {
        List<String> map = Arrays.asList(
            "#############",
            "#I  B      P#",
            "#############"
        );

        Level level = ghostMapParser.parseMap(map);
        Player pacman = playerFactory.createPacMan();
        level.registerPlayer(pacman);

        Inky inky = Navigation.findUnitInBoard(Inky.class, level.getBoard());
        Blinky blinky = Navigation.findUnitInBoard(Blinky.class, level.getBoard());
        assertNotNull(inky);
        assertNotNull(blinky);
        Optional<Direction> direction = inky.nextAiMove();
        assertEquals(Direction.EAST, direction.get());
    }

    @Test
    void InkyBetweenBlinkyAndPacManTest() {
        List<String> map = Arrays.asList(
            "#############",
            "#B  I      P#",
            "#############"
        );

        Level level = ghostMapParser.parseMap(map);
        Player pacman = playerFactory.createPacMan();
        pacman.setDirection(Direction.WEST);
        level.registerPlayer(pacman);

        Inky inky = Navigation.findUnitInBoard(Inky.class, level.getBoard());
        Blinky blinky = Navigation.findUnitInBoard(Blinky.class, level.getBoard());
        assertNotNull(inky);
        assertNotNull(blinky);
        Optional<Direction> direction = inky.nextAiMove();
        assertEquals(Direction.WEST, direction.get());
    }
}
