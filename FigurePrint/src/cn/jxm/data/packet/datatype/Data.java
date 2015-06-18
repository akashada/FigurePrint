package cn.jxm.data.packet.datatype;

public abstract class Data {
	private byte[] byteArray;
	
	public Data(int num){
		byteArray = new byte[num];
	}
	
	public int getLength(){
		return byteArray.length;
	}
	
	public void setByteArray(byte[] bytes){
		for(int i = 0;i < byteArray.length;i++){
			byteArray[i] = bytes[i];
		}
	}
	
	public byte[] getByteArray(){
		byte[] bytes = new byte[byteArray.length];
		for(int i = 0;i < byteArray.length;i++){
			bytes[i] = byteArray[i];
		}
		return bytes;
	}
	
	public abstract String convertDataToHexStr();
	public abstract void convertHexStrToData(String hex);
	abstract void convertDataToBytesArray();
	abstract void convertByteArrayToData();
}
