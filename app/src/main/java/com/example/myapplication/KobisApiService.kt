package com.example.myapplication

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface KobisApiService {
    @GET("searchDailyBoxOfficeList.json")
    fun getBoxOffice(
        @Query("key") apiKey: String,
        @Query("targetDt") targetDate: String,
//        @Query("itemPerPage") itemPerPage: String,
        @Query("multiMovieYn") multiMovie : String?,
        @Query("repNationCd") nationCode: String?
    ) : Call<BoxOfficeResponse>
}