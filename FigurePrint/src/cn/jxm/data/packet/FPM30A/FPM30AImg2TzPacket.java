package cn.jxm.data.packet.FPM30A;

import cn.jxm.data.packet.datatype.Uint8;

public class FPM30AImg2TzPacket extends FPM30APacket{

	public FPM30AImg2TzPacket(long address,int bufId) {
		this.address.setLongData(address);
		this.pid.setIntData(0x01);
		this.data.add(new Uint8(0x02));
		if(bufId == 1){
			this.data.add(new Uint8(0x01));
		}else{
			this.data.add(new Uint8(0x02));
		}
	}


}
