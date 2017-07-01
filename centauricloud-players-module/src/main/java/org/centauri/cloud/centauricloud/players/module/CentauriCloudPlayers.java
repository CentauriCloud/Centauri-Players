package org.centauri.cloud.centauricloud.players.module;

import lombok.Getter;
import org.centauri.cloud.centauricloud.players.module.players.PlayerManager;
import org.centauri.cloud.cloud.module.AbstractModule;
import org.centauri.cloud.players.common.packets.PacketManager;

public class CentauriCloudPlayers extends AbstractModule {

	@Getter private static CentauriCloudPlayers instance;

	@Getter private final PlayerManager playerManager = new PlayerManager();

	@Override
	public void onEnable() {
		instance = this;
		PacketManager.registerPackets();
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

}