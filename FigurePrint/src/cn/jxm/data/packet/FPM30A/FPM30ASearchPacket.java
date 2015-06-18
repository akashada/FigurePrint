package cn.jxm.data.packet.FPM30A;

import cn.jxm.data.packet.datatype.Uint8;

public class FPM30ASearchPacket extends FPM30APacket{
	
	public FPM30ASearchPacket(long address,int bufId,int startPage,int pageNum){
		this.address.setLongData(address);
		this.pid.setIntData(0x01);
		this.data.add(new Uint8(0x04));
		this.data.add(new Uint8(bufId));
		this.data.add(new Uint8((startPage & 0x0000FF00) >> 8));
		this.data.add(new Uint8(startPage & 0x000000FF));
		this.data.add(new Uint8((pageNum & 0x0000FF00) >> 8));
		this.data.add(new Uint8(pageNum & 0x000000FF));
	}
	
}
