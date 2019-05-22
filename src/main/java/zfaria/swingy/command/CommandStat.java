package zfaria.swingy.command;

import zfaria.swingy.Game;
import zfaria.swingy.hero.Hero;

public class CommandStat implements IGameCommand {
    @Override
    public void run(Game game) {
        Hero h = game.getHero();
        String fmtstr = "Name: %s\nClass: %s\nLevel: %d\nExperience: %d\nAttack Damage: %.2f + %.2f\n" +
                "Hit Points: %.2f + %.2f\nDefense: %.2f%% + %.2f%%\nLuck: %.0f%%";
        String res = String.format(fmtstr, h.getName(), h.getHeroClass(), h.getLevel(), h.getExperience(),
                h.getAttack() - h.getWeapon().getStat(), h.getWeapon().getStat(),
                h.getHitPoints(), h.getHelm().getStat(),
                h.getDefense() * 100 - h.getArmor().getStat(), h.getArmor().getStat(),
                h.getLuck() * 100);
        game.getView().messageUser(res);
    }
}
