package org.dzmitry.common;

import org.dzmitry.common.model.Hero;
import org.dzmitry.common.model.Player;
import org.dzmitry.common.model.Race;

import java.util.UUID;

public class DummyDataUtils {

    public static Player createDummyElfPlayer() {
        Player player = new Player(UUID.randomUUID().toString(), "Dzmitry_Kapachou");
        Hero hero = new Hero();
        hero.setLevel(7);
        hero.setRace(Race.ELF);
        player.setHero(hero);
        return player;
    }

    public static Player createDummyDarkElfPlayer() {
        Player player = new Player(UUID.randomUUID().toString(), "Viktor Kapachou");
        Hero hero = new Hero();
        hero.setLevel(14);
        hero.setRace(Race.DARK_ELF);
        player.setHero(hero);
        return player;
    }

    public static Player createDummyHumanPlayer() {
        Player player = new Player(UUID.randomUUID().toString(), "Sergey Petrovsky");
        Hero hero = new Hero();
        hero.setLevel(12);
        hero.setRace(Race.HUMAN);
        player.setHero(hero);
        return player;
    }
}
