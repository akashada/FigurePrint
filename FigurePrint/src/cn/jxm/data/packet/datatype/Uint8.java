package cn.jxm.data.packet.datatype;

public class Uint8 extends Data{

	private int data;
	
	public Uint8(){
		super(1);
	}
	
	public Uint8(int data) {
		super(1);
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
		int temp = ((int)bytes[0]) & 0x000000FF;
		String hex = Integer.toHexString(temp).toUpperCase();
		if(this.data < 0x10){
			hex = "0" + hex;
		}
		
		return "0x" + hex;
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
		byte[] bytes = new byte[1];
		bytes[0] = (byte)(this.data & 0x000000FF);
		this.setByteArray(bytes);
	}

	@Override
	void convertByteArrayToData() {
		// TODO Auto-generated method stub
		byte[] bytes = this.getByteArray();
		this.data = 0x000000FF & ((int)bytes[0]); 
	}
}
