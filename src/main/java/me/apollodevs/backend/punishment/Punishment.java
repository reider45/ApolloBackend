package me.apollodevs.backend.punishment;

import java.sql.Timestamp;
import java.util.Calendar;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import me.apollodevs.backend.Apollo;
import me.apollodevs.backend.util.F;

public class Punishment {

	PunishType type;
	OfflinePlayer offender;

	String reason;
	OfflinePlayer issuer;

	Timestamp issuedOn;

	Boolean isActive;

	/**
	 * @param Punishment Type
	 * @param Offender (Player)
	 * @param Reason
	 * @param Issuer (Player)
	 * @param Severity
	 */

	public Punishment(PunishType type, OfflinePlayer offender, String reason, OfflinePlayer issuer) {

		this.type = type;
		this.offender = offender;
		
		this.reason = reason.replaceAll("[^a-zA-Z0-9\\s]+", "");
		
		this.issuer = issuer;

		this.issuedOn = new Timestamp(Calendar.getInstance().getTimeInMillis());

		this.isActive = true;

		// TODO save isActive
	}

	public PunishType getType() {
		return type;
	}

	public OfflinePlayer getOffender() {
		return offender;
	}

	public String getReason() {
		return reason;
	}

	public OfflinePlayer getIssuer() {
		return issuer;
	}

	public Boolean isActive() {
		return isActive;
	}

	public void activate() {
		Player p = Bukkit.getPlayer(offender.getName());

		// TODO add mute
		if(p != null && p.isOnline()){
			switch(type) {
			case WARN:
				p.sendMessage("§4You have been warned by §b" + issuer.getName() + " §4for §6"+reason+"§4.");
				break;
			case KICK:
				p.kickPlayer("§4You have been kicked by §b" + issuer.getName() + " §4for §6"+reason+"§4.");
				break;
			case BAN:
				p.kickPlayer("§4You have been banned by §b" + issuer.getName() + " §4for §6"+reason+"§4.");
				break;
			default:
				break;
			}
		}
	}



}