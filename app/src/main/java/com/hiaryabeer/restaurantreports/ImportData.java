package com.hiaryabeer.restaurantreports;

import static com.hiaryabeer.restaurantreports.view.CashReport.myBindingCash;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.conn.HttpHostConnectException;
import com.hiaryabeer.restaurantreports.model.SoldQtyReportModel;
import com.hiaryabeer.restaurantreports.model.detailCashReport;
import com.hiaryabeer.restaurantreports.model.totalCashModel;
import com.hiaryabeer.restaurantreports.view.CashReport;
import com.hiaryabeer.restaurantreports.view.SoldQtyReport;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ImportData {
   String IpAddress="10.0.0.22";
   String ipPort="8085";
   String CoNo="734";
   public String headerDll = "", link = "";
   private Context context;
   private ProgressDialog progressDialog;
   public static ArrayList<SoldQtyReportModel>soldQtyreportModelList=new ArrayList<>();
   public static ArrayList<String>POSNOList=new ArrayList<>();
   public  ApiCash myAPI;
   public  static  int posType=0;
   public  static  List<detailCashReport> detailCashReportList=new ArrayList<>();
   public ImportData(Context context) {
      this.context = context;
      link = "http://" + IpAddress.trim() +":"+ ipPort.trim() + headerDll.trim();
      Retrofit retrofit = RetrofitInstance.getInstance(link);
      myAPI = retrofit.create(ApiCash.class);
   }
   public void getSoldQtyReportData(String PosNo,String FromD,String ToD) {

      if (!IpAddress.equals("")) {
         new JSONTask_GetSoldQtyReportData(PosNo, FromD, ToD).execute();

      }
   }

   public void getPosNo() {

      if (!IpAddress.equals("")) {
         new JSONTask_GetPosNo().execute();

      }
   }
   private class  JSONTask_GetPosNo extends AsyncTask<String, String, String> {

      private String custId = "", JsonResponse;

      @Override
      protected void onPreExecute() {
         super.onPreExecute();
         String do_ = "my";
         Log.e("onPreExecute", "onPreExecute");
      }

      @Override
      protected String doInBackground(String... params) {

         try {
            if (!IpAddress.equals("")) {


               link = "http://" + IpAddress.trim() +":"+ ipPort.trim() + headerDll.trim() + "/GetPosNo?CONO=" + CoNo.trim();

               Log.e("link", "" + link);
            }
         } catch (Exception e) {
            Log.e("Exception==",""+e.getMessage());
         }


         try {

            //*************************************

            String JsonResponse = null;
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI(link));

//

            HttpResponse response = client.execute(request);


            BufferedReader in = new BufferedReader(new
                    InputStreamReader(response.getEntity().getContent()));

            StringBuffer sb = new StringBuffer("");
            String line = "";
            Log.e("finalJson***Import", sb.toString());

            while ((line = in.readLine()) != null) {
               sb.append(line);
            }

            in.close();


            // JsonResponse = sb.toString();

            String finalJson = sb.toString();
            Log.e("finalJson***Import", finalJson);


//                JSONArray parentObject = new JSONArray(finalJson);

            return finalJson;


         }//org.apache.http.conn.HttpHostConnectException: Connection to http://10.0.0.115 refused
         catch (HttpHostConnectException ex) {
            ex.printStackTrace();
//                progressDialog.dismiss();

            Handler h = new Handler(Looper.getMainLooper());
            h.post(new Runnable() {
               public void run() {

                  Toast.makeText(context, "Ip Connection Failed", Toast.LENGTH_LONG).show();
               }
            });


            return null;
         } catch (Exception e) {
            e.printStackTrace();
            Log.e("Exception**", "" + e.getMessage());
//                progressDialog.dismiss();
            return null;
         }


         //***************************

      }

      @Override
      protected void onPostExecute(String respon) {
         super.onPostExecute(respon);

         JSONObject jsonObject1 = null;

         POSNOList.clear();
         if (respon != null) {
            if (respon.length() != 0) {

               if (respon.contains("Not Connected To DB")){
                  Handler h = new Handler(Looper.getMainLooper());
                  h.post(new Runnable() {
                     public void run() {

                        Toast.makeText(context, "Not Connected To DB", Toast.LENGTH_LONG).show();
                     }
                  });

               }
               else if (respon.contains("POSNO")) {
                  JSONArray array = null;


                  try {
                     array = new JSONArray(respon);
                  } catch (JSONException e) {
                     e.printStackTrace();
                  }


                  if (array.length()>0)

                     for (int i = 0; i < array.length(); i++) {

                        try {

                           jsonObject1 = array.getJSONObject(i);

                        } catch (JSONException e) {
                           e.printStackTrace();
                        }

                        try {

                           POSNOList.add(jsonObject1.getString("POSNO"));


                        } catch (JSONException e) {
                           e.printStackTrace();
                        }
                     }
                  if(posType==1)
                  {
                     myBindingCash.responPosCash.setText("POSNO");
                  }else
                  SoldQtyReport.POSNO_respon.setText("POSNO");
               }

            }

         }

      }

   }


   private class JSONTask_GetSoldQtyReportData extends AsyncTask<String, String, String> {
String PosNo,FromD,ToD;

      public JSONTask_GetSoldQtyReportData(String posNo, String fromD, String toD) {
         PosNo = posNo;
         FromD = fromD;
         ToD = toD;
      }

      @Override
      protected void onPreExecute() {
         try {
            Thread.sleep(1000);
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
         super.onPreExecute();
         progressDialog = new ProgressDialog(context);
         progressDialog.setCancelable(false);
         progressDialog.setMessage("Loading...");
         progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
         progressDialog.setProgress(0);
         progressDialog.show();
      }

      @Override
      protected String doInBackground(String... params) {

         try {
            if (!link.equals("")) {
            //   http://localhost:8085/GetSoldQty?CONO=734&POSNO=0&D1=01/01/2021&D2=31/01/2021

               link =
                 "http://" + IpAddress.trim() +":"+ ipPort.trim() +  headerDll.trim() +"/GetSoldQty?CONO="+CoNo.trim()+"&POSNO="+PosNo+"&D1="+FromD+"&D2="+ToD;

               Log.e("link", "" +  link );
            }
         } catch (Exception e) {
            progressDialog.dismiss();
         }

         try {

            //*************************************

            String JsonResponse = null;
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI(link));

//

            HttpResponse response = client.execute(request);


            BufferedReader in = new BufferedReader(new
                    InputStreamReader(response.getEntity().getContent()));

            StringBuffer sb = new StringBuffer("");
            String line = "";
            Log.e("finalJson***Import", sb.toString());

            while ((line = in.readLine()) != null) {
               sb.append(line);
            }

            in.close();


            // JsonResponse = sb.toString();

            String finalJson = sb.toString();
            Log.e("finalJson***Import", finalJson);




            return finalJson;


         }//org.apache.http.conn.HttpHostConnectException: Connection to http://10.0.0.115 refused
         catch (HttpHostConnectException ex)

         {
            ex.printStackTrace();
//                progressDialog.dismiss();
            progressDialog.dismiss();
            Handler h = new Handler(Looper.getMainLooper());
            h.post(new Runnable() {
               public void run() {

                  Toast.makeText(context, "Ip Connection Failed", Toast.LENGTH_LONG).show();
               }
            });


            return null;
         }
         catch (Exception e)

         {
            e.printStackTrace();
            Log.e("Exception", "" + e.getMessage());
//                progressDialog.dismiss();
            progressDialog.dismiss();
            return null;
         }


         //***************************

      }


      @Override
      protected void onPostExecute(String array) {
         super.onPostExecute(array);
         progressDialog.dismiss();
         JSONObject jsonObject1 = null;
         if (array != null) {
            if (array.contains("ITEMK")) {

               if (array.length() != 0) {
                  try {
                     JSONArray requestArray = null;
                     requestArray = new JSONArray(array);
                     soldQtyreportModelList.clear();
                     Log.e("requestArray==",""+requestArray.length());
                     for (int i = 0; i < requestArray.length(); i++)
                     {
                        Log.e("sss===","sssss");
                        SoldQtyReportModel soldQtyReportModel = new     SoldQtyReportModel();
                        jsonObject1 = requestArray.getJSONObject(i);
                        soldQtyReportModel.setKind(  jsonObject1.getString("ITEMK"));
                        soldQtyReportModel.setModel(  jsonObject1.getString("ITEMM"));
                        soldQtyReportModel.setGroup(  jsonObject1.getString("ITEMG"));

                        soldQtyReportModel.setItemCode(  jsonObject1.getString("ITEMOCODE"));
                        soldQtyReportModel.setItemname(  jsonObject1.getString("ITEMONAMEA"));
                        soldQtyReportModel.setDiscount(  jsonObject1.getString("DISC"));

                        soldQtyReportModel.setHINTS(  jsonObject1.getString("HINTS"));
                        soldQtyReportModel.setQty(  jsonObject1.getString("QTY"));
                        soldQtyReportModel.setGross(  jsonObject1.getString("GROSS"));

                        soldQtyReportModel.setTax(  jsonObject1.getString("TAX"));
                        soldQtyReportModel.setSERVICE(  jsonObject1.getString("SERVICE"));
                        soldQtyReportModel.setSERVICETAX ( jsonObject1.getString("SERVICETAX"));

                        soldQtyReportModel.setGrossPerc( jsonObject1.getString("GROSSPERC"));
                        soldQtyReportModel.setNetsales ( jsonObject1.getString("NET"));

                        soldQtyreportModelList.add(soldQtyReportModel);
                     }


                  } catch (JSONException e) {
                 Log.e("e==",e.getMessage());
                  }


               }



               Log.e("soldQtyreportModelList==",""+soldQtyreportModelList.size());



               SoldQtyReport.soldQty_Respon.setText("fill");       }
         } else {
            SoldQtyReport.soldQty_Respon.setText("nodata");
         }
      }

   }


   public void fetchCallData(String from,String toDat,String pos) {

      Call<List<totalCashModel>> myData = myAPI.gaCashInfo(CoNo,from,toDat,pos+"");

      myData.enqueue(new Callback<List<totalCashModel>>() {
         @Override
         public void onResponse(Call<List<totalCashModel>> call, Response<List<totalCashModel>> response) {
            if (!response.isSuccessful()) {
               Log.e("onResponse", "not=" + response.message());
            } else {
               myBindingCash.salesText.setText(response.body().get(0).getSALES());
               myBindingCash.returnedText.setText(response.body().get(0).getRETURNED());
               myBindingCash.netText.setText(response.body().get(0).getNET());

               fetchCashDetailData(from, toDat, pos);
//             Log.e("onResponse", "=" + response.body().get(0).getSALES());

            }
         }

         @Override
         public void onFailure(Call<List<totalCashModel>> call, Throwable throwable) {
            Log.e("onFailure", "=" + throwable.getMessage());
            Toast.makeText(context, "throwable"+throwable.getMessage(), Toast.LENGTH_SHORT).show();
         }
      });
   }
   public void fetchCashDetailData(String from,String toDat,String pos) {

      Call<List<detailCashReport>> myData = myAPI.gaCashInfoDetail(CoNo,from,toDat,pos+"");

      myData.enqueue(new Callback<List<detailCashReport>>() {
         @Override
         public void onResponse(Call<List<detailCashReport>> call, Response<List<detailCashReport>> response) {
            if (!response.isSuccessful()) {
               Log.e("onResponse", "not=" + response.message());
            } else {
               detailCashReportList.clear();
               detailCashReportList.addAll(response.body());
               myBindingCash.responPosCash.setText("fill");

             Log.e("onResponse", "gaCashInfoDetail=" + response.body().size());

            }
         }

         @Override
         public void onFailure(Call<List<detailCashReport>> call, Throwable throwable) {
            Log.e("onFailure", "=" + throwable.getMessage());
            Toast.makeText(context, "throwable"+throwable.getMessage(), Toast.LENGTH_SHORT).show();
         }
      });
   }
}
