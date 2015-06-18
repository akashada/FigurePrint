/*
 * author:jxm
 * date:2014-12-27 17:16
 * description: packet of figure module MFP30A
 * +-----------+-------------------+--------------------------
 * |Packet Head|Address            |PID
 * +-----------+-------------------+----------------------
 * |2 bytes    |4 bytes            |1 byte|
 * +-----------+-------------------+--------------------------------
 * |0xEF01     |0xFFFFFFFF(default)|
 * +-----------+-------------------+-------------------------------
 */
package cn.jxm.data.packet.FPM30A;

import java.util.ArrayList;
import java.util.List;

import cn.jxm.data.packet.Packet;
import cn.jxm.data.packet.datatype.Uint32;
import cn.jxm.data.packet.datatype.Uint16;
import cn.jxm.data.packet.datatype.Uint8;

public class FPM30APacket implements Packet{

	Uint16 head = new Uint16(0xEF01);
	Uint32 address = new Uint32();
	Uint8 pid = new Uint8();//unsigned byte
	Uint16 dataLen = new Uint16(0);//max = 256,unsigned byte
    List<Uint8> data = new ArrayList<Uint8>();
	Uint16 checkSum = new Uint16(0);
	
	
	public FPM30APacket(){
		super();
	}
	
	public FPM30APacket(Uint32 adress,Uint8 pid,List<Uint8> data){
		super();
		this.address = adress;
		this.pid = pid;
		this.data = data;
	}
	
	@Override
	public byte[] toByteArray() {
		// TODO Auto-generated method stub
		this.dataLen.setIntData(this.data.size() + 2);
		int packetLen = 9 + this.dataLen.getIntData();
		byte packetBytes[] = new byte[packetLen];
		
		packetBytes[0] = this.head.getByteArray()[0];
		packetBytes[1] = this.head.getByteArray()[1];
		packetBytes[2] = this.address.getByteArray()[0];
		packetBytes[3] = this.address.getByteArray()[1];
		packetBytes[4] = this.address.getByteArray()[2];
		packetBytes[5] = this.address.getByteArray()[3];
		packetBytes[6] = this.pid.getByteArray()[0];
		packetBytes[7] = this.dataLen.getByteArray()[0];
		packetBytes[8] = this.dataLen.getByteArray()[1];
		
		int i,tempSum = 0;
		for(i = 0;i < data.size();i++){
			packetBytes[9 + i] = this.data.get(i).getByteArray()[0];
			tempSum += this.data.get(i).getIntData(); 
		}
		
		this.checkSum.setIntData(this.pid.getIntData() + this.dataLen.getIntData() + tempSum);
		packetBytes[9 + i] = this.checkSum.getByteArray()[0];
		packetBytes[10 + i] = this.checkSum.getByteArray()[1];
		return packetBytes;
	}
	@Override
	public void printPacket() {
		// TODO Auto-generated method stub
		toByteArray();
		System.out.println("********************************************");
		System.out.println("Head: " + this.head.convertDataToHexStr());
		System.out.println("Adress: " + this.address.convertDataToHexStr());
		System.out.println("Pid: " + this.pid.convertDataToHexStr());
		System.out.println("DataLen: "+ this.dataLen.convertDataToHexStr());
		
		System.out.println("Data:");
		for(int i = 0;i < this.data.size();i++){
			System.out.print("|" + this.data.get(i).convertDataToHexStr());
		}
		System.out.println("|");
		System.out.println("CheckSum: " + this.checkSum.convertDataToHexStr());
		System.out.println("********************************************");
	}

	public Uint16 getHead() {
		return head;
	}

	public void setHead(Uint16 head) {
		this.head = head;
	}

	public Uint32 getAddress() {
		return address;
	}

	public void setAddress(Uint32 address) {
		this.address = address;
	}

	public Uint8 getPid() {
		return pid;
	}

	public void setPid(Uint8 pid) {
		this.pid = pid;
	}

	public Uint16 getDataLen() {
		return dataLen;
	}

	public void setDataLen(Uint16 dataLen) {
		this.dataLen = dataLen;
	}

	public List<Uint8> getData() {
		return data;
	}

	public void setData(List<Uint8> data) {
		this.data = data;
	}

	public Uint16 getCheckSum() {
		return checkSum;
	}

	public void setCheckSum(Uint16 checkSum) {
		this.checkSum = checkSum;
	}

}
