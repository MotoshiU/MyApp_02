package kancomi.com

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kancomi.com.databinding.ActivityMainBinding
import java.util.*

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var quizNum = 0
    private var correctNum = 0
    private val textGermany = arrayOf("test", "test2", "test3", "test4")
    private val choices = arrayOf(
        arrayOf("テスト", "配列", "選択肢"),
        arrayOf("テスト2", "配列2", "選択2"),
        arrayOf("テスト3", "配列3", "選択3"),
        arrayOf("テスト4", "配列4", "選択4")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.questionNumberTextView.text = "第" + (quizNum + 1) + "問"
        binding.questionTextView.text = textGermany[0] + "の意味は？"

        val list = listOf(0, 1, 2)
        val num = list.shuffled()

        binding.btnChoice1.text = choices[0][num[0]]
        binding.btnChoice2.text = choices[0][num[1]]
        binding.btnChoice3.text = choices[0][num[2]]

        binding.btnNext.isEnabled = false

        binding.btnChoice1.setOnClickListener {
            if(binding.btnChoice1.text == choices[quizNum][0]) {
                correctAnswer()
            } else {
                incorrectAnswer()
            }
            btnSwitch()
        }

        binding.btnChoice2.setOnClickListener {
            if(binding.btnChoice2.text == choices[quizNum][0]) {
                correctAnswer()
            } else {
                incorrectAnswer()
            }
            btnSwitch()
        }

        binding.btnChoice3.setOnClickListener {
            if(binding.btnChoice3.text == choices[quizNum][0]) {
                correctAnswer()
            } else {
                incorrectAnswer()
            }
            btnSwitch()
        }

        binding.btnNext.setOnClickListener {
            quizNum++

            if (quizNum == choices.count()) {
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("correctNumber", correctNum.toString())
                startActivity(intent)
                finish()

            } else {
                val numNext = list.shuffled()

                binding.questionNumberTextView.text = "第" + (quizNum + 1) + "問"
                binding.questionTextView.text = textGermany[quizNum] + "の意味は？"

                binding.btnChoice1.text = choices[quizNum][numNext[0]]
                binding.btnChoice2.text = choices[quizNum][numNext[1]]
                binding.btnChoice3.text = choices[quizNum][numNext[2]]

                binding.answerTextView.text = ""
                binding.correctAnswerTextView.text = ""

                if (quizNum == (choices.count() - 1))
                    binding.btnNext.text = "Result"

                binding.btnChoice1.isEnabled = true
                binding.btnChoice2.isEnabled = true
                binding.btnChoice3.isEnabled = true
                binding.btnNext.isEnabled = false
            }
        }
    }

    private fun correctAnswer() {
        binding.answerTextView.text = "正解"
        binding.answerTextView.setTextColor(Color.RED)
        correctNum++
    }

    private fun incorrectAnswer() {
        binding.answerTextView.text = "不正解"
        binding.answerTextView.setTextColor(Color.BLUE)
        binding.correctAnswerTextView.text = "正解は、" + choices[quizNum][0]
    }

    private fun btnSwitch() {
        binding.btnChoice1.isEnabled = false
        binding.btnChoice2.isEnabled = false
        binding.btnChoice3.isEnabled = false
        binding.btnNext.isEnabled = true
    }
}