package kr.co.material.basic.materialwidget

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import kr.co.material.basic.R
import kr.co.material.basic.materialwidget.MaterialTextAndButtonActivity
import java.util.*

class MaterialTextAndButtonActivity : AppCompatActivity() {
    private lateinit var userID: TextInputEditText
    private lateinit var userPW: TextInputEditText

    companion object {
        var loginMaps: HashMap<String, String> = HashMap()
        init {
            loginMaps = HashMap()
            loginMaps["pyo"] = "123"
            loginMaps["in"] = "1234"
            loginMaps["soo"] = "12345"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material_text_and_button)
        userID = findViewById(R.id.userID)
        userPW = findViewById(R.id.userPW)
        val cancelBtn = findViewById<MaterialButton>(R.id.cancelBtn)
        cancelBtn.setOnClickListener {
            userID.text = null
            userID.error = null
            userPW.text = null
            userPW.error = null
        }
        val loginBtn = findViewById<MaterialButton>(R.id.loginBtn)
        loginBtn.setOnClickListener { checkIDAndPassword() }
    }

    private fun checkIDAndPassword() {
        val userKey = userID.text.toString()
        val userValue = userPW.text.toString()
        if (loginMaps.containsKey(userKey)) {
            val currentValue = loginMaps[userKey]
            if (userValue.equals(currentValue, ignoreCase = true)) {
                Toast.makeText(this@MaterialTextAndButtonActivity,
                        "로그인 성공", Toast.LENGTH_SHORT).show()
            } else {
                userPW.error = "패스워드가 다릅니다"
            }
        } else {
            userID.error = "ID가 존재하지 않습니다"
        }
    }
}