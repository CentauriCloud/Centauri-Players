package org.centauri.cloud.players.plugin.bungee;

import lombok.Getter;
import net.md_5.bungee.api.plugin.Plugin;
import org.centauri.cloud.bungee.BungeeConnectorPlugin;
import org.centauri.cloud.players.common.packets.PacketManager;
import org.centauri.cloud.players.plugin.bungee.listener.PlayerListener;
import org.centauri.cloud.players.plugin.bungee.netty.NetworkHandler;

public class CentauriPlayersPlugin extends Plugin {

	@Getter private static CentauriPlayersPlugin instance;

	@Override
	public void onEnable() {
		instance = this;
		PacketManager.register();
		
		BungeeConnectorPlugin.getInstance().getPacketHandlers().add(new NetworkHandler());

		this.getProxy().getPluginManager().registerListener(this, new PlayerListener());
		
		this.getLogger().info("Loaded CentauriCloud players-module plugin.");
	}

}