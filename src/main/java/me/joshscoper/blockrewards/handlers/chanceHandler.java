package me.joshscoper.blockrewards.handlers;

import me.joshscoper.blockrewards.Main;

import java.util.Random;

public class chanceHandler {

    private int chance;
    private final Main main;
    private String tier;

    public chanceHandler(Main main, String tier){
        this.main = main;
        this.tier = tier.toUpperCase();
        getChance();
    }

    public int getChance(){
        switch (tier){
            case "LEGENDARY":
                chance = main.getLegendaryConfig().getChance();
                break;
            case "EPIC":
                chance = main.getEpicConfig().getChance();
                break;
            case "RARE":
                chance = main.getRareConfig().getChance();
                break;
            case "UNCOMMON":
                chance =main.getUncommonConfig().getChance();
                break;
            case "COMMON":
                chance = main.getCommonConfig().getChance();
                break;
        }
        return chance;
    }


    public boolean metChance(){
        Random random = new Random();
        int prc = random.nextInt(100);
        if (prc <= chance){
            return true;
        } else {
            return false;
        }
    }
}
