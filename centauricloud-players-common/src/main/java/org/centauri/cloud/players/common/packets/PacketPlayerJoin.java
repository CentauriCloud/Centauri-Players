package org.centauri.cloud.players.common.packets;

import io.netty.buffer.ByteBuf;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.centauri.cloud.common.network.packets.Packet;

@NoArgsConstructor
@AllArgsConstructor
public class PacketPlayerJoin implements Packet {

	@Getter private UUID uniqueId;
	@Getter private String name;

	@Override
	public void encode(ByteBuf buf) {
		buf.writeLong(this.uniqueId.getMostSignificantBits());
		buf.writeLong(this.uniqueId.getLeastSignificantBits());

		//If the sender is a spigot server,
		//we only have to send the uuid,
		//because the master already knows the name
		if(name != null) {
			buf.writeByte(0x01);
			this.writeString(name, buf);
		} else {
			buf.writeByte(0x00);
		}
	}

	@Override
	public void decode(ByteBuf buf) {
		System.out.println("Decoding playerJoin");
		this.uniqueId = new UUID(buf.readLong(), buf.readLong());
		if(buf.readByte() != 0x00)
			this.name = this.readString(buf);
	}

}