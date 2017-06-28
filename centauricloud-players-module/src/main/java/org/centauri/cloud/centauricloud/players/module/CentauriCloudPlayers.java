package org.centauri.cloud.centauricloud.players.module;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.Getter;
import org.centauri.cloud.centauricloud.players.module.players.Player;
import org.centauri.cloud.cloud.module.AbstractModule;

public class CentauriCloudPlayers extends AbstractModule {

	@Getter private static CentauriCloudPlayers instance;

	@Getter private final Set<Player> players = new HashSet<>();

	@Override
	public void onEnable() {
		instance = this;
	}

	@Override
	public void onDisable() {
	
	}

	@Override
	public String getAuthor() {
		return "Centauri Developer Team";
	}

	@Override
	public String getName() {
		return "CentaurCloudPlayers";
	}

	@Override
	public String getVersion() {
		return "Alpha-1.0";
	}

	public Player getPlayer(UUID uniqueId) {
		return this.players.stream().filter(player -> player.getUniqueId().equals(uniqueId)).findFirst().get();
	}

	public Player getPlayer(String name) {
		return this.players.stream().filter(player -> player.getName().equalsIgnoreCase(name)).findFirst().get();
	}

}