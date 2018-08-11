package me.apollodevs.backend.commands;

import me.apollodevs.backend.bungee.BungeeUtils;

import org.bukkit.entity.Player;

public class HubCommand {
	
	public static boolean execute(Player p, String[] args){
		
		if(args.length == 0){
			
			BungeeUtils.connectToServer(p, "hub");
			return true;
		}
		return false;
	}

}
