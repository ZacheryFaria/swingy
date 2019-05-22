package zfaria.swingy.command;

import zfaria.swingy.Coordinate;
import zfaria.swingy.Game;
import zfaria.swingy.Map;
import zfaria.swingy.hero.Hero;

public class CommandMap implements IGameCommand {
    @Override
    public void run(Game game) {
        Map m = game.getMap();
        Hero h = game.getHero();
        StringBuilder builder = new StringBuilder();
        Coordinate c = new Coordinate();
        for (int j = 0; j < m.getSize(); j++) {
            for (int i = 0; i < m.getSize(); i++) {
                if (h.getPosition().equals(c)) {
                    builder.append("X ");
                } else if (m.containsMonster(c)) {
                    builder.append("E ");
                } else if (m.hasVisited(c)) {
                    builder.append(". ");
                } else {
                    builder.append("? ");
                }
                c.addCoordinate(1, 0);
            }
            builder.append("\n");
            c.addCoordinate(-m.getSize(), 0);
            c.addCoordinate(0, 1);
        }
        game.getView().messageUser(builder.toString());
    }
}
