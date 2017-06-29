package org.centauri.cloud.players.plugin.bungee.listener;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.api.event.PostLoginEvent;
import org.centauri.cloud.bungee.BungeeConnectorPlugin;
import org.centauri.cloud.players.common.packets.PacketPlayerDisconnect;
import org.centauri.cloud.players.common.packets.PacketPlayerJoin;

public class PlayerListener implements Listener {
	
	@EventHandler
	public void onJoin(PostLoginEvent event) {
		ProxiedPlayer player = event.getPlayer();
		BungeeConnectorPlugin.getInstance().getClient().getChannel().writeAndFlush(new PacketPlayerJoin(player.getUniqueId(), player.getName()));
	}
	
	@EventHandler
	public void onDisconnect(PlayerDisconnectEvent event) {
		ProxiedPlayer player = event.getPlayer();
		BungeeConnectorPlugin.getInstance().getClient().getChannel().writeAndFlush(new PacketPlayerDisconnect(player.getUniqueId()));
	}

}