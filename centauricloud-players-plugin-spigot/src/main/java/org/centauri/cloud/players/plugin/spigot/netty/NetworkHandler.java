package org.centauri.cloud.players.plugin.spigot.netty;

import io.netty.channel.ChannelHandlerContext;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.centauri.cloud.common.network.packets.Packet;
import org.centauri.cloud.common.network.util.PacketHandler;
import org.centauri.cloud.players.common.packets.PacketPlayerKick;
import org.centauri.cloud.players.common.packets.PacketPlayerMessage;

public class NetworkHandler implements PacketHandler {

	@Override
	public void channelRead(ChannelHandlerContext chc, Packet packet) {
		if(packet instanceof PacketPlayerMessage) {
			PacketPlayerMessage msgPacket = (PacketPlayerMessage) packet;
			Player player = Bukkit.getPlayer(msgPacket.getUniqueId());
			if(player != null)
				player.sendMessage(msgPacket.getMsg());
		} else if(packet instanceof PacketPlayerKick) {
			PacketPlayerKick kickPacket = (PacketPlayerKick) packet;
			Player player = Bukkit.getPlayer(kickPacket.getUniqueId());
			if(player != null)
				player.kickPlayer(kickPacket.getMsg());
		}
	}

}