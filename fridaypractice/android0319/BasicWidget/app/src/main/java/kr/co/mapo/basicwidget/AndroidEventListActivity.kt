package com.pyo.android.simple.widget

import kr.co.mapo.basicwidget.BaseActivity
import kr.co.mapo.basicwidget.EventPriorityActivity
import kr.co.mapo.basicwidget.SimpleViewCallBackEventActivity

abstract class AndroidEventListActivity:BaseActivity() {
    override fun displayActivityList () {
        addActionMap("3.1.뷰 콜백 이벤트구현", SimpleViewCallBackEventActivity::class.java)
        //addActionMap("3.2.액티비티 콜백 이벤트 구현" ,ActivityCallbackEventActivity .class);
        addActionMap("3.2.이벤트우선순위샘플", EventPriorityActivity::class.java)
        //addActionMap("3.4.터치이벤트체크",GestureTouchEventActivity.class);
    }
}