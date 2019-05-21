package zfaria.swingy.command;

import zfaria.swingy.Game;

public interface IGameCommand {

    /**
     * I use a custom runnable clone as I need to supply an argument to the method call.
     * @param game
     */
    void run(Game game);
}
