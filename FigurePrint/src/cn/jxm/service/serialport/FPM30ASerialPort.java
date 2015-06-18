/*
 * author:jxm
 * date:2014-12-25 9:44
 * description:基于SerialPort的简单封装,单例模式
 */
package cn.jxm.service.serialport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidParameterException;

import cn.jxm.data.packet.Packet;
import cn.jxm.data.packet.FPM30A.FPM30APacket;
import cn.jxm.data.packet.datatype.Uint16;
import cn.jxm.data.packet.datatype.Uint8;
import android_serialport_api.SerialPort;

public class FPM30ASerialPort {
	
	//串口设备路径，例如 /dev/ttyS0, /dev/ttyUSB0, ...
	private String devicePath;
	//波特率，例如9600, 115200,...
	private int baudRate;
	private SerialPort serialPort = null;
	private FileOutputStream fos;
	private FileInputStream fis;
//	private static FPM30ASerialPort mySerialPort = null;
	
	public FPM30ASerialPort(String devicePath, int baudRate){
		super();
		this.devicePath = devicePath;
		this.baudRate = baudRate;
//		open();
	}
	
	public void open() throws Exception{
		serialPort = new SerialPort(new File(devicePath), baudRate, 0);
		fos = (FileOutputStream) serialPort.getOutputStream();
		fis = (FileInputStream) serialPort.getInputStream();
	}
	
//	public static FPM30ASerialPort getInstance(String devPath,int baudRate) throws Exception {
//		if(mySerialPort == null){
//			mySerialPort = new FPM30ASerialPort(devPath, baudRate);
//		}
//		return mySerialPort;
//	}
	
//	public static FPM30ASerialPort getInstance(){
//		return mySerialPort;
//	}
	
	public void close(){
		if(serialPort != null){
			serialPort.close();
			serialPort = null;
		}
	}
	
//	public int write(byte[] data){
//		try {
//			fos.write(data);
//			fos.flush();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return -1;
//		}
//		return 0;
//	}
	
	public int write(Packet p){
		try {
			fos.write(p.toByteArray());
			fos.flush();
			p.printPacket();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		return 0;
	}
	
	public int read(byte[] buffer){
		int size = 0;
		try {
			size = fis.read(buffer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		return size;
	}
	public int read(Packet packet){
		byte[] head = new byte[2];
		byte[] address = new byte[4];
		byte[] pid = new byte[1];
		byte[] len = new byte[2];
		byte[] checkSum = new byte[2];
		
		FPM30APacket p = (FPM30APacket)packet;
		
		read(head,2);
		Uint16 FPM30AHead = new Uint16();
		FPM30AHead.setByteArray(head);
		if(FPM30AHead.getIntData() == 0xEF01){
			p.getHead().setByteArray(head);
			read(address,4);
			p.getAddress().setByteArray(address);
			
			read(pid,1);
			p.getPid().setByteArray(pid);
			
			read(len,2);
			p.getDataLen().setByteArray(len);
			
			int contentLen = p.getDataLen().getIntData() - 2;
			byte[] contents = new  byte[contentLen];
			read(contents, contentLen);
			p.getData().clear();
			for(int i = 0;i < contentLen;i++){
				Uint8 data = new Uint8();
				byte[] byteData = new byte[1];
				byteData[0] = contents[i];
				data.setByteArray(byteData);
				p.getData().add(data);
			}
			
			read(checkSum,2);
			p.getCheckSum().setByteArray(checkSum);
			p.printPacket();
		}
		return -1;
	}
	
	private int read(byte[] buf,int n){
		int size = 0;
		int count = 0;
		try {
			while(size < n){
				size = fis.read(buf,count,n - size);
				count += size;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}
}
