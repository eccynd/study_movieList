package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityMovieBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MovieActivity : AppCompatActivity() {

    private lateinit var movieAdapter: MovieAdapter
    private lateinit var recyclerView: RecyclerView
    private val API_KEY = "cf40937c42993f9848a67c596e0220cf"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val ActivityMovie = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(ActivityMovie.root)

        recyclerView = ActivityMovie.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchMovies(intent.getStringExtra("multiMovie").toString(), intent.getStringExtra("multiMovie").toString())
    }

    // 영화API 호출
    private fun fetchMovies(mutiMovie : String, nationCode : String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(KobisApiService::class.java)

        // 어제 날짜 가져오기
        val dateFormat = SimpleDateFormat("yyyymmdd", Locale.getDefault())
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, -1)
        val targetDate = dateFormat.format(calendar.time)

        apiService.getBoxOffice(API_KEY, targetDate, mutiMovie, nationCode).enqueue(object :
            Callback<BoxOfficeResponse> {
            override fun onResponse(
                call: Call<BoxOfficeResponse>,
                response: Response<BoxOfficeResponse>
            ) {
                if (response.isSuccessful) {
                    val movies =
                        response.body()?.boxOfficeResult?.dailyBoxOfficeList ?: emptyList()
                    movieAdapter = MovieAdapter(movies)
                    recyclerView.adapter = movieAdapter
                } else {
                    Log.e("API_ERROR", "Response Error: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<BoxOfficeResponse>, t: Throwable) {
                Log.e("API_ERROR", "Network Error: ${t.message}")
            }
        })
    }
}