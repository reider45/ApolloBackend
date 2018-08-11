package me.apollodevs.backend.handlers;

import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import me.apollodevs.backend.Apollo;
import me.apollodevs.backend.punishment.PunishType;

public class ChatHandler implements Listener {

	HashSet<String> blockedCommands;

	public ChatHandler() {
		// Register Chat Handler
		blockedCommands = new HashSet<String>();
		blockedCommands.add("plugins");
		blockedCommands.add("pl");
		blockedCommands.add("?");
		blockedCommands.add("pex");
		blockedCommands.add("glist");
		blockedCommands.add("stop");
		blockedCommands.add("reload");
		blockedCommands.add("rl");
		blockedCommands.add("bukkit:stop");
		blockedCommands.add("bukkit:plugins");
		blockedCommands.add("bukkit:pl");
		blockedCommands.add("bukkit:reload");
		blockedCommands.add("restart");
		blockedCommands.add("spigot:restart");
		Bukkit.getPluginManager().registerEvents(this, Apollo.getPlugin());
	}

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		if(Apollo.getPunishments().hasPunishment(e.getPlayer(), PunishType.MUTE)){
			e.getPlayer().sendMessage("§cYou are muted!");
			e.setCancelled(true);
			return;
		} else {
			e.setFormat("§7[§3" + Apollo.getLevel().getLevel(e.getPlayer()) + "§7]§e " + e.getPlayer().getDisplayName()
					+ "§7: §f" + e.getMessage());
			if (e.getMessage().length() > 2) {
				if (!isLegalChat(e.getMessage()))
					e.setCancelled(true);
			}
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onCommandPreProcess(PlayerCommandPreprocessEvent event) {
		if (!event.getPlayer().isOp()) {

			for (String blocked : blockedCommands)
				if (event.getMessage().toLowerCase().startsWith("/" + blocked)) {
					event.setCancelled(true);
					event.getPlayer().sendMessage("§cYou're not allowed to do this!");
				}

		}
	}

	public Boolean isLegalChat(String message) {
		return true;
	}

}
