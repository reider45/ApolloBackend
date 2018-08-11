package me.apollodevs.backend.commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.apollodevs.backend.Apollo;
import me.apollodevs.backend.punishment.PunishType;

public class PunishCommand {

	@SuppressWarnings("deprecation")
	// TODO may be issues with offline player UUID grabbing
	public static boolean execute(CommandSender sender, String[] args) {

		if (args.length > 2) {
			
			Player p = null;
			if (sender instanceof Player) {
				p = (Player) sender;

				if (!p.hasPermission("apollo.punish"))
					return false;
			}

			// target
			OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);

			// category
			PunishType type = PunishType.valueOf(args[1].toUpperCase());
			if (type == null)
				type = PunishType.WARN;

			String reason = "";
			for (int i = 2; i < args.length; i++){
				reason += args[i];
				if((args.length-1) != i)
					reason+=" ";
			}

			if(p != null)
				Apollo.getPunishments().createPunishment(type, target, reason, p);
			else
				Apollo.getPunishments().createPunishment(type, target, reason, 
						Bukkit.getOfflinePlayer("b6b36239-61c3-375f-ade0-3070155b8f29"));
			return true;
		}
		
		return false;
	}
}
