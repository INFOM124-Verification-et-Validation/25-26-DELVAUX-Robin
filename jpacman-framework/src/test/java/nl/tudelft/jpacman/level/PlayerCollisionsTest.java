package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.npc.ghost.*;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerCollisionsTest {
    private PacManSprites pacManSprites = new PacManSprites();
    private PlayerFactory playerFactory = new PlayerFactory(pacManSprites);
    private GhostFactory ghostFactory = new GhostFactory(pacManSprites);

    private PlayerCollisions collider = new PlayerCollisions();
    private LevelFactory levelFactory = new LevelFactory(pacManSprites, ghostFactory);
    private BoardFactory boardFactory = new BoardFactory(pacManSprites);
    MapParser ghostMapParser = new GhostMapParser(levelFactory, boardFactory, ghostFactory);
    List<String> map = Arrays.asList(
        "#############",
        "#C    .    P#",
        "#############"
    );
    Level level = ghostMapParser.parseMap(map);
    Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
    Blinky blinky = Navigation.findUnitInBoard(Blinky.class, level.getBoard());
    Player pacman = playerFactory.createPacMan();

    @Test
    void PlayerCollideSGhost() {
        assertTrue(pacman.isAlive());

        collider.collide(pacman, clyde);

        assertFalse(pacman.isAlive());
    }

    @Test
    void GhostCollidesPlayer() {
        assertTrue(pacman.isAlive());

        collider.collide(clyde, pacman);

        assertFalse(pacman.isAlive());
    }

    @Test
    void PlayerCollidesPellet() {
        int current_score = pacman.getScore();
        int pellet_point = 15;
        Pellet pellet = new Pellet(pellet_point, pacman.getSprite());
        level.registerPlayer(pacman);
        pellet.occupy(pacman.getSquare());
        int current_nb_pellets = level.remainingPellets();

        collider.collide(pacman, pellet);

        assertEquals(pacman.getScore(), current_score + pellet_point);
        assertEquals(level.remainingPellets(), current_nb_pellets - 1);
        assertFalse(pellet.hasSquare());
    }

    @Test
    void PelletCollidesPlayer() {
        int current_score = pacman.getScore();
        int pellet_point = 15;
        Pellet pellet = new Pellet(pellet_point, pacman.getSprite());
        level.registerPlayer(pacman);
        pellet.occupy(pacman.getSquare());
        int current_nb_pellets = level.remainingPellets();

        collider.collide(pellet, pacman);

        assertEquals(pacman.getScore(), current_score + pellet_point);
        assertEquals(level.remainingPellets(), current_nb_pellets - 1);
        assertFalse(pellet.hasSquare());
    }

    @Test
    void GhostCollidesPellet() {
        Square square = clyde.getSquare();
        Pellet pellet = new Pellet(15, pacman.getSprite());
        pellet.occupy(square);
        int current_score = pacman.getScore();
        int current_nb_pellets = level.remainingPellets();

        collider.collide(clyde, pellet);

        assertEquals(pellet.getSquare(), square);
        assertEquals(pacman.getScore(), current_score);
        assertEquals(level.remainingPellets(), current_nb_pellets);
    }

    @Test
    void PelletCollidesGhost() {
        Square square = clyde.getSquare();
        Pellet pellet = new Pellet(15, pacman.getSprite());
        pellet.occupy(square);
        int current_score = pacman.getScore();
        int current_nb_pellets = level.remainingPellets();

        collider.collide(pellet, clyde);

        assertEquals(pellet.getSquare(), square);
        assertEquals(pacman.getScore(), current_score);
        assertEquals(level.remainingPellets(), current_nb_pellets);
    }


    @Test
    void GhostCollidesGhost() {
        collider.collide(clyde, blinky);
    }


}
