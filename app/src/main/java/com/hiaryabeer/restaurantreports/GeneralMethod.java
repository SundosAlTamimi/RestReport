package com.hiaryabeer.restaurantreports;

import static android.content.Context.MODE_PRIVATE;
import static com.hiaryabeer.restaurantreports.view.MainActivity.CONO_PREF;
import static com.hiaryabeer.restaurantreports.view.MainActivity.IP_PREF;
import static com.hiaryabeer.restaurantreports.view.MainActivity.IP_SETTINGS;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.TextView;
import android.content.SharedPreferences;
import androidx.core.content.ContextCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class GeneralMethod {
    public  Context context;

    public static String IpAddress, CoNo;

    public  Activity myActivity;
    private Calendar myCalendar;
    public GeneralMethod(Context myContext, Activity activity){
        this.context=myContext;
        this.myActivity=activity;
        myCalendar = Calendar.getInstance();
    }
    public  void setWindow(){

        Window window = (myActivity).getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(context,R.color.primarytextcolor));

    }
    public  String DateInToday(){

        Date currentTimeAndDate = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String today = df.format(currentTimeAndDate);
        return convertToEnglish(today);
    }

    public void DateClick(TextView dateText){

        new DatePickerDialog(context, openDatePickerDialog(dateText), myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    public DatePickerDialog.OnDateSetListener openDatePickerDialog(final TextView DateText) {
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "dd/MM/yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                DateText.setText(sdf.format(myCalendar.getTime()));
            }

        };
        return date;
    }
    public String convertToEnglish(String value) {
        String newValue = (((((((((((value + "").replaceAll("١", "1")).replaceAll("٢", "2")).replaceAll("٣", "3")).replaceAll("٤", "4")).replaceAll("٥", "5")).replaceAll("٦", "6")).replaceAll("٧", "7")).replaceAll("٨", "8")).replaceAll("٩", "9")).replaceAll("٠", "0").replaceAll("٫", "."));
        return newValue;
    }
    public boolean checkIpSettings() {

        SharedPreferences sharedPref = context.getSharedPreferences(IP_SETTINGS, MODE_PRIVATE);
        IpAddress = sharedPref.getString(IP_PREF, "");
        CoNo = sharedPref.getString(CONO_PREF, "");

        Log.e("IP_PREF", IpAddress + "");
        Log.e("CONO_PREF", CoNo);

        return !(IpAddress + "").trim().equals("") &&
                !(CoNo + "").trim().equals("");

    }
}
