package zfaria.swingy.view;

import zfaria.swingy.Map;
import zfaria.swingy.hero.Hero;

import java.util.Scanner;

public class ConsoleView implements GameView {

    private Scanner scanner;

    @Override
    public void init() {
        scanner = new Scanner(System.in);
    }

    @Override
    public String promptUser(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }

    @Override
    public void messageUser(String msg) {
        System.out.println(msg);
    }

    @Override
    public void updateHeroData(Hero h) {
    }

    @Override
    public void updateMapData(Map m, Hero h) {
    }

    @Override
    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    @Override
    public void lock() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void show() {
    }
}
