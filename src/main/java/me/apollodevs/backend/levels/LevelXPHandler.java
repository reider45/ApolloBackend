package me.apollodevs.backend.levels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.apollodevs.backend.Apollo;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class LevelXPHandler implements Listener {
	
	private HashMap<Player, Integer> levelxp;
	public List<Integer> levels;
	
	public LevelXPHandler() {
		Bukkit.getPluginManager().registerEvents(this, Apollo.getPlugin());
		
		levelxp = new HashMap<Player, Integer>();
		levels = new ArrayList<Integer>();
		
		// TODO Load from Database
		calculate();
		
		// Add all existing online players to local system
		for(Player p : Bukkit.getOnlinePlayers())
			levelxp.put(p, Apollo.getLevelSQL().getXP(p));
	}
	
	public void calculate() {
		int expo = 0000, boost = 0000;
		for(int i = 0; i <= 100; i++){

				boost += (10*i);

				if(i % 10 == 0 && i != 0) {
					expo += 5500;
				}

				int previousLevel;

				if(levels.size() > 0)
					previousLevel = levels.get(levels.size()-1);
				else
					previousLevel = 0;


				int xp = 250 + (i * 500) + boost + expo;

				levels.add(xp + previousLevel);

			}
	}
	
	/**
	 * Shutdown level system
	 */
	public void shutdown() {
		for(Player p : Bukkit.getOnlinePlayers())
			updateXP(p);
	}
	
	/**
	 * Add xp to player
	 */
	public void addXP(Player p, Integer amount) {
		levelxp.put(p, getXP(p) + amount);
	}
	
	/**
	 * Remove xp to player
	 */
	public void takeXP(Player p, Integer amount) {
		levelxp.put(p, getXP(p) - amount);
	}
	
	/**
	 * Get player's xp
	 */
	public Integer getXP(Player p) {
		return levelxp.get(p);
	}
	
	/**
	 * Update XP over SQL, only do on disconnect!
	 */
	private void updateXP(Player p) {
		if(levelxp.get(p) <= 390){
			if(!Apollo.getLevelSQL().hasXP(p)) {
				// They don't exist (never had coins before)
				return;
			}
		}
		Apollo.getLevelSQL().setXP(p, levelxp.get(p));
	}
	
	/**
	 * Get player's XP Level
	 */
	public int getLevel(Player p) {
		int xp = getXP(p);

		int level = 1;
		int minimum = 0;
		for(int i = 0; i < levels.size(); i++) {
			if(levels.get(i) > getXP(p))
				break;
			
			int toCompare = Math.abs(levels.get(i)-xp);
			if( toCompare >= minimum && minimum != 0) {
				break;
			} else {
				level = levels.indexOf( levels.get(i) ) + 1;
				minimum = toCompare;
			}
		}
		
		return level;
	}
	
	// check how much XP is needed to next level
	public int XPToLevel(Player p){

		if(getLevel(p) >= levels.size())
			return 0;
		
		int nextLevelXP = levels.get(getLevel(p));
		int playerXP = getXP(p);
		
		return (nextLevelXP-playerXP);
		
	}
	
	// *********************************************************************************************
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		levelxp.put(p, Apollo.getLevelSQL().getXP(p));
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		updateXP(e.getPlayer());
	}

}
