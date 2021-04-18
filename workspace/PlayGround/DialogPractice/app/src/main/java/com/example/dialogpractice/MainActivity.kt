package com.example.dialogpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.dialogpractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.deleteBtn.setOnClickListener{
            val dialog = CustomDialog("정말 삭제하시겠습니까?",this)
            dialog.show()
        }

    }

    fun dialogCallback(flag:Boolean){
        if(flag == true){
            Toast.makeText(this,"삭제했습니다.",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this,"취소했습니다.",Toast.LENGTH_SHORT).show()
        }
    }

}