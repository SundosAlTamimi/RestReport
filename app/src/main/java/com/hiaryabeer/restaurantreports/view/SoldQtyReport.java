package com.hiaryabeer.restaurantreports.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import com.hiaryabeer.restaurantreports.ImportData;
import com.hiaryabeer.restaurantreports.R;
import com.hiaryabeer.restaurantreports.adapters.SoldQtyReportAdapter;
import com.hiaryabeer.restaurantreports.model.SoldQtyReportModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class SoldQtyReport extends AppCompatActivity {
    ImportData importData;
    TextView FromDate, ToDate;
    Spinner PosNo_Sp,Group_Sp;
    Button Preivew;
    Calendar myCalendar;
    RecyclerView recyclerView;
  public static   TextView POSNO_respon,soldQty_Respon, NetTotal,totaldiscount, totalgross,Totaltax;
    String Pos_No, FromD, ToD;
    SoldQtyReportAdapter soldqtyRep_Adapter;
    double totaldiscount_=0,totalgross_=0,Totaltax_=0,NetTotal_=0;
    ArrayList<String>Grouplist=new ArrayList<>();
 ArrayList<SoldQtyReportModel>FiltersoldQtylist=new ArrayList<>();
 LinearLayout group_lin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sold_qty_report);
        init();
        myCalendar = Calendar.getInstance();
        group_lin.setVisibility(View.GONE);
        Date currentTimeAndDate = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String today = df.format(currentTimeAndDate);
        FromDate.setText(today);
        ToDate.setText(today);
        Group_Sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String d=Group_Sp.getSelectedItem().toString();
                if(i!=0){
                    for(int x=0;x<importData.soldQtyreportModelList.size();x++) {

                        if (d.equals(importData.soldQtyreportModelList.get(x).getGroup()))

                            FiltersoldQtylist.add(importData.soldQtyreportModelList.get(x));
                    }
                    Log.e("FiltersoldQtylist",FiltersoldQtylist.size()+"");
                    try {
                        fillRecApadter(FiltersoldQtylist);
                        notify();
                    }

              catch (Exception exception){
                  Log.e("exception==",exception.getMessage()+"");
              }

                }else
                {
                    fillRecApadter(importData.soldQtyreportModelList);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        FromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(SoldQtyReport.this, openDatePickerDialog(0), myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
        ToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(SoldQtyReport.this, openDatePickerDialog(1), myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        Preivew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( PosNo_Sp.getSelectedItem()!=null)
                Pos_No = PosNo_Sp.getSelectedItem().toString();
else             Pos_No ="";
                FromD = FromDate.getText().toString().trim();
                ToD = ToDate.getText().toString().trim();
                importData.getSoldQtyReportData(Pos_No, FromD, ToD);
            }
        });
    }

    void init() {
        group_lin=findViewById(R.id.group_lin);
        totaldiscount=findViewById(R.id.totaldiscount);
        totalgross=findViewById(R.id.totalgross);
        Group_Sp=findViewById(R.id.Group_Sp);
        Totaltax=findViewById(R.id.Totaltax);
        NetTotal=findViewById(R.id.NetTotal);
        totaldiscount.setText("");
        totalgross.setText("");
        Totaltax.setText("");
        recyclerView=findViewById(R.id.Rec);
        soldQty_Respon= findViewById(R.id.soldQty_Respon);
        POSNO_respon = findViewById(R.id.POSNO_respon);
        FromDate = findViewById(R.id.fromdate);
        ToDate = findViewById(R.id.todate);
        PosNo_Sp = findViewById(R.id.PosNO);
        Preivew = findViewById(R.id.Preivew);
        importData = new ImportData(SoldQtyReport.this);
        importData.getPosNo();
        POSNO_respon.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().equals("POSNO")) {
                    fillSp();
                }
            }
        });
        soldQty_Respon.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().equals("fill")) {
                    fillRecApadter(importData.soldQtyreportModelList);
                    Grouplist.clear();
                    Set<String> set = new HashSet<String>();
                    for(int i=0;i<importData.soldQtyreportModelList.size();i++) {

                        if(!Grouplist.contains(importData.soldQtyreportModelList.get(i)))
                            set.add(importData.soldQtyreportModelList.get(i).getGroup());
                                  }
                    Grouplist.add(0,"");
                    Grouplist.addAll(set);
                    group_lin.setVisibility(View.VISIBLE);
                    fillGroupSp();

                }else{

                }
            }
        });
    }

    private void fillSp() {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, importData.POSNOList);
        PosNo_Sp.setAdapter(adapter);



    }
    private void fillGroupSp() {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, Grouplist);
        Group_Sp.setAdapter(adapter);



    }
    private void  fillRecApadter(ArrayList<SoldQtyReportModel>soldQtyReportModels){
        Log.e("fffffff===",soldQtyReportModels.size()+"");
        recyclerView.setLayoutManager(new LinearLayoutManager(SoldQtyReport.this));

        soldqtyRep_Adapter    = new SoldQtyReportAdapter(soldQtyReportModels,SoldQtyReport.this);
        recyclerView.setAdapter(soldqtyRep_Adapter);
        totaldiscount_ = 0;
        totalgross_ = 0;
        Totaltax_ = 0;
        NetTotal_= 0;
       for(int i=0;i<soldQtyReportModels.size();i++) {


            totaldiscount_ += Double.parseDouble(soldQtyReportModels.get(i).getDiscount());
            totalgross_ += Double.parseDouble(soldQtyReportModels.get(i).getGross());
            Totaltax_ += Double.parseDouble(soldQtyReportModels.get(i).getTax());
            NetTotal_+=Double.parseDouble(soldQtyReportModels.get(i).getNetsales());
        }

        NetTotal.setText(new DecimalFormat("###.###").format(NetTotal_));
       // NetTotal.setText(NetTotal_+"");
        totaldiscount.setText(new DecimalFormat("###.###").format(totaldiscount_));
       // totaldiscount.setText(totaldiscount_+"");
        totalgross.setText(new DecimalFormat("###.###").format(totalgross_));
       // totalgross.setText(totalgross_+"");
        Totaltax.setText(new DecimalFormat("###.###").format(Totaltax_));
     //   Totaltax.setText(Totaltax_+"");

    }
    public DatePickerDialog.OnDateSetListener openDatePickerDialog(final int flag) {
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(flag);
            }

        };
        return date;
    }
    private void updateLabel(int flag) {

        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        if (flag == 0)
            FromDate.setText(sdf.format(myCalendar.getTime()));
        else
            ToDate.setText(sdf.format(myCalendar.getTime()));

    }
}