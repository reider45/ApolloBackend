package me.apollodevs.backend.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class WebsiteCommand {

	public static boolean execute (Player p, String[] args){
		
		if(args.length == 0){
			p.sendMessage(ChatColor.RED + "Visit our website at http://theapollo.com");
			return true;
		}
		return false;
	}
}
