package com.example.myapplication

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var diversityMovieBtnState : Boolean = false
    private var commercialMovieBtnState : Boolean = false
    private var nationResult : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val ActivityMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(ActivityMain.root)

        val diversityMovieBtn = ActivityMain.diversityMovieBtn
        val commercialMovieBtn = ActivityMain.commercialMovieBtn

        // 영화 타입 버튼 클릭 시 이벤트
        fun toggleButton(button : Button, btnState : Boolean): Boolean {
            var newState = !btnState
            var color = if (!newState) R.color.blue else R.color.red
            button.setBackgroundColor(ContextCompat.getColor(this, color))
            return newState
        }
        diversityMovieBtn.setOnClickListener {
            diversityMovieBtnState = toggleButton(diversityMovieBtn, diversityMovieBtnState)
        }
        commercialMovieBtn.setOnClickListener {
            commercialMovieBtnState = toggleButton(commercialMovieBtn, commercialMovieBtnState)
        }

        // 조회버튼 클릭
        val viewMovieButton = ActivityMain.viewMovieListBtn
        viewMovieButton.setOnClickListener {
            var multiMovie = ""
            if(!diversityMovieBtnState && commercialMovieBtnState){
                multiMovie = "N"
            } else if(diversityMovieBtnState && !commercialMovieBtnState) {
                multiMovie = "Y"
            }

            // 한국/외국 입력 데이터 가져오기
            nationResult = ActivityMain.nationResultBox.text.toString().trim()
            if (nationResult != null && nationResult.equals("한국")) {
                nationResult = "K"
            }
            if (nationResult != null && nationResult.equals("외국")) {
                nationResult = "F"
            }

            val intent = Intent(this, MovieActivity::class.java)
            intent.putExtra("multiMovie",multiMovie)
            // 검색기능 구현 해보기(공백 제거)
            intent.putExtra("nationResult", nationResult)
            startActivity(intent)
            Log.d("보고싶은 영화", "${multiMovie}, ${nationResult}")
        }
    }
}