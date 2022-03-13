package com.hiaryabeer.restaurantreports.view;

import static com.hiaryabeer.restaurantreports.ImportData.detailCashReportList;
import static com.hiaryabeer.restaurantreports.ImportData.posType;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.hiaryabeer.restaurantreports.GeneralMethod;
import com.hiaryabeer.restaurantreports.GroupSales_Adapter;
import com.hiaryabeer.restaurantreports.ImportData;
import com.hiaryabeer.restaurantreports.R;
import com.hiaryabeer.restaurantreports.adapters.Cash_detail_adapter;
import com.hiaryabeer.restaurantreports.databinding.ActivityCashReportBinding;
import com.hiaryabeer.restaurantreports.model.detailCashReport;

import java.util.ArrayList;
import java.util.List;

public class CashReport extends AppCompatActivity {

    GeneralMethod generalMethod;
    EditText from_date_r;
    public static  ActivityCashReportBinding myBindingCash;
    ImportData importData;
    String posNo="0";
//    salesManSpinner_cash
    Cash_detail_adapter cash_detail_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myBindingCash= DataBindingUtil.setContentView(this, R.layout.activity_cash_report);

        setInitialView();
        generalMethod.setWindow();
    }

    private void setInitialView() {
        importData=new ImportData(this);
        generalMethod=new GeneralMethod(CashReport.this,CashReport.this);
        myBindingCash.fromDateR.setText(generalMethod.DateInToday());
        myBindingCash.toDateR.setText(generalMethod.DateInToday());
        myBindingCash.showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });
        myBindingCash.fromDateR.setOnClickListener(v ->{
            generalMethod.DateClick( myBindingCash.fromDateR);
        });
        myBindingCash.toDateR.setOnClickListener(v ->{
            generalMethod.DateClick( myBindingCash.toDateR);
        });
        posType=1;
        importData.getPosNo();

        myBindingCash.responPosCash.addTextChangedListener(new TextWatcher() {
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
                else if(editable.toString().equals("fill")){
                    fillRecyclerDetail();

                }
            }
        });
//        fillSp();
        myBindingCash.responPosCash.setText("fill");
    }

    private void fillRecyclerDetail() {
//        List<detailCashReport> listDetail = new ArrayList<>();
//        detailCashReport detailCashReport = new detailCashReport();
//        detailCashReport.setCNAME("name");
//        detailCashReport.setAMOUNT("100.333");
//        listDetail.add(detailCashReport);
        if (detailCashReportList.size() != 0) {
        cash_detail_adapter = new Cash_detail_adapter(detailCashReportList, this);
        myBindingCash.cashDetailRecycle.setAdapter(cash_detail_adapter);
    }

    }

    private void fillSp() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, importData.POSNOList);
        myBindingCash.salesManSpinnerCash.setAdapter(adapter);
    }

    private void getData() {
        if (myBindingCash.salesManSpinnerCash.getSelectedItem() != null)
        {
            posNo = myBindingCash.salesManSpinnerCash.getSelectedItem().toString();
        }
//        CONO=737&D1=01/01/2017&D2=31/01/2021&POSNO=0
        importData.fetchCallData(myBindingCash.fromDateR.getText().toString().trim(),myBindingCash.toDateR.getText().toString().trim(),posNo);
    }
}