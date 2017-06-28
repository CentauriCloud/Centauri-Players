package org.centauri.cloud.players.common.packets;

import io.netty.buffer.ByteBuf;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.centauri.cloud.common.network.packets.Packet;

@NoArgsConstructor
@AllArgsConstructor
public class PacketPlayerKick implements Packet {

	@Getter private UUID uniqueId;

	@Override
	public void encode(ByteBuf buf) {
		buf.writeLong(this.uniqueId.getMostSignificantBits());
		buf.writeLong(this.uniqueId.getLeastSignificantBits());
	}

	@Override
	public void decode(ByteBuf buf) {
		this.uniqueId = new UUID(buf.readLong(), buf.readLong());
	}

}