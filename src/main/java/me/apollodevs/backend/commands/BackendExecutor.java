package me.apollodevs.backend.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.apollodevs.backend.Apollo;
import me.apollodevs.backend.ui.AdminUI;

public class BackendExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		
		if (command.getName().equalsIgnoreCase("punish")){
			PunishCommand.execute(sender, args);
		}

		if (sender instanceof Player) {

			Player p = (Player) sender;

			if (command.getName().equalsIgnoreCase("hub") 
					|| command.getName().equalsIgnoreCase("lobby")) {
				HubCommand.execute(p, args);
			}else
			
			if (command.getName().equalsIgnoreCase("dev") && p.hasPermission("dev.reid")){
				// TODO remove
				p.sendMessage("Coins: " + Apollo.getCurrency().getCurrency(p));
				p.sendMessage("Level: " + Apollo.getLevel().getLevel(p));
				p.sendMessage("XP: " + Apollo.getLevel().getXP(p));
				p.sendMessage("Until Next: " + Apollo.getLevel().XPToLevel(p));
				p.sendMessage("XP FOR LEVEL 1: " + Apollo.getLevel().levels.get(0));
				
				p.openInventory(AdminUI.adminGUI());
			}else
			
			if (command.getName().equalsIgnoreCase("admin") && p.hasPermission("dev.reid")){
			
			
				
			}else
			
			if (command.getName().equalsIgnoreCase("website")) {
				WebsiteCommand.execute(p, args);
			}else
			
			if (command.getName().equalsIgnoreCase("help")) {
				HelpCommand.execute(p, args);
			}else
			
			if (command.getName().equalsIgnoreCase("store") || 
					command.getName().equalsIgnoreCase("shop")) {
				StoreCommand.execute(p, args);
			}else
				
			if (command.getName().equalsIgnoreCase("stats")) {
				StatsCommand.execute(p, args);
			}
			
		}

		return false;
	}

}
