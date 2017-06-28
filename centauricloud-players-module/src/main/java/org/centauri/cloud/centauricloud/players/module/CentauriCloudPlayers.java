package org.centauri.cloud.centauricloud.players.module;

import lombok.Getter;
import org.centauri.cloud.cloud.module.AbstractModule;

public class CentauriCloudPlayers extends AbstractModule {

	@Getter private static CentauriCloudPlayers instance;

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

}