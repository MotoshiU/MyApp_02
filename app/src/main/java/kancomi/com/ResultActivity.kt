package kancomi.com

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kancomi.com.databinding.ActivityResultBinding

private lateinit var binding: ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val correctNum = intent.getStringExtra("correctNumber")
//        val correctNum = 4
//        Log.v("ResultActivity", "=========================================")
//        Log.v("ResultActivity", correctNum.toString())
//        Log.v("ResultActivity", "=========================================")
        binding.correctNumberTextView.text = "正解数は" + correctNum + "個でした"

        binding.btnBackTop.setOnClickListener{
            val intent = Intent(this, TopActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}