package cn.jxm.service.FPM30A;


import cn.jxm.data.packet.Packet;
import cn.jxm.data.packet.PacketFactory;
import cn.jxm.data.packet.FPM30A.FPM30AACKPacket;
import cn.jxm.data.packet.FPM30A.FPM30APacket;
import cn.jxm.data.packet.FPM30A.FPM30APacketFactory;
import cn.jxm.data.packet.FPM30A.FPM30APacketType;
import cn.jxm.service.FigurePrintService;
import cn.jxm.service.serialport.FPM30ASerialPort;

public class FPM30AFigurePrintService implements FigurePrintService {

	private final long address = 0xFFFFFFFF;
	private final long pwd = 0xFFFFFFFF;
	private final String devicePath = "/dev/ttyUSB0";
	private final int baudRate = 57600;
	private FPM30ASerialPort serialPort;
	private PacketFactory packetFactory = new FPM30APacketFactory();
	private static FPM30AFigurePrintService figurePrintService = null;
	
	private FPM30AFigurePrintService(){
		try {
			initHardware();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static FPM30AFigurePrintService getInstance(){
		if(figurePrintService == null){
			figurePrintService = new FPM30AFigurePrintService();
		}
		return figurePrintService;
	}
	@Override
	public int initHardware() throws Exception {
		// TODO Auto-generated method stub
		serialPort = new FPM30ASerialPort(devicePath, baudRate);
		serialPort.open();
		return 0;
	}

	@Override
	public int searchBy1_N() {
		// TODO Auto-generated method stub
//		FPM30AACKPacket ACKPacket = new FPM30AACKPacket();
//		Packet vfyPwdPacket = packetFactory.createPacket(FPM30APacketType.VfyPwd,address,pwd);
//		serialPort.write(vfyPwdPacket);
//		serialPort.read(ACKPacket);
		Packet genImgPacket = packetFactory.createPacket(FPM30APacketType.GenImg,address);
		FPM30AACKPacket ACKPacket = new FPM30AACKPacket();
		serialPort.write(genImgPacket);
		serialPort.read(ACKPacket);
		if(ACKPacket.getACKCode() == 0x00){
			Packet img2TzPacket = packetFactory.createPacket(FPM30APacketType.Img2Tz,address,0x01);
			serialPort.write(img2TzPacket);
			serialPort.read(ACKPacket);
			if(ACKPacket.getACKCode() == 0x00){
				Packet searchPacket = packetFactory.createPacket(FPM30APacketType.Search,address,0x01,0x01,0x05);
				serialPort.write(searchPacket);
				serialPort.read(ACKPacket);
				if(ACKPacket.getACKCode() == 0x00){
//					return ACKPacket.getData().
					int h = ACKPacket.getData().get(1).getIntData();
					int l = ACKPacket.getData().get(2).getIntData();
					return (h << 8 | l) & 0x0000FFFF;
				}else{
//					return ACKPacket.getACKCode();
					return -1;
				}
			}else{
//				return ACKPacket.getACKCode();
				return -1;
			}
		}else{
//			return ACKPacket.getACKCode(); 
			return -1;
		}
		
	}

	@Override
	public int searchBy1_1(int userId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getFigureNum() {
		// TODO Auto-generated method stub
		Packet templeteNumPacket = packetFactory.createPacket(FPM30APacketType.TempleteNum,address);
		serialPort.write(templeteNumPacket);
		FPM30AACKPacket ACkPacket = new FPM30AACKPacket();
		serialPort.read(ACkPacket);
		int templeteNum = ACkPacket.getTempleteNum();
		return templeteNum;
	}

	@Override
	public int addFigure(int id) {
		// TODO Auto-generated method stub
		FPM30AACKPacket ACKPacket = new FPM30AACKPacket();
		Packet genImgPacket = packetFactory.createPacket(FPM30APacketType.GenImg,address);
		serialPort.write(genImgPacket);
		serialPort.read(ACKPacket);
		if(ACKPacket.getACKCode() == 0x00){
			Packet img2TzPacket = packetFactory.createPacket(FPM30APacketType.Img2Tz,address,0x01);
			serialPort.write(img2TzPacket);
			serialPort.read(ACKPacket);
			if(ACKPacket.getACKCode() == 0x00){
				Packet storePacket = packetFactory.createPacket(FPM30APacketType.Store,address,0x01,id);
				serialPort.write(storePacket);
				serialPort.read(ACKPacket);
				return ACKPacket.getACKCode();
			}else{
				return ACKPacket.getACKCode();
			}
		}else{
			return ACKPacket.getACKCode();
		}
	}

	@Override
	public int addFigure() {
		// TODO Auto-generated method stub
		int next = this.getFigureNum() + 1;
		return addFigure(next);
	}
}
