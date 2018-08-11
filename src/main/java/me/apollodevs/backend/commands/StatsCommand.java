package me.apollodevs.backend.commands;

import me.apollodevs.backend.ui.StatsUI;

import org.bukkit.entity.Player;

public class StatsCommand {
	
	public static boolean execute(Player p, String[] args){
		if(args.length == 0){
			p.openInventory(StatsUI.statsGUI(p));
			return true;
		}
		return false;
	}
}
