package cn.jxm.data.packet.datatype;

import cn.jxm.data.packet.datatype.Data;

public class Uint32 extends Data{

	private long data;
	
	public Uint32(){
		super(4);
		this.data = 0xFFFFFFFF;
		convertDataToBytesArray();
	}
	
	public Uint32(long data){
		super(4);
		this.data = data;
		convertDataToBytesArray();
	}
	
	public long getLongData(){
		convertByteArrayToData();
		return this.data;
	}
	
	public void setLongData(long data){
		this.data = data;
		convertDataToBytesArray();
	}
	
	@Override
	public
	String convertDataToHexStr() {
		// TODO Auto-generated method stub
		byte[] bytes = getByteArray();
		int byte1 = 0x000000FF & ((int)bytes[0]);
		int byte2 = 0x000000FF & ((int)bytes[1]);
		int byte3 = 0x000000FF & ((int)bytes[2]);
		int byte4 = 0x000000FF & ((int)bytes[3]);
		
		String hex1 = Integer.toHexString(byte1).toUpperCase();
		String hex2 = Integer.toHexString(byte2).toUpperCase();
		String hex3 = Integer.toHexString(byte3).toUpperCase();
		String hex4 = Integer.toHexString(byte4).toUpperCase();
		
		if(byte1 < 0x10){
			hex1 = "0" + hex1;
		}
		
		if(byte2 < 0x10){
			hex2 = "0" + hex2;
		}
		
		if(byte3 < 0x10){
			hex3 = "0" + hex3;
		}
		
		if(byte4 < 0x10){
			hex4 = "0" + hex4;
		}
		return "0x" + hex1 + hex2 + hex3 + hex4;
	}

	@Override
	public
	void convertHexStrToData(String hex) {
		// TODO Auto-generated method stub
		this.data = Long.parseLong(hex, 16);
		convertDataToBytesArray();
	}

	@Override
	void convertDataToBytesArray() {
		// TODO Auto-generated method stub
		byte[] bytes = new byte[4];
		bytes[0] = (byte)((this.data & 0x00000000FF000000) >> 24);
		bytes[1] = (byte)((this.data & 0x0000000000FF0000) >> 16);
		bytes[2] = (byte)((this.data & 0x000000000000FF00) >> 8);
		bytes[3] = (byte)(this.data & 0x00000000000000FF);
		this.setByteArray(bytes);
	}

	@Override
	void convertByteArrayToData() {
		// TODO Auto-generated method stub
		byte bytes[] = this.getByteArray();
		long data0 = 0x00000000000000FF & ((long)bytes[0]);
		long data1 = 0x00000000000000FF & ((long)bytes[1]);
		long data2 = 0x00000000000000FF & ((long)bytes[2]);
		long data3 = 0x00000000000000FF & ((long)bytes[3]);
		
		this.data = ((long)(data0 << 24 | data1 << 16 | data2 << 8 | data3)) & 0x00000000FFFFFFFF;
	}

}
