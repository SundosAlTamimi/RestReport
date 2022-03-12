package com.hiaryabeer.restaurantreports;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.hiaryabeer.restaurantreports.databinding.ActivityCashReportBinding;

public class CashReport extends AppCompatActivity {

    GeneralMethod generalMethod;
    EditText from_date_r;
    ActivityCashReportBinding myBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myBinding= DataBindingUtil.setContentView(this,R.layout.activity_cash_report);

        setInitialView();
        generalMethod.setWindow();
    }

    private void setInitialView() {
        generalMethod=new GeneralMethod(CashReport.this,CashReport.this);
        myBinding.fromDateR.setText(generalMethod.DateInToday());
        myBinding.toDateR.setText(generalMethod.DateInToday());
        myBinding.showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });
    }

    private void getData() {
    }
}