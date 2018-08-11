package me.apollodevs.backend.coins;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

import me.apollodevs.backend.Apollo;
import me.apollodevs.backend.util.DataUtil;

import org.bukkit.entity.Player;

public class CurrencySQL {

	/**
	 * Return a player's coins
	 * @param Player
	 */
	protected Integer getCoins(Player p){
		if(hasCoins(p)){
			try {
				ResultSet rs = Apollo.getDataHandler().querySQLAsync(
						"SELECT * FROM " + DataUtil.DBName + "_coins WHERE player_uuid='"+p.getUniqueId()+"'");

				if(rs.next()){
					return rs.getInt("amount");
				} else {
					return 0;
				}
			} catch (SQLException | InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}

		return 0;
	}

	/**
	 * Change player's coin balance
	 * @param Player
	 * @param Coin Amount
	 */
	protected void setCoins(Player p, Integer amount){
		if(hasCoins(p)){
			Apollo.getDataHandler().updateSQLAsync(
					"UPDATE "+ DataUtil.DBName + "_coins SET amount='"+ amount + "' WHERE player_uuid='" + p.getUniqueId()+"'");
		} else {
			createCoins(p, amount);
		}
	}


	// ***************************************************************************************************************

	/**
	 * Check if a player has a coin balance
	 */
	protected Boolean hasCoins(Player p){
		try {
			ResultSet rs = Apollo.getDataHandler().querySQLAsync(
					"SELECT * FROM " + DataUtil.DBName + "_coins WHERE player_uuid='" + p.getUniqueId()+"'");
			return rs.next();
		} catch (InterruptedException | ExecutionException | SQLException e) {
			e.printStackTrace();
		}	
		return false;
	}

	/**
	 * Create a player entry in our database
	 */
	private void createCoins(Player p, Integer amount){
		// this needs to be changed based on how the table will look
		Apollo.getDataHandler().updateSQLAsync(
				"INSERT INTO "+ DataUtil.DBName +"_coins VALUES ('0','"+p.getUniqueId()+"','"+amount+"')"); 
	}



}
