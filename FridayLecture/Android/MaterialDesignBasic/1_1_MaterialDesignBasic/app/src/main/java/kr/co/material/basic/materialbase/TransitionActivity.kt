package kr.co.material.basic.materialbase

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.transition.ChangeBounds
import androidx.transition.Transition
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import kr.co.material.basic.R

class TransitionActivity : AppCompatActivity() {
    private lateinit var checkBox: CheckBox
    private lateinit var firstBtn: Button
    private lateinit var secondBtn: Button
    private lateinit var thirdBtn: Button
    private lateinit var fourthBtn: Button
    private lateinit var relativeLayout: RelativeLayout
    private lateinit var transitionSet: TransitionSet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transition)

        val transitionsContainer = findViewById<LinearLayout>(R.id.transitionContainer)

        val buttonMessage = findViewById<TextView>(R.id.text)
        findViewById<Button>(R.id.btn).also {
            it.setOnClickListener(object : View.OnClickListener {
                var visible = false
                override fun onClick(v: View) {
                    TransitionManager.beginDelayedTransition(transitionsContainer)
                    visible = !visible
                    buttonMessage.visibility = if (visible) View.VISIBLE else View.GONE
                }
            })
        }
        checkBox = findViewById(R.id.checkBox)
        firstBtn = findViewById(R.id.firstBtn)
        secondBtn = findViewById(R.id.secondBtn)
        thirdBtn = findViewById(R.id.thirdBtn)
        fourthBtn = findViewById(R.id.fourthBtn)
        relativeLayout = findViewById(R.id.transitionLayout)
        transitionSet = TransitionSet()

        val first: Transition = ChangeBounds()
        val second: Transition = ChangeBounds()
        val third: Transition = ChangeBounds()
        val fourth: Transition = ChangeBounds()

        first.addTarget(firstBtn)
        second.setStartDelay(50).addTarget(secondBtn)
        third.setStartDelay(100).addTarget(thirdBtn)
        fourth.setStartDelay(150).addTarget(fourthBtn)

        transitionSet.addTransition(first).addTransition(second).addTransition(third).addTransition(fourth)

        val girlImage = findViewById<ImageView>(R.id.girlsImage)

        girlImage.setOnClickListener {
            val intent = Intent(this@TransitionActivity, TransitionTargetActivity::class.java)
            val options = ActivityOptions
                    .makeSceneTransitionAnimation(this@TransitionActivity, girlImage, "hero")
            startActivity(intent, options.toBundle())
        }
    }

    fun transitionEvent(view: View) {
        when (view.id) {
            R.id.firstBtn -> alignButtons(left = true, top = true)
            R.id.secondBtn -> alignButtons(false, true)
            R.id.thirdBtn -> alignButtons(true, false)
            R.id.fourthBtn -> alignButtons(false, false)
        }
    }

    private fun alignButtons(left: Boolean, top: Boolean) {

        if (checkBox.isChecked) { //TransitionSet 적용여부
            TransitionManager.beginDelayedTransition(relativeLayout, transitionSet)
        } else {
            TransitionManager.beginDelayedTransition(relativeLayout)
        }
        val oldAlignmentLR = if (left) RelativeLayout.ALIGN_PARENT_RIGHT else RelativeLayout.ALIGN_PARENT_LEFT
        val newAlignmentLR = if (left) RelativeLayout.ALIGN_PARENT_LEFT else RelativeLayout.ALIGN_PARENT_RIGHT
        val oldAlignmentTB = if (top) RelativeLayout.ABOVE else RelativeLayout.BELOW
        val newAlignmentTB = if (top) RelativeLayout.BELOW else RelativeLayout.ABOVE

        var params: RelativeLayout.LayoutParams = firstBtn.layoutParams as RelativeLayout.LayoutParams

        params.addRule(if (top) RelativeLayout.ALIGN_PARENT_BOTTOM else RelativeLayout.BELOW, 0)
        params.addRule(oldAlignmentLR, 0)
        params.addRule(if (top) RelativeLayout.BELOW else RelativeLayout.ALIGN_PARENT_BOTTOM, if (top) R.id.checkBox else 1)
        params.addRule(newAlignmentLR)
        firstBtn.layoutParams = params
        params = secondBtn.layoutParams as RelativeLayout.LayoutParams
        params.addRule(oldAlignmentLR, 0)
        params.addRule(oldAlignmentTB, 0)
        params.addRule(newAlignmentLR)
        params.addRule(newAlignmentTB, R.id.firstBtn)
        secondBtn.layoutParams = params
        params = thirdBtn.layoutParams as RelativeLayout.LayoutParams
        params.addRule(oldAlignmentLR, 0)
        params.addRule(oldAlignmentTB, 0)
        params.addRule(newAlignmentLR)
        params.addRule(newAlignmentTB, R.id.secondBtn)
        thirdBtn.layoutParams = params
        params = fourthBtn.layoutParams as RelativeLayout.LayoutParams
        params.addRule(oldAlignmentLR, 0)
        params.addRule(oldAlignmentTB, 0)
        params.addRule(newAlignmentLR)
        params.addRule(newAlignmentTB, R.id.thirdBtn)
        fourthBtn.layoutParams = params
    }
}