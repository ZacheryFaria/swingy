package zfaria.swingy.util;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import zfaria.swingy.hero.Hero;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DatabaseTest {

    private static String dbURL = "jdbc:sqlite:" + System.getProperty("user.dir") + "/swingy_test.db";
    private static Database db;

    private boolean insertTestCompleted = false;

    @BeforeClass
    public static void init() {
        db = new Database(dbURL);
    }

    @Test
    public void insertTest() {
        if (!insertTestCompleted) {
            Hero h = new Hero("Robert", "mage");
            assertEquals(true, db.serializeHero(null, h));
        }
        insertTestCompleted = true;
    }

    @Test
    public void listTest() {
        List<String> list = db.getAvailableHeroes();
        assertNotEquals(0, list.size());
    }

    @Test
    public void selectTest() {
        if (!insertTestCompleted)
            insertTest();
        Hero h = db.loadHero(1);
        assertNotNull(h);
    }

    @AfterClass
    public static void cleanup() {
        Path p = new File(System.getProperty("user.dir") + "/swingy_test.db").toPath();
        try {
            Files.delete(p);
        } catch (IOException e) {
        }
    }

}