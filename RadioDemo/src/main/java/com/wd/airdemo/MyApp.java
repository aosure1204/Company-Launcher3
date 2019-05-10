package com.wd.airdemo;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Process;

import com.wd.ms.ITaskBinder;
import com.wd.ms.ITaskCallback;
import com.wd.ms.tools.MSTools;
import com.wd.airdemo.module.FinalMain;
import com.wd.airdemo.module.FinalRemoteModule;

public class MyApp extends Application {
    private static MyApp OBJ = new MyApp();
    private static boolean isBindMS = false;
    private ITaskBinder mMainModule;
    private static int mThemeNumber = 0;

    public static MyApp getOBJ() {
        return OBJ;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MSTools.getInstance().init(this, new MSTools.IConnectListener() {
            @Override
            public void onSuccess() {
                ITaskBinder module = MSTools.getInstance().getModule(FinalRemoteModule.MODULE_MAIN);
                try {
                    module.registerCallback(mCallback, FinalMain.U_SET_THEME, 1);
                } catch (android.os.RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailed() {
            }
        });

        mThemeNumber = readTheme();
    }

    private ITaskCallback.Stub mCallback = new ITaskCallback.Stub() {
        @Override
        public void update(int updateCode, int[] ints, float[] flts, String[] strs) {
            if (updateCode == FinalMain.U_SET_THEME) {
                int value = ints[0];
                if (mThemeNumber != value) {
                    saveTheme(value);
                    Process.killProcess(Process.myPid());
                }
            }
        }
    };

    private int readTheme() {
        SharedPreferences read = getSharedPreferences("theme", Context.MODE_PRIVATE);
        return read.getInt("theme_number", 0);
    }

    private void saveTheme(int value) {
        SharedPreferences.Editor editor = getSharedPreferences("theme", Context.MODE_PRIVATE).edit();
        editor.putInt("theme_number", value);
        editor.commit();
    }

    public static int getUserTheme() {
        return mThemeNumber;
    }

}
