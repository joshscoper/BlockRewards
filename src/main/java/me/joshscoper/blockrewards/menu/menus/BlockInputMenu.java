package me.joshscoper.blockrewards.menu.menus;

import me.joshscoper.blockrewards.Main;
import me.joshscoper.blockrewards.handlers.tierHandler;
import me.joshscoper.blockrewards.menu.Menu;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class BlockInputMenu extends Menu {

    private final Main main;
    private String tier;
    private tierHandler thandler;
    private List<ItemStack> contents = new ArrayList<>();

    public BlockInputMenu(Main main, String tier, String color){
        super(main, 54, main.TCC(color + tier.toUpperCase() + " &aBlocks"));
        this.main = main;
        this.tier = tier;
        this.thandler = new tierHandler(main, tier);
        int blocks = thandler.getTier().getBlocks().size();
        for (int i = 0; i < blocks; i++){
            inventory.setItem(i, new ItemStack(Material.getMaterial(thandler.getTier().getBlocks().get(i))));
            contents.add(new ItemStack(Material.getMaterial(thandler.getTier().getBlocks().get(i))));
        }
    }

    @Override
    public void onClose(InventoryCloseEvent event) {
        List<String> blocks = new ArrayList<>();
        for (ItemStack item : contents){
            if (!item.getType().equals(Material.AIR)) {
                blocks.add(item.getType().toString());
                event.getPlayer().sendMessage(main.TCC("&3&lBlockRewards &a-> &a" + item.getType().toString() + " was added to &6&l" + tier.toUpperCase() + "&a!"));
            }
        }
        thandler.getTier().setBlocks(blocks);
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
        item.setAmount(1);
        if (contents.contains(item)){
            contents.remove(item);
            inventory.remove(item);
        } else {
            contents.add(item);
            inventory.addItem(item);
        }
    }



}
