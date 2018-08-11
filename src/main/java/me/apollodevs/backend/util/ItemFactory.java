package me.apollodevs.backend.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemFactory {
	
	/**
	 * @param material
	 * @param displayName
	 * @param lore
	 * @param quanitity
	 * @return
	 */
	public static ItemStack createItem(Material mat, String displayName, String lore, int quanitity) {
		ItemStack stack = new ItemStack(mat, quanitity);
		ItemMeta meta = stack.getItemMeta();
		meta.setDisplayName(displayName);
		
		List<String> loreList = new ArrayList<String>();
		for(String s : lore.split("~"))
			loreList.add(s);
		
		meta.setLore(loreList);
		stack.setItemMeta(meta);
		
		return stack;
	}
	
	/**
	 * Make an item glow
	 */
	public static ItemStack addGlow(ItemStack s) {
		s.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
		return s;
	}

}
