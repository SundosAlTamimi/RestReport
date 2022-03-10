package com.hiaryabeer.restaurantreports;

import android.content.Context;

public class ImportData {
   String IpAddress;
   String ipPort;
   String CoNo;
   public String headerDll = "", link = "";
   private Context context;

   public ImportData(Context context) {
      this.context = context;
   }
}
