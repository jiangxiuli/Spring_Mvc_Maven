package com.zhht.project.util;



/**
 * 执行时间监测
 * 
 * @author WangShengguang 2016-11-24
 *
 */
public class CExcuteTimerUtil {
	
	private long time_Begin = 0;
	private long time_Ended = 0;

	public CExcuteTimerUtil() {
		super();
	}

	public long getTime_Begin() {
		return time_Begin;
	}

	public void setTime_Begin(long time_Begin) {
		this.time_Begin = time_Begin;
	}

	public long getTime_Ended() {
		return time_Ended;
	}

	public void setTime_Ended(long time_Ended) {
		this.time_Ended = time_Ended;
	}
	
	public long getTotalUsedSecond(){
		long ret = 0;
		//------------------------------------
		ret = getTime_Ended() - getTime_Begin();
		//------------------------------------
		return ret;
	}
	
	public String toString(){
		return DateUtil.getUsedTime(getTotalUsedSecond());
	}
	
	public void timerBegin(){
		setTime_Begin(System.currentTimeMillis());
	}
	
	public void timerEnded(){
		setTime_Ended(System.currentTimeMillis());
	}

}
