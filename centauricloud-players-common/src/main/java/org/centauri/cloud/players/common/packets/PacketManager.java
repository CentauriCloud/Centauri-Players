package org.centauri.cloud.players.common.packets;

import org.centauri.cloud.common.network.packets.Packet;

public class PacketManager {

	public static void registerPackets() {
		register(PacketPlayerDisconnect.class);
		register(PacketPlayerJoin.class);
		register(PacketPlayerKick.class);
		register(PacketPlayerMessage.class);
	}

	private static void register(Class<? extends Packet> clazz) {
		org.centauri.cloud.common.network.PacketManager.getInstance().register(clazz);
	}

}