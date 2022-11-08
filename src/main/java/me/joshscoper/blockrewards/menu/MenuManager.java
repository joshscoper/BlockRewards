package me.joshscoper.blockrewards.menu;

import me.joshscoper.blockrewards.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;

public class MenuManager implements Listener {

    private final Main main;

    public Map<Player, MenuSession> menus = new HashMap<>();

    public MenuManager(Main main) {
        this.main = main;


        for (Player ps : Bukkit.getServer().getOnlinePlayers()){
            createMenuSession(ps);
        }
        Bukkit.getServer().getPluginManager().registerEvents(this, main);
    }

    public MenuSession getPlayerSession(Player p) {
        return menus.get(p);
    }

    public void createMenuSession(Player p) {
        if (getPlayerSession(p) == null) {
            menus.put(p, new MenuSession(p, main));
        }
    }

    public void removePlayerSession(Player p) {
        if (getPlayerSession(p) != null) {
            menus.remove(p);
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        createMenuSession(e.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        removePlayerSession(e.getPlayer());
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onClose(InventoryCloseEvent e) {
        if (!(e.getPlayer() instanceof Player))
            return;
        Player p = (Player) e.getPlayer();
        MenuSession session = getPlayerSession(p);
        Menu menu = session.getMenu();
        if (menu != null) {
            menu.onClose(e);
            session.onClose();

        }

    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onClick(InventoryClickEvent e) {

        if (!(e.getWhoClicked() instanceof Player))
            return;
        Player p = (Player) e.getWhoClicked();
        MenuSession session = getPlayerSession(p);
        Menu menu = session.getMenu();
        if (menu != null) {
            e.setCancelled(true);
            menu.execute(e);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onDrag(InventoryDragEvent e) {
        if (!(e.getWhoClicked() instanceof Player))
            return;
        Player p = (Player) e.getWhoClicked();
        MenuSession session = getPlayerSession(p);
        Menu menu = session.getMenu();
        if (menu != null) {
            e.setCancelled(true);
        }

    }
}
