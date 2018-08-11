package me.apollodevs.backend.handlers;

import me.apollodevs.backend.Apollo;
import me.apollodevs.backend.nametags.Nametags;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class JoinHandler implements Listener {
	
	public JoinHandler(){
		Bukkit.getPluginManager().registerEvents(this, Apollo.getPlugin());
	}
	
	@EventHandler
	public void onJoin(final PlayerJoinEvent e) throws Exception {
		
		if(e.getPlayer().hasPermission("Apollo.Vip+")){
				Nametags.getAPI().setTag(e.getPlayer(), "&7[&bVIP&e+&7] ");
				e.setJoinMessage(Nametags.getAPI().getPrefix(e.getPlayer()) + ChatColor.AQUA +  e.getPlayer().getDisplayName() + " §6 joined the lobby!");
			}else{
				e.setJoinMessage(null);
			}
		}
}
