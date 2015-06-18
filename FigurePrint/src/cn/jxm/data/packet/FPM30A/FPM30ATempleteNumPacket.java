package cn.jxm.data.packet.FPM30A;

import cn.jxm.data.packet.datatype.Uint8;

public class FPM30ATempleteNumPacket extends FPM30APacket{

	public FPM30ATempleteNumPacket(long address){
		this.address.setLongData(address);
		this.pid.setIntData(0x01);
		this.data.add(new Uint8(0x1D));
	}
}
