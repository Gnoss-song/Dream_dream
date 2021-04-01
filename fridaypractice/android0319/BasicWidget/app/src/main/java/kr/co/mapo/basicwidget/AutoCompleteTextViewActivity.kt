package com.pyo.android.simple.widget
import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.MultiAutoCompleteTextView
import android.widget.Spinner
import android.widget.Toast
import kr.co.mapo.basicwidget.R
import kr.co.mapo.basicwidget.databinding.ActivityMainBinding

class AutoCompleteTextViewActivity:Activity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    lateinit var  bloodGroup:Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
//        bloodGroup = findViewById(R.id.spinner_id)
        val arrayBloodType = resources.getStringArray(R.array.blood_group)
        val spinnerAdapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_dropdown_item_1line,
            arrayBloodType)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        bloodGroup.adapter = spinnerAdapter

        bloodGroup.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(applicationContext,
                    spinnerAdapter.getItem(position)+"선택됨!",
                Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(applicationContext,
                    "혈액형이 선택 되지 않았네요!",
                    Toast.LENGTH_SHORT).show()
            }
        }

        //자동 완성 제시어 목록

        val autoColors : List<String> = mutableListOf("red", "green", "orange", "blue", "purple",
            "black", "yellow", "cyan", "magenta","white")
        //어댑터에 제시어 목록을 출력할 레이아웃 설정
        var adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_dropdown_item_1line,
            autoColors)
        var autoEdit : AutoCompleteTextView = findViewById(R.id.auto_complete_text_view)

        //위젯과 어댑터 연결

        autoEdit.setAdapter(adapter)

        var multiAutoEdit :MultiAutoCompleteTextView= findViewById(R.id.multi_auto_complete_text_view)
        multiAutoEdit.setAdapter(adapter)

        //제시어 목록을 구분할 구분자(콤마) 설정
        multiAutoEdit.setTokenizer(MultiAutoCompleteTextView.CommaTokenizer())
    }
}