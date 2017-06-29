package org.centauri.cloud.centauricloud.players.module.listener;

import org.centauri.cloud.centauricloud.players.module.CentauriCloudPlayers;
import org.centauri.cloud.centauricloud.players.module.players.Player;
import org.centauri.cloud.cloud.event.Listener;
import org.centauri.cloud.cloud.event.events.PacketReceivingEvent;
import org.centauri.cloud.cloud.server.Server;
import org.centauri.cloud.cloud.server.SpigotServer;
import org.centauri.cloud.common.network.packets.Packet;
import org.centauri.cloud.players.common.packets.PacketPlayerDisconnect;
import org.centauri.cloud.players.common.packets.PacketPlayerJoin;

public class NetworkHandler {
	
	@Listener
	public void onReceivingPacket(PacketReceivingEvent event) {
		Packet packet = event.getPacket();
		Server server = event.getServer();
	
		if(packet instanceof PacketPlayerJoin) {
			PacketPlayerJoin joinPacket = (PacketPlayerJoin) packet;
			if(server instanceof SpigotServer)
				CentauriCloudPlayers.getInstance().getPlayerManager().getPlayer(joinPacket.getUniqueId()).setServer((SpigotServer) server);
			else
				CentauriCloudPlayers.getInstance().getPlayerManager().add(new Player(joinPacket.getUniqueId(), joinPacket.getName()));
		} else if(packet instanceof PacketPlayerDisconnect) {
			PacketPlayerDisconnect disconnectPacket = (PacketPlayerDisconnect) packet;
			CentauriCloudPlayers.getInstance().getPlayerManager().remove(disconnectPacket.getUniqueId());
		}
	}

}