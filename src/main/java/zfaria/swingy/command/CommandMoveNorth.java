package zfaria.swingy.command;

import zfaria.swingy.Coordinate;
import zfaria.swingy.Game;

public class CommandMoveNorth implements IGameCommand {

    @Override
    public void run(Game game) {
        if (game.getHero().getPosition().getY() > 0)
            game.movePlayer(new Coordinate(0, -1));
        else
            game.restart();
    }
}
