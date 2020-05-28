package com.hunter.hnotitydd;

import android.app.Application;
import com.hunter.anotifydd.PerformanceReportManager;

/**
 * Description:AppApplication
 *
 * @author mayuhai
 * date: 2020/5/14 11:19 AM
 */
public class AppApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initReport();
    }

    private void initReport() {
        PerformanceReportManager.getInstance().setContext(this);
    }
}
