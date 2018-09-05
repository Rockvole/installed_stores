import 'dart:async';

import 'package:flutter/services.dart';

enum StoreName { PLAY_STORE, AMAZON_STORE, SAMSUNG_STORE, APPLE_STORE }

class InstalledStores {
  static const MethodChannel _channel =
      const MethodChannel('installed_stores');


  static Future<Set<StoreName>> get getStoresSet async {
    Set<StoreName> storesSet = new Set();
    final List<dynamic> storesList = await _channel.invokeMethod('getInstalledStores');
    print(storesList);
    if(storesList.contains("PLAY_STORE")) storesSet.add(StoreName.PLAY_STORE);
    if(storesList.contains("AMAZON_STORE")) storesSet.add(StoreName.AMAZON_STORE);
    if(storesList.contains("SAMSUNG_STORE")) storesSet.add(StoreName.SAMSUNG_STORE);
    if(storesList.contains("APPLE_STORE")) storesSet.add(StoreName.APPLE_STORE);
    return storesSet;
  }
}
