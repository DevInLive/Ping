/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.bukkitserver.smilesdc;

import java.util.logging.Logger;
import java.util.regex.Pattern;
import net.minecraft.server.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Smile
 */
public class Ping extends JavaPlugin  {
    
        public static final Logger log = Logger.getLogger("Minecraft");
    
        public void onEnable()
        {
           log.info("[] Ping plugin enabel!");    
        }
        public void onDisable()
        {
           log.info("[] Ping plugin disable!"); 
        }
        /*
         * Command Pping.
         * @args Player, sender.
         */
        public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
        {
            Player p = (Player) sender;
                            if(cmd.getName().equalsIgnoreCase("pping"))
                            {
                                    if(!p.hasPermission("pping.getping"))
                                    {
                                            p.sendMessage(ChatColor.RED + "У вас нет прав для этого!");
                                    return true;
                                    }
                                            if(args.length == 0)
                                            {
                                                 p.sendMessage(ChatColor.GRAY + "Ваш пинг: "+((CraftPlayer)((Player)sender)).getHandle().ping);
                                            } 
                                                else if(args.length == 1)
                                                {
                                                    
                                                      if(!Bukkit.getOfflinePlayer(args[0]).isOnline())
                                                    {
                                                        sender.sendMessage(ChatColor.RED + "Игрок с ником "+ChatColor.WHITE + args[0] + ChatColor.RED + " не онлайн.");
                                                    return true;
                                                    }
                                                        Player player = Bukkit.getPlayer(args[0]);
                                                        player.sendMessage(ChatColor.GRAY + "Пинг игрока "+ ChatColor.WHITE + player.getDisplayName()+ChatColor.GRAY + ": " + ((CraftPlayer)player).getHandle().ping); 
                                                } 
                                                    else
                                                    {
                                                        p.sendMessage("Ошибка!");                                                    
                                                    }
              return true;
                            }
              return false;
        }
        
        /*
         *  Pattern's.
         */
        protected static Pattern chatColorPattern = Pattern.compile("(?i)&([0-9A-F])");
	protected static Pattern chatMagicPattern = Pattern.compile("(?i)&([K])");
	protected static Pattern chatBoldPattern = Pattern.compile("(?i)&([L])");
	protected static Pattern chatStrikethroughPattern = Pattern.compile("(?i)&([M])");
	protected static Pattern chatUnderlinePattern = Pattern.compile("(?i)&([N])");
	protected static Pattern chatItalicPattern = Pattern.compile("(?i)&([O])");
	protected static Pattern chatResetPattern = Pattern.compile("(?i)&([R])");
}
