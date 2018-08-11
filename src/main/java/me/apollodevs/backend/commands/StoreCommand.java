package me.apollodevs.backend.commands;

import org.bukkit.entity.Player;

public class StoreCommand {

	public static boolean execute(Player p, String[] args){
		
		if(args.length == 0){
			p.sendMessage("§cStore: §7http://theapollo.com/store");
		}
		return false;
	}
}
