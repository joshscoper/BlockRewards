package me.joshscoper.blockrewards.filemanager;

import me.joshscoper.blockrewards.Main;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class ConfigFile {

    private final Main main;
    private String tier;
    private File directory;
    private File file;
    private FileConfiguration config;
    public ConfigFile(Main main, String tier){
        this.main = main;
        this.tier = tier;
        this.directory = new File(main.getDataFolder() + File.separator + "Tiers[Only Configure In Game!]");
        this.file = new File(directory, tier + ".yml");
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    public void setupFile(){
        if (!directory.exists()){
            directory.mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
                for (ConfigData data :ConfigData.values()){
                    if (config.get(data.getPath()) == null){
                        config.set(data.getPath(), data.getDefaultValue());
                        saveFile();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    // Load File

    public void loadFile(){
        try {
            config.load(file);
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Boolean fileExists(){
        if (file.exists()){
            return true;
        }else {
            return false;
        }
    }

    // Get|Set Color
    public void setColor(String colorcode){
        config.set("GUI_COLOR", colorcode);
        saveFile();
    }

    public String getColor(){
        return config.getString("GUI_COLOR");
    }

    // Get|Set Chance
    public void setChance(int chance) {
        config.set("Chance", chance);
        saveFile();
    }

    public int getChance(){
        return config.getInt("Chance");
    }

    // Get|Set Blocks

    public void setBlocks(List<String> blocks){
        config.set("Blocks", blocks);
        saveFile();
    }

    public List<String> getBlocks(){
        List<String> blocks = (List<String>) config.getList("Blocks");
        return blocks;
    }

    // Get|Set Rewards|Amount

    public void setRewardsAmount(int amount){
        config.set("Reward_Amount", amount);
        saveFile();
    }

    public int getRewardsAmount(){
        return config.getInt("Reward_Amount");
    }

    public void setRewards(List<String> rewards){
        config.set("Rewards", rewards);
        saveFile();
    }

    public List<String> getRewards(){
        List<String> rewards = (List<String>) config.getList("Rewards");
        return rewards;
    }

    //Save File

    public void saveFile(){
        try{
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
