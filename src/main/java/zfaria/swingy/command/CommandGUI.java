package zfaria.swingy.command;

import zfaria.swingy.Game;
import zfaria.swingy.view.ViewFactory;

import javax.swing.text.View;

public class CommandGUI implements IGameCommand {
    @Override
    public void run(Game game) {
        game.setView(ViewFactory.SWING_VIEW);
    }
}
