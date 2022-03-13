package com.hiaryabeer.restaurantreports;

import com.hiaryabeer.restaurantreports.model.totalCashModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiCash {
//    http://10.0.0.22:8085/GetPOSCASH?CONO=737&D1=01/01/2017&D2=31/01/2021&POSNO=0
    @GET("GetPOSCASH")
    Call<List<totalCashModel>> gaCashInfo(@Query("CONO") String ComNo, @Query("D1") String from,
                                          @Query("D2") String toDate, @Query("POSNO") String Pos );
}
