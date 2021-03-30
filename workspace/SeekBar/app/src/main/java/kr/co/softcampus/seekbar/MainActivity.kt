package kr.co.softcampus.seekbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            textView.text = "SeekBar 1 : ${seekBar.progress}"
            textView2.text = "SeekBar 2 : ${seekBar2.progress}"
        }
        button2.setOnClickListener {
            seekBar.progress = 3
            seekBar2.progress = 8
        }
        button3.setOnClickListener {
            seekBar.incrementProgressBy(1)
            seekBar2.incrementProgressBy(1)
        }
        button4.setOnClickListener {
            seekBar.incrementProgressBy(-1)
            seekBar2.incrementProgressBy(-1)
        }
        seekBar.setOnSeekBarChangeListener(listener1)
        seekBar2.setOnSeekBarChangeListener(listener1)
    }

    val listener1 = object : SeekBar.OnSeekBarChangeListener {
        // ProgressBar의 값이 변경되었을때
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            when (seekBar?.id) {
                R.id.seekBar -> {
                    textView.text = "첫 번째 SeekBar : ${progress}로 설정되었습니다.\n"
                }
                R.id.seekBar2 -> {
                    textView.text = "두 번째 SeekBar : ${progress}로 설정되었습니다.\n"
                }
            }
            if (fromUser == true) {
                textView2.append("사용자에 의해 설정되었습니다.")
            } else {
                textView2.append("코드를 통해 설정되었습니다. ")
            }
        }

        //사용자가 터치 했을 때
        override fun onStartTrackingTouch(seekBar: SeekBar?) {
            when (seekBar?.id) {
                R.id.seekBar -> {
                    textView2.text = "첫 번째 SeekBar 사용자 터치 시작"
                }
                R.id.seekBar2 -> {
                    textView2.text = "두 번째 SeekBar 사용자 터치 시작"
                }
            }
        }

        //사용자가 터치를 끝냈을 때
        override fun onStopTrackingTouch(seekBar: SeekBar?) {
            when (seekBar?.id) {
                R.id.seekBar -> {
                    textView2.text = "첫 번째 SeekBar 사용자 터치 종료"
                }
                R.id.seekBar2 -> {
                    textView2.text = "두 번째 SeekBar 사용자 터치 종료"
                }

            }
        }
    }
}
