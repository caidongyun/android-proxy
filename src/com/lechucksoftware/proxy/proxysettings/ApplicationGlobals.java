package com.lechucksoftware.proxy.proxysettings;

import android.app.Application;
import android.content.Intent;
import android.util.Log;
import com.lechucksoftware.proxy.proxysettings.constants.AndroidMarket;
import com.lechucksoftware.proxy.proxysettings.constants.Constants;
import com.lechucksoftware.proxy.proxysettings.db.DataSource;
import com.lechucksoftware.proxy.proxysettings.utils.EventReportingUtils;
import com.lechucksoftware.proxy.proxysettings.utils.Utils;
import com.shouldit.proxy.lib.*;
import com.shouldit.proxy.lib.log.LogWrapper;


public class ApplicationGlobals extends Application
{
    private static final String TAG = ApplicationGlobals.class.getSimpleName();

    private static ApplicationGlobals mInstance;
    private ProxyManager proxyManager;
//    private static ProxyConfiguration selectedConfiguration;
//    private static ProxyEntity selectedProxy;
    private DataSource dbManager;
    public AndroidMarket activeMarket;

    @Override
    public void onCreate()
    {
        super.onCreate();

        LogWrapper.startTrace(TAG,"STARTUP", Log.ERROR);

        mInstance = this;

        proxyManager = new ProxyManager(ApplicationGlobals.this);
        dbManager = new DataSource(ApplicationGlobals.this);

        activeMarket = Utils.getInstallerMarket(ApplicationGlobals.this);

        // SETUP Libraries
        EventReportingUtils.setup(ApplicationGlobals.this);
        APL.setup(ApplicationGlobals.this, EventReportingUtils.getInstance());

        LogWrapper.d(TAG, "Calling broadcast intent " + Constants.PROXY_SETTINGS_STARTED);
        sendBroadcast(new Intent(Constants.PROXY_SETTINGS_STARTED));
    }

    public static ApplicationGlobals getInstance()
    {
        if (mInstance == null)
        {
            EventReportingUtils.sendException(new Exception("Cannot find valid instance of ApplicationGlobals, trying to instanciate a new one"));
            mInstance = new ApplicationGlobals();
        }

        return mInstance;
    }

    public static ProxyManager getProxyManager()
    {
        if (getInstance().proxyManager == null)
        {
            EventReportingUtils.sendException(new Exception("Cannot find valid instance of ProxyManager, trying to instanciate a new one"));
            getInstance().proxyManager = new ProxyManager(getInstance());
        }

        return getInstance().proxyManager;
    }

    public static DataSource getDBManager()
    {
        if (getInstance().dbManager == null)
        {
            EventReportingUtils.sendException(new Exception("Cannot find valid instance of DataSource, trying to instanciate a new one"));
            getInstance().dbManager = new DataSource(getInstance());
        }

        return getInstance().dbManager;
    }

//    public static void setSelectedConfiguration(ProxyConfiguration selectedConfiguration)
//    {
//        ApplicationGlobals.selectedConfiguration = selectedConfiguration;
//    }
//
//    public static void setSelectedProxy(ProxyEntity selectedProxy)
//    {
//        ApplicationGlobals.selectedProxy = selectedProxy;
//    }
//
//    public static ProxyConfiguration getSelectedConfiguration()
//    {
//        return ApplicationGlobals.selectedConfiguration;
//    }
//
//    public static ProxyEntity getSelectedProxy()
//    {
//        return ApplicationGlobals.selectedProxy;
//    }
}
