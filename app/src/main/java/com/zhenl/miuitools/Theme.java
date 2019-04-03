package com.zhenl.miuitools;

import java.lang.reflect.Field;
import java.util.HashMap;

import de.robv.android.xposed.IXposedHookZygoteInit.StartupParam;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
import miui.drm.DrmManager;
import miui.drm.ThemeReceiver;

public class Theme {
    public static void initZygote(StartupParam startupParam) throws Throwable {
        Field field = ThemeReceiver.class.getDeclaredField("sLocations");
        field.setAccessible(true);
        HashMap<String, String> sLocations = (HashMap<String, String>) field.get(null);
        sLocations.clear();
    }

    public static void handleLoadPackage(LoadPackageParam lpparam) throws Throwable {
        if (lpparam.packageName.equals("com.android.thememanager")) {
            XposedBridge.hookAllMethods(DrmManager.class, "isLegal", new XC_MethodReplacement() {
                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                    return DrmManager.DrmResult.DRM_SUCCESS;
                }
            });
            XposedBridge.hookAllMethods(DrmManager.class, "isPermanentRights", new XC_MethodReplacement() {
                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                    return true;
                }
            });
            XposedBridge.hookAllMethods(DrmManager.class, "isRightsFileLegal", new XC_MethodReplacement() {
                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                    return true;
                }
            });
        }
    }
}
