package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.npc.ghost.Clyde;
import nl.tudelft.jpacman.npc.ghost.GhostFactory;
import nl.tudelft.jpacman.npc.ghost.GhostMapParser;
import nl.tudelft.jpacman.npc.ghost.Navigation;
import nl.tudelft.jpacman.sprite.PacManSprites;

import java.util.Arrays;
import java.util.List;

public class main {

    public static void main(String[] args) {
        PacManSprites pacManSprites = new PacManSprites();
         PlayerFactory playerFactory = new PlayerFactory(pacManSprites);
         GhostFactory ghostFactory = new GhostFactory(pacManSprites);

         PlayerCollisions collider = new PlayerCollisions();
         LevelFactory levelFactory = new LevelFactory(pacManSprites, ghostFactory);
         BoardFactory boardFactory = new BoardFactory(pacManSprites);
        MapParser ghostMapParser = new GhostMapParser(levelFactory, boardFactory, ghostFactory);
        List<String> map = Arrays.asList(
            "#############",
            "#C    .    P#",
            "#############"
        );
        Level level = ghostMapParser.parseMap(map);
        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        Player pacman = playerFactory.createPacMan();
        level.registerPlayer(pacman);
        System.out.println(level.getBoard().squareAt(1, 1));
    }
}
