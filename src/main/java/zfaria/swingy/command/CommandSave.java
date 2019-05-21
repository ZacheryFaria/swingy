package zfaria.swingy.command;

import zfaria.swingy.Game;

public class CommandSave implements IGameCommand {
    @Override
    public void run(Game game) {
        game.saveGame();
    }
}
