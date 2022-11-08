package me.joshscoper.blockrewards.menu;

import me.joshscoper.blockrewards.Main;
import org.bukkit.entity.Player;

public class MenuSession {
    private Menu menu;

    private Player player;
    private boolean locked;
    public Main main;

    public MenuSession(Player player, Main main) {
        this.player = player;
        this.main = main;
    }

    public Menu getMenu() {
        return menu;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public Player getPlayer() {
        return player;
    }

    public void newMenu(Menu menu) {

        player.openInventory(menu.getInventory());

        this.menu = menu;
    }

    public void closeMenu() {
        player.closeInventory();
    }

    public void onClose() {
        this.menu = null;
    }
}
