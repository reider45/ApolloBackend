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

public class StatsUI {

	public static Inventory inv;
	public static String inventory_name;

	public static void initialize() {
		inventory_name = "Stats Viewer";
		
		inv = Bukkit.createInventory(null, 45);

		ItemMeta meta;
		List<String> lore = new ArrayList<String>();
		ItemStack white = new ItemStack(Material.STAINED_GLASS_PANE, 1);
		ItemStack unused = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 2);
		
		meta = white.getItemMeta();
		meta.setDisplayName(" ");
		meta.setLore(lore);
		white.setItemMeta(meta);
		
		meta = unused.getItemMeta();
		meta.setDisplayName("§e?");
		lore.add("§4COMING SOON!");
		meta.setLore(lore);
		unused.setItemMeta(meta);
		
		for(int i = 0; i < inv.getSize(); i++)
			inv.setItem(i, white);
		
		inv.setItem(10, unused);
		inv.setItem(12, unused);
		inv.setItem(14, unused);
		inv.setItem(16, unused);
		inv.setItem(28, unused);
		inv.setItem(30, unused);
		inv.setItem(32, unused);
		inv.setItem(34, unused);

		ItemStack treasurehunt = new ItemStack(Material.DIAMOND, 1);
		meta = treasurehunt.getItemMeta();
		
		meta.setDisplayName("§aTreasure Hunt Stats");
		lore.clear();
		lore.add("§fWins: §a0");
		lore.add("");
		lore.add("§fGames Played: §a0");
		lore.add("");
		lore.add("§fClick for more details!");
		meta.setLore(lore);
		treasurehunt.setItemMeta(meta);
		treasurehunt = ItemFactory.addGlow(treasurehunt);
		
		inv.setItem(10, treasurehunt);

	}
	
	// slot numbers http://prntscr.com/7ym5v4

	public static Inventory statsGUI(Player p) {

		List<String> lore = new ArrayList<String>();
		Inventory toReturn = Bukkit.createInventory(null, 45, inventory_name);
		toReturn.setContents(inv.getContents());

		
		//Bitch this is a test FUCK
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
		SkullMeta skullmeta = (SkullMeta) skull.getItemMeta();
		skullmeta.setOwner(p.getName());
		lore.add("");
		lore.add("§fCoins Earned: §a"+Apollo.getCurrency().getCurrency(p));
		// TODO should be total earned, not balance);
		lore.add(" ");
		lore.add("§fGames Played: §a0");
		lore.add("");
		lore.add("§fGames Won: §a0");
		lore.add("");
		lore.add("§fRank: §aVIP");
		lore.add("");
		lore.add("§fClick for more details!");
		skullmeta.setLore(lore);
		skullmeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Global Stats");
		skull.setItemMeta(skullmeta);
		toReturn.setItem(22, skull);

		return toReturn;

	}
	
	public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) {
		if(clicked.getType() == Material.DIAMOND) {
			p.openInventory(SubStatsUI.statsGUI());
		}
	}
}
