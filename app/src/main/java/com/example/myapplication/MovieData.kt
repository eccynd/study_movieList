package com.example.myapplication

data class BoxOfficeResponse(
    val boxOfficeResult: BoxOfficeResult
)

data class BoxOfficeResult(
    val dailyBoxOfficeList: List<Movie>
)

data class Movie(
    val rank: String,
    val movieNm: String,
    val openDt: String,
    val salesAmt: String,
    val audiCnt: String
)
