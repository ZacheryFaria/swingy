package zfaria.swingy.command;

import zfaria.swingy.Game;

public class CommandHelp implements IGameCommand {

    private static final String helpString = "" +
            "Commands:\n" +
            "(N)orth\n" +
            "(S)outh\n" +
            "(E)ast\n" +
            "(W)est\n" +
            "Save\n" +
            "Exit\n";

    @Override
    public void run(Game game) {
        game.getView().messageUser(helpString);
    }
}
