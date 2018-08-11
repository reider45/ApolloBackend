package me.apollodevs.backend.handlers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.apollodevs.backend.Apollo;
import me.apollodevs.backend.ui.AdminUI;
import me.apollodevs.backend.ui.StatsUI;
import me.apollodevs.backend.ui.SubStatsUI;

public class InventoryHandler implements Listener {
	
	public InventoryHandler(){	
		Bukkit.getPluginManager().registerEvents(this, Apollo.getPlugin());
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		String title = e.getInventory().getTitle();

		if (title.equals(AdminUI.inventory_name) || title.equals(StatsUI.inventory_name) 
				|| title.equals(SubStatsUI.inventory_name)) {
			e.setCancelled(true);
			if(e.getCurrentItem() == null) return;
			if (title.equals(StatsUI.inventory_name))
				StatsUI.clicked((Player) e.getWhoClicked(), e.getSlot(), e.getCurrentItem(), e.getInventory());
			else if (title.equals(AdminUI.inventory_name))
				AdminUI.clicked((Player) e.getWhoClicked(), e.getSlot(), e.getCurrentItem(), e.getInventory());
		}
	}

}
