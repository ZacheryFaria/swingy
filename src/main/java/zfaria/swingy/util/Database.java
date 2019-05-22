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
                    "armor integer,\n" +
                    "helm integer,\n" +
                    "weapon integer" +
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
        String sql = "SELECT * FROM heroes WHERE name = ? and class = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, h.getName());
            stmt.setString(2, h.getHeroClass());
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
                     "    %d,\n" +
                     "    %d,\n" +
                     "    %d);";
            sql = String.format(sql, h.getName(), h.getHeroClass(), h.getExperience(), h.getArmor().getBaseStat(),
                    h.getHelm().getBaseStat(),
                    h.getWeapon().getBaseStat());
        } else {
            sql = "UPDATE heroes SET \n" +
                    "experience = %d," +
                    "armor = \"%d\"," +
                    "helm = \"%d\"," +
                    "weapon = \"%d\"" +
                    "where name = \"%s\" and " +
                    "class = \"%s\"";
            sql = String.format(sql, h.getExperience(), h.getArmor().getBaseStat(),
                    h.getHelm().getBaseStat(), h.getWeapon().getBaseStat(), h.getName(), h.getHeroClass());
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
                builder.setArmor(res.getInt("armor"));
                builder.setHelm(res.getInt("helm"));
                builder.setWeapon(res.getInt("weapon"));
                res.close();
                return builder.build();
            } else
                return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteHero(Hero h) {
        String sql = "DELETE FROM heroes WHERE name = ? and class = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, h.getName());
            stmt.setString(2, h.getHeroClass());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
