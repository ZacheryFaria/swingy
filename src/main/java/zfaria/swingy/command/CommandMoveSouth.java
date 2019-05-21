package zfaria.swingy.command;

import zfaria.swingy.Coordinate;
import zfaria.swingy.Game;

public class CommandMoveSouth implements IGameCommand {
    @Override
    public void run(Game game) {
        if (game.getHero().getPosition().getY() < game.getMap().getSize() - 1)
            game.movePlayer(new Coordinate(0, 1));
        else {
            game.restart();
        }
    }
}
