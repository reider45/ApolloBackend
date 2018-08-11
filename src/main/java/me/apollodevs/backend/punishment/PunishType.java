package me.apollodevs.backend.punishment;

public enum PunishType {
	BAN("Ban"),
	KICK("Kick"),
	MUTE("Mute"),
	WARN("Warn");
	
	private String name;
	
	private PunishType(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
