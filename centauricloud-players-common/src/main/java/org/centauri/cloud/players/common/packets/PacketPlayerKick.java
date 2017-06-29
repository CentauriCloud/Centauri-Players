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
	@Getter private String msg;

	@Override
	public void encode(ByteBuf buf) {
		buf.writeLong(this.uniqueId.getMostSignificantBits());
		buf.writeLong(this.uniqueId.getLeastSignificantBits());
		if(msg != null) {
			buf.writeByte(0x01);
			this.writeString(msg, buf);
		} else {
			buf.writeByte(0x00);
		}
	}

	@Override
	public void decode(ByteBuf buf) {
		this.uniqueId = new UUID(buf.readLong(), buf.readLong());
		if(buf.readByte() != 0x00)
			this.msg = this.readString(buf);
	}

}