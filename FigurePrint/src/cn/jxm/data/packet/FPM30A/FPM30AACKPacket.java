package cn.jxm.data.packet.FPM30A;

public class FPM30AACKPacket extends FPM30APacket{

	public int getACKCode(){
		return this.getData().get(0).getIntData();
	}
	
	public int getTempleteNum(){
		int h = this.getData().get(1).getIntData();
		int l = this.getData().get(2).getIntData();
		return ((int)(h << 8 | l)) & 0x0000FFFF;
	}
}
