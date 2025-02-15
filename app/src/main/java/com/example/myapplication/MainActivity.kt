package com.example.myapplication

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var multiMovie : String = ""
    private var nationCode : String = ""
    private var nationResult : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val ActivityMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(ActivityMain.root)

        val diversityMovieBtn = ActivityMain.diversityMovieBtn
        val commercialMovieBtn = ActivityMain.commercialMovieBtn

        diversityMovieBtn.setOnClickListener {
            multiMovie = "Y"
            diversityMovieBtn.setBackgroundColor(Color.RED)
        }

        commercialMovieBtn.setOnClickListener {
            nationCode = "K"
            commercialMovieBtn.setBackgroundColor(Color.RED)
        }

        val viewMovieButton = ActivityMain.viewMovieListBtn
        viewMovieButton.setOnClickListener {
            nationResult = ActivityMain.nationResultBox.toString()

            Log.d("viewMovieButton", "${multiMovie}, ${nationCode}")
            val intent = Intent(this, MovieActivity::class.java)
            intent.putExtra("multiMovie",multiMovie)
            intent.putExtra("nationCode",nationCode)

            // 검색기능 구현 해보기@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
            intent.putExtra("nationResult",nationResult)
            startActivity(intent)
        }
    }
}