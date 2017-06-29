package org.centauri.cloud.players.plugin.spigot;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.centauri.cloud.players.plugin.spigot.listener.PlayerListener;
import org.centauri.cloud.players.plugin.spigot.netty.NetworkHandler;
import org.centauri.cloud.spigot.SpigotConnectorPlugin;
import static org.centauri.cloud.spigot.SpigotConnectorPlugin.getPluginLogger;

public class CentauriPlayersPlugin extends JavaPlugin {

	@Getter private static CentauriPlayersPlugin instance;

	@Override
	public void onEnable() {
		instance = this;

		SpigotConnectorPlugin.getInstance().getPacketHandlers().add(new NetworkHandler());

		Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
		
		getPluginLogger().info("Loaded CentauriCloud players-module plugin.");
	}

}