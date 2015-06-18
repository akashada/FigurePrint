package cn.jxm.service;

public interface FigurePrintService {
	public int initHardware() throws Exception;
	public int searchBy1_N();
	public int searchBy1_1(int userId);
	public int getFigureNum();
	public int addFigure(int id);
	public int addFigure();
}
