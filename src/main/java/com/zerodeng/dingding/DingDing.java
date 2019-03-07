package com.zerodeng.dingding;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * @ClassName:DingDing
 * @Description:TODO 钉钉机器人
 * @Author:ZeroDeng
 * @Date:2018-12-13 15:31
 * @Version:1.0
 **/
public
class  DingDing {
    private static Logger logger = Logger.getLogger(DingDing.class);

    public static String TOKEN = "46064a86087d3df96aaf3f20320e9ab7b6136ccb7cd8efe65548ed9a2faddfcb";
    public static String WEBHOOK= "https://oapi.dingtalk.com/robot/send?access_token="+TOKEN;

    private static String SystemName = "";

    public DingDing(String SystemName){
        SystemName = SystemName;
    }

    public DingDing(){

    }

    /**
    * @Author ZeroDeng
    * @Description TODO 钉钉机器人发送消息
    * @Date 16:01 2018-12-13
    * @Param [SystemName, ExceptionMsg, UserPhone]
    * @return java.lang.String
    * @Version 1.0
    **/
    public static String sendMsg(String SystemName,String ExceptionMsg,List<String> UserPhone){
        String result = "";
        System.out.println(UserPhone.toString());
        HttpClient httpclient = HttpClients.createDefault();

        HttpPost httppost = new HttpPost(WEBHOOK);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");

        String Msg ="{\n" +
                "     \"msgtype\": \"text\",\n" +
                "     \"text\": {\n" +
                "         \"content\": \"["+SystemName+"]异常信息:"+ExceptionMsg+"\"\n" +
                "     },\n" +
                "     \"at\": {\n" +
                "         \"atMobiles\": "+UserPhone+", \n" +
                "         \"isAtAll\": false\n" +
                "     }\n" +
                "}";

        StringEntity se = new StringEntity(Msg, "utf-8");
        httppost.setEntity(se);

        try {
            HttpResponse response = httpclient.execute(httppost);
            if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
                result= EntityUtils.toString(response.getEntity(), "utf-8");
            }
        }catch (Exception E){
            logger.error("钉钉机器人消息通知失败："+E.getMessage());
        }

        return result;
    }

    public static void main(String args[]) {
        List<String> phone =new ArrayList<>();
        phone.add("18301057176");
        phone.add("18301057169");
        sendMsg("测试系统","aggadgadsga",phone);
    }


}
