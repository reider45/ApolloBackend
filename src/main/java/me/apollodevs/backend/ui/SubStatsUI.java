package me.apollodevs.backend.ui;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import me.apollodevs.backend.Apollo;
import me.apollodevs.backend.util.ItemFactory;

public class SubStatsUI {

	public static Inventory inv;
	public static String inventory_name;

	public static void initialize() {
		inventory_name = "Stats Viewer";
		
		inv = Bukkit.createInventory(null, 45);

		ItemMeta meta;
		List<String> lore = new ArrayList<String>();
		ItemStack white = new ItemStack(Material.STAINED_GLASS_PANE, 1);
		ItemStack bed = new ItemStack(Material.BED, 1);
		
		meta = white.getItemMeta();
		meta.setDisplayName(" ");
		meta.setLore(lore);
		white.setItemMeta(meta);
		
		meta = bed.getItemMeta();
		meta.setDisplayName("§7← Go Back");
		meta.setLore(lore);
		bed.setItemMeta(meta);
		
		
		for(int i = 0; i < inv.getSize(); i++)
			inv.setItem(i, white);
		
		ItemStack treasurehunt = new ItemStack(Material.BOOK, 1);
		meta = treasurehunt.getItemMeta();
		
		meta.setDisplayName("§f§lTreasure Hunt Stats");
		lore.clear();
		lore.add("");
		lore.add("§aWins: §f0");
		lore.add("§aGames Played: §f0");
		lore.add("§aKills: §f0");
		lore.add("§aDeaths: §f0");
		lore.add("§aCoins Earned: §f0");
		meta.setLore(lore);
		treasurehunt.setItemMeta(meta);
		
		inv.setItem(22, treasurehunt);
		inv.setItem(4, bed);

	}
	
	// slot numbers http://prntscr.com/7ym5v4

	public static Inventory statsGUI() {
		return inv;
	}
	
	public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) {
		if(clicked.getType() == Material.BED) {
			p.openInventory(StatsUI.statsGUI(p));
		}
	}
}
