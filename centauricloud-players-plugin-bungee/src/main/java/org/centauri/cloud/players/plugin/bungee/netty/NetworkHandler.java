package org.centauri.cloud.players.plugin.bungee.netty;

import io.netty.channel.ChannelHandlerContext;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.centauri.cloud.common.network.packets.Packet;
import org.centauri.cloud.common.network.util.PacketHandler;
import org.centauri.cloud.players.common.packets.PacketPlayerKick;
import org.centauri.cloud.players.common.packets.PacketPlayerMessage;
import org.centauri.cloud.players.plugin.bungee.CentauriPlayersPlugin;

public class NetworkHandler implements PacketHandler {

	@Override
	public void channelRead(ChannelHandlerContext chc, Packet packet) {
		if(packet instanceof PacketPlayerMessage) {
			PacketPlayerMessage msgPacket = (PacketPlayerMessage) packet;
			ProxiedPlayer player = CentauriPlayersPlugin.getInstance().getProxy().getPlayer(msgPacket.getUniqueId());
			if(player != null)
				player.sendMessage(TextComponent.fromLegacyText(msgPacket.getMsg()));
		} else if(packet instanceof PacketPlayerKick) {
			PacketPlayerKick kickPacket = (PacketPlayerKick) packet;
			ProxiedPlayer player = CentauriPlayersPlugin.getInstance().getProxy().getPlayer(kickPacket.getUniqueId());
			if(player != null)
				player.disconnect(TextComponent.fromLegacyText(kickPacket.getMsg()));
		}
	}

}