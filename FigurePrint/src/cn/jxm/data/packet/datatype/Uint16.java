package cn.jxm.data.packet.datatype;

public class Uint16 extends Data{

	private int data;
	
	public Uint16(){
		super(2);
	}
	
	public Uint16(int data) {
		super(2);
		this.data = data;
		convertDataToBytesArray();
	}
	
	public int getIntData(){
		convertByteArrayToData();
		return this.data;
	}
	
	public void setIntData(int data){
		this.data = data;
		convertDataToBytesArray();
	}
	
	@Override
	public
	String convertDataToHexStr() {
		// TODO Auto-generated method stub
		byte[] bytes = this.getByteArray();
		int low = ((int)bytes[1]) & 0x000000FF;
		int high = ((int)bytes[0]) & 0x000000FF;
		String hexLow = Integer.toHexString(low).toUpperCase();
		String hexHigh = Integer.toHexString(high).toUpperCase();
		
		if(low < 0x10){
			hexLow = "0" + hexLow; 
		}
		
		if(high < 0x10){
			hexHigh = "0" + hexHigh;
		}
		return "0x" + hexHigh + hexLow;
	}

	@Override
	public
	void convertHexStrToData(String hex) {
		// TODO Auto-generated method stub
		this.data = Integer.parseInt(hex, 16);
		convertDataToBytesArray();
	}

	@Override
	void convertDataToBytesArray() {
		// TODO Auto-generated method stub
		byte[] bytes = new byte[2];
		bytes[1] = (byte)(this.data & 0x000000FF);
		bytes[0] = (byte)((this.data & 0x0000FF00) >> 8);
		this.setByteArray(bytes);
	}

	@Override
	void convertByteArrayToData() {
		// TODO Auto-generated method stub
		byte[] bytes = this.getByteArray();
		int low = 0x000000FF & ((int)bytes[1]);
		int high = 0x000000FF & ((int)bytes[0]);
		this.data = ((int)(high << 8 | low)) & 0x0000FFFF; 
	}

}
