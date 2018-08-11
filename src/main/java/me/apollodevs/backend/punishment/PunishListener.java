package me.apollodevs.backend.punishment;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import me.apollodevs.backend.Apollo;

public class PunishListener implements Listener {
	
	public PunishListener() {
		Bukkit.getPluginManager().registerEvents(this, Apollo.getPlugin());
	}
	
	@EventHandler
	public void onLogin(PlayerLoginEvent e) {
		if(Apollo.getPunishments().hasPunishment(e.getPlayer(), PunishType.BAN))
				e.disallow(Result.KICK_BANNED, "You are banned! Appeal on our website!");
	}

}
