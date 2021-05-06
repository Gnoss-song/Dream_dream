package kr.co.softcampus.thread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import kr.co.softcampus.thread.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //isRunning = false로 두어서 조건주기.
    var isRunning = false


    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.button.setOnClickListener{

            // currentTimeMillis :: 현재시간을 ms로 나타내주는 것.
            val now = System.currentTimeMillis()
            binding.textView.text = "버튼 클릭 : $now"
        }

//        while(true){
//            SystemClock.sleep(100)
//            val now2 = System.currentTimeMillis()
//            Log.d("test","while 문 : $now2")
//        }


        //isRunning을 true로 설정하고 조건을 부여하기 위함.
        isRunning= true

        //쓰레드 처리하는 과정. run을 이용해서 작동시킨다. 조건은 isRunning이 true일때.
        val thread1 = object : Thread() {
            override fun run() {
                super.run()
                while(isRunning){
                    SystemClock.sleep(100)
                    val now2 = System.currentTimeMillis()
                    Log.d("test","Thread:$now2")
                    binding.textView2.text = "Thread : $now2"
                }
            }
        }
        thread1.start()
    }

    // 액티비티가 사라질때 쓰레드도 제거하기 위해서 isRunning을 넣음.
    override fun onDestroy() {
        super.onDestroy()
        isRunning = false
    }
}