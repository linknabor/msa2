/**
 * 
 */
package com.eshequ.msa.util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * 
 * 日期工具
 * 
 * @author 段
 * 
 */
public class DateUtil {

	private static final ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>();
	private static final Object object = new Object();
	private static ParsePosition parsePosition = new ParsePosition(0);

	/**
	 * 获取SimpleDateFormat
	 * 
	 * @param pattern
	 *            日期格式
	 * @return SimpleDateFormat对象
	 * @throws RuntimeException
	 *             异常：非法日期格式
	 */
	private static SimpleDateFormat getDateFormat(String pattern) throws RuntimeException {
		SimpleDateFormat dateFormat = threadLocal.get();
		if (dateFormat == null) {
			synchronized (object) {
				if (dateFormat == null) {
					dateFormat = new SimpleDateFormat(pattern);
					dateFormat.setLenient(false);
					threadLocal.set(dateFormat);
				}
			}
		}
		dateFormat.applyPattern(pattern);
		return dateFormat;
	}
	
	private static ParsePosition getDefaultParsePosition() {
		return parsePosition;
	}

	/**
	 * 获取系统日期yyyyMMdd格式
	 * 
	 * @return
	 */
	public static String getSysDate() {
		SimpleDateFormat simpledateformat = getDateFormat("yyyyMMdd");
		String s = simpledateformat.format(Calendar.getInstance().getTime());
		return s;
	}

	/**
	 * 获取系统时间HHmmss格式
	 * 
	 * @return
	 */
	public static String getSysTime() {
		SimpleDateFormat simpledateformat = getDateFormat("HHmmss");
		String s = simpledateformat.format(Calendar.getInstance().getTime());
		return s;
	}

	/**
	 * convert yyyy-MM-dd to yyyyMMdd
	 * 
	 * @param date
	 * @return
	 */
	public static String format2DB(String date) {
		if (ObjectUtil.isEmpty(date))
			return "";
		return date.replaceAll("-", "");
	}

	/**
	 * convert HH:mm:ss to HHmmss
	 * 
	 * @param time
	 * @return
	 */
	public static String formatTime2DB(String time) {
		if (ObjectUtil.isEmpty(time))
			return "";
		return time.replaceAll(":", "");
	}

	/**
	 * covert yyyyMMdd to yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static String formatFromDB(String date) {
		if (ObjectUtil.isEmpty(date))
			return "";
		StringBuffer buf = new StringBuffer(date);
		return buf.insert(6, '-').insert(4, '-').toString();
	}

	/**
	 * convert HHmmss to HH:mm:ss
	 * 
	 * @param time
	 * @return
	 */
	public static String formatTimeFromDB(String time) {
		if (ObjectUtil.isEmpty(time))
			return "";
		StringBuffer buf = new StringBuffer(time);
		return buf.insert(2, ':').insert(5, ':').toString();
	}

	/**
	 * covert yyyyMMdd HHmmss to yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @param time
	 * @return
	 */
	public static String formatDateTimeFromDB(String date, String time) {
		if ((ObjectUtil.isEmpty(date)) || (date.length() < 8))
			date = "        ";
		if ((ObjectUtil.isEmpty(time)) || (date.length() < 6))
			time = "      ";
		StringBuffer buf = new StringBuffer(date);
		buf.insert(6, '-').insert(4, '-');
		StringBuffer buf1 = new StringBuffer(time);
		buf1.insert(2, ':').insert(5, ':');
		return buf.toString() + " " + buf1.toString();
	}

	/**
	 * get year list
	 * 
	 * @return
	 */
	public static List<String> getYear() {
		List<String> vRet = new ArrayList<String>();
		int iCurrYear = Integer.parseInt(getSysDate().substring(0, 4));
		for (int i = 0; i <= iCurrYear - 2005 + 3; i++) {
			vRet.add(Integer.toString(2005 + i));
		}
		return vRet;
	}

	/**
	 * getNextMonth
	 * 
	 * @param ogYear
	 * @param iValue
	 * @return
	 */
	public static String getNextMonth(String ogYear, int iValue) {
		String strRetDate = "";
		if (iValue == 12) {
			strRetDate = Integer.parseInt(ogYear) + 1 + "01";
		} else if (iValue == 11) {
			strRetDate = ogYear + "12";
		} else if (iValue == 10) {
			strRetDate = ogYear + "11";
		} else if (iValue == 9) {
			strRetDate = ogYear + "10";
		} else {
			strRetDate = ogYear + "0" + (iValue + 1);
		}
		return strRetDate;
	}

	/**
	 * getNextDateByNum
	 * 
	 * @param sDate
	 * @param i
	 * @return
	 */
	public static String getNextDateByNum(String sDate, int i) {
		SimpleDateFormat simpledateformat = getDateFormat("yyyyMMdd");
		Date date = simpledateformat.parse(sDate, getDefaultParsePosition());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(5, i);
		date = calendar.getTime();
		sDate = simpledateformat.format(date);
		return sDate;
	}

	/**
	 * getNextDateByMonth
	 * 
	 * @param s
	 * @param i
	 * @return
	 */
	public static String getNextDateByMonth(String sDate, int i) {
		SimpleDateFormat simpledateformat = getDateFormat("yyyyMMdd");
		Date date = simpledateformat.parse(sDate, getDefaultParsePosition());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(2, i);
		date = calendar.getTime();
		sDate = simpledateformat.format(date);
		return sDate;
	}

	/**
	 * getNextDateByYear
	 * 
	 * @param sDate
	 * @param i
	 * @return
	 */
	public static String getNextDateByYear(String sDate, int i) {
		SimpleDateFormat simpledateformat = getDateFormat("yyyyMMdd");
		Date date = simpledateformat.parse(sDate, new ParsePosition(0));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(1, i);
		date = calendar.getTime();
		sDate = simpledateformat.format(date);
		return sDate;
	}

	/**
	 * getMonthLastDate
	 * 
	 * @param ogdate
	 * @return
	 */
	public static String getMonthLastDate(String ogdate) {
		if (ogdate.length() == 6)
			ogdate = ogdate + "01";
		else {
			ogdate = ogdate.substring(0, 6) + "01";
		}
		ogdate = getNextDateByMonth(ogdate, 1);
		ogdate = getNextDateByNum(ogdate, -1);
		return ogdate;
	}

	/**
	 * get Chinese date 1999年5月5日
	 * 
	 * @param fdate
	 * @return
	 */
	public static String getCNDate(String fdate) {
		if (ObjectUtil.isEmpty(fdate))
			return "";
		String cur_date = fdate;
		cur_date = cur_date.substring(0, 4) + "年" + cur_date.substring(4, 6) + "月" + cur_date.substring(6) + "日";
		return cur_date;
	}

	/**
	 * get Chinese time 13时55分12秒
	 * 
	 * @param ftime
	 * @return
	 */
	public static String getCNTime(String ftime) {
		String cur_time = ftime;
		cur_time = cur_time.substring(0, 2) + "时" + cur_time.substring(2, 4) + "分" + cur_time.substring(4) + "秒";
		return cur_time;
	}

	/**
	 * getDatePeriod
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int getDatePeriod(String startDate, String endDate) {
		String[] date1 = startDate.split("-");
		String[] date2 = endDate.split("-");

		GregorianCalendar gc1 = new GregorianCalendar(Integer.parseInt(date1[0]), Integer.parseInt(date1[1]),
				Integer.parseInt(date1[2]));

		GregorianCalendar gc2 = new GregorianCalendar(Integer.parseInt(date2[0]), Integer.parseInt(date2[1]),
				Integer.parseInt(date2[2]));

		long longDate1 = gc1.getTimeInMillis();
		long longDate2 = gc2.getTimeInMillis();
		long period = longDate2 - longDate1;

		period /= 86400000L;

		return (int) period;
	}

	/**
	 * dateMargin
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int dateMargin(String startDate, String endDate) {
		SimpleDateFormat df = getDateFormat("yyyyMMdd");

		int a = 0;
		try {
			long l = df.parse(startDate).getTime();
			long e = df.parse(endDate).getTime();
			a = (int) ((e - l) / 1000L / 60L / 60L / 24L);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		return a;
	}

	/**
	 * getSecsDiff
	 * 
	 * @param beginDt
	 * @param endDt
	 * @return
	 */
	public static int getSecsDiff(String beginDt, String endDt) {
		if ((beginDt == null) || (endDt == null))
			return 0;
		if (beginDt.length() == 8)
			beginDt = beginDt + "000000";
		if (beginDt.length() == 6)
			beginDt = getSysDate() + beginDt;
		if (endDt.length() == 8)
			endDt = endDt + "000000";
		if (endDt.length() == 6) {
			endDt = getSysDate() + endDt;
		}
		int iBYYYY = Integer.parseInt(beginDt.substring(0, 4));
		int iBMM = Integer.parseInt(beginDt.substring(4, 6));
		int iBDD = Integer.parseInt(beginDt.substring(6, 8));
		int iBhh = Integer.parseInt(beginDt.substring(8, 10));
		int iBmm = Integer.parseInt(beginDt.substring(10, 12));
		int iBss = Integer.parseInt(beginDt.substring(12, 14));
		int iEYYYY = Integer.parseInt(endDt.substring(0, 4));
		int iEMM = Integer.parseInt(endDt.substring(4, 6));
		int iEDD = Integer.parseInt(endDt.substring(6, 8));
		int iEhh = Integer.parseInt(endDt.substring(8, 10));
		int iEmm = Integer.parseInt(endDt.substring(10, 12));
		int iEss = Integer.parseInt(endDt.substring(12, 14));
		Calendar BeginDate = new GregorianCalendar(iBYYYY, iBMM, iBDD, iBhh, iBmm, iBss);
		Calendar EndDate = new GregorianCalendar(iEYYYY, iEMM, iEDD, iEhh, iEmm, iEss);
		long lBegin = BeginDate.getTime().getTime();
		long lEnd = EndDate.getTime().getTime();

		long lDiff = lBegin - lEnd;
		BeginDate = null;
		EndDate = null;
		return (int) (lDiff / 1000L);
	}

	/**
	 * getYear
	 * 
	 * @param fdate
	 * @return
	 */
	public static String getYear(String fdate) {
		if (ObjectUtil.isEmpty(fdate))
			return "";
		String cur_date = fdate.substring(0, 4);
		return cur_date;
	}

	/**
	 * getMonth
	 * 
	 * @param fdate
	 * @return
	 */
	public static String getMonth(String fdate) {
		if (ObjectUtil.isEmpty(fdate))
			return "";
		String cur_date = fdate.replaceAll("-", "");
		cur_date = cur_date.substring(4, 6);
		return cur_date;
	}

	/**
	 * getDay
	 * 
	 * @param fdate
	 * @return
	 */
	public static String getDay(String fdate) {
		if (ObjectUtil.isEmpty(fdate))
			return "";
		String cur_date = fdate.replaceAll("-", "");
		cur_date = cur_date.substring(6);
		return cur_date;
	}

}
