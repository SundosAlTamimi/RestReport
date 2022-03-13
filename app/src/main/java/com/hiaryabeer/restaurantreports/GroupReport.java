package com.hiaryabeer.restaurantreports;


import static com.hiaryabeer.restaurantreports.view.MainActivity.CONO_PREF;
import static com.hiaryabeer.restaurantreports.view.MainActivity.IP_PREF;
import static com.hiaryabeer.restaurantreports.view.MainActivity.IP_SETTINGS;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class GroupReport extends AppCompatActivity {

    private TextInputLayout groupTF;
    private TextView fromDateTV, toDateTV;
    private AutoCompleteTextView groupTV;
    private Button showBtn;
    private RecyclerView groupSalesRV;
    private TextView totalGross, totalDisc, totalTax, totService, totServiceTax;
    private GroupSales_Adapter groupSales_adapter;
    private List<GroupSales_Model> groupSales_List;
    private List<GroupSales_Model> tempSales_List;
    private List<String> groups;
    private SweetAlertDialog pDialog;
    public String headerDll = "", link = "";
    GeneralMethod generalMethod;
    private String ipAddress, coNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_report);

        init();
        generalMethod.setWindow();
        initDates();

        if (checkIpSettings()) {
            getAllSales(new GetSalesCallBack() {
                            @Override
                            public void onResponse(JSONArray response) {

                                groupSales_List.clear();
                                groups.clear();

                                try {

                                    for (int i = 0; i < response.length(); i++) {

                                        groupSales_List.add(new GroupSales_Model(
                                                response.getJSONObject(i).getString("ITEMK"),
                                                response.getJSONObject(i).getString("ITEMM"),
                                                response.getJSONObject(i).getString("ITEMG"),
                                                response.getJSONObject(i).getString("QTY"),
                                                response.getJSONObject(i).getString("GROSS"),
                                                response.getJSONObject(i).getString("GROSSSUM"),
                                                response.getJSONObject(i).getString("DISC"),
                                                response.getJSONObject(i).getString("TOTAFTRDISC"),
                                                response.getJSONObject(i).getString("TAX"),
                                                response.getJSONObject(i).getString("NET"),
                                                "",
                                                fromDateTV.getText().toString(),
                                                toDateTV.getText().toString(),
                                                response.getJSONObject(i).getString("SERVICE"),
                                                response.getJSONObject(i).getString("SERVICETAX")
                                        ));

                                        groups.add(response.getJSONObject(i).getString("ITEMG"));


                                    }

                                    updateAdapter();
                                    totalGross.setText(String.valueOf(groupSales_List.stream().map(GroupSales_Model::getGross).mapToDouble(Double::parseDouble).sum()));
                                    totalTax.setText(String.valueOf(groupSales_List.stream().map(GroupSales_Model::getTax).mapToDouble(Double::parseDouble).sum()));
                                    totalDisc.setText(String.valueOf(groupSales_List.stream().map(GroupSales_Model::getDiscount).mapToDouble(Double::parseDouble).sum()));
                                    totService.setText(String.valueOf(groupSales_List.stream().map(GroupSales_Model::getService).mapToDouble(Double::parseDouble).sum()));
                                    totServiceTax.setText(String.valueOf(groupSales_List.stream().map(GroupSales_Model::getService_tax).mapToDouble(Double::parseDouble).sum()));
                                    updateGroupList();

                                } catch (JSONException e) {

                                }


                            }

                            @Override
                            public void onError(String error) {

                            }
                        }, ipAddress, coNo,
                    fromDateTV.getText().toString(), toDateTV.getText().toString());
        } else {

            showSweetDialog(GroupReport.this, 3, "", "IP Address Not Found !");

        }

        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkIpSettings()) {

                    getAllSales(new GetSalesCallBack() {
                                    @Override
                                    public void onResponse(JSONArray response) {

                                        groupSales_List.clear();
                                        groups.clear();

                                        try {

                                            for (int i = 0; i < response.length(); i++) {

                                                groupSales_List.add(new GroupSales_Model(
                                                        response.getJSONObject(i).getString("ITEMK"),
                                                        response.getJSONObject(i).getString("ITEMM"),
                                                        response.getJSONObject(i).getString("ITEMG"),
                                                        response.getJSONObject(i).getString("QTY"),
                                                        response.getJSONObject(i).getString("GROSS"),
                                                        response.getJSONObject(i).getString("GROSSSUM"),
                                                        response.getJSONObject(i).getString("DISC"),
                                                        response.getJSONObject(i).getString("TOTAFTRDISC"),
                                                        response.getJSONObject(i).getString("TAX"),
                                                        response.getJSONObject(i).getString("NET"),
                                                        "",
                                                        fromDateTV.getText().toString(),
                                                        toDateTV.getText().toString(),
                                                        response.getJSONObject(i).getString("SERVICE"),
                                                        response.getJSONObject(i).getString("SERVICETAX")
                                                ));

                                                groups.add(response.getJSONObject(i).getString("ITEMG"));

                                            }

                                            updateAdapter();
                                            totalGross.setText(String.valueOf(groupSales_List.stream().map(GroupSales_Model::getGross).mapToDouble(Double::parseDouble).sum()));
                                            totalTax.setText(String.valueOf(groupSales_List.stream().map(GroupSales_Model::getTax).mapToDouble(Double::parseDouble).sum()));
                                            totalDisc.setText(String.valueOf(groupSales_List.stream().map(GroupSales_Model::getDiscount).mapToDouble(Double::parseDouble).sum()));
                                            totService.setText(String.valueOf(groupSales_List.stream().map(GroupSales_Model::getService).mapToDouble(Double::parseDouble).sum()));
                                            totServiceTax.setText(String.valueOf(groupSales_List.stream().map(GroupSales_Model::getService_tax).mapToDouble(Double::parseDouble).sum()));
                                            updateGroupList();

                                        } catch (JSONException e) {

                                        }


                                    }

                                    @Override
                                    public void onError(String error) {

                                    }
                                }, ipAddress, coNo,
                            fromDateTV.getText().toString(), toDateTV.getText().toString());
                } else {

                    showSweetDialog(GroupReport.this, 3, "", "IP Address Not Found !");

                }


            }
        });

        groupTV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                tempSales_List.clear();

                for (int i = 0; i < groupSales_List.size(); i++) {

                    if (groupSales_List.get(i).getGroup().equals(parent.getItemAtPosition(position))) {

                        tempSales_List.add(groupSales_List.get(i));

                    }

                }
                groupSales_adapter = new GroupSales_Adapter(tempSales_List, GroupReport.this);
                groupSalesRV.setAdapter(groupSales_adapter);

                totalGross.setText(String.valueOf(tempSales_List.stream().map(GroupSales_Model::getGross).mapToDouble(Double::parseDouble).sum()));
                totalTax.setText(String.valueOf(tempSales_List.stream().map(GroupSales_Model::getTax).mapToDouble(Double::parseDouble).sum()));
                totalDisc.setText(String.valueOf(tempSales_List.stream().map(GroupSales_Model::getDiscount).mapToDouble(Double::parseDouble).sum()));
                totService.setText(String.valueOf(tempSales_List.stream().map(GroupSales_Model::getService).mapToDouble(Double::parseDouble).sum()));
                totServiceTax.setText(String.valueOf(tempSales_List.stream().map(GroupSales_Model::getService_tax).mapToDouble(Double::parseDouble).sum()));

            }
        });


    }

    private void initDates() {

        Date currentTimeAndDate = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

        String curDate = dateFormat.format(currentTimeAndDate);

        fromDateTV.setText(curDate);
        toDateTV.setText(curDate);

        fromDateTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();

                new DatePickerDialog(GroupReport.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        fromDateTV.setText(dateFormat.format(calendar.getTime()));

                    }
                },
                        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        toDateTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();

                new DatePickerDialog(GroupReport.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        toDateTV.setText(dateFormat.format(calendar.getTime()));

                    }
                },
                        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

    }

    private boolean checkIpSettings() {

        SharedPreferences sharedPref = getSharedPreferences(IP_SETTINGS, MODE_PRIVATE);
        ipAddress = sharedPref.getString(IP_PREF, "");
        coNo = sharedPref.getString(CONO_PREF, "");

        Log.e("IP_PREF", ipAddress + "");
        Log.e("CONO_PREF", coNo);

        return !(ipAddress + "").trim().equals("") &&
                !(coNo + "").trim().equals("");

    }


    private interface GetSalesCallBack {

        void onResponse(JSONArray response);

        void onError(String error);

    }


    private void getAllSales(GetSalesCallBack getSalesCallBack, String ipAddress, String coNo,
                             String fromDate, String toDate) {

        pDialog = new SweetAlertDialog(GroupReport.this, SweetAlertDialog.PROGRESS_TYPE);

        pDialog.getProgressHelper().setBarColor(Color.parseColor("#31AFB4"));
        pDialog.setTitleText("Loading ...");
        pDialog.setCancelable(false);
        pDialog.show();

        // http://10.0.0.22:8085/GetGroupSales?CONO=734&D1=01/01/2021&D2=31/01/2021

        if (!ipAddress.equals("") || !coNo.equals(""))
            link = "http://" + ipAddress + "/GetGroupSales?CONO=" + coNo + "&D1=" + fromDate + "&D2=" + toDate;

        Log.e("getGroupSales_Link", link);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(link, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                getSalesCallBack.onResponse(response);
                pDialog.dismissWithAnimation();
//                GeneralMethod.showSweetDialog(context, 1, "Users Saved", null);
                Log.e("getGroupSales_Response", response + "");

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                getSalesCallBack.onError(error.getMessage() + "");
                pDialog.dismissWithAnimation();

                groupSales_List.clear();
                groups.clear();
                updateAdapter();
                totalGross.setText(String.valueOf(0.0));
                totalTax.setText(String.valueOf(0.0));
                totalDisc.setText(String.valueOf(0.0));
                totService.setText(String.valueOf(0.0));
                totServiceTax.setText(String.valueOf(0.0));
                updateGroupList();

                if ((error.getMessage() + "").contains("SSLHandshakeException") || (error.getMessage() + "").equals("null")) {

                    showSweetDialog(GroupReport.this, 0, null, "Connection to server failed");

                } else if ((error.getMessage() + "").contains("ConnectException")) {

                    showSweetDialog(GroupReport.this, 0, "Connection Failed", null);

                } else if ((error.getMessage() + "").contains("NoRouteToHostException")) {

                    showSweetDialog(GroupReport.this, 3, "", "Please check the entered IP info");

                } else if ((error.getMessage() + "").contains("No Data Found")) {

                    showSweetDialog(GroupReport.this, 3, "", "No Data Found");

                } else {

                    showSweetDialog(GroupReport.this, 3, "", error.getMessage() +"");

                }
                Log.e("getGroupSales_Error", error.getMessage() + "");

            }
        });

        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueueSingleton.getInstance(this.getApplicationContext()).addToRequestQueue(jsonArrayRequest);

    }

    private void init() {
        generalMethod=new GeneralMethod(GroupReport.this,GroupReport.this);

        groupTF = findViewById(R.id.groupTF);
        toDateTV = findViewById(R.id.toDateTV);
        fromDateTV = findViewById(R.id.fromDateTV);
        groupTV = findViewById(R.id.groupTV);
        groupTF = findViewById(R.id.groupTF);
        showBtn = findViewById(R.id.showBtn);
        groupSalesRV = findViewById(R.id.groupSalesRV);
        totalGross = findViewById(R.id.totalGross);
        totalTax = findViewById(R.id.totalTax);
        totalDisc = findViewById(R.id.totalDisc);
        totService = findViewById(R.id.totService);
        totServiceTax = findViewById(R.id.totServiceTax);

        groupSales_List = new ArrayList<>();
        tempSales_List = new ArrayList<>();
        groups = new ArrayList<>();

        //        headerDll = "/Falcons/VAN.Dll/";

    }

    private void updateAdapter() {

        groupSales_adapter = new GroupSales_Adapter(groupSales_List, this);
        groupSalesRV.setAdapter(groupSales_adapter);

    }

    private void updateGroupList() {

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, groups);

        Log.e("group_list", groups.toString());

        groupTV.setAdapter(adapter);

    }

    public static void showSweetDialog(Context context, int type, String title, String content) {
        switch (type) {
            case 0://Error Type
                new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText(title)
                        .setContentText(content)
                        .setConfirmText("OK")
                        .show();
                break;
            case 1://Success Type
                new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText(title)
                        .setContentText(content)
                        .setConfirmText("OK")
                        .show();
                break;
            case 3://warning Type
                new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText(title)
                        .setContentText(content)
                        .setConfirmText("OK")
                        .show();
                break;

        }
    }

}