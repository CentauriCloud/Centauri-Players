package org.centauri.cloud.players.plugin.spigot.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.centauri.cloud.players.common.packets.PacketPlayerJoin;
import org.centauri.cloud.spigot.SpigotConnectorPlugin;

public class PlayerListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		SpigotConnectorPlugin.getInstance().getClient().getChannel().writeAndFlush(new PacketPlayerJoin(event.getPlayer().getUniqueId(), null));
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
	}

}