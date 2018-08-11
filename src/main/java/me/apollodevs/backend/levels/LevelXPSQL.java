package me.apollodevs.backend.levels;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

import me.apollodevs.backend.Apollo;
import me.apollodevs.backend.util.DataUtil;

import org.bukkit.entity.Player;

public class LevelXPSQL {
	
	/**
	 * Return a player's levels
	 * @param Player
	 */
	protected Integer getXP(Player p){
		try {
			ResultSet rs = Apollo.getDataHandler().querySQLAsync(
					"SELECT * FROM " + DataUtil.DBName + "_xp WHERE player_uuid='"+p.getUniqueId()+"'");

			if(rs.next()){
				return rs.getInt("xp");
			} else {
				return 250;
			}
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		return 0;
	}
	
	/**
	 * Change player's levelxp
	 * @param Player
	 * @param Coin Amount
	 */
	protected void setXP(Player p, Integer amount){
		if(!hasXP(p)){
		Apollo.getDataHandler().updateSQLAsync(
				"UPDATE '"+ DataUtil.DBName + "_xp' SET xp="+ amount + " WHERE player_uuid='" + p.getUniqueId()+"'");
		} else {
			createXP(p, amount);
		}
	}
	
	
	// ***************************************************************************************************************
	
	/**
	 * Check if a player has a level
	 */
	protected Boolean hasXP(Player p){
		try {
			ResultSet rs = Apollo.getDataHandler().querySQLAsync(
					"SELECT * FROM " + DataUtil.DBName + "_xp WHERE player_uuid='" + p.getUniqueId()+"'");
			return rs.next();
		} catch (InterruptedException | ExecutionException | SQLException e) {
			e.printStackTrace();
		}	
		return false;
	}
	
	/**
	 * Create a player entry in our database
	 */
	private void createXP(Player p, Integer amount){
		// this needs to be changed based on how the table will look
		Apollo.getDataHandler().updateSQLAsync("INSERT INTO '"+ DataUtil.DBName +"_xp' VALUES (0,'"+p.getUniqueId()+"','"+amount+"')"); 
	}



}
