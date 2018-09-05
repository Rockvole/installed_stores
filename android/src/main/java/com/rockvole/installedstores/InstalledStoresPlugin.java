package com.rockvole.installedstores;

import android.content.Context;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.List;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/** InstalledStoresPlugin */
public class InstalledStoresPlugin implements MethodCallHandler {
  /** Plugin registration. */
  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "installed_stores");
    channel.setMethodCallHandler(new InstalledStoresPlugin(registrar));
  }

  private final Registrar mRegistrar;

  private InstalledStoresPlugin(Registrar registrar) {
    this.mRegistrar = registrar;
  }

  @Override
  public void onMethodCall(MethodCall call, Result result) {
    if (call.method.equals("getInstalledStores")) {
      List<String> list = new ArrayList<>();
      if(isPackageInstalled(mRegistrar.context(), "com.android.vending")) list.add("PLAY_STORE");
      if(isPackageInstalled(mRegistrar.context(), "com.amazon.venezia")) list.add("AMAZON_STORE");
      if(isPackageInstalled(mRegistrar.context(), "com.sec.android.app.samsungapps")) list.add("SAMSUNG_STORE");
      result.success(list);
    } else {
      result.notImplemented();
    }
  }

  public static final boolean isPackageInstalled(Context ctx, String packageName) {
    try {
      ctx.getPackageManager().getPackageInfo(packageName, 0);
    } catch (PackageManager.NameNotFoundException e) {
      return false;
    }
    return true;
  }
}
