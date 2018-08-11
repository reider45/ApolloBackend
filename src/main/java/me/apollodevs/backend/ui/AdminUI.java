package me.apollodevs.backend.ui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.apollodevs.backend.util.ItemFactory;

public class AdminUI {
	
	public static Inventory inv;
	public static String inventory_name;

	public static void initialize() {
		inventory_name = "Admin Control";
		
		inv = Bukkit.createInventory(null, 9, inventory_name);
		
		ItemStack day = ItemFactory.createItem(Material.WATCH, "§6§lDay", "§eSet time to day", 1);
		ItemStack night = ItemFactory.createItem(Material.FIREBALL, "§9§lNight", "§eSet time to night", 1);

		inv.setItem(0, day);
		inv.setItem(1, night);
	}
	
	// slot numbers http://prntscr.com/7ym5v4

	public static Inventory adminGUI() {
		return inv;
	}
	
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

