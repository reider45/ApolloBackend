package me.apollodevs.backend.ui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PunishUI {
	
	public static Inventory inv;
	public static String inventory_name;

	public static void initialize() {
		inventory_name = "Punishment System";
		
		inv = Bukkit.createInventory(null, 9, inventory_name);
	
		// TODO setup categories

	}
	
	// slot numbers http://prntscr.com/7ym5v4

	public static Inventory punishGUI(Player opening) {
		Inventory toReturn = Bukkit.createInventory(null, 54, inventory_name);
		toReturn.setContents(inv.getContents());
		
		return inv;
	}
	

	//Why would we use Admin GUI to set the time of the day if we can just stop time from going on using GameRule?
	public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) {
		if(clicked.getType() == Material.WATCH) {
			p.getWorld().setTime(0);
			p.sendMessage("§6§lSet the time to §eday§6§l!");
		} else
		if(clicked.getType() == Material.FIREBALL){
			p.getWorld().setTime(18000);
			p.sendMessage("§6§lSet the time to §9night§6§l!");
		}
	}

}
