package com.hunter.anotifydd;

import android.content.Context;

import com.hunter.anotifydd.net.TCHttpURLClient;
import com.hunter.anotifydd.util.LogUtil;
import com.hunter.anotifydd.util.Utils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 性能标准通知
 * author: mayuhai
 * created on: 2019/6/20 12:29 PM
 */
public class PerformanceReportManager {
    private static PerformanceReportManager INSTANCE = new PerformanceReportManager();
    private Context context;
    /**
     * 保证只有一个CrashHandler实例
     */
    private PerformanceReportManager() {
    }

    /**
     * 获取CrashHandler实例 ,单例模式
     */
    public static PerformanceReportManager getInstance() {
        if (INSTANCE == null) {
            synchronized (PerformanceReportManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PerformanceReportManager();
                }
            }
        }
        return INSTANCE;
    }

    public PerformanceReportManager setContext(Context context) {
        this.context = context;
        return this;
    }

    public Context getContext() {
        return context;
    }

    /**
     *
     * @param Tag               创建机器人时选择匹配标签
     * @param exceptionStr      内容
     * @param reportDDRobotUrl  推送钉钉的webhook地址
     */
    public void notifyToDD(String Tag, String exceptionStr, String reportDDRobotUrl) {
        String body = "";
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("msgtype", "text");

            JSONObject jsonObjectContent = new JSONObject();
            jsonObjectContent.put("content", Tag + Utils.deviceInfo(context) + exceptionStr);
            jsonObject.put("text", jsonObjectContent.toString());

            body = jsonObject.toString();

            TCHttpURLClient.getInstance().postJson(reportDDRobotUrl, body, new TCHttpURLClient.OnHttpCallback() {
                @Override
                public void onSuccess(String result) {
                    LogUtil.d("notifyToDD", "succes");
                }

                @Override
                public void onError() {
                    LogUtil.d("notifyToDD", "onError");
                }
            });


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}


