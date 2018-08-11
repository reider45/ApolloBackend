package me.apollodevs.backend.coins;

import me.apollodevs.backend.Apollo;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class CurrencyHandler implements Listener {
	
	//private HashMap<Player, Integer> currency;
	
	public CurrencyHandler() {
		Bukkit.getPluginManager().registerEvents(this, Apollo.getPlugin());
		
		//currency = new HashMap<Player, Integer>();
		
		// Add all existing online players to local system
		//for(Player p : Bukkit.getOnlinePlayers())
			//currency.put(p, Apollo.getCurrencySQL().getCoins(p));
	}
	
	/**
	 * Shutdown currency system
	 */
	public void shutdown() {
		//for(Player p : Bukkit.getOnlinePlayers())
			//updateCurrency(p);
	}
	
	/**
	 * Add currency to player
	 */
	public void addCurrency(Player p, Integer amount) {
		Apollo.getCurrencySQL().setCoins(p, getCurrency(p)+amount);
		//currency.put(p, getCurrency(p) + amount);
	}
	
	/**
	 * Remove currency to player
	 */
	public void takeCurrency(Player p, Integer amount) {
		Apollo.getCurrencySQL().setCoins(p, getCurrency(p)-amount);
		//currency.put(p, getCurrency(p) - amount);
	}
	
	/**
	 * Get player's currency
	 */
	public Integer getCurrency(Player p) {
		return Apollo.getCurrencySQL().getCoins(p);
	}
	
	/**
	 * Update currency over SQL, only do on disconnect!
	 */
	/*private void updateCurrency(Player p) {
		if(getCurrency(p) <= 0){
			if(!Apollo.getCurrencySQL().hasCoins(p)) {
				// They don't exist (never had coins before)
				return;
			}
		}
		Apollo.getCurrencySQL().setCoins(p, getCurrenc);
		currency.remove(p);
	}*/
	
	// *********************************************************************************************
	
	/*@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		p.sendMessage(""+getCurrency(p));
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		
	}*/

}
