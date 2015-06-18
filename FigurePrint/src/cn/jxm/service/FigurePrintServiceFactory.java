package cn.jxm.service;

import cn.jxm.service.FPM30A.FPM30AFigurePrintService;

public class FigurePrintServiceFactory {

	private static FigurePrintService figurePrintService = null;
	
	public static FigurePrintService createFigurePrintService(FigurePrintServiceType type){
		switch (type) {
		case FPM30A:
		{
			figurePrintService = FPM30AFigurePrintService.getInstance();
			break;
		}

		default:
			break;
		}
		return figurePrintService;
	}
}
