package zfaria.swingy;

import zfaria.swingy.command.*;
import zfaria.swingy.enemies.Enemy;
import zfaria.swingy.hero.Hero;
import zfaria.swingy.util.Database;
import zfaria.swingy.view.GameView;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Game {

    private static Validator validator;

    static {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    private GameView view;

    private Database database;

    private Hero hero;

    private Map map;

    private static java.util.Map<String, IGameCommand> commands;

    static {
        commands = new HashMap<String, IGameCommand>();
        commands.put("save", new CommandSave());
        commands.put("exit", new CommandExit());
        commands.put("north", new CommandMoveNorth());
        commands.put("south", new CommandMoveSouth());
        commands.put("east", new CommandMoveEast());
        commands.put("west", new CommandMoveWest());
    }

    public Game(GameView view) {
        this.view = view;

        database = new Database(Database._dbURL);

        view.init();
        view.clearScreen();
        hero = chooseHero();
        map = new Map(hero.getLevel());
        hero.setStartingPosition(map);
        hero.setClassStats();
        mainLoop();
        view.clearScreen();
    }

    private void mainLoop() {
        view.updateMapData(map, hero);
        view.updateHeroData(hero);
        String input = view.promptUser("Enter a command");
        input = input.toLowerCase();
        if (commands.containsKey(input)) {
            commands.get(input).run(this);
        } else {
            view.messageUser("Invalid command: " + input);
        }
        mainLoop();
    }

    private Hero chooseHero() {
        String option = null;
        do {
            view.messageUser("Would you like to create a new hero, or load a previous one?");
            option = view.promptUser("Choices: (C)reate new or (S)elect existing");
            option = option.toLowerCase();
            if (option.equals("c") || option.equals("create")) {
                return createNewHero();
            } else if (option.equals("s") || option.equals("select")) {
                if (database.getAvailableHeroes().size() == 0) {
                    view.messageUser("No heroes saved. Creating instead...");
                    return createNewHero();
                }
                return loadHero();
            }
            option = null;
        } while (option == null);
        return null;
    }

    private Hero loadHero() {
        List<String> list = database.getAvailableHeroes();
        StringBuilder b = new StringBuilder();
        for (String s : list) {
            b.append(s);
            b.append("\n");
        }
        view.messageUser(b.toString());
        String res = view.promptUser("Please select the id of a hero");
        int id = 0;
        try {
            id = Integer.parseInt(res);
        } catch (NumberFormatException e) {
            view.messageUser("Not a valid hero id.");
            return loadHero();
        }
        Hero h = database.loadHero(id);
        if (h == null) {
            view.messageUser("Not a valid hero id.");
            return loadHero();
        }
        return h;
    }

    private Hero createNewHero() {
        String name = view.promptUser("What will your hero name be?");
        String heroClass = null;
        heroClass = view.promptUser("Please select a class: Mage, Warrior, Rogue");
        heroClass = heroClass.toLowerCase();
        Hero h = new Hero(name, heroClass);
        Set<ConstraintViolation<Hero>> violationSet = validator.validate(h);
        if (violationSet.size() == 0) {
            return h;
        } else {
            for (ConstraintViolation<Hero> cv : violationSet) {
                view.messageUser(cv.getMessage());
            }
            view.messageUser("Please try again.");
            return createNewHero();
        }
    }

    public void saveGame() {
        database.serializeHero(view, hero);
    }

    public void movePlayer(Coordinate dir) {
        Coordinate c = hero.getPosition().clone();
        c.addCoordinate(dir);
        map.visit(c);
        if (map.enemyExists(c)) {
            fight(hero, map.getEnemyAt(c));
            if (!map.getEnemyAt(c).isAlive()) {
                map.removeEnemy(c);
                hero.getPosition().addCoordinate(dir);
            }
        } else {
            hero.getPosition().addCoordinate(dir);
        }
    }

    public void fight(Hero h, Enemy enemy) {
        view.messageUser("You encountered an enemy!\n" + enemy);
        String input = view.promptUser("Do you wish to fight, or flee?");
        input = input.toLowerCase();
        if (input.equals("fight")) {
            h.fight(view, enemy);
        } else if (input.equals("flee")) {
            h.flee(view, enemy);
        } else {
            view.messageUser("Invalid command " + input);
            fight(h, enemy);
        }
        view.updateHeroData(hero);
        view.updateMapData(map, hero);
    }

    public Hero getHero() {
        return hero;
    }

    public Map getMap() {
        return map;
    }

    public void restart() {
        map = new Map(hero.getLevel());
        hero.setStartingPosition(map);
        hero.reward(map.getSize() / 2 * 100);
        hero.setClassStats();
    }
}
