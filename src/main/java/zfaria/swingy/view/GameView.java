package zfaria.swingy.view;

import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import zfaria.swingy.Map;
import zfaria.swingy.hero.Hero;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Scanner;

public interface GameView  {


    /**
     * GameView.init() shall prepare any necessary housekeeping before anything else is done.
     * Such as creating a window and widgets, clearing the screen, etc.
     */
    void init();

    /**
     * This will be the basic way to prompt the user for input, will return a string when the user
     * sends data. Blocks until the user sends a message
     * @param prompt
     * @return
     */
    String promptUser(String prompt);

    /**
     * Similar to promptUser, however no response is expected
     * @param msg
     */
    void messageUser(String msg);

    /**
     * Updates the hero display, if there is one. May not be implemented on console
     * @param h
     */
    void updateHeroData(Hero h);

    /**
     * Updates the map display, may not be used every time on console.
     * @param m
     */
    void updateMapData(Map m, Hero h);

    /**
     * Clears the screen
     */
    void clearScreen();
}
