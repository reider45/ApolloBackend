package me.apollodevs.backend.handlers;

import me.apollodevs.backend.Apollo;
import me.apollodevs.backend.nametags.Nametags;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class WorldHandler implements Listener {
	
	public WorldHandler(){
		Bukkit.getPluginManager().registerEvents(this, Apollo.getPlugin());
	}

	
    @EventHandler
    public void onChange(PlayerChangedWorldEvent e) {
        Nametags.getAPI().refresh();
    }
}
