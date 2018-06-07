package com.zhht.project.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * 这个Https协议工具类，采用HttpsURLConnection实现。
 * 提供get和post两种请求静态方法
 * 
 * @author marker
 * @date 2014年8月30日
 * @version 1.0
 */
public class HttpUtil {

    private static TrustManager myX509TrustManager = new X509TrustManager() {

        public void checkClientTrusted(X509Certificate[] arg0, String arg1)
            throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] arg0, String arg1)
            throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

    };

    public static String sendHttpsPOST(String url, String data) {
        String result = null;
        OutputStream out = null;
        BufferedReader in = null;
        try {
            // 设置SSLContext
            SSLContext sslcontext = SSLContext.getInstance("TLS");
            sslcontext
                .init(null, new TrustManager[] {myX509TrustManager}, null);

            // 打开连接
            // 要发送的POST请求url?Key=Value&amp;Key2=Value2&amp;Key3=Value3的形式
            URL requestUrl = new URL(url);
            HttpsURLConnection httpsConn =
                (HttpsURLConnection) requestUrl.openConnection();
            // 连接超时3秒
            httpsConn.setConnectTimeout(3000);
            // 读取超时3秒
            httpsConn.setReadTimeout(3000);
            // 设置套接工厂
            httpsConn.setSSLSocketFactory(sslcontext.getSocketFactory());

            // 加入数据
            httpsConn.setRequestMethod("POST");
            httpsConn.setDoOutput(true);
            out = httpsConn.getOutputStream();

            if (data != null)
                out.write(data.getBytes("UTF-8"));
            out.flush();
            out.close();

            // 获取输入流
            in =
                new BufferedReader(new InputStreamReader(
                    httpsConn.getInputStream()));
            int code = httpsConn.getResponseCode();
            if (HttpsURLConnection.HTTP_OK == code) {
                String temp = in.readLine();
                /* 连接成一个字符串 */
                while (temp != null) {
                    if (result != null)
                        result += temp;
                    else
                        result = temp;
                    temp = in.readLine();
                }
            }
        } catch (KeyManagementException | NoSuchAlgorithmException
            | IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    public static String sendHttpsGET(String url) {
        String result = null;
        BufferedReader in = null;
        try {
            // 设置SSLContext
            SSLContext sslcontext = SSLContext.getInstance("TLS");
            sslcontext
                .init(null, new TrustManager[] {myX509TrustManager}, null);

            // 打开连接
            // 要发送的POST请求url?Key=Value&amp;Key2=Value2&amp;Key3=Value3的形式
            URL requestUrl = new URL(url);
            HttpsURLConnection httpsConn =
                (HttpsURLConnection) requestUrl.openConnection();

            // 设置套接工厂
            httpsConn.setSSLSocketFactory(sslcontext.getSocketFactory());

            // 加入数据
            httpsConn.setRequestMethod("GET");
            // httpsConn.setDoOutput(true);

            // 获取输入流
            in =
                new BufferedReader(new InputStreamReader(
                    httpsConn.getInputStream()));
            int code = httpsConn.getResponseCode();
            if (HttpsURLConnection.HTTP_OK == code) {
                String temp = in.readLine();
                /* 连接成一个字符串 */
                while (temp != null) {
                    if (result != null)
                        result += temp;
                    else
                        result = temp;
                    temp = in.readLine();
                }
            }
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return result;
    }

    public static String sendHttpGET(String url) {
        String result = null;
        BufferedReader in = null;
        try {

            // 打开连接
            // 要发送的POST请求url?Key=Value&amp;Key2=Value2&amp;Key3=Value3的形式
            URL requestUrl = new URL(url);
            HttpURLConnection httpsConn =
                (HttpURLConnection) requestUrl.openConnection();

            // 加入数据
            httpsConn.setRequestMethod("GET");
            // httpsConn.setDoOutput(true);

            // 获取输入流
            in =
                new BufferedReader(new InputStreamReader(
                    httpsConn.getInputStream(), "UTF-8"));
            int code = httpsConn.getResponseCode();
            if (HttpsURLConnection.HTTP_OK == code) {
                String temp = in.readLine();
                /* 连接成一个字符串 */
                while (temp != null) {
                    if (result != null)
                        result += temp;
                    else
                        result = temp;
                    temp = in.readLine();
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static InputStream sendHttpGetImage(String url) {
        CloseableHttpResponse response = null;
        InputStream input = null;
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet get = new HttpGet(url);
            response = httpClient.execute(get);
            HttpEntity entity = response.getEntity();
            input = entity.getContent();
            // byte[] byt = EntityUtils.toByteArray(entity);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return input;
    }

    public static InputStream getInputStream(String path)
        throws IOException {
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL(path);
            if (url != null) {
                httpURLConnection = (HttpURLConnection) url.openConnection();
                // 设置连接网络的超时时间
                httpURLConnection.setConnectTimeout(3000);
                httpURLConnection.setDoInput(true);
                // 设置本次http请求使用get方式请求
                httpURLConnection.setRequestMethod("GET");
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode == 200) {
                    // 从服务器获得一个输入流
                    inputStream = httpURLConnection.getInputStream();
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return inputStream;
    }

    public static String sendHttpPOST(String url, String data) {
        return sendHttpPOST(url, data, null);
    }

    public static String sendHttpPOST(String url, String data, String strJson) {
        String result = null;
        OutputStream out = null;
        BufferedReader in = null;
        try {

            // 打开连接
            // 要发送的POST请求url?Key=Value&amp;Key2=Value2&amp;Key3=Value3的形式
            URL requestUrl = new URL(url);
            HttpURLConnection httpsConn =
                (HttpURLConnection) requestUrl.openConnection();
            httpsConn.setDoOutput(true);
            httpsConn.setRequestMethod("POST");
            httpsConn.setRequestProperty("Accept-Charset", "utf-8");
            if (null == strJson) {

                httpsConn.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            } else {
                httpsConn.setRequestProperty("Content-Type", strJson);
            }
            // httpsConn.setRequestProperty("Content-Length",
            // String.valueOf(parameterData.length()));
            // 加入数据

            httpsConn.setConnectTimeout(10000);
            httpsConn.setReadTimeout(10000);

            out = httpsConn.getOutputStream();

            if (data != null)
                out.write(data.getBytes("UTF-8"));
            out.flush();
            out.close();

            // 获取输入流
            in =
                new BufferedReader(new InputStreamReader(
                    httpsConn.getInputStream(), "UTF-8"));
            // List<String> ss= IOUtils.readLines(in);
            int code = httpsConn.getResponseCode();
            if (HttpsURLConnection.HTTP_OK == code) {
                String temp = in.readLine();
                /* 连接成一个字符串 */
                while (temp != null) {
                    if (result != null)
                        result += temp;
                    else
                        result = temp;
                    temp = in.readLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // System.out.println(result);
        try {
            result = new String(result.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // System.out.println(result);
        return result;
    }

    public static void main(String[] args) {
        /*
         * String url="http://192.168.11.26:8080/ZHHTBoss/s1/uc/queryUser";
         * String data=
         * "param={\"data\":{\"userId\":\"0c0c8c16_7d60_11e5_a40d_44a8422565ee\",\" type\": \"0\"},\"k\": \"261d55bc-a3f4-4ca1-889d-8556104a9a33\"}"
         * ;
         * 
         * 
         * String url1="http://192.168.11.26:8080/ZHHTTCMSApp/app/coupon/getAllCoupon";
         * 
         * String data1=
         * "param={\"data\":{\"userId\":\"0c0c8c16_7d60_11e5_a40d_44a8422565ee\",\" type\": \"0\"},\"k\": \"261d55bc-a3f4-4ca1-889d-8556104a9a33\"}"
         * ;
         * String sendHttpPOST = sendHttpPOST(url1,data1);
         */
        /*
         * String url1="http://192.168.11.26:8080/ZHHTTCMSApp/app/coupon/getAllCoupon";
         * AppUser appUser = appUserDao.selectByPrimaryKey(userId);
         * String token = appUser.getToken();
         * 
         * String data1=
         * "param={\"data\":{\"userId\":\"0c0c8c16_7d60_11e5_a40d_44a8422565ee\",\" type\": \"0\"},\"k\": \"261d55bc-a3f4-4ca1-889d-8556104a9a33\"}"
         * ;
         * String sendHttpPOST1 = HttpUtil.sendHttpPOST(url1,data1);
         * System.out.println(sendHttpPOST1);
         * JSONObject fromObject1 = JSONObject.fromObject(sendHttpPOST1);
         * 
         * @SuppressWarnings("unchecked")
         * Map<String,Class> map=new HashMap<>();
         * map.put("data", Map.class);
         * Map<String,Object> bean = (Map<String, Object>) JSONObject.toBean(fromObject1,
         * Map.class,map);
         * Map<String,Object> bean2 = (Map<String,Object>) bean.get("data");
         */
        // List<UserCouponVO> couponList1 = (List<UserCouponVO>)
        // bean2.get("SpUsercouponVO");
        // List<UserCouponVO> userList1 = (List<UserCouponVO>)
        // JSONArray.toCollection(userArray, UserCouponVO.class);
        // String map=(String) bean.get("data");
        // JSONObject fromObject2 = JSONObject.fromObject(map);
        // Map<String,Object> bean2 = (Map<String, Object>) JSONObject.toBean(fromObject2,
        // Map.class);
        // List<UserCouponVO> couponList1 = (List<UserCouponVO>)
        // bean2.get("SpUsercouponVO");

        String url1 = " http://10.100.16.59:8988/sym_cps/cps/order/send";
        String s =
            "{" + "\"k\":\"zero\"," + "\"code\":\"CPS3004\","
                + "\"oprNum\":\"abc001\"," + "\"ip\": \"10.100.16.59\","
                + "\"signature\": \"\","
                + "\"data\":{\"entryTime\":\"2016-04-28 10:00:00\","
                + "\"predictExitTime\":\"2016-04-28 11:00:00\","
                + "\"orderMoney\":190," + "\"plateNumber\":\"京A00919\","
                + "\"parkId\":\"3eac2b5c-723e-11e5-9fe3-44a8422565ee\","
                + "\"orderCode\":\"aaaa\"," + "\"carColor\":\"red\"" + "}"
                + "}";
        TransformForWS t = new TransformForWS();
        t.setK("zero");
        t.setCode("CPS3004");
        t.setOprNum("abc001");
        t.setIp("10.100.16.59");
        t.setSignature("");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("entryTime", "2016-04-28 10:00:00");
        map.put("predictExitTime", "2016-04-28 11:00:00");
        map.put("orderMoney", 190);
        map.put("plateNumber", "京A00919");
        map.put("parkId", "3eac2b5c-723e-11e5-9fe3-44a8422565ee");
        map.put("orderCode", "aaaa");
        map.put("carColor", "red");
        t.setData(map);
        /*
         * t.setData(
         * "{\"entryTime\":\"2016-04-28 10:00:00\","+
         * "\"predictExitTime\":\"2016-04-28 11:00:00\","+
         * "\"orderMoney\":190,"+
         * "\"plateNumber\":\"京A00919\","+
         * "\"parkId\":\"3eac2b5c-723e-11e5-9fe3-44a8422565ee\","+
         * "\"orderCode\":\"aaaa\","+
         * "\"carColor\":\"red\""
         * );
         */

        String data1 = "param=" + JsonUtil.toJson(t);
        System.out.println(data1);
        String sendHttpPOST1 = HttpUtil.sendHttpPOST(url1, data1);
        System.out.println(sendHttpPOST1);
        JSONObject fromObject1 = JSONObject.fromObject(sendHttpPOST1);
        @SuppressWarnings("unchecked")
        Map<String, Class> map1 = new HashMap<>();
        map1.put("data", Map.class);
        Map<String, Object> bean =
            (Map<String, Object>) JSONObject
                .toBean(fromObject1, Map.class, map);
        // Map<String,Object> bean2 = (Map<String,Object>) bean.get("data");
    }

}
