package cn.jxm.data.packet.FPM30A;

import cn.jxm.data.packet.Packet;
import cn.jxm.data.packet.PacketFactory;
import cn.jxm.data.packet.FPM30A.FPM30AGenImgPacket;
import cn.jxm.data.packet.FPM30A.FPM30AImg2TzPacket;
import cn.jxm.data.packet.FPM30A.FPM30ASearchPacket;

public class FPM30APacketFactory implements PacketFactory {

	private Packet packet;

	@Override
	public Packet createPacket(Object... parameter) {
		// TODO Auto-generated method stub
		FPM30APacketType packettype = (FPM30APacketType) parameter[0];
		long address = (Long) parameter[1];
		switch (packettype) {
		case GenImg: {
			packet = new FPM30AGenImgPacket(address);
			break;
		}
		case Img2Tz: {
			int bufId = (Integer) parameter[2];
			packet = new FPM30AImg2TzPacket(address, bufId);
			break;
		}
		case Search:{
			int bufId = (Integer) parameter[2];
			int startPage = (Integer)parameter[3];
			int pageNum = (Integer)parameter[4];
			packet = new FPM30ASearchPacket(address, bufId, startPage, pageNum);
			break;
		}
		case VfyPwd:{
			long pwd = (Long)parameter[2];
			packet = new FPM30AVfyPwdPacket(address, pwd);
			break;
		}
		case Store:{
			int bufId = (Integer)parameter[2];
			int pageId = (Integer)parameter[3];
			packet = new FPM30AStorePacket(address,bufId,pageId);
			break;
		}
		case TempleteNum:{
			packet = new FPM30ATempleteNumPacket(address);
			break;
		}
		default:
			break;
		}
		return packet;
	}

}
