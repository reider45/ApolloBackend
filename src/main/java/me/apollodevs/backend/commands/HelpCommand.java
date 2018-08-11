package me.apollodevs.backend.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;


public class HelpCommand {

	public static boolean execute(Player p, String[] args) {

		if(args.length == 0){
			p.sendMessage(ChatColor.GRAY + "To report Bugs or any server issues:");
			p.sendMessage(ChatColor.RED + "http://theapollo.com/bugs");
			p.sendMessage(ChatColor.GRAY + "For a issues with payments email:");
			p.sendMessage(ChatColor.RED + "payments@theapollo.com");
			p.sendMessage(ChatColor.GRAY + "To report hackers visit our forums:");
			p.sendMessage(ChatColor.RED + "http://theapollo.com/forums");
			p.sendMessage(ChatColor.GRAY + "For other issues visit");
			p.sendMessage(ChatColor.RED + "support@theapollo.com");
			return true;
		}
		
		return false;
	}
}