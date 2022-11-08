package me.joshscoper.blockrewards;

import me.joshscoper.blockrewards.commands.BlockRewards;
import me.joshscoper.blockrewards.filemanager.ConfigFile;
import me.joshscoper.blockrewards.listeners.BlockBreak;
import me.joshscoper.blockrewards.menu.MenuManager;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;

public final class Main extends JavaPlugin {

    public static Main plugin;

    private ConfigFile commonConfig;
    private ConfigFile uncommonConfig;
    private ConfigFile rareConfig;
    private ConfigFile epicConfig;
    private ConfigFile legendaryConfig;
    private MenuManager menuManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println(SystemColor.GREEN + "BlockRewards Enabled!");
        plugin = this;
        menuManager = new MenuManager(this);

        if (!this.getDataFolder().exists()) {
            initializeTiers();
        } else {
            loadFiles();
        }

        registerEvents();
        registerCommands();
    }

    public void registerEvents(){
        new BlockBreak(this);
        System.out.println(SystemColor.YELLOW + "Events Registered!");
    }

    public void registerCommands(){
        new BlockRewards(this);
        System.out.println(SystemColor.YELLOW + "Commands Registered!");
    }

    public static String TCC(String string){
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public void initializeTiers(){
        for (Tiers tier : Tiers.values()){
            switch (tier.getTier()){
                case "Legendary":
                    legendaryConfig = new ConfigFile(this, tier.getTier());
                    break;
                case "Epic":
                    epicConfig = new ConfigFile(this, tier.getTier());
                    break;
                case "Rare":
                    rareConfig = new ConfigFile(this, tier.getTier());
                    break;
                case "Uncommon":
                    uncommonConfig = new ConfigFile(this, tier.getTier());
                    break;
                case "Common":
                    commonConfig = new ConfigFile(this, tier.getTier());
                    break;
            }
        }
        initializeFiles();
    }


    public ConfigFile getCommonConfig(){return commonConfig;}
    public ConfigFile getUncommonConfig(){return uncommonConfig;}
    public ConfigFile getRareConfig(){return rareConfig;}
    public ConfigFile getEpicConfig(){return epicConfig;}
    public ConfigFile getLegendaryConfig(){return legendaryConfig;}

    public MenuManager getMenuManager(){return menuManager;}

    public void initializeFiles(){
        commonConfig.setupFile();
        uncommonConfig.setupFile();
        rareConfig.setupFile();
        epicConfig.setupFile();
        legendaryConfig.setupFile();
    }

    public void saveFiles(){
        commonConfig.saveFile();
        uncommonConfig.saveFile();
        rareConfig.saveFile();
        epicConfig.saveFile();
        legendaryConfig.saveFile();
    }

    public void loadFiles(){
        for (Tiers tier : Tiers.values()){
            switch (tier.getTier()){
                case "Legendary":
                    legendaryConfig = new ConfigFile(this, tier.getTier());
                    break;
                case "Epic":
                    epicConfig = new ConfigFile(this, tier.getTier());
                    break;
                case "Rare":
                    rareConfig = new ConfigFile(this, tier.getTier());
                    break;
                case "Uncommon":
                    uncommonConfig = new ConfigFile(this, tier.getTier());
                    break;
                case "Common":
                    commonConfig = new ConfigFile(this, tier.getTier());
                    break;
            }
        }
        commonConfig.loadFile();
        uncommonConfig.loadFile();
        rareConfig.loadFile();
        epicConfig.loadFile();
        legendaryConfig.loadFile();
    }

    public void reload(){
        loadFiles();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        saveFiles();
    }
}
