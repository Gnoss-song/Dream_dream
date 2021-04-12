package kr.co.softcampus.matcheap_mypage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kr.co.softcampus.matcheap_mypage.databinding.ActivityMainBinding
import java.lang.StringBuilder


class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //서비스 정책 설정
        binding.btnDialog.setOnClickListener(View.OnClickListener() {
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.my_dialog, null)
            val mBuilder = AlertDialog.Builder(this).setView(mDialogView).setTitle("서비스정책 및 이용약관")
            val mAlertDialog = mBuilder.show()
            val okButton = mDialogView.findViewById<Button>(R.id.popup_ok)
            okButton.setOnClickListener {
                mAlertDialog.dismiss()
            }

            //액티비티 이동
        })
        binding.btnFavorite.setOnClickListener {
            val secondIntent = Intent(this, My0102::class.java)
            startActivity(secondIntent)

        }
        binding.btnMyreview.setOnClickListener {
            val thirdIntent = Intent(this, My0103::class.java)
            startActivity(thirdIntent)

        }
        binding.btnNotice.setOnClickListener {
            val fourIntent = Intent(this, My0104::class.java)
            startActivity(fourIntent)
        }

        //건의사항 이메일 보내기
        binding.btnReport.setOnClickListener {
            val sendEmail = Intent(Intent.ACTION_SEND)
            sendEmail.type = "plain/Text"
            sendEmail.putExtra(Intent.EXTRA_EMAIL, "<" + getString(R.string.email) + ">")
            sendEmail.putExtra(
                Intent.EXTRA_SUBJECT,
                "<" + getString(R.string.app_name) + " " + getString(R.string.report) + ">"
            )
            sendEmail.putExtra(
                Intent.EXTRA_TEXT,
                "기기명 (Device):\n안드로이드 OS (Android OS):\n내용 (Content):\n"
            )
            sendEmail.type = "message/rfc822"
            startActivity(sendEmail)
        }
        fun onBackPressed() {
            return
        }
    }

}


