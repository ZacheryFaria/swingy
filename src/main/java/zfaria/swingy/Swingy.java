package zfaria.swingy;

import zfaria.swingy.view.ConsoleView;
import zfaria.swingy.view.SwingView;

import javax.swing.*;

public class Swingy {

    public static void main(String args[]) {
        if (args.length == 1) {
            if (args[0].equals("gui"))
                new Game(new SwingView());
            else if (args[0].equals("console"))
                new Game(new ConsoleView());
            else
                System.out.println("Invalid options. gui or console expected.");
        } else
            System.out.println("Invalid options. gui or console expected.");
    }

}
