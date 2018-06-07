package com.zhht.project.snow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;

/**
 * 系统本机环境变量参数
 * @author ningquan
 *
 */
public abstract class SystemEnvParams {
	/**主机名称*/
	public static String HOST_NAME 	 = Constant.EMPTY_STRING;
	/**主机IP地址*/
	public static String HOST_ADDRESS =Constant.EMPTY_STRING;
	/**主机MAC地址*/
	public static String MAC_ADDRESS = Constant.EMPTY_STRING;
	static{
		try {
			InetAddress ia = InetAddress.getLocalHost();
			if(ia == null){
				throw new RuntimeException("当前服务器ipAddress配置错误，请检查服务器配置！");
			}
			HOST_NAME = ia.getHostName();
			HOST_ADDRESS = ia.getHostAddress();
			MAC_ADDRESS = getLocalMacAddress(ia);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取当前机器的mac地址
	 * @author ningquan
	 * @throws Exception
	 */
	public static String getLocalMacAddress(InetAddress ia){
		if(Constant.LIUX_OS){
			return getLinuxMacAddress();
		}
		return getWindowsMacAddress(ia);
	}
	
	public static String  getWindowsMacAddress(InetAddress ia){
		try {
			byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
			StringBuffer sb = new StringBuffer("");
			for(int i=0; i<mac.length; i++) {
				if(i!=0) {
					sb.append("-");
				}
				int temp = mac[i]&0xff;
				String str = Integer.toHexString(temp);
				if(str.length()==1) {
					sb.append("0"+str);
				}else {
					sb.append(str);
				}
			}
			return sb.toString().toUpperCase();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getWindowsMacAddress(){
		 String address = "";
		 try {
             String command = "cmd.exe /c ipconfig /all";
             Process p = Runtime.getRuntime().exec(command);
             BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
             String line;
             while ((line = br.readLine()) != null) {
                 if (line.indexOf("Physical Address") > 0) {
                     int index = line.indexOf(":");
                     index += 2;
                     address = line.substring(index);
                     break;
                 }
             }
             br.close();
             return address.trim();
         } catch (IOException e) {
         }
		return address;
	}
	public static String getLinuxMacAddress(){
		String address = "";
		String command = "/bin/sh -c ifconfig -a";
        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.indexOf("HWaddr") > 0) {
                    int index = line.indexOf("HWaddr") + "HWaddr".length();
                    address = line.substring(index);
                    break;
                }
            }
            br.close();
        } catch (IOException e) {
        }
        return address;
	}
	public static String getLinuxMacAddress2() {
        String mac = null;
        BufferedReader bufferedReader = null;
        Process process = null;
        try {            
            process = Runtime.getRuntime().exec("ifconfig eth0");
            bufferedReader = new BufferedReader(new InputStreamReader(
                    process.getInputStream()));
            String line = null;
            int index = -1;
            while ((line = bufferedReader.readLine()) != null) {
                index = line.toLowerCase().indexOf("HWaddr");
                if (index != -1) {
                    mac = line.substring(index + 4).trim();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            bufferedReader = null;
            process = null;
        }
        return mac;
    }
}
