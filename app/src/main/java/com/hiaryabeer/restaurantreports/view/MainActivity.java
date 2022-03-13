package com.hiaryabeer.restaurantreports.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.card.MaterialCardView;
import com.hiaryabeer.restaurantreports.GeneralMethod;
import com.hiaryabeer.restaurantreports.GroupReport;
import com.hiaryabeer.restaurantreports.MostRecentSalesReport;
import com.hiaryabeer.restaurantreports.R;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    MaterialCardView soldqtyrepCard, grouprepCard, cashrepCard, mostsalesepCard;
    ConstraintLayout ipSettings_layout;

    public final static String IP_SETTINGS = "IP_SETTINGS";
    public final static String IP_PREF = "IP_Address";
    public final static String CONO_PREF = "Company_No";

    private String ipAddress, coNo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        ipSettings_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showSettingsDialog();

            }
        });

        if (!checkIpSettings())
            showSettingsDialog();

    }

    private void showSettingsDialog() {

        BottomSheetDialog dialog = new BottomSheetDialog(MainActivity.this, R.style.SheetDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.ip_settings_dialog);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = getResources().getDisplayMetrics().widthPixels;
        dialog.getWindow().setAttributes(lp);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));



        dialog.show();

        TextInputLayout textInputIpAddress, textInputCoNo;
        EditText ipEdt, coNoEdt;
        Button submitBtn;

        textInputIpAddress = dialog.findViewById(R.id.textInputIpAddress);
        textInputCoNo = dialog.findViewById(R.id.textInputCoNo);
        ipEdt = dialog.findViewById(R.id.ipEdt);
        coNoEdt = dialog.findViewById(R.id.coNoEdt);
        submitBtn = dialog.findViewById(R.id.submitBtn);

        SharedPreferences sharedPref = getSharedPreferences(IP_SETTINGS, MODE_PRIVATE);

        ipEdt.setText(sharedPref.getString(IP_PREF, ""));
        coNoEdt.setText(sharedPref.getString(CONO_PREF, ""));

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ipAddress = ipEdt.getText().toString().trim();
                String coNo = coNoEdt.getText().toString().trim();

                if (!ipAddress.equals("")) {

                    if (!coNo.equals("")) {

                        SharedPreferences.Editor editor = getSharedPreferences(IP_SETTINGS, MODE_PRIVATE).edit();
                        editor.putString(IP_PREF, ipAddress);
                        editor.putString(CONO_PREF, coNo);
                        editor.apply();

                        dialog.dismiss();

                    } else {

                        textInputCoNo.setError("*Required");


                    }

                } else {

                    textInputIpAddress.setError("*Required");

                }


            }
        });

        ipEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                textInputIpAddress.setError(null);

            }
        });

        coNoEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                textInputCoNo.setError(null);

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


    void init() {
        soldqtyrepCard = findViewById(R.id.soldqtyrepCard);
        grouprepCard = findViewById(R.id.grouprepCard);
        cashrepCard = findViewById(R.id.cashrepCard);
        mostsalesepCard = findViewById(R.id.mostsalesepCard);
        ipSettings_layout = findViewById(R.id.ipSettings_layout);
        soldqtyrepCard.setOnClickListener(onClickListener);
        grouprepCard.setOnClickListener(onClickListener);
        cashrepCard.setOnClickListener(onClickListener);
        mostsalesepCard.setOnClickListener(onClickListener);
        GeneralMethod generalMethod = new GeneralMethod(MainActivity.this, MainActivity.this);
        generalMethod.setWindow();


    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {

                case R.id.soldqtyrepCard:
                    Intent intent1 = new Intent(MainActivity.this, SoldQtyReport.class);
                    startActivity(intent1);
                    break;

                case R.id.grouprepCard:
                    Intent intent2 = new Intent(MainActivity.this, GroupReport.class);
                    startActivity(intent2);
                    break;

                case R.id.cashrepCard:
                    Intent intent3 = new Intent(MainActivity.this, CashReport.class);
                    startActivity(intent3);
                    break;

                case R.id.mostsalesepCard:
                    Intent intent4 = new Intent(MainActivity.this, MostRecentSalesReport.class);
                    startActivity(intent4);
                    break;

            }

        }

    };

}