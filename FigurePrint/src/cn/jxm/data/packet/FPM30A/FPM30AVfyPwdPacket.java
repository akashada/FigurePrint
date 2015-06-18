package cn.jxm.data.packet.FPM30A;

import cn.jxm.data.packet.datatype.Uint32;
import cn.jxm.data.packet.datatype.Uint8;

public class FPM30AVfyPwdPacket extends FPM30APacket{

	public FPM30AVfyPwdPacket(long address,long pwd){
		this.address.setLongData(address);
		this.pid.setIntData(0x01);
		this.data.add(new Uint8(0x13));
		
		Uint32 ldat = new Uint32(pwd);
		for(int i = 0;i < 4;i++){
			Uint8 dat = new Uint8();
			byte[] bytes = new byte[1];
			bytes[0] = ldat.getByteArray()[i];
			dat.setByteArray(bytes);
			this.data.add(dat);
		}
	}
}
