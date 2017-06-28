package org.centauri.cloud.centauricloud.players.module.players;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.centauri.cloud.cloud.server.BungeeServer;
import org.centauri.cloud.cloud.server.SpigotServer;
import org.centauri.cloud.players.common.packets.PacketPlayerKick;

@RequiredArgsConstructor
public class Player {

	//basic informations
	@Getter private final UUID uniqueId;
	@Getter private final String name;

	//server informations
	@Getter @Setter private SpigotServer server;
	@Getter @Setter private BungeeServer proxy;

	@Getter private final ConcurrentHashMap<String, Object> extraData = new ConcurrentHashMap<>();

	public void kick() {
		this.kick("You were kicked: No reason defined\n"
				+ "~ CentauriCloud");
	}
	
	public void kick(String msg) {
		this.kick(msg, true);
	}
	
	public void kick(String msg, boolean proxyKick) {
		if(proxyKick)
			this.proxy.sendPacket(new PacketPlayerKick(this.uniqueId));
		else
			this.server.sendPacket(new PacketPlayerKick(this.uniqueId));
	}
}