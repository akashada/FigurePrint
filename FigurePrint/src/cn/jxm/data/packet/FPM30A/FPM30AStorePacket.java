package cn.jxm.data.packet.FPM30A;

import cn.jxm.data.packet.datatype.Uint8;

public class FPM30AStorePacket extends FPM30APacket{

	public FPM30AStorePacket(long address,int bufId,int pageId){
		this.address.setLongData(address);
		this.pid.setIntData(0x01);
		this.data.add(new Uint8(0x06));
		this.data.add(new Uint8(bufId));
		this.data.add(new Uint8((pageId & 0x0000FF00) >> 8));
		this.data.add(new Uint8(pageId & 0x000000FF));
	}
}
