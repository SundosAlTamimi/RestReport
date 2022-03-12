package com.hiaryabeer.restaurantreports;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.card.MaterialCardView;

public class MainActivity extends AppCompatActivity {
    MaterialCardView soldqtyrepCard,grouprepCard,cashrepCard,mostsalesepCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    void init(){
        soldqtyrepCard=findViewById(R.id.soldqtyrepCard);
        grouprepCard=findViewById(R.id.grouprepCard);
        cashrepCard=findViewById(R.id.cashrepCard);
        mostsalesepCard=findViewById(R.id.mostsalesepCard);
        soldqtyrepCard.setOnClickListener(onClickListener);
        grouprepCard.setOnClickListener(onClickListener);
        cashrepCard.setOnClickListener(onClickListener);
        mostsalesepCard.setOnClickListener(onClickListener);
        GeneralMethod generalMethod=new GeneralMethod(MainActivity.this,MainActivity.this);
        generalMethod.setWindow();


    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {

                case R.id.soldqtyrepCard:
                    Intent intent1 = new Intent(MainActivity.this, SoldQtyReport.class);
                    startActivity(intent1);
                    break ;

                case R.id.grouprepCard:
                    Intent intent2 = new Intent(MainActivity.this, GroupReport.class);
                    startActivity(intent2);
                    break ;

                case R.id.cashrepCard:
                    Intent intent3 = new Intent(MainActivity.this, CashReport.class);
                    startActivity(intent3);
                    break ;

                case R.id.mostsalesepCard:
                    Intent intent4 = new Intent(MainActivity.this, MostRecentSalesReport.class);
                    startActivity(intent4);
                    break ;

            }

        }

    };

}