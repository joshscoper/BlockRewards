package me.joshscoper.blockrewards.handlers;


import me.joshscoper.blockrewards.Main;
import me.joshscoper.blockrewards.Tiers;
import me.joshscoper.blockrewards.filemanager.ConfigFile;

public class tierHandler {

    private final Main main;
    private String tier;

    public tierHandler(Main main, String tier){
        this.main = main;
        this.tier = tier.toUpperCase();
    }

    public boolean isTier(){
        for (Tiers t : Tiers.values()){
            if (t.getTier().equalsIgnoreCase(tier)){
                return true;
            }
        }
        return false;
    }

    public ConfigFile getTier(){
        ConfigFile configFile = null;
        switch (tier) {
            case "LEGENDARY":
                configFile = main.getLegendaryConfig();
                break;
            case "EPIC":
                configFile = main.getEpicConfig();
                break;
            case "RARE":
                configFile = main.getRareConfig();
                break;
            case "UNCOMMON":
                configFile = main.getUncommonConfig();
                break;
            case "COMMON":
                configFile = main.getCommonConfig();
                break;
        }
        return configFile;
    }


}
