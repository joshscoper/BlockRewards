package me.joshscoper.blockrewards.menu.menus;


import me.joshscoper.blockrewards.Main;
import me.joshscoper.blockrewards.handlers.rewardHandler;
import me.joshscoper.blockrewards.handlers.tierHandler;
import me.joshscoper.blockrewards.menu.Menu;
import me.joshscoper.blockrewards.util.ItemStackSerializer;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class RewardInputMenu extends Menu {
    private final Main main;
    private String tier;
    private tierHandler thandler;
    private List<ItemStack> contents = new ArrayList<>();
    private List<String> rlist = new ArrayList<>();

    public RewardInputMenu(Main main, String tier, String color){
        super(main, 54, main.TCC(color + tier.toUpperCase() + " &aRewards"));
        this.main = main;
        this.tier = tier;
        this.thandler = new tierHandler(main, tier);
        rewardHandler rhandler = new rewardHandler(main, tier);
        int rewards = rhandler.getRewards().size();
        rlist = rhandler.getRewards();
        for (int i = 0; i < rewards; i++) {
            inventory.addItem(rhandler.getItem(i));
            contents.add(rhandler.getItem(i));
        }
    }

    @Override
    public void onClose(InventoryCloseEvent event) {
        List<String> rewards = new ArrayList<>();
        for (ItemStack item : contents){
            if (!item.getType().equals(Material.AIR)) {
                rewards.add(ItemStackSerializer.serialize(item));
                if (item.getItemMeta().getDisplayName().equalsIgnoreCase("")){
                    event.getPlayer().sendMessage(main.TCC("&3&lBlockRewards &a-> &a" + item.getType().toString() + " was added to &6&l" + tier.toUpperCase() + "&a!"));
                } else {
                    event.getPlayer().sendMessage(main.TCC("&3&lBlockRewards &a-> &a" + item.getItemMeta().getDisplayName() + " was added to &6&l" + tier.toUpperCase() + "&a!"));
                }
            }
        }
        thandler.getTier().setRewards(rewards);
    }

    @Override
    public boolean hasParent() {
        return false;
    }

    @Override
    public Menu getParent() {
        return null;
    }

    @Override
    public void execute(InventoryClickEvent event){
        ItemStack clicked = event.getCurrentItem();
        ItemStack item = clicked;
        if (contents.contains(item)){
            contents.remove(item);
            inventory.remove(item);
        } else {
            contents.add(item);
            inventory.addItem(item);
        }
    }
}
