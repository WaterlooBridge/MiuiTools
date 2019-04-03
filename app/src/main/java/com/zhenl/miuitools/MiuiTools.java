package com.zhenl.miuitools;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class MiuiTools implements IXposedHookZygoteInit, IXposedHookLoadPackage {

    public void initZygote(StartupParam startupParam) throws Throwable {
        Theme.initZygote(startupParam);
    }

    public void handleLoadPackage(LoadPackageParam lpparam) throws Throwable {
        Theme.handleLoadPackage(lpparam);
    }
}
