package zfaria.swingy.util;

import zfaria.swingy.hero.Hero;
import zfaria.swingy.hero.HeroBuilder;
import zfaria.swingy.view.GameView;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private static Connection conn;

    public static final String _dbURL = "jdbc:sqlite:" + System.getProperty("user.dir") + "/swingy.db";

    public Database(String dbURL) {
        try {
            conn = DriverManager.getConnection(dbURL);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to SQLite driver, aborting...");
        }

        try {
            String sql = "CREATE TABLE IF NOT EXISTS heroes (\n" +
                    "id integer PRIMARY KEY AUTOINCREMENT,\n" +
                    "name text NOT NULL,\n" +
                    "class text NOT NULL,\n" +
                    "experience integer,\n" +
                    "armor text,\n" +
                    "helm text,\n" +
                    "weapon text\n" +
                    ");";
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean heroExists(Hero h) {
        boolean ret;
        String sql = "SELECT * FROM heroes WHERE name = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, h.getName());
            ResultSet res = stmt.executeQuery();
            ret = !res.isClosed();
            res.close();
            return ret;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean serializeHero(GameView view, Hero h) {
        String sql;
        if (!heroExists(h)) {
             sql = "INSERT INTO heroes (name, class, experience, armor, helm, weapon) values (\n" +
                    "    \"%s\",\n" +
                    "    \"%s\",\n" +
                    "    %d, \n" +
                    "    \"%s\",\n" +
                    "    \"%s\",\n" +
                    "    \"%s\");";
            sql = String.format(sql, h.getName(), h.getHeroClass(), h.getExperience(), h.getArmor().toString(),
                    h.getHelm().toString(), h.getWeapon().toString());
        } else {
            sql = "UPDATE heroes SET \n" +
                    "experience = %d," +
                    "armor = \"%s\"," +
                    "helm = \"%s\"," +
                    "weapon = \"%s\"" +
                    "where name = \"%s\"";
            sql = String.format(sql, h.getExperience(), h.getArmor().toString(),
                    h.getHelm().toString(), h.getWeapon().toString(), h.getName());
        }
        try (Statement stmt = conn.createStatement()) {
            if (heroExists(h)) {
                stmt.executeUpdate(sql);
            } else {
                stmt.execute(sql);
            }
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
            //view.messageUser("Error saving hero to database\n" + e.getMessage());
            //return false;
        }
    }

    public List<String> getAvailableHeroes() {
        List<String> list = new ArrayList<String>();
        try (Statement stmt = conn.createStatement()) {
            String sql = "SELECT * FROM heroes";
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                String s = "| ID: %d | Name: %s | Class: %s | Experience: %d |";
                s = String.format(s, res.getInt("id"), res.getString("name"), res.getString("class"), res.getInt("experience"));
                list.add(s);
            }
            res.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public Hero loadHero(int id) {
        HeroBuilder builder = new HeroBuilder();
        try (Statement stmt = conn.createStatement()) {
            String sql = "SELECT * FROM heroes where id = " + id;
            ResultSet res = stmt.executeQuery(sql);
            if (res.next()) {
                builder.setName(res.getString("name"));
                builder.setClassName(res.getString("class"));
                builder.setExperience(res.getInt("experience"));
                builder.setArmor(res.getString("armor"));
                builder.setHelm(res.getString("helm"));
                builder.setWeapon(res.getString("weapon"));
                res.close();
                return builder.build();
            } else
                return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
