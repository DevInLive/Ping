/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.bukkitserver.smilesdc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import net.minecraft.server.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Smile
 */
public class Ping extends JavaPlugin {
    
        public String dontHavePermission = "У вас нет прав для этого!";
        public String yourPing = "Ваш пинг: %ping";
        public String otherPing = "Пинг игрока %player : %ping";
        public String playerNotOnline = "Игрок %player не онлайн!";
        public String error = "Ошибка!";
    
        public static final Logger log = Logger.getLogger("Minecraft");
    
        public void onEnable()
        {                    
            File fileConf = new File(getDataFolder(), "config.yml");
		if(!fileConf.exists())
                {
		    InputStream resourceAsStream = Ping.class.getResourceAsStream("/ru/bukkitserver/smilesdc/config.yml");
		    getDataFolder().mkdirs();
		    try 
                    {
		        FileOutputStream fos = new FileOutputStream(fileConf);
		        byte[] buff = new byte[65536];
		        int n;
		        while((n = resourceAsStream.read(buff)) > 0)
                        {
		            fos.write(buff, 0, n);
		            fos.flush();
		        }
		        fos.close();
		        buff = null;
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    getLogger().info("[] Сonfig loaded");
		}
                        FileConfiguration config = this.getConfig();
                        this.dontHavePermission = config.getString("DontHavePermission", this.dontHavePermission);
                        this.yourPing = config.getString("YourPing", this.yourPing);
                        this.otherPing = config.getString("OtherPing", this.otherPing);
                        this.playerNotOnline = config.getString("PlayerNotOnline", this.playerNotOnline);
                        this.error = config.getString("Error", this.error);
                        log.info("[] Ping plugin enabel!");
        }
        
        public void onDisable()
        {
           log.info("[] Ping plugin disable!"); 
        }
        
        public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
        {
            Player p = (Player) sender;
                            if(cmd.getName().equalsIgnoreCase("pping"))
                            {
                                    if(!p.hasPermission("pping.getping"))
                                    {
                                            dontHavePermission = ChatColor.translateAlternateColorCodes('&',dontHavePermission);
                                            p.sendMessage(dontHavePermission);
                                    return true;
                                    }
                                            if(args.length == 0)
                                            {
                                                 yourPing = ChatColor.translateAlternateColorCodes('&', yourPing);
                                                 int ping = ((CraftPlayer)((Player)sender)).getHandle().ping;
                                                 String pings = String.valueOf(ping);
                                                 yourPing = yourPing.replace("%ping", pings);
                                                 yourPing = ChatColor.translateAlternateColorCodes('&', yourPing);
                                                 p.sendMessage(yourPing);
                                            } 
                                                else if(args.length == 1)
                                                {
                                                    
                                                      if(!Bukkit.getOfflinePlayer(args[0]).isOnline())
                                                    {
                                                        playerNotOnline = playerNotOnline.replace("%player", args[0]);
                                                        playerNotOnline = ChatColor.translateAlternateColorCodes('&', playerNotOnline);
                                                        sender.sendMessage(playerNotOnline);
                                                    return true;
                                                    }
                                                        Player player = Bukkit.getPlayer(args[0]);
                                                        int ping = ((CraftPlayer)player).getHandle().ping;
                                                        String pings = String.valueOf(ping);
                                                        otherPing = otherPing.replace("%player", player.getDisplayName());
                                                        otherPing = otherPing.replace("%ping", pings);
                                                        otherPing = ChatColor.translateAlternateColorCodes('&', otherPing);
                                                        player.sendMessage(otherPing);
                                                } 
                                                    else
                                                    {
                                                        error = ChatColor.translateAlternateColorCodes('&', error);
                                                        p.sendMessage(error);                                                    
                                                    }
              return true;
                            }
              return false;
        }
}
