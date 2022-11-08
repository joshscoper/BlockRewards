package me.joshscoper.blockrewards.handlers;


import me.joshscoper.blockrewards.Main;
import me.joshscoper.blockrewards.util.ItemStackSerializer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Random;

public class rewardHandler {

    private final Main main;
    private String tier;
    private List<String> rewards;
    private int amount;

    public rewardHandler(Main main, String tier){
        this.main = main;
        this.tier = tier;
        rewards = getRewards();
        amount = getAmount();
    }

    public void giveReward(Player player){
        Random random = new Random();
        player.sendMessage(main.TCC("&3&lBlockRewards &a-> &eYou received a(n) &6&l" + tier.toUpperCase() + "&e reward!"));
        for (int i = 0; i < amount; i++){
            int item = random.nextInt(rewards.size());
            player.getWorld().dropItem(player.getLocation(), getItem(item));
        }
    }

    public ItemStack getItem(int i){
        ItemStack item = ItemStackSerializer.deserialize(rewards.get(i));
        return item;
    }

    public List<String> getRewards(){
        tier = tier.toUpperCase();
        switch (tier){
            case "LEGENDARY":
                rewards = main.getLegendaryConfig().getRewards();
                break;
            case "EPIC":
                rewards = main.getEpicConfig().getRewards();
                break;
            case "RARE":
                rewards = main.getRareConfig().getRewards();
                break;
            case "UNCOMMON":
                rewards =main.getUncommonConfig().getRewards();
                break;
            case "COMMON":
                rewards = main.getCommonConfig().getRewards();
                break;
        }
        return rewards;
    }

    public int getAmount() {
        tier = tier.toUpperCase();
        switch (tier) {
            case "LEGENDARY":
                amount = main.getLegendaryConfig().getRewardsAmount();
                break;
            case "EPIC":
                amount = main.getEpicConfig().getRewardsAmount();
                break;
            case "RARE":
                amount = main.getRareConfig().getRewardsAmount();
                break;
            case "UNCOMMON":
                amount = main.getUncommonConfig().getRewardsAmount();
                break;
            case "COMMON":
                amount = main.getCommonConfig().getRewardsAmount();
                break;
        }
        return amount;
    }

}
