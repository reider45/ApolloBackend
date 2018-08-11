package me.apollodevs.backend.punishment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import me.apollodevs.backend.Apollo;
import me.apollodevs.backend.util.DataUtil;

public class PunishmentHandler {

	@SuppressWarnings("deprecation")
	public List<Punishment> getPunishments(Player p) {
		List<Punishment> punishments = new ArrayList<Punishment>();
		try {
			ResultSet rs = Apollo.getDataHandler().querySQLAsync(
					"SELECT * FROM " + DataUtil.DBName + "_punish WHERE offender_uuid='" + p.getUniqueId()+"'");

			while (rs.next())
				punishments.add(
						new Punishment(PunishType.valueOf(rs.getString("type")), p, rs.getString("reason"), Bukkit.getOfflinePlayer("issuer_uuid")));

		} catch (InterruptedException | ExecutionException | SQLException e) {
			e.printStackTrace();
		}
		return punishments;
	}

	public PunishType punishFromString(String s) {
		switch (s) {
		case "Ban":
			return PunishType.BAN;
		case "Kick":
			return PunishType.KICK;
		case "Mute":
			return PunishType.MUTE;
		case "Warn":
			return PunishType.WARN;
		}
		return null;
	}

	// PunishType type, Player offender, String reason, Player issuer, int
	/**
	 * 
	 * @param type
	 * @param offender
	 * @param reason
	 * @param issuer
	 */
	public void createPunishment(PunishType type, OfflinePlayer offender, String reason, OfflinePlayer issuer) {
		Punishment punishment = new Punishment(type, offender, reason, issuer);
		punishment.activate();

		String query = "INSERT INTO " + DataUtil.DBName + "_punish "
				+ "VALUES (0,'" + offender.getUniqueId() + "','"+type.getName().toUpperCase()+"','"+punishment.getReason()+"','"+issuer.getUniqueId()+"')";
		
		Apollo.getDataHandler().updateSQLAsync(query);
	}
	
	public Boolean hasPunishment(Player p, PunishType type) {
		for(Punishment punish : getPunishments(p))
			if(punish.isActive())
				if(punish.getType() == type)
					return true;
		return false;
	}
}
