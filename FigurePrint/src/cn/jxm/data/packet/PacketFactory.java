package cn.jxm.data.packet;

public interface PacketFactory {
	Packet createPacket(Object... parameter);
}
