package me.apollodevs.backend;

import me.apollodevs.backend.coins.CurrencyHandler;
import me.apollodevs.backend.coins.CurrencySQL;
import me.apollodevs.backend.commands.BackendExecutor;
import me.apollodevs.backend.data.DataHandler;
import me.apollodevs.backend.handlers.ChatHandler;
import me.apollodevs.backend.handlers.InventoryHandler;
import me.apollodevs.backend.handlers.JoinHandler;
import me.apollodevs.backend.handlers.WorldHandler;
import me.apollodevs.backend.levels.LevelXPHandler;
import me.apollodevs.backend.levels.LevelXPSQL;
import me.apollodevs.backend.nametags.Nametags;
import me.apollodevs.backend.punishment.PunishListener;
import me.apollodevs.backend.punishment.PunishmentHandler;
import me.apollodevs.backend.ui.AdminUI;
import me.apollodevs.backend.ui.StatsUI;
import me.apollodevs.backend.ui.SubStatsUI;
import me.apollodevs.backend.util.F;

import org.bukkit.plugin.java.JavaPlugin;

public class Apollo extends JavaPlugin {

	public ChatHandler chatHandler;
	public JoinHandler joinHandler;
	public WorldHandler worldhandler;
	
	public CurrencySQL coinManager;

	private static DataHandler dataHandler;
	
	private static CurrencySQL currencySystem;
	private static CurrencyHandler currencyHandler;
	
	private static LevelXPSQL levelSystem;
	private static LevelXPHandler levelHandler;
	
	private static PunishmentHandler punishHandler;

	private static Apollo plugin;


	@Override
	public void onEnable() {
		plugin = this;
		saveDefaultConfig();
		
		// this is a test commit
		this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		F.log("Registered Bungee Channel");
		
		F.log("Creating Connection to MySQL Server...");
		dataHandler = new DataHandler(
				getConfig().getString("Database.Host"),
				getConfig().getString("Database.User"),
				getConfig().getString("Database.Password"),
				getConfig().getInt("Database.Port"),
				getConfig().getString("Database.DBName"));
		
		// Initialize Currency System
		F.log("Initializing Currency System");
		currencySystem = new CurrencySQL();
		currencyHandler = new CurrencyHandler();
		
		
		// Initialize Leveling System
		F.log("Initializing Level System");
		levelSystem = new LevelXPSQL();
		levelHandler = new LevelXPHandler();
		
		// Initialize Statistics System
		F.log("Initializing GUI's");
		StatsUI.initialize();
		AdminUI.initialize();
		SubStatsUI.initialize();
		
		// Initialize Commands
		F.log("Registering Commands..");
		
		BackendExecutor executor = new BackendExecutor();
		String commands = "hub:dev:lobby:website:help:store:shop:stats:punish";
		for(String c : commands.split(":"))
			getCommand(c).setExecutor(executor);
		
		// Initialize Listeners
		F.log("Enabling Listeners..");
		registerListeners();

		
		// Initialize Utilities
		F.log("Setting up NameTags..");
		new Nametags();
		
		// Initialize Punishments
		F.log("Initializing Punishments..");
		punishHandler = new PunishmentHandler();
		
		F.log("Ready to go!");
		
	}

	@Override
	public void onDisable() {
		currencyHandler.shutdown(); // Save's all player's currencies.
		levelHandler.shutdown(); // Save Levels
		
		plugin = null;
	}
	
	public void shutdown()  {
		currencyHandler.shutdown();
		levelHandler.shutdown();
		// TODO shutdown system
	}
	
	public void registerListeners() {
		new ChatHandler();
		new JoinHandler();
		new WorldHandler();
		new PunishListener();
		new InventoryHandler();
		new PunishListener();
		
		F.log("Listeners Enabled!");
	}
	
	public static Apollo getPlugin() {
		return plugin;
	}
	
	public static DataHandler getDataHandler() {
		return dataHandler;
	}
	
	public static CurrencyHandler getCurrency() {
		return currencyHandler;
	}
	
	public static CurrencySQL getCurrencySQL() {
		return currencySystem;
	}
	
	public static LevelXPHandler getLevel(){
		return levelHandler;
	}
	
	public static LevelXPSQL getLevelSQL(){
		return levelSystem;
	}
	
	public static PunishmentHandler getPunishments(){
		return punishHandler;
	}

}
