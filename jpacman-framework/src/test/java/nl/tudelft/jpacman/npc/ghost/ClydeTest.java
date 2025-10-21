package nl.tudelft.jpacman.npc.ghost;

import nl.tudelft.jpacman.board.Board;
import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.level.*;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

public class ClydeTest {
    private PacManSprites pacManSprites = new PacManSprites();
    private Clyde clyde;
    private BoardFactory boardFactory = new BoardFactory(pacManSprites);
    private GhostFactory ghostFactory = new GhostFactory((pacManSprites));
    private LevelFactory levelFactory = new LevelFactory(pacManSprites, ghostFactory);
    private PlayerFactory playerFactory = new PlayerFactory(pacManSprites);

    MapParser ghostMapParser = new GhostMapParser(levelFactory, boardFactory, ghostFactory);


    void mySetUp() {
        PacManSprites sprites = mock(PacManSprites.class);
        boardFactory = new BoardFactory(sprites);
        ghostFactory = new GhostFactory(sprites);
        clyde = (Clyde)ghostFactory.createClyde();
        levelFactory = new LevelFactory(sprites, ghostFactory);
//        map = new GhostMapParser(levelFactory, boardFactory, ghostFactory);

        playerFactory = new PlayerFactory(sprites);

    }


    @Test
    void testNextAiMove() {


        ArrayList<String> carte = new ArrayList<>(Arrays.asList("###############", "#P         C  #", "###############"));
        System.out.println(clyde.hasSquare());
        Level level = ghostMapParser.parseMap(carte);
        Board board = level.getBoard();
        System.out.println(Navigation.findUnitInBoard(Clyde.class, board).getDirection());
//        System.out.println(clyde.nextAiMove());


        // Test without player
//        System.out.println(clyde.nextAiMove());
//        assertThat(clyde.nextAiMove()).isNull();




        Player player = playerFactory.createPacMan();
        level.registerPlayer(player);

    }

    @Test
    void distanceGreaterThan8AndPathBlockedTest() {
        List<String> map = Arrays.asList(
            "############",
            "#C#       P#",
            "############"
        );
        Level level = ghostMapParser.parseMap(map);

        // On doit ajouter le player manuellement, Clyde est déjà mis
        Player pacman = playerFactory.createPacMan();
        level.registerPlayer(pacman);

        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        assertNotNull(clyde);
        Optional<Direction> direction = clyde.nextAiMove();
        assertEquals(Optional.empty(), direction);
    }
}
