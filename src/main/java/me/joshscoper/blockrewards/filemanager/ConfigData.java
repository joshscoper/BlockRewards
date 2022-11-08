package me.joshscoper.blockrewards.filemanager;

import me.joshscoper.blockrewards.util.ItemStackSerializer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public enum ConfigData {


    GUI_COLOR("GUI_COLOR", "&f"),
    CHANCE("Chance", 100),
    BLOCKS("Blocks", getBlocks()),
    REWARD_AMOUNT("Reward_Amount", 1),
    REWARDS("Rewards", getRewards());

    private String path;
    private Object defaultValue;
    private static List<String> blocks;
    private static List<String> rewards;

    ConfigData(String path, Object defaultValue){
        this.path = path;
        this.defaultValue = defaultValue;
    }

    public static List getBlocks(){
        blocks = new ArrayList<String>();
        blocks.add(Material.STONE.toString());
        return blocks;
    }

    public static List getRewards(){
        rewards = new ArrayList<>();
        rewards.add(ItemStackSerializer.serialize(new ItemStack(Material.STONE)));
        return rewards;
    }

    public String getPath(){return path;}
    public Object getDefaultValue(){return defaultValue;}
}
