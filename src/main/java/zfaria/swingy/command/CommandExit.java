package zfaria.swingy.command;

import zfaria.swingy.Game;

public class CommandExit implements IGameCommand {
    @Override
    public void run(Game game) {
        game.saveGame();
        System.exit(0);
    }
}
