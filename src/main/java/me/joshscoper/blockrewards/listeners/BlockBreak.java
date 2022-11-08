package me.joshscoper.blockrewards.listeners;

import me.joshscoper.blockrewards.Main;
import me.joshscoper.blockrewards.Tiers;
import me.joshscoper.blockrewards.handlers.chanceHandler;
import me.joshscoper.blockrewards.handlers.rewardHandler;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class BlockBreak implements Listener {

    private final Main main;

    public BlockBreak(Main main){
        this.main = main;
    }


    @EventHandler
    public void onBreak(org.bukkit.event.block.BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Tiers tier = getTier(block.getType());
        chanceHandler chance = new chanceHandler(main, tier.getTier());
        rewardHandler reward = new rewardHandler(main, tier.getTier());
        if (player.hasPermission("blockrewards.reward." + tier.getTier())) {
            if (reward.getRewards().size() > 0) {
                if (chance.metChance()) {
                    reward.giveReward(player);
                    player.getWorld().playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1,1);
                }
            }
        }
    }



    public Tiers getTier(Material material){
        Tiers t = null;
        for (Tiers tier : Tiers.values()){
            switch (tier){
                case LEGENDARY:
                    if (main.getLegendaryConfig().getBlocks().contains(material.toString())){
                        t = Tiers.LEGENDARY;
                        break;
                    }
                case EPIC:
                    if (main.getEpicConfig().getBlocks().contains(material.toString())){
                        t = Tiers.EPIC;
                        break;
                    }
                case RARE:
                    if (main.getRareConfig().getBlocks().contains(material.toString())){
                        t = Tiers.RARE;
                        break;
                    }
                case UNCOMMON:
                    if (main.getUncommonConfig().getBlocks().contains(material.toString())){
                        t = Tiers.UNCOMMON;
                        break;
                    }
                case COMMON:
                    if (main.getCommonConfig().getBlocks().contains(material.toString())){
                        t = Tiers.COMMON;
                    }
                    break;
            }
        }
        return t;
    }

}

