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

		if (packet instanceof PacketPlayerJoin) {
			PacketPlayerJoin joinPacket = (PacketPlayerJoin) packet;
			Player player = CentauriCloudPlayers.getInstance().getPlayerManager().getPlayer(joinPacket.getUniqueId());
			if (player == null) {
				player = new Player(joinPacket.getUniqueId(), joinPacket.getName());
				CentauriCloudPlayers.getInstance().getPlayerManager().add(player);
				return;
			}

			if (server instanceof SpigotServer) {
				SpigotServer spigotServer = (SpigotServer) server;
				CentauriCloudPlayers.getInstance().getPlayerManager().getPlayer(joinPacket.getUniqueId()).setServer(spigotServer);
				spigotServer.setPlayers(spigotServer.getPlayers() + 1); // TODO: Atomic integer
			}

		} else if (packet instanceof PacketPlayerDisconnect) {
			PacketPlayerDisconnect disconnectPacket = (PacketPlayerDisconnect) packet;
			CentauriCloudPlayers.getInstance().getPlayerManager().remove(disconnectPacket.getUniqueId());
			Player player = CentauriCloudPlayers.getInstance().getPlayerManager().getPlayer(disconnectPacket.getUniqueId());

			if (server instanceof SpigotServer) {
				SpigotServer spigotServer = (SpigotServer) server;
				spigotServer.setPlayers(spigotServer.getPlayers() - 1); // TODO: Atomic integer
			} else {

				if (player.getServer() != null) {
					SpigotServer spigotServer = (SpigotServer) player.getServer();
					spigotServer.setPlayers(spigotServer.getPlayers() - 1);
				}

			}

		}
	}

}
