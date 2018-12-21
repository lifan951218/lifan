package cn.congine.web.action.sub;

import static cn.congine.comm.util.DateUtil.changeTostring;
import static cn.congine.comm.util.DateUtil.strToDate;
import static cn.congine.comm.util.RightUtil.hasRight;
import static cn.congine.comm.util.StringUtil.getLabel;
import static cn.congine.comm.util.StringUtil.nullString;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import cn.congine.comm.action.CommAction;
import cn.congine.comm.po.Result;
import cn.congine.comm.util.ConfigUtil;
import cn.congine.comm.util.ConstUtil;
import cn.congine.comm.util.DateUtil;
import cn.congine.comm.util.ExcelUtil;
import cn.congine.comm.util.RangePartition;
import cn.congine.po.TbArguments;
import cn.congine.po.TbGroup;
import cn.congine.po.TbMembers;
import cn.congine.po.TbWarn;
import cn.congine.service.TbArgumentsService;
import cn.congine.service.TbGroupService;
import cn.congine.service.TbSectionService;
import cn.congine.service.TbTemperatureService;
import cn.congine.service.TbTrainboxService;
import cn.congine.service.TbWarnService;
import cn.congine.service.TbTransitreceiptService;

/**
 * 轴温数据：轴温、报警
 * 
 * @author lincoln
 * 
 */
public class TbTemperatureSubAction extends CommAction {
	private static final Log log = LogFactory.getLog(TbTemperatureSubAction.class);
	private static final long serialVersionUID = 9140336393132946519L;
	public static final int qian1 = 60 * 60 * 1000;
	public static final int qian2 = 30 * 60 * 1000;
	public static final int hou = 10 * 60 * 1000;
	

	@SuppressWarnings("unused")
	private static int NULL_AXLE = -9;
	private TbTemperatureService service = new TbTemperatureService();
	private TbWarnService srv_warn = new TbWarnService();
	private TbGroupService srv_group = new TbGroupService();
	private TbTrainboxService srv_trainbox = new TbTrainboxService();

	private String id;
	private String roadsection;
	private String shop;
	private String traingroup;
	private String trainnumber;
	private String carriagenumber;
	private String axle;
	private Integer z1;
	private Integer z2;
	private Integer z3;
	private Integer z4;
	private Integer z5;
	private Integer z6;
	private Integer z7;
	private Integer z8;
	private Integer z9;
	private Integer z12;
	private Integer z34;
	private Integer z56;
	private Integer z78;
	private Integer z13;
	private Integer z24;
	private Integer z57;
	private Integer z68;
	private Long recordtime;
	private Long readtime;
	private String comdisconnect;
	private String openway;
	private String disway;
	private String yujing;
	private String tongce;
	private String tongzhou;
	private String wensheng;
	private String gaowen;
	private String diwen;
	private String lowtemperature;
	private String hightemperature;
	private String earlywarn;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	
	public String getGaowen() {
		return gaowen;
	}

	public void setGaowen(String gaowen) {
		this.gaowen = gaowen;
	}

	public String getDiwen() {
		return diwen;
	}

	public void setDiwen(String diwen) {
		this.diwen = diwen;
	}

	public String getTongce() {
		return tongce;
	}

	
	public String getWensheng() {
		return wensheng;
	}

	public void setWensheng(String wensheng) {
		this.wensheng = wensheng;
	}

	public String getTongzhou() {
		return tongzhou;
	}

	public void setTongzhou(String tongzhou) {
		this.tongzhou = tongzhou;
	}

	public void setTongce(String tongce) {
		this.tongce = tongce;
	}

	public String getYujing() {
		return yujing;
	}

	public void setYujing(String yujing) {
		this.yujing = yujing;
	}

	public String getRoadsection() {
		return roadsection;
	}

	public void setRoadsection(String roadsection) {
		this.roadsection = roadsection;
	}

	public String getShop() {
		return shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

	public String getTraingroup() {
		return traingroup;
	}

	public void setTraingroup(String traingroup) {
		this.traingroup = traingroup;
	}

	public String getTrainnumber() {
		return trainnumber;
	}

	public void setTrainnumber(String trainnumber) {
		this.trainnumber = trainnumber;
	}
	
	public String getAxle() {
		return axle;
	}

	public void setAxle(String axle) {
		this.axle = axle;
	}

	public String getCarriagenumber() {
		return carriagenumber;
	}

	public void setCarriagenumber(String carriagenumber) {
		this.carriagenumber = carriagenumber;
	}

	public Integer getZ1() {
		return z1;
	}

	public void setZ1(Integer z1) {
		this.z1 = z1;
	}

	public Integer getZ2() {
		return z2;
	}

	public void setZ2(Integer z2) {
		this.z2 = z2;
	}

	public Integer getZ3() {
		return z3;
	}

	public void setZ3(Integer z3) {
		this.z3 = z3;
	}

	public Integer getZ4() {
		return z4;
	}

	public void setZ4(Integer z4) {
		this.z4 = z4;
	}

	public Integer getZ5() {
		return z5;
	}

	public void setZ5(Integer z5) {
		this.z5 = z5;
	}

	public Integer getZ6() {
		return z6;
	}

	public void setZ6(Integer z6) {
		this.z6 = z6;
	}

	public Integer getZ7() {
		return z7;
	}

	public void setZ7(Integer z7) {
		this.z7 = z7;
	}

	public Integer getZ8() {
		return z8;
	}

	public void setZ8(Integer z8) {
		this.z8 = z8;
	}

	public Integer getZ9() {
		return z9;
	}

	public void setZ9(Integer z9) {
		this.z9 = z9;
	}

	public Integer getZ12() {
		return z12;
	}

	public void setZ12(Integer z12) {
		this.z12 = z12;
	}

	public Integer getZ34() {
		return z34;
	}

	public void setZ34(Integer z34) {
		this.z34 = z34;
	}

	public Integer getZ56() {
		return z56;
	}

	public void setZ56(Integer z56) {
		this.z56 = z56;
	}

	public Integer getZ78() {
		return z78;
	}

	public void setZ78(Integer z78) {
		this.z78 = z78;
	}

	public Integer getZ13() {
		return z13;
	}

	public void setZ13(Integer z13) {
		this.z13 = z13;
	}

	public Integer getZ24() {
		return z24;
	}

	public void setZ24(Integer z24) {
		this.z24 = z24;
	}

	public Integer getZ57() {
		return z57;
	}

	public void setZ57(Integer z57) {
		this.z57 = z57;
	}

	public Integer getZ68() {
		return z68;
	}

	public void setZ68(Integer z68) {
		this.z68 = z68;
	}

	public Long getRecordtime() {
		return recordtime;
	}

	public void setRecordtime(Long recordtime) {
		this.recordtime = recordtime;
	}

	public Long getReadtime() {
		return readtime;
	}

	public void setReadtime(Long readtime) {
		this.readtime = readtime;
	}

	public String getComdisconnect() {
		return comdisconnect;
	}

	public void setComdisconnect(String comdisconnect) {
		this.comdisconnect = comdisconnect;
	}

	public String getOpenway() {
		return openway;
	}

	public void setOpenway(String openway) {
		this.openway = openway;
	}

	public String getDisway() {
		return disway;
	}

	public void setDisway(String disway) {
		this.disway = disway;
	}

	public String getLowtemperature() {
		return lowtemperature;
	}

	public void setLowtemperature(String lowtemperature) {
		this.lowtemperature = lowtemperature;
	}

	public String getHightemperature() {
		return hightemperature;
	}

	public void setHightemperature(String hightemperature) {
		this.hightemperature = hightemperature;
	}

	public String getEarlywarn() {
		return earlywarn;
	}

	public void setEarlywarn(String earlywarn) {
		this.earlywarn = earlywarn;
	}

	/**
	 * 默认显示轴温数据查询
	 */
	public void initPage() {
		Result result = hasRight("轴温数据");
		if(!result.isSuccess()){
			out(result.getMessage());
			return;
		}
		initCcsjPage();
	}

	/**
	 * 轴温数据查询
	 */
	@SuppressWarnings("unused")
	public void initCcsjPage() {
		

//		Result resultright = hasRight("轴温数据");
//		if(!resultright.isSuccess()){
//			out(resultright.getMessage());
//			return;
//		}

		/**
		 * 用到的service
		 */
		TbTemperatureService tmpService = new TbTemperatureService();
		TbSectionService sctService = new TbSectionService();
		TbArgumentsService argSevice = new TbArgumentsService();
		TbTransitreceiptService calWensheng = new TbTransitreceiptService();

		HttpServletRequest request = this.getRequest();
		HttpSession session = request.getSession();
		String sessionid = session.getId();

		/**
		 * 查询条件
		 */
		String sqlWhere = " where 1=1 ";
		String sqlSessionid = " and sessionid='" + sessionid + "' ";
		String sqlOrderby = " order by traingroup, to_number(carriagenumber), recordtime, trainnumber ";
		String sqlOrderby2 = " order by recordtime, traingroup, to_number(carriagenumber), trainnumber ";
		String sqlOthers = " ";
		String sqlTable = "";

		/**
		 * 页面参数
		 */
		// 如果dowhat.equals("search")，即进行查询，不需要输出form，否则输出form
		String dowhat = request.getParameter("dowhat");
		String readType = request.getParameter("readType");
		boolean isChukuRead = "1".equals(readType);
		boolean search = null != dowhat && dowhat.equals("search");
		boolean export = null != dowhat && dowhat.equals("export");
		// 这四个条件修改后会导致重新计算温升标志
		String startTimeStr = nullString(request.getParameter("startTime"));
		String endTimeStr = nullString(request.getParameter("endTime"));
		String gradientStr = request.getParameter("gradientstr"); // 梯度值
		String gaptimeStr = request.getParameter("gaptimestr"); // 时间段（单位：分钟，需要转换为毫秒进行对比）
		// 设置参数
		String earlyWarningStr = request.getParameter("earlyWarningstr"); // 预警
		String coaxialStr = request.getParameter("coaxialstr"); // 同轴温差
		String ipsilateralStr = request.getParameter("ipsilateralstr"); // 同侧温差
		String lowtemperatureStr = request.getParameter("diwenstr"); // 低温异常
		String hightemperatureStr = request.getParameter("gaowenstr");// 高温异常
		// 筛选条件
		int conditionLen = 0;
		String conditionPar = "";
		String wenshengPar = request.getParameter("wensheng"); //
		String openwayPar = request.getParameter("openway"); //
		String diswayPar = request.getParameter("disway"); //
		String disconnPar = request.getParameter("disconn"); //
		String diwenPar = request.getParameter("diwen"); //
		String gaowenPar = request.getParameter("gaowen"); //
		String tongzhouPar = request.getParameter("tongzhou"); //
		String tongzhouyuPar = request.getParameter("tongzhouyu"); // 同轴与：低温、高温、同侧、同轴、温升、预警。不选是或，选中是与
		String tongcePar = request.getParameter("tongce"); //
		String tongceGZPar = request.getParameter("tongceGZ"); //
		String yujingPar = request.getParameter("yujing"); //
		// 路段
		String roadsectionPar = request.getParameter("roadsection"); 	// 路段
		String shopPar = request.getParameter("shop"); 					// 车间
		String traingroupPar = nullString(request.getParameter("traingroup"));		// 车次-组
		String trainnumberPar = nullString(request.getParameter("trainnumber")); 	// 车厢号
		String chehao = request.getParameter("chehao");				 	// 手动输入车号
		String axlePar = nullString(request.getParameter("axle"));
		int axle = "".equals(axlePar) ? -9 : Integer.parseInt(axlePar);
		//区间
		String validTime = nullString(request.getParameter("validTime"));
		String regionstarttime = nullString(request.getParameter("regionstarttime"));
		String regionendtime = nullString(request.getParameter("regionendtime"));
		//String regionqian = nullString(request.getParameter("regionqian"));
		//String regionhou = nullString(request.getParameter("regionhou"));
		String [] regionstarttimeArr = regionstarttime.split(";");
		String [] regionendtimeArr = regionendtime.split(";");
		//String [] regionqianArr = regionqian.split(";");
		//String [] regionhouArr = regionhou.split(";");
		String dotr = nullString(request.getParameter("dotr"));
		String dotrcxh = nullString(request.getParameter("dotrcxh"));
		String dotrczch = nullString(request.getParameter("dotrczch"));
		String dotrsjd = nullString(request.getParameter("dotrsjd"));
		String dotrshowWarn = nullString(request.getParameter("dotrshowWarn"));
		boolean kmisSearch = !"".equals(trainnumberPar) && null == dowhat;
		
		if ("".equals(dotrsjd))
		{
			if (ConfigUtil.isShengYangJu())
			{
				dotrsjd = "0";
			}
			else
			{
				dotrsjd = gaptimeStr;
			}
		}
		/**
		 * 设置默认参数
		 */
		TbArguments args = argSevice.findById("1");

		// 赋予一个值，防止参数取不到，数据库也取不到的情况
		Long earlyWarning = 50l;
		try {
			earlyWarning = Long.parseLong(earlyWarningStr);
		} catch (Exception e) {
			if (null != args.getEarlywarning()) {
				earlyWarning = args.getEarlywarning();
			}
		}

		Long gradient = 10l;
		try {
			gradient = Long.parseLong(gradientStr);
		} catch (Exception e) {
			if (null != args.getGradient()) {
				gradient = args.getGradient();
			}
		}

		Long gaptime = 60l;
		try {
			gaptime = Long.parseLong(gaptimeStr);
		} catch (Exception e) {
			if (null != args.getGaptime()) {
				gaptime = args.getGaptime();
			}
		}

		Long coaxial = 10l;
		try {
			coaxial = Long.parseLong(coaxialStr);
		} catch (Exception e) {
			if (null != args.getCoaxial()) {
				coaxial = args.getCoaxial();
			}
		}

		Long ipsilateral = 10l;
		try {
			ipsilateral = Long.parseLong(ipsilateralStr);
		} catch (Exception e) {
			if (null != args.getIpsilateral()) {
				ipsilateral = args.getIpsilateral();
			}
		}

		Long lowtemperatureValue = 10l;
		try {
			lowtemperatureValue = Long.parseLong(lowtemperatureStr);
		} catch (Exception e) {
			if (null != args.getLowtemperature()) {
				lowtemperatureValue = args.getLowtemperature();
			}
		}

		Long hightemperatureValue = 10l;
		try {
			hightemperatureValue = Long.parseLong(hightemperatureStr);
		} catch (Exception e) {
			if (null != args.getHightemperature()) {
				hightemperatureValue = args.getHightemperature();
			}
		}
		
		String readtypePar;
		if (isChukuRead)
		{
			readtypePar = " and readtype=1 ";
		} else {
			readtypePar = " and readtype is null ";
		}
		sqlWhere += readtypePar;
		/**
		 * 时间条件（查询、计算）
		 */
		Long startTime = 0l;
		try {
			startTime = strToDate(startTimeStr).getTime();
			sqlWhere += " and recordtime >= " + startTime.longValue() + " ";
		} catch (Exception e) {
		}

		Long endTime = 0l;
		try {
			endTime = strToDate(endTimeStr).getTime();
			sqlWhere += " and recordtime <= " + endTime.longValue() + " ";
		} catch (Exception e) {
		}
		
		RangePartition rangePartition = new RangePartition(startTime, endTime);
		
		/**
		 * 点击某行增加的时间段（查询、计算）
		 */
		boolean showDataInShijianduan = false;
		Long dotrTime = 0l;
		
		if (!"".equals(dotr))
		{
			dotrTime = strToDate(dotr).getTime();
			showDataInShijianduan = true;
			Long dotrStime = 0l;
			long sjd = Long.valueOf(dotrsjd) * 60 * 1000;
			//沈阳局修改为显示全部
			if (!"0".equals(dotrsjd))
			{
				try
				{
					dotrStime = dotrTime - sjd;
					sqlWhere += " and recordtime >= " + dotrStime.longValue() + " ";
				}
				catch (Exception e)
				{
				}
				
				Long dotrEtime = 0l;
				try
				{
					dotrEtime = dotrTime + sjd;
					sqlWhere += " and recordtime <= " + dotrEtime.longValue() + " ";
				}
				catch (Exception e)
				{
				}
			}
			
			trainnumberPar = dotrczch;
		}
		
		/**
		 * 区间参数
		 */
		if ("1".equals(validTime) && null != traingroupPar && !"".equals(traingroupPar)) {
			String sqlRegion = " and (1 = 0 ";
			
			for (int i = 0; i < regionstarttimeArr.length; i++) {
				if (regionstarttimeArr[i].length() == 0) continue;
				
				int regionhou = TbTemperatureSubAction.hou;
				int regionqian = TbTemperatureSubAction.qian2;
				
				if (0 == i) {
					regionqian =TbTemperatureSubAction.qian1;
				}
				
				sqlRegion += " or ( recordtime >= " + (strToDate(regionstarttimeArr[i]).getTime());
				sqlRegion += " and recordtime <= " + (strToDate(regionendtimeArr[i]).getTime()) + " ) ";
			}
			
			if (" and (1 = 0 ".equals(sqlRegion)) {
				//sqlRegion = " ";
			} else {
				sqlRegion += " ) ";
				sqlWhere += sqlRegion;
			}
		}

		/**
		 * 路段条件（查询）
		 */
		//System.out.println("roadsectionPar:" + roadsectionPar);
		if (null != trainnumberPar && !"".equals(trainnumberPar)) {
			sqlWhere += " and trainnumber like '%" + trainnumberPar + "%' ";
		}
		else if (null != traingroupPar && !"".equals(traingroupPar)) {
			sqlWhere += " and traingroup='" + traingroupPar + "' ";
		}
		else if (null != shopPar && !"".equals(shopPar)) {
			sqlWhere += " and shop='" + shopPar + "' ";
		}
		else if (null != roadsectionPar && !"".equals(roadsectionPar)) {
			sqlWhere += " and roadsection='" + roadsectionPar + "' ";
		}
		
		
		
		
		if (!showDataInShijianduan)
		{
			/**
			 * 复选框条件（查询）
			 */
			String sqlYuHuo = " and (1=0 ";
			boolean sqlYuHuoBool = false;
			
			if (null != openwayPar)
			{
				conditionLen++;
				conditionPar = "开路";
				if (axle == NULL_AXLE)
					sqlYuHuo += " or openway='-1' ";
				else
					sqlYuHuo += " or z" + axlePar + " = 181 ";
				sqlYuHuoBool = true;
			}
			if (null != diswayPar)
			{
				conditionLen++;
				conditionPar = "短路";
				if (axle == NULL_AXLE)
					sqlYuHuo += " or disway='-1' ";
				else
					sqlYuHuo += " or z" + axlePar + " = 182 ";
				sqlYuHuoBool = true;
			}
			if (null != disconnPar)
			{
				conditionLen++;
				conditionPar = "通讯断";
				conditionLen ++;
				if (axle == NULL_AXLE)
					sqlYuHuo += " or COMDISCONNECT='0' ";
				else
					sqlYuHuo += " or z" + axlePar + " is null ";
				sqlYuHuoBool = true;
			}
	//		if (null != disconnPar) {
	//			sqlYuHuo += " or ( 0=1 ";
	//			for (int i = 1; i <= 8; i++) {
	//				sqlYuHuo += " or z" + i + " is null ";
	//			}
	//			sqlYuHuo += " ) ";
	//			sqlYuHuoBool = true;
	//		}
			
			// 同轴与
			String tongzhouyu = " or ";
			String tongzhouyu1 = " 1=0 ";
			boolean tongzhouyuAdd = false;
			if (null != tongzhouyuPar && "1".equals(tongzhouyuPar)) {
				tongzhouyu = " and ";
				tongzhouyu1 = " 1=1 ";
			}
			
			String tongzhouyuSqlPar= " or ( " + tongzhouyu1;
			
			if (null != wenshengPar)
			{
				conditionLen++;
				conditionPar = "温升";
				if (axle == NULL_AXLE)
					tongzhouyuSqlPar += tongzhouyu + " wensheng=1 ";
				else
					tongzhouyuSqlPar += tongzhouyu + " ws" + axlePar + "=1 ";
				tongzhouyuAdd = true;
			}
			if (null != yujingPar) {
				conditionLen++;
				conditionPar = "预警";
				tongzhouyuSqlPar += tongzhouyu + " ( 0=1 ";
				if (axle == NULL_AXLE)
					for (int i = 1; i <= 8; i++)
					{
						tongzhouyuSqlPar += " or (z" + i + " != 181 and z" + i + " != 182 and z" + i + "!=183 and z" + i + " >= z9 + "
								+ earlyWarning + ")";
					}
				else
					tongzhouyuSqlPar += " or (z" + axlePar + " != 181 and z" + axlePar + " != 182 and z" + axlePar + "!=183 and z"
							+ axlePar + " >= z9 + " + earlyWarning + ")";

				tongzhouyuSqlPar += " ) ";
				tongzhouyuAdd = true;
			}
			if (null != gaowenPar)
			{
				conditionLen++;
				conditionPar = "高温异常";
				if (axle == NULL_AXLE)
					tongzhouyuSqlPar += tongzhouyu + " hightemperature=1 ";
				else
					tongzhouyuSqlPar += tongzhouyu + " gw" + axlePar + "=1 ";
				tongzhouyuAdd = true;
			}
			if (null != diwenPar) {
				conditionLen++;
				conditionPar = "低温异常";
				tongzhouyuSqlPar += tongzhouyu + " ( 0=1 ";
				if (axle == NULL_AXLE)
					for (int i = 1; i <= 8; i++)
					{
						tongzhouyuSqlPar += " or (z" + i + " !=181 and z" + i + "!=182 and z" + i
								+ "!=183 and z9!=181 and z9!=182 and z9!=183 and z" + i + " <= z9 -" + lowtemperatureValue + ") ";
					}
				else
					tongzhouyuSqlPar += " or (z" + axlePar + " !=181 and z" + axlePar + "!=182 and z" + axlePar
							+ "!=183 and z9!=181 and z9!=182 and z9!=183 and z" + axlePar + " <= z9 -" + lowtemperatureValue + ") ";

				tongzhouyuSqlPar += " ) ";
				tongzhouyuAdd = true;
			}
			if (null != tongzhouPar)
			{
				conditionLen++;
				conditionPar = "同轴";
				tongzhouyuSqlPar += tongzhouyu + " ( 0=1 ";
				if (axle == NULL_AXLE || axle == 1)
					tongzhouyuSqlPar += " or (z1!=181 and z1!=182 and z1!=183 and z2!=181 and z2!=182 and z2!=183 and z12 >= " + coaxial + ")";
				if (axle == NULL_AXLE || axle == 2)
					tongzhouyuSqlPar += " or (z1!=181 and z1!=182 and z1!=183 and z2!=181 and z2!=182 and z2!=183 and z12 <=-" + coaxial + ")";
				if (axle == NULL_AXLE || axle == 3)
					tongzhouyuSqlPar += " or (z3!=181 and z3!=182 and z3!=183 and z4!=181 and z4!=182 and z4!=183 and z34 >= " + coaxial + ")";
				if (axle == NULL_AXLE || axle == 4)
					tongzhouyuSqlPar += " or (z3!=181 and z3!=182 and z3!=183 and z4!=181 and z4!=182 and z4!=183 and z34 <=-" + coaxial + ")";
				if (axle == NULL_AXLE || axle == 5)
					tongzhouyuSqlPar += " or (z5!=181 and z5!=182 and z5!=183 and z6!=181 and z6!=182 and z6!=183 and z56 >= " + coaxial + ")";
				if (axle == NULL_AXLE || axle == 6)
					tongzhouyuSqlPar += " or (z5!=181 and z5!=182 and z5!=183 and z6!=181 and z6!=182 and z6!=183 and z56 <=-" + coaxial + ")";
				if (axle == NULL_AXLE || axle == 7)
					tongzhouyuSqlPar += " or (z7!=181 and z7!=182 and z7!=183 and z8!=181 and z8!=182 and z8!=183 and z78 >= " + coaxial + ")";
				if (axle == NULL_AXLE || axle == 8)
					tongzhouyuSqlPar += " or (z7!=181 and z7!=182 and z7!=183 and z8!=181 and z8!=182 and z8!=183 and z78 <=-" + coaxial + ")";
				tongzhouyuSqlPar += " ) ";
				tongzhouyuAdd = true;
			}
			if (null != tongcePar) {		//沈阳版同侧 13/57/24/68 每组减去该组中最低的，差值大于设置值即为报警
				conditionLen++;
				conditionPar = "同侧";
				tongzhouyuSqlPar += tongzhouyu + " ( 0=1 ";
				if (axle == NULL_AXLE || axle == 1)
					tongzhouyuSqlPar += " or (z1!=181 and z1!=182 and z1!=183 and z3!=181 and z3!=182 and z3!=183 and z13 >= " + ipsilateral + ")";
				if (axle == NULL_AXLE || axle == 3)
					tongzhouyuSqlPar += " or (z1!=181 and z1!=182 and z1!=183 and z3!=181 and z3!=182 and z3!=183 and z13 <=-" + ipsilateral + ")";
				if (axle == NULL_AXLE || axle == 2)
					tongzhouyuSqlPar += " or (z2!=181 and z2!=182 and z2!=183 and z4!=181 and z4!=182 and z4!=183 and z24 >= " + ipsilateral + ")";
				if (axle == NULL_AXLE || axle == 4)
					tongzhouyuSqlPar += " or (z2!=181 and z2!=182 and z2!=183 and z4!=181 and z4!=182 and z4!=183 and z24 <=-" + ipsilateral + ")";
				if (axle == NULL_AXLE || axle == 5)
					tongzhouyuSqlPar += " or (z5!=181 and z5!=182 and z5!=183 and z7!=181 and z7!=182 and z7!=183 and z57 >= " + ipsilateral + ")";
				if (axle == NULL_AXLE || axle == 7)
					tongzhouyuSqlPar += " or (z5!=181 and z5!=182 and z5!=183 and z7!=181 and z7!=182 and z7!=183 and z57 <=-" + ipsilateral + ")";
				if (axle == NULL_AXLE || axle == 6)
					tongzhouyuSqlPar += " or (z8!=181 and z8!=182 and z8!=183 and z6!=181 and z6!=182 and z6!=183 and z68 >= " + ipsilateral + ")";
				if (axle == NULL_AXLE || axle == 8)
					tongzhouyuSqlPar += " or (z8!=181 and z8!=182 and z8!=183 and z6!=181 and z6!=182 and z6!=183 and z68 <=-" + ipsilateral + ")";
				tongzhouyuSqlPar += " ) ";
				tongzhouyuAdd = true;
			}
			if (null != tongceGZPar) {		//广州版同侧 1357/2468 每组减去该组中最低的，差值大于设置值即为报警
				conditionLen++;
				conditionPar = "同侧";
				tongzhouyuSqlPar += tongzhouyu + " ( 0=1 ";
				if (axle == NULL_AXLE || axle == 1)
					tongzhouyuSqlPar += " or (z1!=181 and z1!=182 and z1!=183 and z1-least(z1,z3,z5,z7) >= " + ipsilateral + ")";
				if (axle == NULL_AXLE || axle == 2)
					tongzhouyuSqlPar += " or (z2!=181 and z2!=182 and z2!=183 and z2-least(z2,z4,z6,z8) <=-" + ipsilateral + ")";
				if (axle == NULL_AXLE || axle == 3)
					tongzhouyuSqlPar += " or (z3!=181 and z3!=182 and z3!=183 and z3-least(z1,z3,z5,z7) >= " + ipsilateral + ")";
				if (axle == NULL_AXLE || axle == 4)
					tongzhouyuSqlPar += " or (z4!=181 and z4!=182 and z4!=183 and z4-least(z2,z4,z6,z8) <=-" + ipsilateral + ")";
				if (axle == NULL_AXLE || axle == 5)
					tongzhouyuSqlPar += " or (z5!=181 and z5!=182 and z5!=183 and z5-least(z1,z3,z5,z7) >= " + ipsilateral + ")";
				if (axle == NULL_AXLE || axle == 6)
					tongzhouyuSqlPar += " or (z6!=181 and z6!=182 and z6!=183 and z6-least(z2,z4,z6,z8) <=-" + ipsilateral + ")";
				if (axle == NULL_AXLE || axle == 7)
					tongzhouyuSqlPar += " or (z7!=181 and z7!=182 and z7!=183 and z7-least(z1,z3,z5,z7) >= " + ipsilateral + ")";
				if (axle == NULL_AXLE || axle == 8)
					tongzhouyuSqlPar += " or (z8!=181 and z8!=182 and z8!=183 and z8-least(z2,z4,z6,z8) <=-" + ipsilateral + ")";
				tongzhouyuSqlPar += " ) ";
				tongzhouyuAdd = true;
			}
	
			tongzhouyuSqlPar += " ) ";
			
			if (tongzhouyuAdd ) {
				sqlYuHuo += tongzhouyuSqlPar;
				sqlYuHuoBool = true;
			}
			
			sqlYuHuo += " ) ";
			
			if (sqlYuHuoBool ) {
				sqlOthers += sqlYuHuo;
			}
		}
		/**
		 * 计算温升标志
		 */

		String sql;
		String wenshengSqlPar = (String) session.getAttribute("wenshengSqlPar"
				+ sessionid);
		String wenshengSqlParCur = gradient + ";" + gaptime + ";"
				+ startTimeStr + ";" + endTimeStr + ";" + traingroupPar + ";" + trainnumberPar + ";" + hightemperatureStr + ";" + isChukuRead;
		
		String startTime_temp = startTimeStr;
		String endTime_temp = endTimeStr;
		String[] scopeTime = this.getService().getScopetime("select min(recordtime),max(recordtime) from tb_temperature_search_tmp " + sqlWhere
				+ sqlOthers + sqlSessionid);
		if(!"".equals(scopeTime[0])){
			startTimeStr = scopeTime[0];	
		}
		if(!"".equals(scopeTime[1])){
			endTimeStr = scopeTime[1];
		}
		//System.out.println(sessionid);
		//System.out.println(wenshengSqlPar);
		//System.out.println(wenshengSqlParCur);

		String zwTable = "";
		int pIndex = 0;
		for (String range : rangePartition.getRangeList()) {
			pIndex ++;
			if (1 < pIndex) zwTable += " union all ";
			zwTable += " SELECT * FROM tb_temperature PARTITION(" + range + ")" + sqlWhere;
		}
		zwTable = "select * from (" + zwTable + ")" + sqlOrderby;
		//弹出轴温取消了分页，所以可以计算温升趋势
		//if (!showDataInShijianduan && (search || export || kmisSearch)) {
		if (search || export || kmisSearch) {
			if (null == wenshengSqlPar
					|| !wenshengSqlPar.equals(wenshengSqlParCur)) {

				//System.out.println("重新计算温升标志");
				sql = "delete tb_temperature_search_tmp where sessionid='"
						+ sessionid + "' or sessiontime < sysdate - 0.1 " + readtypePar;
				//System.out.println(sql);
				tmpService.executeMyUpdate(sql);
				if (ConfigUtil.isShengYangJu()) {
					/*
					sql = "INSERT INTO tb_temperature_search_tmp ("
							+ "                 sessionid,sessiontime,id,USERID,roadsection,shop,"
							+ "                 traingroup,trainnumber,carriagenumber,"
							+ "                 z1,z2,z3,z4,z5,z6,z7,z8,z9,"
							+ "                 z12,z34,z56,z78,z13,z24,z57,z68,"
							+ "                 recordtime,readtime,comdisconnect,openway,disway,"
							+ "                 lowtemperature,hightemperature,earlywarn,readtype,"
							+ "                 zx1,zx2,zx3,zx4,zx5,zx6,zx7,zx8,zx9,"
							+ "                 ws1,ws2,ws3,ws4,ws5,ws6,ws7,ws8,ws9,"
							+ "                 wensheng,rownumx)"
							+ "      SELECT   '" + sessionid + "',sysdate,b.*, "
							+ "               0, 0, 0, 0, 0, 0, 0, 0, 0, "
							+ "               0, 0, 0, 0, 0, 0, 0, 0, 0, "
							+ "               0, ROWNUM x "
							+ "        FROM   (" + zwTable + ") b ";
					
					//System.out.println(sql);
					tmpService.executeMyUpdate(sql);
					
					sql = "{call PROC_UPDATE_WENSHENGB('" + sessionid + "', " + gradient + ", " + (args.getGap() * 60 * 1000) + ", "
							+ (gaptime * 60 * 1000) + ", " + hightemperatureStr + ")}";
					//System.out.println(sql);
					tmpService.executeMyUpdate(sql);
					*/
					try {
					    calWensheng.CalcWenSheng(rangePartition,sqlWhere, sessionid, args.getGradient(), 
							(args.getGap() * 60 * 1000), (args.getGaptime() * 60 * 1000),
							args.getHightemperature());
					}catch (Exception e) {
						log.info(e.getMessage());
					}
				} else {
					sql = "INSERT INTO tb_temperature_search_tmp ("
							+ "                 sessionid,sessiontime,id,USERID,roadsection,shop,"
							+ "                 traingroup,trainnumber,carriagenumber,"
							+ "                 z1,z2,z3,z4,z5,z6,z7,z8,z9,"
							+ "                 z12,z34,z56,z78,z13,z24,z57,z68,"
							+ "                 recordtime,readtime,comdisconnect,openway,disway,"
							+ "                 lowtemperature,hightemperature,earlywarn,readtype,"
							+ "                 zx1,zx2,zx3,zx4,zx5,zx6,zx7,zx8,zx9,"
							+ "                 ws1,ws2,ws3,ws4,ws5,ws6,ws7,ws8,ws9,"
							+ "                 wensheng,rownumx)"
							+ "      SELECT   '" + sessionid + "',sysdate,b.*, "
							+ "               1, 1, 1, 1, 1, 1, 1, 1, 1, "
							+ "               1, 1, 1, 1, 1, 1, 1, 1, 1, "
							+ "               1, ROWNUM x "
							+ "        FROM   (" + zwTable + ") b ";
					//System.out.println(sql);
					tmpService.executeMyUpdate(sql);
				}
				
				session.setAttribute("wenshengSqlPar" + sessionid,
						wenshengSqlParCur);
			}
		}
		
//		String searchParStr = (nullString(traingroupPar).equals("") ? "" : "车次组：" + traingroupPar + "&nbsp;&nbsp;&nbsp;")
//				+ (nullString(trainnumberPar).equals("") ? "" : "车种车号：" + trainnumberPar + "&nbsp;&nbsp;&nbsp;")
//				+ (nullString(startTimeStr).equals("") ? "" : "时间：" + startTimeStr + "&nbsp;")
//				+ (nullString(endTimeStr).equals("") ? "" : "至：" + endTimeStr + "&nbsp;&nbsp;&nbsp;");
		
		String searchParStr = (nullString(traingroupPar).equals("") ? "" :  traingroupPar + "组列车")
				+ (nullString(trainnumberPar).equals("") ? "" :  trainnumberPar +"车厢")
				+ (conditionLen == 0?"轴温":(conditionLen == 1) ? conditionPar : "故障")
				+ "数据&nbsp;&nbsp;&nbsp;"
				+ (nullString(startTimeStr).equals("") ? "" : "时间范围：" + startTimeStr + "&nbsp;")
				+ (nullString(endTimeStr).equals("") ? "" : "至：" + endTimeStr + "&nbsp;&nbsp;&nbsp;");
		
		/**
		 * 是否导出excel，导出则不再输出页面
		 */
		if (export) {
			String titleParStr = (nullString(traingroupPar).equals("") ? "" :  traingroupPar + "组列车")
					+ (nullString(trainnumberPar).equals("") ? "" :  trainnumberPar +"车厢")
					+ (conditionLen == 0?"轴温":(conditionLen == 1) ? conditionPar : "故障")
					+ "数据";
			String timeParStr = (nullString(startTimeStr).equals("") ? "" : "时间范围：" + startTimeStr + "&nbsp;")
					+ (nullString(endTimeStr).equals("") ? "" : "至：" + endTimeStr + "&nbsp;&nbsp;&nbsp;");
			
			String filename = "轴温数据" + (new SimpleDateFormat("yyyyMMdd")).format(new Date()) + ".xlsx";
			
			if (null != trainnumberPar && !"".equals(trainnumberPar)) {
				
				filename = trainnumberPar + "车厢" + filename;
			}
			
			if (null != traingroupPar && !"".equals(traingroupPar)) {
				
				filename = traingroupPar + "组列车" + filename;
			}
			
			String filename_temp = (nullString(traingroupPar).equals("") ? "" :  traingroupPar + "组列车")
					+ (nullString(trainnumberPar).equals("") ? "" :  trainnumberPar +"车厢")
					+ (nullString(disconnPar).equals("")?"":"通讯段")
					+ (nullString(openwayPar).equals("")?"":"开路")
					+ (nullString(diswayPar).equals("")?"":"开路")
					+ (nullString(diwenPar).equals("")?"":"低温")
					+ (nullString(yujingPar).equals("")?"":"预警")
					+ (nullString(trainnumberPar).equals("")&&nullString(openwayPar).equals("")&&nullString(diswayPar).equals("")&&nullString(diwenPar).equals("")&&nullString(yujingPar).equals("")?"轴温数据":"数据")
					+ (new SimpleDateFormat("yyyyMMdd")).format(new Date()) + ".xlsx";
			
			
			
			exportZhouwenExcel(filename_temp, openwayPar, diswayPar, titleParStr, timeParStr, sqlWhere + sqlOthers + sqlSessionid);
			
			return;
		}
		
		/*
		 * 
		 * 跳转获取默认值
		 */
		String traingroupPar_temp = "";
		if(!"".equals(traingroupPar) && null != traingroupPar){
			if(null != srv_group.findOnlyTraingroup(traingroupPar)){
				roadsectionPar = srv_group.findOnlyTraingroup(traingroupPar).getTrainmasterid();
				shopPar = srv_group.findOnlyTraingroup(traingroupPar).getSectionid();
				traingroupPar_temp = srv_group.findOnlyTraingroup(traingroupPar).getId();
			}
		}
		if(!"".equals(trainnumberPar) && null != trainnumberPar && "".equals(traingroupPar_temp)){
			if(null != srv_trainbox.findOnlyTrainbox(trainnumberPar)){
				TbGroup group = new TbGroup();
				group = srv_group.findById(srv_trainbox.findOnlyTrainbox(trainnumberPar).getGroupID());
				if(null != group){
					roadsectionPar = group.getTrainmasterid();
					shopPar = group.getSectionid();
					traingroupPar = group.getTraingroup();
				}
			}
		}
		
		
		
		/**
		 * 计算分页
		 */
		int numPerPage = 50; // 每页显示多少条
		if(null != ConfigUtil.getProperty("sizeOfPage"))
		{
			try{
				numPerPage = Integer.parseInt(ConfigUtil.getProperty("sizeOfPage"));
			}
			catch(Exception e){
				
			}
		}
		long rowsCount = 0; // 总共多少条
		List<Object[]> list = null;
		Object[] item;

		sql = "select count(*) from tb_temperature_search_tmp " + sqlWhere
				+ sqlOthers + sqlSessionid;
		//System.out.println(sql);
		
		
		if (search || kmisSearch)
			list = tmpService.getFieldsBySql(sql);

		if (null != list) {
			//System.out.println("list.size() = " + list.size());

			rowsCount = Long.parseLong(list.get(0) + "");
			//System.out.println("list.get(0) = " + rowsCount);
		} else {
			//System.out.println("set list.size() = " + 0);

			rowsCount = 0l;
			//System.out.println("set list.get(0) = " + rowsCount);
		}

		//
		int pagesCount = (int) (rowsCount / numPerPage); // 总共多少页

		if (rowsCount % numPerPage != 0)
			pagesCount++;

		//
		int indexOfPage = 1;

		try {
			indexOfPage = Integer.parseInt(request.getParameter("indexOfPage"));
		} catch (Exception e) {
			indexOfPage = 1;
		}

		if (indexOfPage > pagesCount)
			indexOfPage = pagesCount;
		if (indexOfPage < 1)
			indexOfPage = 1;

		//
		if (showDataInShijianduan)
		{
			String sqlWarn = "SELECT   'warn' SESSIONID, SYSDATE SESSIONTIME, id, USERID,  ROADSECTION, SHOP, TRAINGROUP, TRAINNUMBER, "
					+ " CARRIAGENUMBER, z9 z1, NULL z2, NULL z3, NULL z4, NULL z5, NULL z6, NULL z7, "
					+ " NULL z8, warntemp z9, 0 z12, 0 z34, 0 z56, 0 z78, 0 z13, 0 z24, "
					+ " 0 z57, 0 z68, RECORDTIME, READTIME, '1' COMDISCONNECT, '1' OPENWAY, '1' DISWAY, NULL LOWTEMPERATURE, "
					+ " NULL HIGHTEMPERATURE, NULL EARLYWARN, '0' zx1, '0' zx2, '0' zx3, '0' zx4, '0' zx5, '0' zx6, "
					+ " '0' zx7, '0' zx8, '0' zx9, '0' ws1, '0' ws2, '0' ws3, '0' ws4, '0' ws5, "
					+ " '0' ws6, '0' ws7, '0' ws8, '0' ws9, '0' wensheng, 0 rownumx, NULL td1, NULL td2, "
					+ " NULL td3, NULL td4, NULL td5, NULL td6, NULL td7, NULL td8, NULL tdcz1, NULL tdcz2, "
					+ " NULL tdcz3, NULL tdcz4, NULL tdcz5, NULL tdcz6, NULL tdcz7, NULL tdcz8, NULL SESSIONTIMELONG, NULL gw1, "
					+ " NULL gw2, NULL gw3, NULL gw4, NULL gw5, NULL gw6, NULL gw7, NULL gw8 "
					+ "  FROM   tb_warn " + sqlWhere
					+ " AND zz = 1 "
					+ "UNION ALL "
					+ "SELECT   'warn' SESSIONID, SYSDATE SESSIONTIME, id, USERID,  ROADSECTION, SHOP, TRAINGROUP, TRAINNUMBER, "
					+ " CARRIAGENUMBER, NULL z1, z9 z2, NULL z3, NULL z4, NULL z5, NULL z6, NULL z7, "
					+ " NULL z8, warntemp z9, 0 z12, 0 z34, 0 z56, 0 z78, 0 z13, 0 z24, "
					+ " 0 z57, 0 z68, RECORDTIME, READTIME, '1' COMDISCONNECT, '1' OPENWAY, '1' DISWAY, NULL LOWTEMPERATURE, "
					+ " NULL HIGHTEMPERATURE, NULL EARLYWARN, '0' zx1, '0' zx2, '0' zx3, '0' zx4, '0' zx5, '0' zx6, "
					+ " '0' zx7, '0' zx8, '0' zx9, '0' ws1, '0' ws2, '0' ws3, '0' ws4, '0' ws5, "
					+ " '0' ws6, '0' ws7, '0' ws8, '0' ws9, '0' wensheng, 0 rownumx, NULL td1, NULL td2, "
					+ " NULL td3, NULL td4, NULL td5, NULL td6, NULL td7, NULL td8, NULL tdcz1, NULL tdcz2, "
					+ " NULL tdcz3, NULL tdcz4, NULL tdcz5, NULL tdcz6, NULL tdcz7, NULL tdcz8, NULL SESSIONTIMELONG, NULL gw1, "
					+ " NULL gw2, NULL gw3, NULL gw4, NULL gw5, NULL gw6, NULL gw7, NULL gw8 "
					+ "  FROM   tb_warn " + sqlWhere
					+ " AND zz = 2 "
					+ "UNION ALL "
					+ "SELECT   'warn' SESSIONID, SYSDATE SESSIONTIME, id, USERID,  ROADSECTION, SHOP, TRAINGROUP, TRAINNUMBER, "
					+ " CARRIAGENUMBER, NULL z1, NULL z2, z9 z3, NULL z4, NULL z5, NULL z6, NULL z7, "
					+ " NULL z8, warntemp z9, 0 z12, 0 z34, 0 z56, 0 z78, 0 z13, 0 z24, "
					+ " 0 z57, 0 z68, RECORDTIME, READTIME, '1' COMDISCONNECT, '1' OPENWAY, '1' DISWAY, NULL LOWTEMPERATURE, "
					+ " NULL HIGHTEMPERATURE, NULL EARLYWARN, '0' zx1, '0' zx2, '0' zx3, '0' zx4, '0' zx5, '0' zx6, "
					+ " '0' zx7, '0' zx8, '0' zx9, '0' ws1, '0' ws2, '0' ws3, '0' ws4, '0' ws5, "
					+ " '0' ws6, '0' ws7, '0' ws8, '0' ws9, '0' wensheng, 0 rownumx, NULL td1, NULL td2, "
					+ " NULL td3, NULL td4, NULL td5, NULL td6, NULL td7, NULL td8, NULL tdcz1, NULL tdcz2, "
					+ " NULL tdcz3, NULL tdcz4, NULL tdcz5, NULL tdcz6, NULL tdcz7, NULL tdcz8, NULL SESSIONTIMELONG, NULL gw1, "
					+ " NULL gw2, NULL gw3, NULL gw4, NULL gw5, NULL gw6, NULL gw7, NULL gw8 "
					+ "  FROM   tb_warn " + sqlWhere
					+ " AND zz = 3 "
					+ "UNION ALL "
					+ "SELECT   'warn' SESSIONID, SYSDATE SESSIONTIME, id, USERID,  ROADSECTION, SHOP, TRAINGROUP, TRAINNUMBER, "
					+ " CARRIAGENUMBER, NULL z1, NULL z2, NULL z3, z9 z4, NULL z5, NULL z6, NULL z7, "
					+ " NULL z8, warntemp z9, 0 z12, 0 z34, 0 z56, 0 z78, 0 z13, 0 z24, "
					+ " 0 z57, 0 z68, RECORDTIME, READTIME, '1' COMDISCONNECT, '1' OPENWAY, '1' DISWAY, NULL LOWTEMPERATURE, "
					+ " NULL HIGHTEMPERATURE, NULL EARLYWARN, '0' zx1, '0' zx2, '0' zx3, '0' zx4, '0' zx5, '0' zx6, "
					+ " '0' zx7, '0' zx8, '0' zx9, '0' ws1, '0' ws2, '0' ws3, '0' ws4, '0' ws5, "
					+ " '0' ws6, '0' ws7, '0' ws8, '0' ws9, '0' wensheng, 0 rownumx, NULL td1, NULL td2, "
					+ " NULL td3, NULL td4, NULL td5, NULL td6, NULL td7, NULL td8, NULL tdcz1, NULL tdcz2, "
					+ " NULL tdcz3, NULL tdcz4, NULL tdcz5, NULL tdcz6, NULL tdcz7, NULL tdcz8, NULL SESSIONTIMELONG, NULL gw1, "
					+ " NULL gw2, NULL gw3, NULL gw4, NULL gw5, NULL gw6, NULL gw7, NULL gw8 "
					+ "  FROM   tb_warn " + sqlWhere
					+ " AND zz = 4 "
					+ "UNION ALL "
					+ "SELECT   'warn' SESSIONID, SYSDATE SESSIONTIME, id, USERID,  ROADSECTION, SHOP, TRAINGROUP, TRAINNUMBER, "
					+ " CARRIAGENUMBER, NULL z1, NULL z2, NULL z3, NULL z4, z9 z5, NULL z6, NULL z7, "
					+ " NULL z8, warntemp z9, 0 z12, 0 z34, 0 z56, 0 z78, 0 z13, 0 z24, "
					+ " 0 z57, 0 z68, RECORDTIME, READTIME, '1' COMDISCONNECT, '1' OPENWAY, '1' DISWAY, NULL LOWTEMPERATURE, "
					+ " NULL HIGHTEMPERATURE, NULL EARLYWARN, '0' zx1, '0' zx2, '0' zx3, '0' zx4, '0' zx5, '0' zx6, "
					+ " '0' zx7, '0' zx8, '0' zx9, '0' ws1, '0' ws2, '0' ws3, '0' ws4, '0' ws5, "
					+ " '0' ws6, '0' ws7, '0' ws8, '0' ws9, '0' wensheng, 0 rownumx, NULL td1, NULL td2, "
					+ " NULL td3, NULL td4, NULL td5, NULL td6, NULL td7, NULL td8, NULL tdcz1, NULL tdcz2, "
					+ " NULL tdcz3, NULL tdcz4, NULL tdcz5, NULL tdcz6, NULL tdcz7, NULL tdcz8, NULL SESSIONTIMELONG, NULL gw1, "
					+ " NULL gw2, NULL gw3, NULL gw4, NULL gw5, NULL gw6, NULL gw7, NULL gw8 "
					+ "  FROM   tb_warn " + sqlWhere
					+ " AND zz = 5 "
					+ "UNION ALL "
					+ "SELECT   'warn' SESSIONID, SYSDATE SESSIONTIME, id, USERID,  ROADSECTION, SHOP, TRAINGROUP, TRAINNUMBER, "
					+ " CARRIAGENUMBER, NULL z1, NULL z2, NULL z3, NULL z4, NULL z5, z9 z6, NULL z7, "
					+ " NULL z8, warntemp z9, 0 z12, 0 z34, 0 z56, 0 z78, 0 z13, 0 z24, "
					+ " 0 z57, 0 z68, RECORDTIME, READTIME, '1' COMDISCONNECT, '1' OPENWAY, '1' DISWAY, NULL LOWTEMPERATURE, "
					+ " NULL HIGHTEMPERATURE, NULL EARLYWARN, '0' zx1, '0' zx2, '0' zx3, '0' zx4, '0' zx5, '0' zx6, "
					+ " '0' zx7, '0' zx8, '0' zx9, '0' ws1, '0' ws2, '0' ws3, '0' ws4, '0' ws5, "
					+ " '0' ws6, '0' ws7, '0' ws8, '0' ws9, '0' wensheng, 0 rownumx, NULL td1, NULL td2, "
					+ " NULL td3, NULL td4, NULL td5, NULL td6, NULL td7, NULL td8, NULL tdcz1, NULL tdcz2, "
					+ " NULL tdcz3, NULL tdcz4, NULL tdcz5, NULL tdcz6, NULL tdcz7, NULL tdcz8, NULL SESSIONTIMELONG, NULL gw1, "
					+ " NULL gw2, NULL gw3, NULL gw4, NULL gw5, NULL gw6, NULL gw7, NULL gw8 "
					+ "  FROM   tb_warn " + sqlWhere
					+ " AND zz = 6 "
					+ "UNION ALL "
					+ "SELECT   'warn' SESSIONID, SYSDATE SESSIONTIME, id, USERID, ROADSECTION, SHOP, TRAINGROUP, TRAINNUMBER, "
					+ " CARRIAGENUMBER, NULL z1, NULL z2, NULL z3, NULL z4, NULL z5, NULL z6, z9 z7, "
					+ " NULL z8, warntemp z9, 0 z12, 0 z34, 0 z56, 0 z78, 0 z13, 0 z24, "
					+ " 0 z57, 0 z68, RECORDTIME, READTIME, '1' COMDISCONNECT, '1' OPENWAY, '1' DISWAY, NULL LOWTEMPERATURE, "
					+ " NULL HIGHTEMPERATURE, NULL EARLYWARN, '0' zx1, '0' zx2, '0' zx3, '0' zx4, '0' zx5, '0' zx6, "
					+ " '0' zx7, '0' zx8, '0' zx9, '0' ws1, '0' ws2, '0' ws3, '0' ws4, '0' ws5, "
					+ " '0' ws6, '0' ws7, '0' ws8, '0' ws9, '0' wensheng, 0 rownumx, NULL td1, NULL td2, "
					+ " NULL td3, NULL td4, NULL td5, NULL td6, NULL td7, NULL td8, NULL tdcz1, NULL tdcz2, "
					+ " NULL tdcz3, NULL tdcz4, NULL tdcz5, NULL tdcz6, NULL tdcz7, NULL tdcz8, NULL SESSIONTIMELONG, NULL gw1, "
					+ " NULL gw2, NULL gw3, NULL gw4, NULL gw5, NULL gw6, NULL gw7, NULL gw8 "
					+ "  FROM   tb_warn " + sqlWhere
					+ " AND zz = 7 "
					+ "UNION ALL "
					+ "SELECT   'warn' SESSIONID, SYSDATE SESSIONTIME, id, USERID, ROADSECTION, SHOP, TRAINGROUP, TRAINNUMBER, "
					+ " CARRIAGENUMBER, NULL z1, NULL z2, NULL z3, NULL z4, NULL z5, NULL z6, NULL z7, "
					+ " z9 z8, warntemp z9, 0 z12, 0 z34, 0 z56, 0 z78, 0 z13, 0 z24, "
					+ " 0 z57, 0 z68, RECORDTIME, READTIME, '1' COMDISCONNECT, '1' OPENWAY, '1' DISWAY, NULL LOWTEMPERATURE, "
					+ " NULL HIGHTEMPERATURE, NULL EARLYWARN, '0' zx1, '0' zx2, '0' zx3, '0' zx4, '0' zx5, '0' zx6, "
					+ " '0' zx7, '0' zx8, '0' zx9, '0' ws1, '0' ws2, '0' ws3, '0' ws4, '0' ws5, "
					+ " '0' ws6, '0' ws7, '0' ws8, '0' ws9, '0' wensheng, 0 rownumx, NULL td1, NULL td2, "
					+ " NULL td3, NULL td4, NULL td5, NULL td6, NULL td7, NULL td8, NULL tdcz1, NULL tdcz2, "
					+ " NULL tdcz3, NULL tdcz4, NULL tdcz5, NULL tdcz6, NULL tdcz7, NULL tdcz8, NULL SESSIONTIMELONG, NULL gw1, "
					+ " NULL gw2, NULL gw3, NULL gw4, NULL gw5, NULL gw6, NULL gw7, NULL gw8 "
					+ "  FROM   tb_warn " + sqlWhere
					+ " AND zz = 8 ";
			sql = " select aa.*, rownum rownuma from(select a.* from("
					+ " select SESSIONID,SESSIONTIME,ID,USERID,ROADSECTION,SHOP,TRAINGROUP,TRAINNUMBER,CARRIAGENUMBER,"
					+ "Z1,Z2,Z3,Z4,Z5,Z6,Z7,Z8,Z9,Z12,Z34,Z56,Z78,Z13,Z24,Z57,Z68,"
					+ "RECORDTIME,READTIME,COMDISCONNECT,OPENWAY,DISWAY,LOWTEMPERATURE,HIGHTEMPERATURE,EARLYWARN,"
					+ "ZX1,ZX2,ZX3,ZX4,ZX5,ZX6,ZX7,ZX8,ZX9,WS1,WS2,WS3,WS4,WS5,WS6,WS7,WS8,WS9,"
					+ "WENSHENG,ROWNUMX,TD1,TD2,TD3,TD4,TD5,TD6,TD7,TD8,TDCZ1,TDCZ2,TDCZ3,TDCZ4,TDCZ5,TDCZ6,TDCZ7,TDCZ8,"
					+ "SESSIONTIMELONG,GW1,GW2,GW3,GW4,GW5,GW6,GW7,GW8 from tb_temperature_search_tmp "
					+ sqlWhere + sqlOthers + sqlSessionid
					+ ("true".equals(dotrshowWarn) ? " union all " + sqlWarn : "")
					+ " ) a"
					+ " ORDER BY   recordtime, traingroup, TO_NUMBER( carriagenumber ), trainnumber) aa"
					+ " order by rownuma";
		}
		else
		{
			sql = " SELECT * FROM ( select a.*, rownum rownuma from("
					+ " select SESSIONID,SESSIONTIME,ID,USERID,ROADSECTION,SHOP,TRAINGROUP,TRAINNUMBER,CARRIAGENUMBER,"
					+ "Z1,Z2,Z3,Z4,Z5,Z6,Z7,Z8,Z9,Z12,Z34,Z56,Z78,Z13,Z24,Z57,Z68,"
					+ "RECORDTIME,READTIME,COMDISCONNECT,OPENWAY,DISWAY,LOWTEMPERATURE,HIGHTEMPERATURE,EARLYWARN,"
					+ "ZX1,ZX2,ZX3,ZX4,ZX5,ZX6,ZX7,ZX8,ZX9,WS1,WS2,WS3,WS4,WS5,WS6,WS7,WS8,WS9,"
					+ "WENSHENG,ROWNUMX,TD1,TD2,TD3,TD4,TD5,TD6,TD7,TD8,TDCZ1,TDCZ2,TDCZ3,TDCZ4,TDCZ5,TDCZ6,TDCZ7,TDCZ8,"
					+ "SESSIONTIMELONG,GW1,GW2,GW3,GW4,GW5,GW6,GW7,GW8 from tb_temperature_search_tmp "
					+ sqlWhere + sqlOthers + sqlSessionid + sqlOrderby2
					+ " ) a) WHERE rownuma >" + ((indexOfPage - 1) * numPerPage)
					+ "   AND rownuma <=" + (indexOfPage * numPerPage)
					+ " order by rownuma";
		}
		if (search || kmisSearch)
			list = tmpService.getFieldsBySql(sql);
		//
		if (!search && !export && !kmisSearch)
		{
			String form = "<style>"
					+ ".STYLE1 {color: #FF0000}"
					+ ".STYLE2 {color: #0000FF}"
					+ "</style>"
					+ ""
					+ "		 	<table width=\"100%\">"
					+ "			  "
					+ "			  <tr>"
					+ "				<td><div class=\"tags_bg\" style=\"margin-bottom:0px;\">"
					+ "					<div class=\"tags_but tags_ac\" onClick=\"get('TbTemperatureAction!initCcsjPage.action')\">轴温</div>"
					+ (isChukuRead ? "" : "					<div class=\"tags_but\" onClick=\"defbjsj();\">报警</div>")
					+ "					</div> </td>"
					+ "			  </tr>"
					+ "			</table>"
					+ "			<form action=\"TbTemperatureAction!initCcsjPage.action?1=1\" method=\"post\" name=\"search\" id=\"search\">"
					+ "			<table width=100% bgcolor=\"#BBD6E9\">"
					+ "			  <tr>"
					+ "				<td height=\"46\" colspan=\"2\" valign=\"bottom\">"
					+ "				<div class=\"search_bar\"><div class='nowrap'><table height=46 >"
					+ "                  <tr>"
					+ "                    <td align=\"right\">"
					+ "					&nbsp;<select id=\"roadsection\" name=\"roadsection\" onchange='getShop()'>"
					+ ((String)request.getSession().getAttribute(ConstUtil.TRAINMASTER_INFO)).replaceAll(
							"value='" + nullString(roadsectionPar) + "'",
							"value='" + nullString(roadsectionPar) + "' selected ")
					+ "                    </select><span id=shopdiv><select id=\"shop\" name=\"shop\" onchange='getTraingroup()'>"
					+ request.getSession().getAttribute(ConstUtil.SHOP_INFO)
					+ "				    </select></span><span id=traingroupdiv><select id=\"traingroup\" name=\"traingroup\" onchange='clearTrainnumber();getLastTimeByTraingroup(1);clearRegionSetting();'>"
					+ ((String) request.getSession().getAttribute(ConstUtil.GROUP_INFO)).replaceAll(
							"value='" + nullString(traingroupPar) + "'",
							"value='" + nullString(traingroupPar) + "' selected ")
					+ "                    </select></span>"
					//+ "                      <input name=\"trainnumber\" id=\"trainnumber\" value='"+trainnumberPar+"' size=10 onchange='if(this.value==\"\"){getLastTimeByTraingroup(1);}else{getLastTimeByTrainnumber(1);}' onkeyup=\"inputCheHao(id)\" onclick=\"showOptions(id)\" onblur=\"setHideOptions(id)\" />"
					+ "                      <input name=\"trainnumber\" id=\"trainnumber\" value=\""+trainnumberPar+"\" size=10 onchange='if($(\"traingroup\").value!=\"\"){getLastTimeByTraingroup();}else{getLastTimeByTrainnumber();}' onkeyup=\"inputCheHao(id)\" onkeydown=\"return chexiang(this)\" class=\"input_bianzu\" onclick=\"showOptions(id)\" onblur=\"setHideOptions(id)\" />"
					+ "                      <span id=\"trainnumberOptionsSpan\" style=\"position : absolute; visibility : hidden;\"></span>"
					+ "  <div id='defaulttrainnumberdiv'><input type=hidden id='defaulttrainnumber' value='getLastTimeByTrainnumber(2);'/></div>"
					+ "  <div id=defaulttraingroupdiv><input type=hidden id=defaulttraingroup value='getLastTimeByTraingroup(2);'/></div>"
					+ "  <div id='gotoroadsectiondiv'><input type=hidden id='gotoroadsection' value='" + roadsectionPar + "'/></div>"
					+ "  <div id='gotoshopdiv'><input type=hidden id='gotoshop' value='" + shopPar + "'/></div>"
					+ "  <div id='gototraingroupPar_tempdiv'><input type=hidden id='gototraingroupPar_temp' value='" + traingroupPar + "'/></div>"
					+ "                    </td>"
					+ "                    <td>&nbsp;起始时间:"
					+ "                      <input id=\"startTime\" name=\"startTime\" value=\""+startTime_temp+"\" class=\"timeinput\" onclick=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})\" oninput=\"clearRegionSetting();\" onpropertychange=\"clearRegionSetting();\"/>"
					+ "                    结束时间:"
					+ "                    <input id=\"endTime\" name=\"endTime\" value=\""+endTime_temp+"\"  class=\"timeinput\" onclick=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})\"/>"
					+ (isChukuRead ? "" : "				  <div class=\"height_search\" onclick=\"height_search()\">高级条件</div>")
					+ "               <div class=\"button\" id=searchButton  onclick=\"doSearch(1);\"><div><div style=\"background-image:url(images/search_ico.gif)\">查询</div></div></div>"
					+ "				  </td><td>&nbsp;"
					+ (isChukuRead ? "" : "				  <div class=\"button\" onclick=\"if(valiChehao())hhh();\"><div><div style=\"background-image:url(images/quxian_ico.gif)\">曲线图</div></div></div>")
					+ (isChukuRead ? "" : "				  &nbsp;<div class=\"button\"><div onclick=\"doExport();\"><div style=\"background-image:url(images/export_ico.gif)\">导出</div></div></div> ")
					+ "				  <input type='hidden' id='dowhat' name='dowhat' value='search' />"
					+ "				  <input type='hidden' id='indexOfPage' name='indexOfPage' value='1' />"
					+ "				  <input type='hidden' id='regionstarttime' name='regionstarttime' value='"+ regionstarttime +"' />"
					+ "				  <input type='hidden' id='regionendtime' name='regionendtime' value='"+ regionendtime +"' />"
					+ "				  <input type='hidden' id='regionqian' name='regionqian' value='' />"
					+ "				  <input type='hidden' id='regionhou' name='regionhou' value='' />"
					+ "				  <input type='hidden' id='readType' name='readType' value='" + (isChukuRead ? "1" : "") + "' />"
					+ "					</td>"
					+ "                  </tr>"
					+ "                </table></div></div></td>"
					+ "			  </tr>"
					+ "			  <tr><td>"
					+ "			  <div class=\"search_bar2\" id=\"search_bar2\" style='display:none;'><table width=\"100%\" height=57><tr>"
					+ "			    <td style='padding-left:30px;'>"
					+ "				<table width='90%'>"
					+ "				 <tr>"
					+ "					<td> 同轴温差：<input name=\"coaxialstr\" value=\"" + coaxial + "\" style=\"width:20px;\"/>℃　</td>"
					+ (ConfigUtil.isGuangZhouJu() ? "					<td> 同侧温差：<input name=\"ipsilateralstr\" value=\"" + ipsilateral + "\" style=\"width:20px;\"/>℃　</td>" : "")
					+ (ConfigUtil.isShengYangJu() ? "					<td> 梯度值：<input name=\"gradientstr\" value=\"" + gradient + "\" style=\"width:20px;\" />℃　</td>" : "")
					+ "					<td> 低温异常：<input name=\"diwenstr\" value=\"" + lowtemperatureValue + "\" style=\"width:20px;\" ";if(ConfigUtil.isGuangZhouJu()) form += "onchange='doSearch(1);'"; form +=" />℃　</td>"
					+ "					<td> 预警：<input name=\"earlyWarningstr\" value=\"" + earlyWarning + "\" style=\"width:20px;\" ";if(ConfigUtil.isGuangZhouJu()) form += "onchange='doSearch(1);'"; form +=" />℃　</td>"
					+ "					<td width=100>&nbsp;</td>"
					+ "					<td><input type=\"checkbox\" id=\"disconn\" name=\"disconn\" value=\"1\" ";if(ConfigUtil.isShengYangJu() && null != openway) form += "checked";else if(ConfigUtil.isGuangZhouJu())  form +="onclick='doSearch(1);'"; form += "/>通讯断</td>"
					+ "					<td><input type=\"checkbox\" id=\"openway\" name=\"openway\" value=\"1\" ";if(ConfigUtil.isShengYangJu() && null != openway) form += "checked";else if(ConfigUtil.isGuangZhouJu())  form +="onclick='doSearch(1);'"; form += "/>开路</td>"
					+ "					<td><input type=\"checkbox\" id=\"disway\" name=\"disway\" value=\"1\"  ";if(ConfigUtil.isShengYangJu() && null != disway) form += "checked";else  if(ConfigUtil.isGuangZhouJu()) form +="onclick='doSearch(1);'"; form += "/>短路</td>"
					+ "					<td><input type=\"checkbox\" id=\"diwen\" name=\"diwen\" value=\"1\"  ";if(ConfigUtil.isShengYangJu() && null != diwen) form += "checked";else  if(ConfigUtil.isGuangZhouJu()) form +="onclick='doSearch(1);'"; form += "/>低温异常</td>"
					+ (ConfigUtil.isShengYangJu() ? "					<td><input type=\"checkbox\" id=\"gaowen\" name=\"gaowen\" value=\"1\"  "+ (null == gaowen?"" : "checked") + "/>高温异常</td>" : "")
					+ (ConfigUtil.isShengYangJu() ? "					<td>　　</td>" : "")
					+ (ConfigUtil.isShengYangJu() ? "					<td><input type=\"checkbox\" id=\"validTime\" name=\"validTime\" value=\"1\" onclick=\"timeshow(this)\" "+((!"".equals(validTime))? " checked":"")+"/><label for=validTime style=\"cursor:pointer\">有效区间</label></td>" : "")
					+ (ConfigUtil.isShengYangJu() ? "				 </tr><tr>" : "")
					+ (ConfigUtil.isShengYangJu() ? "					<td> 同侧温差：<input name=\"ipsilateralstr\" value=\"" + ipsilateral + "\" style=\"width:20px;\"/>℃　</td>" : "")
					+ (ConfigUtil.isShengYangJu() ? "					<td> 时间段：<input name=\"gaptimestr\" value=\"" + gaptime + "\" style=\"width:20px;\"/>分钟</td>" : "")
					+ (ConfigUtil.isShengYangJu() ? "					<td> 高温异常：<input name=\"gaowenstr\" value=\"" + hightemperatureValue + "\" style=\"width:20px;\"/>℃　</td>" : "");
					if (ConfigUtil.isShengYangJu())
					{
						form += "					<td> 轴位：<select name='axle' id='axle'><option value=''>全部</option>" ;
						for(int i = 1; i <=9; i++ )
						{
							form += "<option value='" + i + "'" ;
							if(axlePar.equals(String.valueOf(i))) form += " selected";
							if (9 == i)
								form += ">环温</option>";
							else
								form += ">" + i + "</option>";
						}
						form += "</select></td>" ;
					}
					form += (ConfigUtil.isShengYangJu() ? "					<td>　</td>" : "")
					+ "					<td><input type=\"checkbox\" id=\"yujing\" name=\"yujing\" value=\"1\"  ";if(ConfigUtil.isShengYangJu() && null != yujing) form += "checked";else if(ConfigUtil.isGuangZhouJu()) form +="onclick='doSearch(1);'"; form += "/>预警</td>"
					+ (ConfigUtil.isShengYangJu() ? "					<td><input type=\"checkbox\" id=\"wensheng\" name=\"wensheng\" value=\"1\" "+ (null == wensheng?"" : "checked") + "/>温升趋势</td>" : "")
					+ "					<td><input type=\"checkbox\" id=\"tongzhou\" name=\"tongzhou\" value=\"1\" "+ (null == tongzhou?"" : "checked") + (ConfigUtil.isGuangZhouJu() ? "onclick='doSearch(1);'" : "") + "/>同轴温差</td>"
					+ (ConfigUtil.isGuangZhouJu() ? "				<td><input type=\"checkbox\" id=\"tongceGZ\" name=\"tongceGZ\" value=\"1\" onclick='doSearch(1);' "+ (null == tongce?"" : "checked") + "/>同侧温差</td>" : "")
					+ (ConfigUtil.isShengYangJu() ? "					<td><input type=\"checkbox\" id=\"tongce\" name=\"tongce\" value=\"1\" "+ (null == tongce?"" : "checked") + "/>同侧温差</td>" : "")
					+ (ConfigUtil.isShengYangJu() ? "					<td><input type=\"checkbox\" id=\"selectAllCheckbox\" onclick='selectAllPar()' />全选</td>" : "")
					+ "					<td>&nbsp;</td>"
					+ (ConfigUtil.isShengYangJu() ? "					<td><input type=\"checkbox\" name=\"tongzhouyu\" value=\"1\" />条件并存</td>" : "")
					+ (ConfigUtil.isShengYangJu() ? "					<td><input type=\"hidden\" name=\"dotr\" id=\"dotr\" value=\"\" /></td>" : "")//点击某行设置时间
					+ (ConfigUtil.isShengYangJu() ? "					<td><input type=\"hidden\" name=\"dotrcxh\" id=\"dotrcxh\" value=\"\" /></td>" : "")//点击某行设置时间
					+ (ConfigUtil.isShengYangJu() ? "					<td><input type=\"hidden\" name=\"dotrczch\" id=\"dotrczch\" value=\"\" /></td>" : "")//点击某行设置时间
					+ (ConfigUtil.isShengYangJu() ? "					<td><input type=\"hidden\" name=\"dotrsjd\" id=\"dotrsjd\" value=\"\" /></td>" : "")//点击某行设置时间
					+ (ConfigUtil.isShengYangJu() ? "					<td><input type=\"hidden\" name=\"dotrb\" id=\"dotrb\" value=\"\" /></td>" : "")//点击某行设置时间
					+ (ConfigUtil.isShengYangJu() ? "					<td><input type=\"hidden\" name=\"dotrcxhb\" id=\"dotrcxhb\" value=\"\" /></td>" : "")//点击某行设置时间
					+ (ConfigUtil.isShengYangJu() ? "					<td><input type=\"hidden\" name=\"dotrczchb\" id=\"dotrczchb\" value=\"\" /></td>" : "")//点击某行设置时间
					+ (ConfigUtil.isShengYangJu() ? "					<td><input type=\"hidden\" name=\"dotrsjdb\" id=\"dotrsjdb\" value=\"\" /></td>" : "")//点击某行设置时间
					+ (!ConfigUtil.isShengYangJu() ? "					<td><input type=\"checkbox\" id=\"validTime\" name=\"validTime\" value=\"1\" onclick=\"timeshow(this)\" "+((!"".equals(validTime))? " checked":"")+"/><label for=validTime style=\"cursor:pointer\">有效区间</label></td>" : "")
					+ "			    </tr>"
					+ "			  </table>			  </td></tr>"
					+ "			  </table></div>				</td>"
					+ "		      </tr>"
					+ "			</table>"
					+ "			</form>"
					+ "			<div onclick=\"$('search').style.display=$('search').style.display=='none'?'':'none'\" class=\"sh_bar\">▲</div>"
					+ "<div id=\"result\"></div>";
			out(form);
			return;
		}

		String result = "<div class=\"search_t\">"
				+ searchParStr
//				+ "<span class='ws0'>█正常</span>  "
//				+ "<span class='ws1'>█开路/短路/低温"
//				+ (!ConfigUtil.isShengYangJu() ? "" :
//					"/高温"
//				)
//				+ "</span>  "
//				+ "<span class='ws2'>█预警</span>  "
//				+ (!ConfigUtil.isShengYangJu() ? "" :
//					"<span class='ws4'>█同轴</span> "
//				)
//				+ (!ConfigUtil.isShengYangJu() ? "" :
//					"<span class='ws5'>█同侧</span> "
//				)
//				+ (!ConfigUtil.isShengYangJu() ? "" :
//					"<span class='ws6'>█温升</span> "
//				)
//				+ (!ConfigUtil.isShengYangJu() ? "" :
//					"<span class='ws3'>█多个故障</span> "
//				)
				
				+ (!showDataInShijianduan ? "" :
					"<div align=right>时间范围：<input id=showDataShijianduan value='" + dotrsjd + "' size=3>分钟 "
					+ "<input type=button value='刷新' onclick=\"dotr($('dotrb').value, $('dotrcxhb').value, $('dotrczchb').value, $('showDataShijianduan').value);\">"
				)
				+ "           </div>"
				+ "			<div class='all'><div class='titlediv'  " ;
		if(showDataInShijianduan){
				result += "style='width:1200px;'" ;
		}
				result += "><div class='title_left'><table width=\"100%\" class='main_table'>"
				+ "			  <tr>"
				+ "				<th rel=tt id=th1  " + (ConfigUtil.isGuangZhouJu() ? " width=30" : "") + ">序号</th>"
				+ "				<th rel=tt id=th1 width=120>日期</th>"
				+ "				<th rel=tt id=th2 " + (ConfigUtil.isGuangZhouJu() ? " width=35" : "") + ">车次</th>"
				+ "				<th rel=tt id=th3 " + (ConfigUtil.isGuangZhouJu() ? " width=30" : "") + ">车组</th>"
				+ "				<th rel=tt id=th4 width='40'>车厢号</th>"
				+ "				<th rel=tt id=th5  width=60>车种车号</th>"
				+ "				<th rel=tt id=th6 >1号</th>"
				+ "				<th rel=tt id=th7 >2号</th>"
				+ "				<th rel=tt id=th8 >3号</th>"
				+ "				<th rel=tt id=th9 >4号</th>"
				+ "				<th rel=tt id=th10>5号</th>"
				+ "				<th rel=tt id=th11>6号</th>"
				+ "				<th rel=tt id=th12 >7号</th>"
				+ "				<th rel=tt id=th13 >8号</th>"
				+ "				<th rel=tt id=th14 >环温</th>"
				+ (ConfigUtil.isShengYangJu() ? "			    <th rel=tt id=th15 ><span style=\"font-weight:100\">预警</span></th>"
				+ "			    <th rel=tt id=th16 ><span style=\"font-weight:100\">同侧</span></th>"
				+ "			    <th rel=tt id=th17 ><span style=\"font-weight:100\">同轴</span></th>"
				+ "			    <th rel=tt id=th18 ><span style=\"font-weight:100\">低温</span></th>"
				+ "			    <th rel=tt id=th19 ><span style=\"font-weight:100\">高温</span></th>"
				+ "			    <th rel=tt id=th20 ><span style=\"font-weight:100\">温升</span></th>" : "")
				//+ (ConfigUtil.isShengYangJu() ? "	<th rel=tt id=th21 ></th>":"    <th id=th15 width=6 ></th>")
				+ "			  </tr></table></div></div><div class='content' id='zhouwenDataDiv'><div class='content_left'><table width=\"100%\" class=\"main_table\">";
		if (search || kmisSearch)
			if (null != list)
				for (int i = 0; i < list.size(); i++) {
					try {
						item = (Object[]) list.get(i);

						String sessionidpo = nullString((String) item[0]); // sessionid,报警的sessionid值为warn
						String roadsection = nullString((String) item[(3 + 1)]); // 路段
						String shop = nullString((String) item[(4 + 1)]); // 车间
						String traingroup = nullString((String) item[(5 + 1)]); // 车次-组
						String trainnumber = nullString((String) item[(6 + 1)]); // 车种车号
						String carriagenumber = nullString((String) item[(7 + 1)]); // 车厢号
						int z1 = 0; // 轴1
						String z1Display = " ";
						int z2 = 0; // 轴2
						String z2Display = " ";
						int z3 = 0; // 轴3
						String z3Display = " ";
						int z4 = 0; // 轴4
						String z4Display = " ";
						int z5 = 0; // 轴5
						String z5Display = " ";
						int z6 = 0; // 轴6
						String z6Display = " ";
						int z7 = 0; // 轴7
						String z7Display = " ";
						int z8 = 0; // 轴8
						String z8Display = " ";
						int z9 = 0; // 环温
						String z9Display = " ";
						int z12 = 0; // 轴12
						String z12Display = " ";
						int z34 = 0; // 轴34
						String z34Display = " ";
						int z56 = 0; // 轴56
						String z56Display = " ";
						int z78 = 0; // 轴78
						String z78Display = " ";
						int z13 = 0; // 1-3轴温差(同侧)
						String z13Display = " ";
						int z24 = 0; // 2-4轴温差(同侧)
						String z24Display = " ";
						int z57 = 0; // 5-7轴温差(同侧)
						String z57Display = " ";
						int z68 = 0; // 6-8轴温差(同侧)
						String z68Display = " ";
						long recordtime = 0l;
						long readtime = 0l;
						String comdisconnect = (String) item[(27 + 1)]; // 通讯断
						String openway = (String) item[(28 + 1)]; // 开路
						String disway = (String) item[(29 + 1)]; // 短路
						String lowtemperature = (String) item[(30 + 1)]; // 低温异常
						String hightemperature = (String) item[(31 + 1)]; // 高温异常
						String earlywarn = (String) item[(32 + 1)]; // 预警
						
						String zx1 = (String) item[(33 + 1)]; // 温升1
																// 如果大于上一条，则值为1，否则为0
						String zx2 = (String) item[(34 + 1)]; // 温升2
																// 如果大于上一条，则值为1，否则为0
						String zx3 = (String) item[(35 + 1)]; // 温升3
																// 如果大于上一条，则值为1，否则为0
						String zx4 = (String) item[(36 + 1)]; // 温升4
																// 如果大于上一条，则值为1，否则为0
						String zx5 = (String) item[(37 + 1)]; // 温升5
																// 如果大于上一条，则值为1，否则为0
						String zx6 = (String) item[(38 + 1)]; // 温升6
																// 如果大于上一条，则值为1，否则为0
						String zx7 = (String) item[(39 + 1)]; // 温升7
																// 如果大于上一条，则值为1，否则为0
						String zx8 = (String) item[(40 + 1)]; // 温升8
																// 如果大于上一条，则值为1，否则为0
						String zx9 = (String) item[(41 + 1)]; // 环温
																// 如果大于上一条，则值为1，否则为0
						String ws1 = (String) item[(42 + 1)]; // 温升1
																// 如果符合温升条件，则值为1，否则为0
						String ws2 = (String) item[(43 + 1)]; // 温升2
																// 如果符合温升条件，则值为1，否则为0
						String ws3 = (String) item[(44 + 1)]; // 温升3
																// 如果符合温升条件，则值为1，否则为0
						String ws4 = (String) item[(45 + 1)]; // 温升4
																// 如果符合温升条件，则值为1，否则为0
						String ws5 = (String) item[(46 + 1)]; // 温升5
																// 如果符合温升条件，则值为1，否则为0
						String ws6 = (String) item[(47 + 1)]; // 温升6
																// 如果符合温升条件，则值为1，否则为0
						String ws7 = (String) item[(48 + 1)]; // 温升7
																// 如果符合温升条件，则值为1，否则为0
						String ws8 = (String) item[(49 + 1)]; // 温升8
																// 如果符合温升条件，则值为1，否则为0
						String ws9 = (String) item[(50 + 1)]; // 环温
																// 如果符合温升条件，则值为1，否则为0
						String wensheng = (String) item[(51 + 1)]; // 温升
																	// 如果1到8温升全部符合
																	// 则值为1否则为0
						String gw1 = String.valueOf((BigDecimal) item[(70 + 1)]); // 高温异常1
						String gw2 = String.valueOf((BigDecimal) item[(71 + 1)]); // 高温异常1
						String gw3 = String.valueOf((BigDecimal) item[(72 + 1)]); // 高温异常1
						String gw4 = String.valueOf((BigDecimal) item[(73 + 1)]); // 高温异常1
						String gw5 = String.valueOf((BigDecimal) item[(74 + 1)]); // 高温异常1
						String gw6 = String.valueOf((BigDecimal) item[(75 + 1)]); // 高温异常1
						String gw7 = String.valueOf((BigDecimal) item[(76 + 1)]); // 高温异常1
						String gw8 = String.valueOf((BigDecimal) item[(77 + 1)]); // 高温异常1
						
						try {
							recordtime = ((BigDecimal) item[(25 + 1)])
									.longValue(); // 记录时间
							readtime = ((BigDecimal) item[(26 + 1)])
									.longValue(); // 读卡时间
							if (null != item[(8 + 1)]) {
								z1 = ((BigDecimal) item[(8 + 1)]).intValue();
								z1Display = getLabel(z1);
							}
							if (null != item[(9 + 1)]) {
								z2 = ((BigDecimal) item[(9 + 1)]).intValue();
								z2Display = getLabel(z2);
							}
							if (null != item[(10 + 1)]) {
								z3 = ((BigDecimal) item[(10 + 1)]).intValue();
								z3Display = getLabel(z3);
							}
							if (null != item[(11 + 1)]) {
								z4 = ((BigDecimal) item[(11 + 1)]).intValue();
								z4Display = getLabel(z4);
							}
							if (null != item[(12 + 1)]) {
								z5 = ((BigDecimal) item[(12 + 1)]).intValue();
								z5Display = getLabel(z5);
							}
							if (null != item[(13 + 1)]) {
								z6 = ((BigDecimal) item[(13 + 1)]).intValue();
								z6Display = getLabel(z6);
							}
							if (null != item[(14 + 1)]) {
								z7 = ((BigDecimal) item[(14 + 1)]).intValue();
								z7Display = getLabel(z7);
							}
							if (null != item[(15 + 1)]) {
								z8 = ((BigDecimal) item[(15 + 1)]).intValue();
								z8Display = getLabel(z8);
							}
							if (null != item[(16 + 1)]) {
								z9 = ((BigDecimal) item[(16 + 1)]).intValue();
								z9Display = getLabel(z9);
							}
							if (null != item[(17 + 1)]) {
								z12 = ((BigDecimal) item[(17 + 1)]).intValue();
								z12Display = getLabel(z12);
							}
							if (null != item[(18 + 1)]) {
								z34 = ((BigDecimal) item[(18 + 1)]).intValue();
								z34Display = getLabel(z34);
							}
							if (null != item[(19 + 1)]) {
								z56 = ((BigDecimal) item[(19 + 1)]).intValue();
								z56Display = getLabel(z56);
							}
							if (null != item[(20 + 1)]) {
								z78 = ((BigDecimal) item[(20 + 1)]).intValue();
								z78Display = getLabel(z78);
							}
							if (null != item[(21 + 1)]) {
								z13 = ((BigDecimal) item[(21 + 1)]).intValue();
								z13Display = getLabel(z13);
							}
							if (null != item[(22 + 1)]) {
								z24 = ((BigDecimal) item[(22 + 1)]).intValue();
								z24Display = getLabel(z24);
							}
							if (null != item[(23 + 1)]) {
								z57 = ((BigDecimal) item[(23 + 1)]).intValue();
								z57Display = getLabel(z57);
							}
							if (null != item[(24 + 1)]) {
								z68 = ((BigDecimal) item[(24 + 1)]).intValue();
								z68Display = getLabel(z68);
							}
						} catch (Exception e) {
						}

						// 车次与车组保是存在一个字段里的，用-分隔，如果没取到，用这个默认值
						String checi = " ";
						String chezu = " ";
						try {
							String trainGroup[] = traingroup.split("-");
							checi = trainGroup[0];
							chezu = trainGroup[1];
						} catch (Exception e) {
						}

						boolean vt1 = isValidTemp(z1, z1Display);
						boolean vt2 = isValidTemp(z2, z2Display);
						boolean vt3 = isValidTemp(z3, z3Display);
						boolean vt4 = isValidTemp(z4, z4Display);
						boolean vt5 = isValidTemp(z5, z5Display);
						boolean vt6 = isValidTemp(z6, z6Display);
						boolean vt7 = isValidTemp(z7, z7Display);
						boolean vt8 = isValidTemp(z8, z8Display);
						boolean vt9 = isValidTemp(z9, z9Display);
						boolean jingDisplay = false;
						boolean jing1 = false;
						boolean jing2 = false;
						boolean jing3 = false;
						boolean jing4 = false;
						boolean jing5 = false;
						boolean jing6 = false;
						boolean jing7 = false;
						boolean jing8 = false;
						boolean tongceDisplay = false;
						boolean tc1 = false;
						boolean tc2 = false;
						boolean tc3 = false;
						boolean tc4 = false;
						boolean tc5 = false;
						boolean tc6 = false;
						boolean tc7 = false;
						boolean tc8 = false;
						boolean tongzhouDisplay = false;
						boolean tz1 = false;
						boolean tz2 = false;
						boolean tz3 = false;
						boolean tz4 = false;
						boolean tz5 = false;
						boolean tz6 = false;
						boolean tz7 = false;
						boolean tz8 = false;
						boolean diwenDisplay = false;
						boolean dw1 = false;
						boolean dw2 = false;
						boolean dw3 = false;
						boolean dw4 = false;
						boolean dw5 = false;
						boolean dw6 = false;
						boolean dw7 = false;
						boolean dw8 = false;
						boolean gaowenDisplay = false;
						int color1 = 0;
						int color2 = 0;
						int color3 = 0;
						int color4 = 0;
						int color5 = 0;
						int color6 = 0;
						int color7 = 0;
						int color8 = 0;
						
						// 如果有一个轴温大于预警值则提示
						try {
							jing1 = vt1 && vt9 && z1 >= z9 + earlyWarning;
							jing2 = vt2 && vt9 && z2 >= z9 + earlyWarning;
							jing3 = vt3 && vt9 && z3 >= z9 + earlyWarning;
							jing4 = vt4 && vt9 && z4 >= z9 + earlyWarning;
							jing5 = vt5 && vt9 && z5 >= z9 + earlyWarning;
							jing6 = vt6 && vt9 && z6 >= z9 + earlyWarning;
							jing7 = vt7 && vt9 && z7 >= z9 + earlyWarning;
							jing8 = vt8 && vt9 && z8 >= z9 + earlyWarning;
							jingDisplay = jing1 || jing2 || jing3 || jing4 || jing5 || jing6 || jing7 || jing8;
//									(!z1Display.equals(" ") && z1 != 181 && z1 != 182 && z1 >= z9 + earlyWarning)
//									|| (!z2Display.equals(" ") && z2 != 181 && z2 != 182 && z2 >= z9 + earlyWarning)
//									|| (!z3Display.equals(" ") && z3 != 181 && z3 != 182 && z3 >= z9 + earlyWarning)
//									|| (!z4Display.equals(" ") && z4 != 181 && z4 != 182 && z4 >= z9 + earlyWarning)
//									|| (!z5Display.equals(" ") && z5 != 181 && z5 != 182 && z5 >= z9 + earlyWarning)
//									|| (!z6Display.equals(" ") && z6 != 181 && z6 != 182 && z6 >= z9 + earlyWarning)
//									|| (!z7Display.equals(" ") && z7 != 181 && z7 != 182 && z7 >= z9 + earlyWarning)
//									|| (!z8Display.equals(" ") && z8 != 181 && z8 != 182 && z8 >= z9 + earlyWarning);
						} catch (Exception e4) {
						}

						try {
							if (ConfigUtil.isShengYangJu())
							{
								tc1 = vt1 && vt3 && z1 -z3 >= ipsilateral;
								tc2 = vt2 && vt4 && z2 -z4 >= ipsilateral;
								tc3 = vt3 && vt1 && z3 -z1 >= ipsilateral;
								tc4 = vt4 && vt2 && z4 -z2 >= ipsilateral;
								tc5 = vt5 && vt7 && z5 -z7 >= ipsilateral;
								tc6 = vt6 && vt8 && z6 -z8 >= ipsilateral;
								tc7 = vt7 && vt5 && z7 -z5 >= ipsilateral;
								tc8 = vt8 && vt6 && z8 -z6 >= ipsilateral;
							}
							else 
								if (ConfigUtil.isGuangZhouJu())
								{
									tc1 = vt1 && z1 - minZW(new int[]{z1, z3, z5, z7}, new boolean[]{vt1, vt3, vt5, vt7}) >= ipsilateral;
									tc2 = vt2 && z2 - minZW(new int[]{z2, z4, z6, z8}, new boolean[]{vt2, vt4, vt6, vt8}) >= ipsilateral;
									tc3 = vt3 && z3 - minZW(new int[]{z1, z3, z5, z7}, new boolean[]{vt1, vt3, vt5, vt7}) >= ipsilateral;
									tc4 = vt4 && z4 - minZW(new int[]{z2, z4, z6, z8}, new boolean[]{vt2, vt4, vt6, vt8}) >= ipsilateral;
									tc5 = vt5 && z5 - minZW(new int[]{z1, z3, z5, z7}, new boolean[]{vt1, vt3, vt5, vt7}) >= ipsilateral;
									tc6 = vt6 && z6 - minZW(new int[]{z2, z4, z6, z8}, new boolean[]{vt2, vt4, vt6, vt8}) >= ipsilateral;
									tc7 = vt7 && z7 - minZW(new int[]{z1, z3, z5, z7}, new boolean[]{vt1, vt3, vt5, vt7}) >= ipsilateral;
									tc8 = vt8 && z8 - minZW(new int[]{z2, z4, z6, z8}, new boolean[]{vt2, vt4, vt6, vt8}) >= ipsilateral;
								}
							tongceDisplay = tc1 || tc2 || tc3 || tc4 || tc5 || tc6 || tc7 || tc8;
//									(!z13Display.equals(" ") && z13 >= ipsilateral)
//									|| (!z24Display.equals(" ") && z24 >= ipsilateral)
//									|| (!z57Display.equals(" ") && z57 >= ipsilateral)
//									|| (!z68Display.equals(" ") && z68 >= ipsilateral);
						} catch (Exception e3) {
						}

						try {
							tz1 = vt1 && vt2 && z1 - z2 >= coaxial;
							tz2 = vt1 && vt2 && z2 - z1 >= coaxial;
							tz3 = vt3 && vt4 && z3 - z4 >= coaxial;
							tz4 = vt3 && vt4 && z4 - z3 >= coaxial;
							tz5 = vt5 && vt6 && z5 - z6 >= coaxial;
							tz6 = vt5 && vt6 && z6 - z5 >= coaxial;
							tz7 = vt7 && vt8 && z7 - z8 >= coaxial;
							tz8 = vt7 && vt8 && z8 - z7 >= coaxial;
							tongzhouDisplay = tz1 || tz2 || tz3 || tz4 || tz5 || tz6 || tz7 || tz8;
//									(!z12Display.equals(" ") && z12 >= coaxial)
//									|| (!z34Display.equals(" ") && z34 >= coaxial)
//									|| (!z56Display.equals(" ") && z56 >= coaxial)
//									|| (!z78Display.equals(" ") && z78 >= coaxial);
						} catch (Exception e2) {
						}

						try {
							dw1 = vt1 && vt9 && z1 <= z9 - lowtemperatureValue;
							dw2 = vt2 && vt9 && z2 <= z9 - lowtemperatureValue;
							dw3 = vt3 && vt9 && z3 <= z9 - lowtemperatureValue;
							dw4 = vt4 && vt9 && z4 <= z9 - lowtemperatureValue;
							dw5 = vt5 && vt9 && z5 <= z9 - lowtemperatureValue;
							dw6 = vt6 && vt9 && z6 <= z9 - lowtemperatureValue;
							dw7 = vt7 && vt9 && z7 <= z9 - lowtemperatureValue;
							dw8 = vt8 && vt9 && z8 <= z9 - lowtemperatureValue;
							diwenDisplay = dw1 || dw2 || dw3 || dw4 || dw5 || dw6 || dw7 || dw8;
//									(!z1Display.equals(" ") && z1 <= z9 - lowtemperatureValue)
//									|| (!z2Display.equals(" ") && z2 <= z9 - lowtemperatureValue)
//									|| (!z3Display.equals(" ") && z3 <= z9 - lowtemperatureValue)
//									|| (!z4Display.equals(" ") && z4 <= z9 - lowtemperatureValue)
//									|| (!z5Display.equals(" ") && z5 <= z9 - lowtemperatureValue)
//									|| (!z6Display.equals(" ") && z6 <= z9 - lowtemperatureValue)
//									|| (!z7Display.equals(" ") && z7 <= z9 - lowtemperatureValue)
//									|| (!z8Display.equals(" ") && z8 <= z9 - lowtemperatureValue);
						} catch (Exception e1) {
						}

						gaowenDisplay = "1".equals(hightemperature);
						
						
						Map<String, Object> result1;
						Map<String, Object> result2;
						Map<String, Object> result3;
						Map<String, Object> result4;
						Map<String, Object> result5;
						Map<String, Object> result6;
						Map<String, Object> result7;
						Map<String, Object> result8;
						Map<String, Object> result9;
						if (ConfigUtil.isShengYangJu())
						{
							result1 = warnCount(z1, z1Display, jing1, dw1, tc1, tz1, ws1, gw1);
							result2 = warnCount(z2, z2Display, jing2, dw2, tc2, tz2, ws2, gw2);
							result3 = warnCount(z3, z3Display, jing3, dw3, tc3, tz3, ws3, gw3);
							result4 = warnCount(z4, z4Display, jing4, dw4, tc4, tz4, ws4, gw4);
							result5 = warnCount(z5, z5Display, jing5, dw5, tc5, tz5, ws5, gw5);
							result6 = warnCount(z6, z6Display, jing6, dw6, tc6, tz6, ws6, gw6);
							result7 = warnCount(z7, z7Display, jing7, dw7, tc7, tz7, ws7, gw7);
							result8 = warnCount(z8, z8Display, jing8, dw8, tc8, tz8, ws8, gw8);
							result9 = warnCount(z9, z9Display, false, false, false, false, "0", "0");
						}
						else
						{
							result1 = warnCount(z1, z1Display, jing1, dw1, tc1, tz1, "0", "0");
							result2 = warnCount(z2, z2Display, jing2, dw2, tc2, tz2, "0", "0");
							result3 = warnCount(z3, z3Display, jing3, dw3, tc3, tz3, "0", "0");
							result4 = warnCount(z4, z4Display, jing4, dw4, tc4, tz4, "0", "0");
							result5 = warnCount(z5, z5Display, jing5, dw5, tc5, tz5, "0", "0");
							result6 = warnCount(z6, z6Display, jing6, dw6, tc6, tz6, "0", "0");
							result7 = warnCount(z7, z7Display, jing7, dw7, tc7, tz7, "0", "0");
							result8 = warnCount(z8, z8Display, jing8, dw8, tc8, tz8, "0", "0");
							result9 = warnCount(z9, z9Display, false, false, false, false, "0", "0");
						}
						
						//温度颜
//						try {
//							color1 = warnCount(z1, z1Display, z9, lowtemperatureValue, earlyWarning, gw1);
//							color2 = warnCount(z2, z2Display, z9, lowtemperatureValue, earlyWarning, gw2);
//							color3 = warnCount(z3, z3Display, z9, lowtemperatureValue, earlyWarning, gw3);
//							color4 = warnCount(z4, z4Display, z9, lowtemperatureValue, earlyWarning, gw4);
//							color5 = warnCount(z5, z5Display, z9, lowtemperatureValue, earlyWarning, gw5);
//							color6 = warnCount(z6, z6Display, z9, lowtemperatureValue, earlyWarning, gw6);
//							color7 = warnCount(z7, z7Display, z9, lowtemperatureValue, earlyWarning, gw7);
//							color8 = warnCount(z8, z8Display, z9, lowtemperatureValue, earlyWarning, gw8);
//						} catch (Exception e) {
//						}
						
						//
						String rowColor = showDataInShijianduan && dotrTime == recordtime ? "style='color:red;'" : "";
						result += "			  <tr class='rowClass" + (i % 2) + "' " ;
						if (ConfigUtil.isShengYangJu() && ("".equals(dotr)))
						{
							result += " style='cursor:pointer;' ondblclick=\"dotr('" + changeTostring(recordtime) + "', '" + carriagenumber + "', '" + trainnumber + "', '" + dotrsjd + "');\"";
							
						}
						if (showDataInShijianduan && "warn".equals(sessionidpo))
						{
							result += " style='background:#999;'";
						}
						result += ">";
						result += "				<td " + rowColor + " " + (ConfigUtil.isGuangZhouJu() ? " width=30" : "") + ">" + String.valueOf((BigDecimal) item[(79)]) + "</td>"
								+ "				<td " + rowColor + "  width=120>" + changeTostring(recordtime) + "</td>"
								+ "				<td " + rowColor + " " + (ConfigUtil.isGuangZhouJu() ? " width=35" : "") + ">" + checi + "</td>"
								+ "				<td " + rowColor + " " + (ConfigUtil.isGuangZhouJu() ? " width=30" : "") + ">" + chezu + "</td>"
								+ "				<td " + rowColor + (showDataInShijianduan && dotrTime == recordtime ? " id='clickedRowidTr'" : "") + "  width='40'>"
									+ carriagenumber + (showDataInShijianduan && dotrTime == recordtime ? "<input type='hidden' id='clickedRowid' value='" + i + "'>" : "") + "</td>"
								+ "				<td " + rowColor + " width=60>" + trainnumber + "</td>"
								+ "				<td title='" + result1.get("title") + "'><span class='ws" + result1.get("color") + "'>" + z1Display + "</span></td>"
								+ "				<td title='" + result2.get("title") + "'><span class='ws" + result2.get("color") + "'>" + z2Display + "</span></td>"
								+ "				<td title='" + result3.get("title") + "'><span class='ws" + result3.get("color") + "'>" + z3Display + "</span></td>"
								+ "				<td title='" + result4.get("title") + "'><span class='ws" + result4.get("color") + "'>" + z4Display + "</span></td>"
								+ "				<td title='" + result5.get("title") + "'><span class='ws" + result5.get("color") + "'>" + z5Display + "</span></td>"
								+ "				<td title='" + result6.get("title") + "'><span class='ws" + result6.get("color") + "'>" + z6Display + "</span></td>"
								+ "				<td title='" + result7.get("title") + "'><span class='ws" + result7.get("color") + "'>" + z7Display + "</span></td>"
								+ "				<td title='" + result8.get("title") + "'><span class='ws" + result8.get("color") + "'>" + z8Display + "</span></td>"
								+ "				<td title='" + result9.get("title") + "'><span class='ws" + result9.get("color") + "'>" + z9Display + "</span></td>"
								+ (ConfigUtil.isShengYangJu()
										? "			    <td><span class='jing" + jingDisplay + "'>★</span></td>"
										+ "			    <td><span class='tongce" + tongceDisplay + "'>★</span></td>"
										+ "			    <td><span class='tongzhou" + tongzhouDisplay + "'>★</span></td>"
										+ "			    <td><span class='diwen" + diwenDisplay + "'>★</span></td>"
										+ "			    <td><span class='gaowen" + gaowenDisplay + "'>★</span></td>"
										+ "			    <td><span class='wensheng" + wensheng + "'>★</span></td>"
										: ""
								) 
								+ "			  </tr>";
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

		result += "			</table></div></div></div>";
		
		if(!"".equals(dotr)){
			out(result);
			return;
		}
		if (search || kmisSearch)
			result += "<div>共 "
					+ pagesCount
					+ " 页&nbsp;&nbsp;&nbsp; "
					+ " <a href='javascript:void(0);' onclick='doSearch(1)'>首页</a> "
					+ " <a href='javascript:void(0);' onclick='doSearch("
					+ (indexOfPage - 1)
					+ ");'>上一页</a> "
					+ " <a href='javascript:void(0);' onclick='doSearch("
					+ (indexOfPage + 1)
					+ ");'>下一页</a> "
					+ " <a href='javascript:void(0);' onclick='doSearch("
					+ pagesCount
					+ ");'>尾页</a> "
					+ " &nbsp;&nbsp;&nbsp;跳转第 <input id='toNumOfPage' name='toNumOfPage' size=2 value='"
					+ indexOfPage + "' onchange='doSearch(this.value);'/> 页 &nbsp;&nbsp;<input type='button' value='确定' onnclick='doGoto();'/>" 
					+ "<div style='display:inline;float:right;padding-right:18px;'><span class='ws0'>█正常</span>  "
					+ "<span class='ws1'>█开路/短路/低温"
					+ (!ConfigUtil.isShengYangJu() ? "" :
						"/高温"
					)
					+ "</span>  "
					+ "<span class='ws2'>█预警</span>  "
					+ //(!ConfigUtil.isShengYangJu() ? "" :
						"<span class='ws4'>█同轴</span> "
					//)
					+ //(!ConfigUtil.isShengYangJu() ? "" :
						"<span class='ws5'>█同侧</span> "
					//)
					+ (!ConfigUtil.isShengYangJu() ? "" :
						"<span class='ws6'>█温升</span> "
					)
					+ //(!ConfigUtil.isShengYangJu() ? "" :
						"<span class='ws3'>█多个故障</span> "
					//)
					+ "</div>";
		

		result += "</div>";
		
		
		if (search || kmisSearch)
			out(result);

	}

	public List<String> getDefTraingroup(){
		TbMembers tbp = (TbMembers)this.getFromSession(ConstUtil.USER_LOGIN_SESSION_INFO);
		TbGroupService srv_gorup = new TbGroupService();
		List<String> listop = new ArrayList<String>();
		
		String sql = " where 1=1 ";
		String options = "车次/组";
		listop.add(options);
		if (null == tbp.getShop()) {
			return listop;
		} else {
			
			sql += " and SECTIONID='" + tbp.getShop() + "' ";	
		}
		
		if (null != tbp.getTrainmaster() && !"".equals(tbp.getTrainmaster()) && !"-1".equals(tbp.getTrainmaster())) {
			
			sql += " and TRAINMASTERID='" + tbp.getTrainmaster() + "' ";	
		}
		
		for (TbGroup po : srv_gorup.findAll(sql + " order by traingroup")) {
			
			listop.add(po.getTraingroup());
		}
		
		return listop;
	}
	/**
	 * 报警数据查询
	 */
	public void initBjsjPage() {

		HttpServletRequest request = this.getRequest();

		/**
		 * 设置默认参数：报警温度，即轴温减环温
		 */
//		TbArguments args = argSevice.findById("1");
//		Long earlyWarning = args.getEarlywarning();

		/**
		 * 页面参数
		 */
		// 如果dowhat.equals("search")，即进行查询，不需要输出form，否则输出form
		String dowhat = request.getParameter("dowhat");
		boolean export = null != dowhat && dowhat.equals("export");
		
		boolean isSearch = null != dowhat && dowhat.equals("search");
		// 这四个条件修改后会导致重新计算温升标志
		String startTimeStr = nullString(request.getParameter("startTime"));
		String endTimeStr = nullString(request.getParameter("endTime"));
		// 路段
		String roadsectionPar = request.getParameter("roadsection"); // 路段
		String shopPar = request.getParameter("shop"); // 车间
		String traingroupPar = nullString(request.getParameter("traingroup")); // 车次-组
		String trainnumberPar = nullString(request.getParameter("trainnumber")); // 车厢号
		String axle = nullString(request.getParameter("axle"));
		//区间
		String validTime = nullString(request.getParameter("validTime"));
		String regionstarttime = nullString(request.getParameter("regionstarttime"));
		String regionendtime = nullString(request.getParameter("regionendtime"));
		//String regionqian = nullString(request.getParameter("regionqian"));
		//String regionhou = nullString(request.getParameter("regionhou"));
		String [] regionstarttimeArr = regionstarttime.split(";");
		String [] regionendtimeArr = regionendtime.split(";");
		//String [] regionqianArr = regionqian.split(";");
		//String [] regionhouArr = regionhou.split(";");
		String dotr = nullString(request.getParameter("dotr"));
		String dotrcxh = nullString(request.getParameter("dotrcxh"));
		String dotrczch = nullString(request.getParameter("dotrczch"));
		String gaptimeStr = nullString(request.getParameter("gaptimestr"));
		String traingroupPar_temp = "";
		if(!"".equals(traingroupPar) && null != traingroupPar)
		{
			if(null != srv_group.findOnlyTraingroup(traingroupPar)){
				roadsectionPar = srv_group.findOnlyTraingroup(traingroupPar).getTrainmasterid();
				shopPar = srv_group.findOnlyTraingroup(traingroupPar).getSectionid();
				traingroupPar_temp = srv_group.findOnlyTraingroup(traingroupPar).getId();
			}
		}
		if(!"".equals(trainnumberPar) && null != trainnumberPar && "".equals(traingroupPar_temp))
		{
			if(null != srv_trainbox.findOnlyTrainbox(trainnumberPar)){
				TbGroup group = new TbGroup();
				group = srv_group.findById(srv_trainbox.findOnlyTrainbox(trainnumberPar).getGroupID());
				if(null != group){
					roadsectionPar = group.getTrainmasterid();
					shopPar = group.getSectionid();
					traingroupPar = group.getTraingroup();
				}
			}
		}
		
		
		/**
		 * 设置默认参数
		 */
		TbArgumentsService argSevice = new TbArgumentsService();
		TbArguments args = argSevice.findById("1");

		// 赋予一个值，防止参数取不到，数据库也取不到的情况

		Long gaptime = 60l;
		try {
			gaptime = Long.parseLong(gaptimeStr);
		} catch (Exception e) {
			if (null != args.getGaptime()) {
				gaptime = args.getGaptime();
			}
		}
		
		/**
		 * 查询条件
		 */
		String sqlWhere = " Where 1=1 ";
		String sqlOrder = " order by traingroup, recordtime";
//		String sqlTable = " ";
		
		/**
		 * 点击某行增加的时间段（查询、计算）
		 */
		if (!"".equals(dotr))
		{
			Long dotrStime = 0l;
			try
			{
				dotrStime = strToDate(dotr).getTime() - Long.valueOf(gaptimeStr) * 60 * 1000L;
				sqlWhere += " and recordtime >= " + dotrStime.longValue() + " ";
			}
			catch (Exception e)
			{
			}
			
			Long dotrEtime = 0l;
			try
			{
				dotrEtime = strToDate(dotr).getTime() + Long.valueOf(gaptimeStr) * 60 * 1000L;
				sqlWhere += " and recordtime <= " + dotrEtime.longValue() + " ";
			}
			catch (Exception e)
			{
			}
			
			trainnumberPar = dotrczch;
		}

		if (null != trainnumberPar && !"".equals(trainnumberPar)) {
			sqlWhere += " and trainnumber='" + trainnumberPar + "' ";
		}
		else if (null != traingroupPar && !"".equals(traingroupPar)) {
			sqlWhere += " and traingroup='" + traingroupPar + "' ";
		}
		else if (null != shopPar && !"".equals(shopPar)) {
			sqlWhere += " and shop='" + shopPar + "' ";
		}
		else if (null != roadsectionPar && !"".equals(roadsectionPar)) {
			sqlWhere += " and roadsection='" + roadsectionPar + "' ";
		}
		
		Long startTime = 0l;
		try {
			startTime = strToDate(startTimeStr).getTime();
			sqlWhere += " and recordtime >= " + startTime.longValue() + " ";
		} catch (Exception e) {
		}

		Long endTime = 0l;
		try {
			endTime = strToDate(endTimeStr).getTime();
			sqlWhere += " and recordtime <= " + endTime.longValue() + " ";
		} catch (Exception e) {
		}
		
		/**
		 * 区间参数
		 */
		if ("1".equals(validTime) && null != traingroupPar && !"".equals(traingroupPar)) {
			String sqlRegion = " and (1 = 0 ";
			
			for (int i = 0; i < regionstarttimeArr.length; i++) {
				if (regionstarttimeArr[i].length() == 0) continue;
				
				int regionhou = TbTemperatureSubAction.hou;
				int regionqian = TbTemperatureSubAction.qian2;
				
				if (0 == i) {
					regionqian =TbTemperatureSubAction.qian1;
				}
				
				sqlRegion += " or ( recordtime >= " + (strToDate(regionstarttimeArr[i]).getTime() - regionqian);
				sqlRegion += " and recordtime <= " + (strToDate(regionendtimeArr[i]).getTime() + regionhou) + " ) ";
			}
			
			if (" and (1 = 0 ".equals(sqlRegion)) {
				//sqlRegion = " ";
			} else {
				sqlRegion += " ) ";
				sqlWhere += sqlRegion;
			}
		}

//		for (int i = 1; i <= 8; i++) {
//			sqlTable += " select recordtime,trainnumber,traingroup,carriagenumber,"
//					+ i
//					+ " zhou,z"
//					+ i
//					+ ","
//					+ "z"
//					+ i
//					+ "-z9 wc,z9 from TB_TEMPERATURE where z"
//					+ i
//					+ "<120 and z" + i + "-z9 > " + earlyWarning + sqlWhere;
//			if (i < 8) {
//				sqlTable += " union all ";
//			}
//		}

//		sqlTable = "select rownum rownumx, aa.* from (select * from( "
//				+ sqlTable
//				+ ") order by traingroup,to_number(carriagenumber),trainnumber,zhou,recordtime) aa ";
		
		
		String startTime_temp = startTimeStr;
		String endTime_temp = endTimeStr;
		String[] scopeTime = this.getService().getScopetime("select min(stime),max(etime) from (select min(recordtime) stime,max(recordtime) etime from tb_warn " + sqlWhere
													  +" union all select min(recordtime) stime,max(recordtime) etime from tb_warn " + sqlWhere + ")");
		if(!"".equals(scopeTime[0])){
			startTimeStr = scopeTime[0];	
		}
		if(!"".equals(scopeTime[1])){
			endTimeStr = scopeTime[1];
		}
		if(null != axle && !"".equals(axle))
		{
			sqlWhere += " and zz='" + axle + "' ";
		}
		
		String form = "<style> "
				+ ".tags_ac{border-bottom:1px solid #BBD6E9;background:#BBD6E9;} "
				+ "</style> "
				+ "		 	<table width=\"100%\"> "
				+ "			   "
				+ "			  <tr> "
				+ "				<td><div class=\"tags_bg\" style=\"margin-bottom:0px;\"> "
				+ "					<div class=\"tags_but\" onClick=\"defzwsj();\">轴温</div> "
				+ "					<div class=\"tags_but tags_ac\" onClick=\"get('TbTemperatureAction!initBjsjPage.action');\">报警</div> "
				+ "					</div> </td> "
				+ "			  </tr> "
				+ "			</table> "
				+ "			<div class=\"search_bar2\"><form action=\"TbTemperatureAction!initBjsjPage.action?1=1\" id=\"search\" method=\"post\" name=\"search\">"
				+ "			<table> "
				+ "			  <tr> "
				+ "				<td height=\"46\"><table> "
				+ "                  <tr> "
				+ "                    <td align=\"right\">&nbsp;<select id=\"roadsection\" name=\"roadsection\" onchange='getShop()'>"
				+ ((String)request.getSession().getAttribute(ConstUtil.TRAINMASTER_INFO)).replaceAll(
						 "value='" + nullString(roadsectionPar) + "'",
						 "value='" + nullString(roadsectionPar) + "' selected ")
				+ "                    </select><span id=shopdiv><select id=\"shop\" name=\"shop\" onchange='getTraingroup()'>"
				+ request.getSession().getAttribute(ConstUtil.SHOP_INFO)
				+ "				    </select></span><span id=traingroupdiv><select id=\"traingroup\" name=\"traingroup\" onchange='clearTrainnumber();getLastTimeByTraingroup(1);clearRegionSetting();'>"
				+ ((String) request.getSession().getAttribute(ConstUtil.GROUP_INFO)).replaceAll(
						"value='" + nullString(traingroupPar) + "'",
						"value='" + nullString(traingroupPar) + "' selected ")
				+ "                    </select></span>"
				+ "                      <input name=\"trainnumber\" id=\"trainnumber\" value=\""+trainnumberPar+"\" size=10 onchange='getLastTimeByTrainnumber(1)' onkeyup=\"inputCheHaoBJ(id)\" onkeydown=\"return chexiang(this)\" class=\"input_bianzu\" onclick=\"showOptionsBJ(id)\" onblur=\"setHideOptions(id)\" />"
				+ "                      <span id=\"trainnumberOptionsSpan\" style=\"position : absolute; visibility : hidden;\"></span>"
				+ "  <div id='defaulttrainnumberdiv'><input type=hidden id='defaulttrainnumber' value='getLastTimeByTrainnumber(2);'/></div>"
				+ "<div id=defaulttraingroupdiv><input type=hidden id=defaulttraingroup value='getLastTimeByTraingroup(2);'/></div>"
				+ "  <div id='gotoroadsectiondiv'><input type=hidden id='gotoroadsection' value='" + roadsectionPar + "'/></div>"
				+ "  <div id='gotoshopdiv'><input type=hidden id='gotoshop' value='" + shopPar + "'/></div>"
				+ "  <div id='gototraingroupPar_tempdiv'><input type=hidden id='gototraingroupPar_temp' value='" + traingroupPar + "'/></div>"
				+ "                    </td>"
				+ "                    <td>&nbsp;起始时间:"
				+ "                      <input id=\"startTime\" name=\"startTime\" value=\""+startTime_temp+"\" class=\"timeinput\" onclick=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})\" oninput=\"clearRegionSetting();\" onpropertychange=\"clearRegionSetting();\"/>"
				+ "                    结束时间:"
				+ "                    <input id=\"endTime\" name=\"endTime\" value=\""+endTime_temp+"\" class=\"timeinput\" onclick=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})\"/>"
				+ "                    <input type=\"checkbox\" id=\"validTime\" name=\"validTime\" value=\"1\" onclick=\"timeshow(this)\" "+(!"".equals(validTime) ? " checked":"")+"/><label for=validTime style=\"cursor:pointer\">有效区间</label>&nbsp;</td> "
				+ "                  </tr> "
				+ "                </table></td> "
				+ "				<td><div class=\"button\" onclick=\"doSearch(1);\" /><div><div style=\"background-image:url(images/search_ico.gif);\">查询</div></div></div>&nbsp;</td> "
				+ "				  <td>&nbsp;<div class=\"button\"><div onclick=\"doExport();\"><div style=\"background-image:url(images/export_ico.gif)\">导出</div></div></div></td> "
				+ "			  </tr> "
				+ "			</table> "
				+ "				  <input type='hidden' id='dowhat' name='dowhat' value='search' />"
				+ "				  <input type='hidden' id='indexOfPage' name='indexOfPage' value='1' />"
				+ "				  <input type='hidden' id='regionstarttime' name='regionstarttime' value='"+ regionstarttime +"' />"
				+ "				  <input type='hidden' id='regionendtime' name='regionendtime' value='"+ regionendtime +"' />"
				+ "				  <input type='hidden' id='regionqian' name='regionqian' value='' />"
				+ "				  <input type='hidden' id='regionhou' name='regionhou' value='' />"
				+ (ConfigUtil.isShengYangJu() ? "					<td><input  type='hidden' name=\"axle\" id=\"axle\" value=\""+ axle +"\" /></td>" : "")//点击某行设置时间
				+ (ConfigUtil.isShengYangJu() ? "					<td><input  type='hidden' name=\"dotr\" id=\"dotr\" value=\"\" /></td>" : "")//点击某行设置时间
				+ (ConfigUtil.isShengYangJu() ? "					<td><input  type='hidden' name=\"dotrcxh\" id=\"dotrcxh\" value=\"\" /></td>" : "")//点击某行设置时间
				+ (ConfigUtil.isShengYangJu() ? "					<td><input  type='hidden' name=\"dotrczch\" id=\"dotrczch\" value=\"\" /></td>" : "")//点击某行设置时间
				+ (ConfigUtil.isShengYangJu() ? "					<td><input  type='hidden' name=\"dotrsjd\" id=\"dotrsjd\" value=\""+ args.getGaptime() +"\" /></td>" : "")//点击某行设置时间
				+ (ConfigUtil.isShengYangJu() ? "					<td><input  type='hidden' name=\"dotrb\" id=\"dotrb\" value=\"\" /></td>" : "")//点击某行设置时间
				+ (ConfigUtil.isShengYangJu() ? "					<td><input  type='hidden' name=\"dotrcxhb\" id=\"dotrcxhb\" value=\"\" /></td>" : "")//点击某行设置时间
				+ (ConfigUtil.isShengYangJu() ? "					<td><input  type='hidden' name=\"dotrczchb\" id=\"dotrczchb\" value=\"\" /></td>" : "")//点击某行设置时间
				+ (ConfigUtil.isShengYangJu() ? "					<td><input  type='hidden' name=\"dotrsjdb\" id=\"dotrsjdb\" value=\"\" /></td>" : "")//点击某行设置时间
				+ (ConfigUtil.isShengYangJu() ? "					<td><input  type='hidden' name=\"dotrshowWarn\" id=\"dotrshowWarn\" value=\"true\" /></td>" : "")//点击某行设置时间
				+ "           </form></div> "
				+ "		<div onclick=\"$('search').style.display=$('search').style.display=='none'?'':'none'\" style=\"background:url(images/admin_bgx.gif) 0px -77px;height:6px; overflow:hidden;cursor:pointer;\"></div>"
				+ "<div id=result></div>";
		
		if (!isSearch && !export) {
			out(form);
			return;
		}
		
		
		
		String searchParStr = (nullString(traingroupPar).equals("") ? "" :  traingroupPar + "组列车")
		+ (nullString(trainnumberPar).equals("") ? "" :  trainnumberPar + "车厢")
		+ "报警数据"
		+ (nullString(startTimeStr).equals("") ? "" : "&nbsp;&nbsp;时间范围：" + startTimeStr + "&nbsp;")
		+ (nullString(endTimeStr).equals("") ? "" : "至：" + endTimeStr + "&nbsp;&nbsp;&nbsp;");
		
		
		 /**
		 * 是否导出excel，导出则不再输出页面
		 */
		if (export) {
			String titleParStr = (nullString(traingroupPar).equals("") ? "" :  traingroupPar + "组列车")
					+ (nullString(trainnumberPar).equals("") ? "" :  trainnumberPar + "车厢")
					+ "报警数据";
			String timeParStr = (nullString(startTimeStr).equals("") ? "" : "&nbsp;&nbsp;时间范围：" + startTimeStr + "&nbsp;")
					+ (nullString(endTimeStr).equals("") ? "" : "至：" + endTimeStr + "&nbsp;&nbsp;&nbsp;");
			
			String filename = "报警数据" + (new SimpleDateFormat("yyyyMMdd")).format(new Date()) + ".xlsx";
			if (null != trainnumberPar && !"".equals(trainnumberPar)) {
				
				filename = trainnumberPar + "车厢" + filename;
			}
			
			if (null != traingroupPar && !"".equals(traingroupPar)) {
				
				filename = traingroupPar + "组列车" + filename;
			}
			String filename_temp = (nullString(traingroupPar).equals("") ? "" :  traingroupPar + "组列车")
					+ (nullString(trainnumberPar).equals("") ? "" :  trainnumberPar + "车厢")
					+ "报警数据"
					+ (new SimpleDateFormat("yyyyMMdd")).format(new Date()) + ".xlsx";
			exportBaoJInExcel(titleParStr, timeParStr, "tb_warn", filename_temp, sqlWhere, sqlOrder);
			
			return;
		}
		

		TbWarnService warnSv = new TbWarnService();
		
		/**
		 * 计算分页
		 */
		int numPerPage = 50; // 每页显示多少条
		if(null != ConfigUtil.getProperty("sizeOfPage"))
		{
			try{
				numPerPage = Integer.parseInt(ConfigUtil.getProperty("sizeOfPage"));
			}
			catch(Exception e){
				
			}
		}
		long rowsCount = warnSv.getRows(sqlWhere);
		int pagesCount = (int) (rowsCount / numPerPage); // 总共多少页

		if (rowsCount % numPerPage != 0) pagesCount++;
		
		int indexOfPage = 1;

		try {
			indexOfPage = Integer.parseInt(request.getParameter("indexOfPage"));
		} catch (Exception e) {
			indexOfPage = 1;
		}

		if (indexOfPage > pagesCount)
			indexOfPage = pagesCount;
		if (indexOfPage < 1)
			indexOfPage = 1;

		//
//		sql = " SELECT * FROM (" + sqlTable + ") WHERE rownumx >"
//				+ ((indexOfPage - 1) * numPerPage) + "   AND rownumx <="
//				+ (indexOfPage * numPerPage) + " order by rownumx";
		//System.out.println(sql);
//		list = tmpService.getFieldsBySql(sql);

		

		String result = "<div class=\"search_t\">" +
//				"车次组："
//				+ nullString(traingroupPar)
//				+ "                                   车种车号："
//				+ nullString(trainnumberPar)
//				+ "                                   时间："
//				+ nullString(startTimeStr)
//				+ "                                   至"
//				+ nullString(endTimeStr)
				searchParStr
				+ "           </div>"
				+ "			<div class='all'><div class='titlediv'" ;
				if(!"".equals(dotr)){
					result += " style='width:1000px;'";
				}
				result += "><div class='title_left'><table width=\"100%\" class=\"main_table\"> "
				+ "			  <tr> "
				+ "                        <th  style='width:30px;'>序号</th> "
				+ "				<th id=th1 rel=tt width=120 >日期</th> "
				+ "				<th id=th2 rel=tt >车种车号</th> "
				+ "				<th id=th3 rel=tt >车次</th> "
				+ "				<th id=th4 rel=tt >车组</th> "
				+ "				<th id=th5 rel=tt nowrap>车厢号</th> "
				+ "				<th id=th6 rel=tt >轴位</th> "
				+ "				<th id=th7 rel=tt >报警温度</th> "
				+ "				<th id=th8 rel=tt >环温</th> "
				+ "				<th id=th8 rel=tt >温升</th> "
				+ "			  </tr> </table></div></div><div class='content'><div class='content_left'><table width=\"100%\" class=\"main_table\">";

		int i = (indexOfPage - 1) * numPerPage;
			for (TbWarn po : warnSv.queryByPage((indexOfPage - 1) * numPerPage, numPerPage, sqlWhere + sqlOrder)) {
				i++;
				try {
//					item = (Object[]) list.get(i);
//
//					long recordtime = ((BigDecimal) item[1]).longValue();
//					String trainnumber = (String) item[2]; // 车种车号
//					String traingroup = (String) item[3]; // 车次-组
//					String carriagenumber = (String) item[4]; // 车厢号
//					int zhou = ((BigDecimal) item[5]).intValue(); // 轴号
//					int z = ((BigDecimal) item[6]).intValue(); // 轴温
//					((BigDecimal) item[7]).intValue();
//					int z9 = ((BigDecimal) item[8]).intValue(); // 环温

					// 车次与车组保是存在一个字段里的，用-分隔，如果没取到，用这个默认值
					String checi = " ";
					String chezu = " ";
					try {
						String trainGroup[] = po.getTraingroup().split("-");
						checi = trainGroup[0];
						chezu = trainGroup[1];
					} catch (Exception e) {
					}

					result += "			  <tr class='rowClass" + (i % 2) + "' " ;
				if (ConfigUtil.isShengYangJu() && ("".equals(dotr)))
				{
					result += "style='cursor:pointer;' ondblclick=\"dotr('" + changeTostring(po.getRecordtime()) + "','" + po.getCarriagenumber() + "','"
							+ po.getTrainnumber() + "', '0');\">";
							//+ po.getTrainnumber() + "', '" + args.getGaptime() + "');\">";
				}
				else
				{
					result += ">";
				}

							result += "<td style='width:30px;'>" + i + "</td>" +
									"				<td width=120 >" + changeTostring(po.getRecordtime()) + "</td>"
							+ "				<td>" + po.getTrainnumber() + "</td>"
							+ "				<td>" + checi + "</td>"
							+ "				<td>" + chezu + "</td>"
							+ "				<td>" + po.getCarriagenumber() + "</td>"
							+ "				<td>" + po.getZz() + "</td>"
							+ "				<td>" + po.getZ9() + "</td>"
							+ "				<td>" + po.getWarntemp() + "</td>"
							+ "				<td>" + (po.getZ9() - po.getWarntemp()) + "</td>"
							+ "			  </tr> ";
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		result += "			</table></div></div></div>";
		
//		if(!"".equals(dotr)){
//			out(result);
//			return;
//		}
		 		result += "<div>共 "
				+ pagesCount
				+ " 页&nbsp;&nbsp;&nbsp; "
				+ " <a href='javascript:void(0);' onclick='doSearch(1)'>首页</a> "
				+ " <a href='javascript:void(0);' onclick='doSearch("
				+ (indexOfPage - 1)
				+ ");'>上一页</a> "
				+ " <a href='javascript:void(0);' onclick='doSearch("
				+ (indexOfPage + 1)
				+ ");'>下一页</a> "
				+ " <a href='javascript:void(0);' onclick='doSearch("
				+ pagesCount
				+ ");'>尾页</a> "
				+ " &nbsp;&nbsp;&nbsp;跳转第 <input id='toNumOfPage' name='toNumOfPage' size=2 value='"
				+ indexOfPage + "' onchange='doSearch(this.value);'/> 页 &nbsp;&nbsp;<input type='button' value='确定' onnclick='doGoto();'/>"
				+ "</div>";

	
		out(result);
		
	}

	public TbTemperatureService getService() {
		return service;
	}
	
	private Map<String, Object> warnCount(int z1, String z1Display, boolean jing, boolean dw, boolean tc,boolean tz)
	{
		Map<String, Object> result = new HashMap<String, Object>();
		int ws1 = 0;
		int ws2 = 0;
		int color = 0;
		String title = "";
		
		try
		{
			// 通讯断
			if (z1Display.length() == 0)
			{
				title += " ";
			}
			// 开路
			if (z1 == 181)
			{
				ws1 = 1;
				title += "开路 ";
			}
			// 短路
			if (z1 == 182)
			{
				ws1 = 1;
				title += "短路 ";
			}
			// 低温
			if (dw)
			{
				ws1 = 1;
				title += "低温 ";
			}
			
			// 预警
			if (jing)
			{
				ws2 = 1;
				title += "预警 ";
			}
			
			// 多个
			if (ws1 + ws2 >= 2)
				color = 3;
			else
				if (1 == ws1)
					color = 1;
				else
					if (1 == ws2)
						color = 2;
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		if (title.length() == 0) title = "正常";
		
		result.put("color", color);
		result.put("title", title);
		
		return result;
	}
	
	private Map<String, Object> warnCount(int z1, String z1Display, boolean jing, boolean dw, boolean tc,boolean tz, String ws, String gw)
	{
		Map<String, Object> result = new HashMap<String, Object>();
		int ws1 = 0;
		int ws2 = 0;
		int ws3 = 0;
		int ws4 = 0;
		int ws5 = 0;
		int color = 0;
		String title = "";
		
		try
		{
			// 开路
			if (z1 == 181)
			{
				ws1 = 1;
				title += "开路 ";
			}
			// 短路
			if (z1 == 182)
			{
				ws1 = 1;
				title += "短路 ";
			}
			// 低温
			if (dw)
			{
				ws1 = 1;
				title += "低温 ";
			}
			// 高温
			if (null != gw && !"0".equals(gw) && !"null".equals(gw))
			{
				ws1 = 1;
				title += "高温 ";
			}
			
			// 预警
			if (jing)
			{
				ws2 = 1;
				title += "预警 ";
			}
			
			// 同轴
			if (tz)
			{
				ws3 = 1;
				title += "同轴 ";
			}
			
			// 同侧
			if (tc)
			{
				ws4 = 1;
				title += "同侧 ";
			}
			
			// 温升
			if ("1".equals(ws))
			{
				ws5 = 1;
				title += "温升 ";
			}
			
			// 多个
			if (ws1 + ws2 + ws3 + ws4 + ws5 >= 2)
				color = 3;
			else
				if (1 == ws1)
					color = 1;
				else
					if (1 == ws2)
						color = 2;
					else
						if (1 == ws3)
							color = 4;
						else
							if (1 == ws4)
								color = 5;
							else
								if (1 == ws5) color = 6;
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		if (title.length() == 0) title = "正常";
		
		result.put("color", color);
		result.put("title", title);
		
		return result;
	}
	
	private void exportZhouwenExcel(String filename, String openwayPar, String diswayPar, String titleParStr, String timeParStr, String sqlPar) {
		
		/**
		 * 分页读取，计算分页
		 */
		int countOfRows = 0;
		int countOfPages = 0;
		int sizeOfPage = 1000;
		String sql = "";
		String sqlOrderby2 = " order by recordtime, traingroup, to_number(carriagenumber), trainnumber ";
		
		try {
			//list = tmpService.getFieldsBySql(sql);
			sql = "select count(*) count from tb_temperature_search_tmp " + sqlPar;
			countOfRows = Integer.parseInt(this.getService().queryResultSetBySql(sql).get(0).get("COUNT"));
		} catch (Exception e) {
		}
		
		countOfPages = (int)(countOfRows / sizeOfPage);
		
		if (0 != countOfRows % sizeOfPage) countOfPages++;
		
		/**
		 * 生成excel
		 */
		Workbook workbook = new SXSSFWorkbook(1000);
		// cellStyle
		CellStyle cellStyle = ExcelUtil.getTitleStyle(workbook);
		CellStyle dataStyle = ExcelUtil.getDataStyle(workbook);
		CellStyle headerStyle = ExcelUtil.getHeaderStyle(workbook);
		CellStyle leftStyle = ExcelUtil.getTitleLeftStyle(workbook);
		CellStyle rightStyle = ExcelUtil.getTitleRightStyle(workbook);
		Sheet sheet = ExcelUtil.getSheet(workbook, "轴温数据");
		Row row = null;
		Cell cell = null;
		int curRowNum = 0;
		
		/**
		 * 标题
		 */
		String[] dataTitle = new String[] {"日期", "车次", "车组", "车厢号", "车种车号", "1号", "2号", "3号", "4号", "5号", "6号", "7号", "8号", "环温"};
		
		row = sheet.createRow(curRowNum++);
		row.setHeight(ExcelUtil.titlelen);
		cell = row.createCell(0);
		cell.setCellValue(titleParStr.replaceAll("&nbsp;", " "));
		cell.setCellStyle(cellStyle);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, dataTitle.length - 1));
		
		row = sheet.createRow(curRowNum++);
		cell = row.createCell(0);
		cell.setCellValue(timeParStr.replaceAll("&nbsp;", " "));
		cell.setCellStyle(leftStyle);
		sheet.addMergedRegion(new CellRangeAddress(1,1,0,dataTitle.length/2));
		cell = row.createCell(dataTitle.length/2+1);		
		
		row = sheet.createRow(curRowNum++);
		cell = row.createCell(0);
		cell.setCellValue("制表时间:"+ DateUtil.getStringDateTime());
		cell.setCellStyle(leftStyle);
		sheet.addMergedRegion(new CellRangeAddress(2,2,0,dataTitle.length/2));
		cell = row.createCell(dataTitle.length/2+1);		
		
		String shop = (String)this.getFromSession(ConstUtil.SHOP_TMP);
		cell.setCellValue("制表单位:" + shop);
		cell.setCellStyle(rightStyle);
		sheet.addMergedRegion(new CellRangeAddress(2,2,dataTitle.length/2+1,dataTitle.length-1));
		
		row = sheet.createRow(curRowNum++);
		sheet.createFreezePane(0, curRowNum);
		
		for (int cellnum = 0; cellnum < dataTitle.length; cellnum++) {
			
			cell = row.createCell(cellnum);
			//cell.setCellStyle(titleStyle);
			cell.setCellValue(dataTitle[cellnum]);
			cell.setCellStyle(headerStyle);
		}
		
		/*
		 * 设置列宽
		 * 
		 */
		sheet.setColumnWidth(0, 5500);
		sheet.setColumnWidth(1, 1800);
		sheet.setColumnWidth(2, 1500);
		sheet.setColumnWidth(3, 1500);
		sheet.setColumnWidth(4, 2600);
		for(int i = 5; i <= 13; i ++){
			sheet.setColumnWidth(i, 1500);
		}
		
		/*
		 * 设置缩放打印
		 */
		PrintSetup printSetup = sheet.getPrintSetup();
		printSetup.setScale((short) 98);//设置打印缩放55% 
		
		/*
		 * 设置打印标题行
		 */
		workbook.setRepeatingRowsAndColumns(0,0,13,3,3);
		
		/**
		 * 内容
		 */
		List<Object[]> list = null;
		
		for (int pagenum = 1; pagenum <= countOfPages; pagenum++) {
			
			int startRowNum = (pagenum - 1) * sizeOfPage + 1;
			int endRowNum = pagenum * sizeOfPage;
			
			if (endRowNum > countOfRows) endRowNum = countOfRows;
			
			sql = " SELECT recordtime,SUBSTR (traingroup,0, INSTR (traingroup, '-') -1) group1,"
					+ "SUBSTR (traingroup, INSTR (traingroup, '-') + 1) group2,"
					+ "CARRIAGENUMBER,TRAINNUMBER,z1,z2,z3,z4,z5,z6,z7,z8,z9"
					+ " FROM ( select a.*, rownum rownuma from("
					+ " select SESSIONID,SESSIONTIME,ID,USERID,ROADSECTION,SHOP,TRAINGROUP,TRAINNUMBER,CARRIAGENUMBER,"
					+ "Z1,Z2,Z3,Z4,Z5,Z6,Z7,Z8,Z9,Z12,Z34,Z56,Z78,Z13,Z24,Z57,Z68,"
					+ "RECORDTIME,READTIME,COMDISCONNECT,OPENWAY,DISWAY,LOWTEMPERATURE,HIGHTEMPERATURE,EARLYWARN,"
					+ "ZX1,ZX2,ZX3,ZX4,ZX5,ZX6,ZX7,ZX8,ZX9,WS1,WS2,WS3,WS4,WS5,WS6,WS7,WS8,WS9,"
					+ "WENSHENG,ROWNUMX,TD1,TD2,TD3,TD4,TD5,TD6,TD7,TD8,TDCZ1,TDCZ2,TDCZ3,TDCZ4,TDCZ5,TDCZ6,TDCZ7,TDCZ8,"
					+ "SESSIONTIMELONG,GW1,GW2,GW3,GW4,GW5,GW6,GW7,GW8 from tb_temperature_search_tmp "
					+ sqlPar + sqlOrderby2
					+ " ) a) WHERE rownuma >=" + startRowNum
					+ "   AND rownuma <=" + endRowNum
					+ " order by rownuma";
			list = this.getService().getFieldsBySql(sql);
			
			for (Object[] item : list) {
				
				int cellnum = 0;
				
				row = sheet.createRow(curRowNum++);
	
				for (int colIndex = 0; colIndex < item.length; colIndex++) {
					
					Object col = item[colIndex];
					String val = null;
					
					if (null != col) val = col.toString();
					if (0 == colIndex) val = changeTostring(Long.parseLong(val));
					
					cell = row.createCell(cellnum++);
					//cell.setCellStyle(bodyStyle);
					cell.setCellValue(val);
					if(null != diswayPar)
						if("182".equals(val))
							cell.setCellValue("短路");
					if(null != openwayPar)
						if("181".equals(val))
							cell.setCellValue("开路");
					cell.setCellStyle(dataStyle);
				}
			}
		}
		String ref = "A4:N" + curRowNum; 
		CellRangeAddress filter = CellRangeAddress.valueOf(ref);
		sheet.setAutoFilter(filter);
		
//		for (int i = 0; i < dataTitle.length; i++) {
//			sheet.autoSizeColumn(i);
//		}
		/**
		 * 输出下载
		 */
		try {

			filename = new String(filename.getBytes(), "ISO-8859-1");
			this.getResponse().setBufferSize(10000);
			this.getResponse().setContentType("application/vnd.ms-excel;charset=UTF-8");
			this.getResponse().addHeader("Content-Disposition","attachment;filename=" + filename);
			workbook.write(this.getResponse().getOutputStream());
			this.getResponse().getOutputStream().flush();
			this.getResponse().getOutputStream().close();
			
		} catch (IOException e) {
			
			out(e.getMessage());
			
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 *  导出报警excel
	 * @param searchParStr  用于excel中的信息提示显示
	 * @param sqlPar 查询条件
	 */
private void exportBaoJInExcel(String titleParStr, String timeParStr, String sqlTable,String filename, String sqlWhere, String sqlOrder) {
		
		/**
		 * 分页读取，计算分页
		 */
		int countOfRows = 0;
		int countOfPages = 0;
		int sizeOfPage = 1000;
		String sql = "";
		
		try {
			//list = tmpService.getFieldsBySql(sql);
			sql = "select count(*) count from ("+sqlTable+") where 1=1 " ;
			//countOfRows = Integer.parseInt(this.getService().queryResultSetBySql(sql).get(0).get("COUNT"));
			countOfRows = (int)srv_warn.getRows(sqlWhere);
		} catch (Exception e) {
		}
		
		countOfPages = (int)(countOfRows / sizeOfPage);
		
		if (0 != countOfRows % sizeOfPage) countOfPages++;
		
		/**
		 * 生成excel
		 */
		Workbook workbook = new SXSSFWorkbook(1000);
		// cellStyle
		CellStyle cellStyle = ExcelUtil.getTitleStyle(workbook);
		CellStyle dataStyle = ExcelUtil.getDataStyle(workbook);
		CellStyle headerStyle = ExcelUtil.getHeaderStyle(workbook);
		CellStyle leftStyle = ExcelUtil.getTitleLeftStyle(workbook);
		CellStyle rightStyle = ExcelUtil.getTitleRightStyle(workbook);
		Sheet sheet = ExcelUtil.getSheet(workbook, "报警数据");
		Row row = null;
		Cell cell = null;
		int curRowNum = 0;
		
		/**
		 * 标题
		 */
		String[] dataTitle = new String[] {"日期","车种车号", "车次", "车组", "车厢号",  "轴位", "报警温度", "环温", "温升"};
		
		row = sheet.createRow(curRowNum++);
		row.setHeight(ExcelUtil.titlelen);
		cell = row.createCell(0);
		cell.setCellValue(titleParStr.replaceAll("&nbsp;", " "));
		cell.setCellStyle(cellStyle);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, dataTitle.length - 1));
		
		row = sheet.createRow(curRowNum++);
		cell = row.createCell(0);
		cell.setCellValue(timeParStr.replaceAll("&nbsp;", " "));
		cell.setCellStyle(leftStyle);
		sheet.addMergedRegion(new CellRangeAddress(1,1,0,dataTitle.length/2));
		cell = row.createCell(dataTitle.length/2+1);
		
		row = sheet.createRow(curRowNum++);
		cell = row.createCell(0);
		cell.setCellValue("制表时间:"+ DateUtil.getStringDateTime());
		cell.setCellStyle(leftStyle);
		sheet.addMergedRegion(new CellRangeAddress(2,2,0,dataTitle.length/2));
		cell = row.createCell(dataTitle.length/2+1);		
		
		String shop = (String)this.getFromSession(ConstUtil.SHOP_TMP);
		cell.setCellValue("制表单位:" + shop);
		cell.setCellStyle(rightStyle);
		sheet.addMergedRegion(new CellRangeAddress(2,2,dataTitle.length/2+1,dataTitle.length-1));
		
		
		row = sheet.createRow(curRowNum++);
		sheet.createFreezePane(0, curRowNum);
		
		for (int cellnum = 0; cellnum < dataTitle.length; cellnum++) {
			
			cell = row.createCell(cellnum);
			//cell.setCellStyle(titleStyle);
			cell.setCellValue(dataTitle[cellnum]);
			cell.setCellStyle(headerStyle);
		}
		
		/*
		 * 设置列宽
		 * 
		 */
		sheet.setColumnWidth(0, 5500);
		for(int i = 1; i < dataTitle.length; i ++){
			sheet.setColumnWidth(i, 2800);
		}
		
		/*
		 * 设置缩放打印
		 */
//		PrintSetup printSetup = sheet.getPrintSetup();
//		printSetup.setScale((short) 98);//设置打印缩放55% 
		
		/*
		 * 设置打印标题行
		 */
		workbook.setRepeatingRowsAndColumns(0,0,dataTitle.length - 1 ,3,3);
		
		/**
		 * 内容
		 */
		List<Object[]> list = null;
		
		for (int pagenum = 1; pagenum <= countOfPages; pagenum++) {
			
			int startRowNum = (pagenum - 1) * sizeOfPage + 1;
			int endRowNum = pagenum * sizeOfPage;
			
			if (endRowNum > countOfRows) endRowNum = countOfRows;
			
//			sql = " SELECT recordtime,TRAINNUMBER,SUBSTR (traingroup,0, INSTR (traingroup, '-') -1) group1,"
//					+ "SUBSTR (traingroup, INSTR (traingroup, '-') + 1) group2,"
//					+ "CARRIAGENUMBER,zhou,Z1,z9,rownum rownumx"
//					+ " FROM ("
//					+ sqlTable 
//					+ ") a WHERE rownumx >=" + startRowNum
//					+ "   AND rownumx <=" + endRowNum
//					+ " order by rownumx";
			sql = " SELECT recordtime,TRAINNUMBER,SUBSTR (traingroup,0, INSTR (traingroup, '-') -1) group1,"
					+ "SUBSTR (traingroup, INSTR (traingroup, '-') + 1) group2,"
					+ "CARRIAGENUMBER,ZZ,z9,warntemp"
					+ " FROM ( select a.*, rownum rownuma from("
					+ " select * from tb_warn "
					+ sqlWhere + sqlOrder
					+ " ) a) WHERE rownuma >=" + startRowNum
					+ "   AND rownuma <=" + endRowNum
					+ " order by rownuma";
			list = srv_warn.getFieldsBySql(sql);
			//list = srv_warn.queryByPage((pagenum - 1) * sizeOfPage, sizeOfPage, sqlWhere + sqlOrder);
			
			for (Object[] item : list) {
				
				int cellnum = 0;
				
				row = sheet.createRow(curRowNum++);
				long z9Tmp = 0l;
				long warnTmp = 0l;
	
				for (int colIndex = 0; colIndex < item.length; colIndex++) {
					
					Object col = item[colIndex];
					String val = null;
					
					if (null != col) val = col.toString();
					if (0 == colIndex) val = changeTostring(Long.parseLong(val));
					if(colIndex == item.length - 2)
					{
						z9Tmp = Long.parseLong(val);
					}
					if(colIndex == item.length - 1)
					{
						warnTmp = Long.parseLong(val);
					}
					
					cell = row.createCell(cellnum++);
					//cell.setCellStyle(bodyStyle);
					cell.setCellValue(val);
					cell.setCellStyle(dataStyle);
				}
				cell = row.createCell(cellnum++);
				cell.setCellValue(z9Tmp - warnTmp);
				cell.setCellStyle(dataStyle);
			}
		}
		String ref = "A4:H" + curRowNum; 
		CellRangeAddress filter = CellRangeAddress.valueOf(ref);
		sheet.setAutoFilter(filter);
//		for (int i = 0; i < dataTitle.length; i++) {
//			sheet.autoSizeColumn(i);
//		}
		/**
		 * 输出下载
		 */
		try {

			filename = new String(filename.getBytes(), "ISO-8859-1");
			this.getResponse().setBufferSize(10000);
			this.getResponse().setContentType("application/vnd.ms-excel;charset=UTF-8");
			this.getResponse().addHeader("Content-Disposition","attachment;filename=" + filename);
			workbook.write(this.getResponse().getOutputStream());
			this.getResponse().getOutputStream().flush();
			this.getResponse().getOutputStream().close();
			
		} catch (IOException e) {
			
			out(e.getMessage());
			
			e.printStackTrace();
		}
		
    }
	
	private boolean isValidTemp(Integer temp, String tempDisplay)
	{
		if (null == temp || 181 == temp || 182 == temp || 183 == temp || "".equals(tempDisplay.trim()))
			return false;
		else
			return true;
	}
	
	private boolean isValidTemp(Integer temp)
	{
		if (null == temp || 181 == temp || 182 == temp || 183 == temp)
			return false;
		else
			return true;
	}
	
	private int minZW(int[] zw, boolean[] vt)
	{
		int result = 183;
		
		for (int index = 0; index < zw.length; index++)
		{
			if (vt[index] && zw[index] < result) result = zw[index];
		}
		
		return result;
	}
}
