package com.wd.launcher3;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LauncherActivity extends Activity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private IBinder mWindowToken;

    private WindowManager mWindowManager;
    private View mFolderOnlineAppsWindowView;
    private View mFolderCommonToolWindowView;

    private List<String> mInstalledPackagesName = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        View appSettings = findViewById(R.id.app_settings);
        appSettings.setOnClickListener(this);
        View folderOnlineApps = findViewById(R.id.folder_online_apps);
        folderOnlineApps.setOnClickListener(this);
        View appVDR = findViewById(R.id.app_vdr);
        appVDR.setOnClickListener(this);
        View appEBook = findViewById(R.id.app_ebook);
        appEBook.setOnClickListener(this);
        View folderCommonTool = findViewById(R.id.folder_common_tool);
        folderCommonTool.setOnClickListener(this);
        View appDriveAssist = findViewById(R.id.app_drive_assist);
        appDriveAssist.setOnClickListener(this);
        View appWeather = findViewById(R.id.app_weather);
        appWeather.setOnClickListener(this);
        View appPhoto = findViewById(R.id.app_photo);
        appPhoto.setOnClickListener(this);
        View appCarSetting = findViewById(R.id.app_car_setting);
        appCarSetting.setOnClickListener(this);
        View appBTPhone = findViewById(R.id.app_btphone);
        appBTPhone.setOnClickListener(this);
        View appMusic = findViewById(R.id.app_music);
        appMusic.setOnClickListener(this);
        View appAirConditioner = findViewById(R.id.app_air_conditioner);
        appAirConditioner.setOnClickListener(this);
        View appNavigation = findViewById(R.id.app_navigation);
        appNavigation.setOnClickListener(this);
        View appRadio = findViewById(R.id.app_radio);
        appRadio.setOnClickListener(this);

        mWindowManager = this.getSystemService(WindowManager.class);
        mWindowToken = appSettings.getWindowToken();
    }

    @Override
    protected void onResume() {
        super.onResume();

        getInstalledPackages();
    }

    private void getInstalledPackages() {
        final PackageManager packageManager = this.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                mInstalledPackagesName.add(pn);
            }
        }
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "onClick: enter.");
        switch (v.getId()) {
            case R.id.app_settings:
                startApp("com.wd.settings", "com.wd.settings.LauncherActivity");
                break;
            case R.id.folder_online_apps:
                mFolderOnlineAppsWindowView = addFolderWindow(R.layout.folder_online_apps);
                if(mFolderOnlineAppsWindowView != null) {
                    final View rootView = mFolderOnlineAppsWindowView;
                    View onlineAppsContainer = rootView.findViewById(R.id.online_apps_container);
                    onlineAppsContainer.setOnClickListener(this);
                    View appWeChat = rootView.findViewById(R.id.app_we_chat);
                    appWeChat.setOnClickListener(this);
                    View appHimalayan = rootView.findViewById(R.id.app_himalayan);
                    appHimalayan.setOnClickListener(this);
                    View appCoolMyMusic = rootView.findViewById(R.id.app_cool_my_music);
                    appCoolMyMusic.setOnClickListener(this);
                    View appQQBrowser = rootView.findViewById(R.id.app_qq_browser);
                    appQQBrowser.setOnClickListener(this);
                }
                break;
            case R.id.app_vdr:
                startApp("com.wd.vdr", "com.wd.vdr.LauncherActivity");
                break;
            case R.id.app_ebook:
                startApp("com.wd.ebook", "com.wd.ebook.LauncherActivity");
                break;
            case R.id.folder_common_tool:
                mFolderCommonToolWindowView = addFolderWindow(R.layout.folder_common_tool);
                if(mFolderCommonToolWindowView != null) {
                    final View rootView = mFolderCommonToolWindowView;
                    View commonToolContainer = rootView.findViewById(R.id.common_tool_container);
                    commonToolContainer.setOnClickListener(this);
                    View appCalendar = rootView.findViewById(R.id.app_calendar);
                    appCalendar.setOnClickListener(this);
                    View appCalculator = rootView.findViewById(R.id.app_calculator);
                    appCalculator.setOnClickListener(this);
                    View appSystemInfo = rootView.findViewById(R.id.app_system_info);
                    appSystemInfo.setOnClickListener(this);
                    View appVideo = rootView.findViewById(R.id.app_video);
                    appVideo.setOnClickListener(this);
                }
                break;
            case R.id.app_drive_assist:
                startApp("com.wd.driveassist", "com.wd.driveassist.LauncherActivity");
                break;
            case R.id.app_weather:
                startApp("com.wd.weather", "com.wd.weather.LauncherActivity");
                break;
            case R.id.app_photo:
                startApp("com.wd.photo", "com.wd.photo.LauncherActivity");
                break;
            case R.id.app_car_setting:
                startApp("com.wd.carsetting", "com.wd.carsetting.LauncherActivity");
                break;
            case R.id.app_btphone:
                startApp("com.wd.btphone", "com.wd.btphone.LauncherActivity");
                break;
            case R.id.app_music:
                startApp("com.wd.music", "com.wd.music.LauncherActivity");
                break;
            case R.id.app_air_conditioner:
                startApp("com.wd.airconditioner", "com.wd.airconditioner.LauncherActivity");
                break;
            case R.id.app_navigation:
                startApp("cn.jyuntech.map", "cn.jyuntech.map.ui.MainActivity");
                break;
            case R.id.app_radio:
                startApp("com.wd.radio", "com.wd.radio.LauncherActivity");
                break;
            // apps in folder_online_apps
            case R.id.online_apps_container:
                removeFolderWindow();
                break;
            case R.id.app_we_chat:
                startApp("com.txznet.webchat");
                break;
            case R.id.app_himalayan:
                startApp("com.ximalaya.ting.android.car");
                break;
            case R.id.app_cool_my_music:
                startApp("cn.kuwo.kwmusiccar");
                break;
            case R.id.app_qq_browser:
                startApp("com.tencent.mtt");
                break;
            // apps in folder_common_tool
            case R.id.common_tool_container:
                removeFolderWindow();
                break;
            case R.id.app_calendar:
                startApp("com.wd.calendar", "com.wd.calendar.LauncherActivity");
                break;
            case R.id.app_calculator:
                startApp("com.wd.calculator", "com.wd.calculator.LauncherActivity");
                break;
            case R.id.app_system_info:
                startApp("com.wd.usermanual", "com.wd.usermanual.LauncherActivity");
                break;
            case R.id.app_video:
                startApp("com.wd.video", "com.wd.video.LauncherActivity");
                break;
        }
    }

    private void startApp(String pkg, String cls) {
        if(mInstalledPackagesName.contains(pkg)) {
            Intent intent = new Intent();
            ComponentName component = new ComponentName(pkg, cls);
            intent.setComponent(component);
            intent.setAction(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            Toast.makeText(this, R.string.app_is_not_installed, Toast.LENGTH_SHORT).show();
        }
    }

    private void startApp(String pkg){
        PackageManager packageManager = getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage(pkg);
        if(intent == null) {
            Toast.makeText(this, R.string.app_is_not_installed, Toast.LENGTH_SHORT).show();
        } else {
            startActivity(intent);
        }
    }

    private View addFolderWindow(int resourceId) {
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_PANEL,
                WindowManager.LayoutParams.FLAG_SPLIT_TOUCH,
                PixelFormat.TRANSLUCENT);
        lp.token = mWindowToken;
        View windowView = LayoutInflater.from(this).inflate(resourceId, null);
        mWindowManager.addView(windowView, lp);

        return windowView;
    }

    private void removeFolderWindow() {
        if(mFolderOnlineAppsWindowView != null) {
            mWindowManager.removeView(mFolderOnlineAppsWindowView);
            mFolderOnlineAppsWindowView = null;
        }
        if(mFolderCommonToolWindowView != null) {
            mWindowManager.removeView(mFolderCommonToolWindowView);
            mFolderCommonToolWindowView = null;
        }
    }
}
