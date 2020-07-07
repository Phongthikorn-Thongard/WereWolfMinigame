package me.Skyblueplayer.Playerdata;

import java.util.UUID;

import org.bukkit.event.Listener;

public class Playermanager implements Listener{
	private UUID uuid;
	private boolean ingame;
	private boolean isdead;
	private boolean isWolf;
	private boolean isVillager;
	private boolean isWitch;
	private boolean isGunner;
	private boolean hasjob;
	
	public Playermanager(UUID uuid, boolean ingame, boolean isdead, boolean isWolf, boolean isVillager,
			boolean isWitch, boolean isGunner, boolean hasjob) {
		this.setUuid(uuid);
		this.setIngame(ingame);	
		this.setIsdead(isdead);
		this.setWolf(isWolf);
		this.setVillager(isVillager);
		this.setWitch(isWitch);
		this.setGunner(isGunner);
		this.setHasjob(hasjob);
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public boolean isIngame() {
		return ingame;
	}

	public void setIngame(boolean ingame) {
		this.ingame = ingame;
	}

	public boolean isIsdead() {
		return isdead;
	}

	public void setIsdead(boolean isdead) {
		this.isdead = isdead;
	}

	public boolean isWolf() {
		return isWolf;
	}

	public void setWolf(boolean isWolf) {
		this.isWolf = isWolf;
	}

	public boolean isVillager() {
		return isVillager;
	}

	public void setVillager(boolean isVillager) {
		this.isVillager = isVillager;
	}

	public boolean isWitch() {
		return isWitch;
	}

	public void setWitch(boolean isWitch) {
		this.isWitch = isWitch;
	}

	public boolean isGunner() {
		return isGunner;
	}

	public void setGunner(boolean isGunner) {
		this.isGunner = isGunner;
	}

	public boolean isHasjob() {
		return hasjob;
	}

	public void setHasjob(boolean hasjob) {
		this.hasjob = hasjob;
	}
}
