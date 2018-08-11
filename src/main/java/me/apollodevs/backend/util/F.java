package me.apollodevs.backend.util;

import java.util.logging.Level;

import org.bukkit.Bukkit;

public class F {
	
	/**
	 * Log messages to console
	 */
	public static void log(String message) {
		Bukkit.getLogger().log(Level.INFO, "[Apollo] " + message);
	}
	
	public static void log(Level severity, String message) {
		Bukkit.getLogger().log(severity, "[Apollo] " + message);
	}

}
