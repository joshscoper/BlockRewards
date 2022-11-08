package me.joshscoper.blockrewards.commands;

import me.joshscoper.blockrewards.Main;
import me.joshscoper.blockrewards.handlers.tierHandler;
import me.joshscoper.blockrewards.menu.menus.BlockInputMenu;
import me.joshscoper.blockrewards.menu.menus.RewardInputMenu;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.awt.*;
import java.util.Collections;

public class BlockRewards implements CommandExecutor {

    private final Main main;

    public BlockRewards(Main main){
        this.main = main;
        Bukkit.getPluginCommand("blockrewards").setExecutor(this);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            command.setAliases(Collections.singletonList("br"));
            if (command.getName().equalsIgnoreCase("blockrewards")){
                //Arg 0 (edit)
                if (args.length > 0) {
                    //help
                    if (args[0].equalsIgnoreCase("help")){
                        sendHelp(player);
                    }

                    if (args[0].equalsIgnoreCase("reload")){
                        main.reload();
                        player.sendMessage(main.TCC("&3&lBlockRewards &a-> &aPlugin is being reloaded!"));
                    }

                    if (args[0].equalsIgnoreCase("edit")){
                        //chance [tier]
                        if (args[1].equalsIgnoreCase("chance")){
                            tierHandler thandler = new tierHandler(main, args[2]);
                            if (thandler.isTier()){
                                if (Integer.parseInt(args[3])!=0){
                                    thandler.getTier().setChance(Integer.parseInt(args[3]));
                                    player.sendMessage(main.TCC("&3&lBlockRewards &a-> &6&l" + args[2].toUpperCase() + "&e's chance has been set to " + args[3] + "%"));
                                    return true;
                                }
                            } else {
                                player.sendMessage(main.TCC("&3&lBlockRewards &a-> &cYou must specify a valid tier! &7[&8Common, Uncommon, Rare, Epic, Legendary&7]"));
                            }
                        }

                        //blocks [tier]
                        if (args[1].equalsIgnoreCase("blocks")){
                            tierHandler tierHandler = new tierHandler(main, args[2]);
                            if (tierHandler.isTier()){
                                //open block menu
                                main.getMenuManager().getPlayerSession(player).newMenu(new BlockInputMenu(main, args[2], tierHandler.getTier().getColor()));
                            } else {
                                player.sendMessage("&3&lBlockRewards &a-> &cYou must specify a valid tier! &7[&8Common, Uncommon, Rare, Epic, Legendary&7]");
                            }
                        }

                        //rewards[tier]
                        if (args[1].equalsIgnoreCase("rewards")){
                            tierHandler tierHandler = new tierHandler(main, args[2]);
                            if (tierHandler.isTier()){
                                //open rewards menu
                                main.getMenuManager().getPlayerSession(player).newMenu(new RewardInputMenu(main, args[2], tierHandler.getTier().getColor()));
                            } else {
                                player.sendMessage("&3&lBlockRewards &a-> &cYou must specify a valid tier! &7[&8Common, Uncommon, Rare, Epic, Legendary&7]");
                            }
                        }

                        //rewardamount [tier] [amount]
                        if (args[1].equalsIgnoreCase("rewardamount")){
                            tierHandler tierHandler = new tierHandler(main, args[2]);
                            if (tierHandler.isTier()){
                                if (Integer.parseInt(args[3])!=0) {
                                    tierHandler.getTier().setRewardsAmount(Integer.parseInt(args[3]));
                                    player.sendMessage(main.TCC("&3&lBlockRewards &a-> &6&l" + args[2].toUpperCase() + " &e's reward amount has been set to " + args[3]));
                                }
                            } else {
                                player.sendMessage("&3&lBlockRewards &a-> &cYou must specify a valid tier! &7[&8Common, Uncommon, Rare, Epic, Legendary&7]");
                            }
                        }
                    }
                } else {
                    sendHelp(player);
                }
            }
        } else {
            System.out.println(SystemColor.RED + "Only players can use BlockRewards commands!");
            return true;
        }
        return true;
    }


    public void sendHelp(Player player){
        net.md_5.bungee.api.chat.TextComponent editchance = new net.md_5.bungee.api.chat.TextComponent(Main.TCC("&f★ &b/blockrewards edit chance [tier] [amount]"));
        editchance.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(main.TCC("&7Sets chance for tier"))));
        editchance.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/blockrewards edit chance [tier] [amount]"));

        net.md_5.bungee.api.chat.TextComponent editblocks = new net.md_5.bungee.api.chat.TextComponent(main.TCC("&f★ &b/blockrewards edit blocks [tier]"));
        editblocks.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(main.TCC("&7opens block menu"))));
        editblocks.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/blockrewards edit blocks [tier]"));

        net.md_5.bungee.api.chat.TextComponent editrewards = new net.md_5.bungee.api.chat.TextComponent(main.TCC("&f★ &b/blockrewards edit rewards [tier]"));
        editrewards.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(main.TCC("&7opens rewards menu"))));
        editrewards.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/blockrewards edit rewards [tier]"));

        net.md_5.bungee.api.chat.TextComponent editrewardamount = new TextComponent(main.TCC("&f★ &b/blockrewards edit rewardamount [tier] [amount]"));
        editrewardamount.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(main.TCC("&7Sets reward amount for tier"))));
        editrewardamount.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/blockrewards edit rewardamount [tier] [amount]"));

        player.sendMessage(main.TCC("&7+------------[&3&lBlock Rewards&7]-------------+"));
        player.sendMessage(" ");
        player.spigot().sendMessage(editchance);
        player.sendMessage(" ");
        player.spigot().sendMessage(editblocks);
        player.sendMessage(" ");
        player.spigot().sendMessage(editrewards);
        player.sendMessage(" ");
        player.spigot().sendMessage(editrewardamount);
        player.sendMessage(" ");
        player.sendMessage(main.TCC("&7+---------------------------------+"));
    }



    /*  blockrewards(br) -> Help Menu <JSON Formatted>
        blockrewards(br) edit chance [tier] [amount] -> Sets chance for tier
        blockrewards(br) edit blocks [tier] -> opens block menu
        blockrewards(br) edit rewards [tier] -> opens rewards menu
        blockrewards(br) edit rewardsamount [tier] [amount] - Sets reward amount for tier
     */





}
