package zfaria.swingy.command;

import zfaria.swingy.Coordinate;
import zfaria.swingy.Game;

public class CommandMoveWest implements IGameCommand {
    @Override
    public void run(Game game) {
        if (game.getHero().getPosition().getX() > 0)
            game.movePlayer(new Coordinate(-1, 0));
        else
            game.restart();
    }
}
