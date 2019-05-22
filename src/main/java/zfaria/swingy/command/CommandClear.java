package zfaria.swingy.command;

import zfaria.swingy.Game;
import zfaria.swingy.command.IGameCommand;

public class CommandClear implements IGameCommand {
    @Override
    public void run(Game game) {
        game.getView().clearScreen();
    }
}
