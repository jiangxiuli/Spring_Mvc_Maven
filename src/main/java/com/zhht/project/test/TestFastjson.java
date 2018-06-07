package com.zhht.project.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zhht.project.bean.AppCar;

public class TestFastjson {

    public static void main(String[] args) {
        AppCar appCar = new AppCar("", 2l, "", true, false, true, new Date(), false);
        int number = 1;
        Date date = new Date();
        // map
        Map<String, Object> testMap = new HashMap<String, Object>();
        testMap.put("int", 5);
        testMap.put("string", "testString");
        testMap.put("appCar", appCar);
        testMap.put("date", new Date());
        // list
        List<String> testList = new ArrayList<String>();
        testList.add("teacher1");
        testList.add("teacher2");
        testList.add("teacher3");
        // list1
        List<AppCar> testList1 = new ArrayList<AppCar>();
        testList1.add(appCar);
        testList1.add(appCar);
        testList1.add(appCar);

        // ToJsonString
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
        String jsonObject = JSON.toJSONString(appCar, SerializerFeature.WriteDateUseDateFormat);
        String jsonNumber = JSON.toJSONString(number, SerializerFeature.WriteDateUseDateFormat);
        String jsonMap = JSON.toJSONString(testMap, SerializerFeature.WriteDateUseDateFormat);
        String jsonDate = JSON.toJSONString(date, SerializerFeature.WriteDateUseDateFormat);
        String jsonList = JSON.toJSONString(testList, SerializerFeature.WriteDateUseDateFormat);
        String jsonList1 = JSON.toJSONString(testList1, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteDateUseDateFormat);

        // 打印
        System.out.println("jsonObject------------" + jsonObject);
        System.out.println("jsonNumber------------" + jsonNumber);
        System.out.println("jsonMap------------" + jsonMap);
        System.out.println("jsonDate------------" + jsonDate);
        System.out.println("jsonList------------" + jsonList);
        System.out.println("jsonList1------------" + jsonList1);
    }

}
