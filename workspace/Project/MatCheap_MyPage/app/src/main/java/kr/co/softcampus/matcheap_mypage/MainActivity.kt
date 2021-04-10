package kr.co.softcampus.matcheap_mypage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kr.co.softcampus.matcheap_mypage.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnDialog.setOnClickListener(View.OnClickListener() {
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.my_dialog,null)
            val mBuilder = AlertDialog.Builder(this).setView(mDialogView).setTitle("서비스정책 및 이용약관")
            val mAlertDialog = mBuilder.show()
            val okButton = mDialogView.findViewById<Button>(R.id.popup_ok)
            okButton.setOnClickListener{
                mAlertDialog.dismiss()
            }

        })
        binding.btnFavorite.setOnClickListener{
            val secondIntent = Intent(this,My0102::class.java)
            startActivity(secondIntent)

        }
        binding.btnMyreview.setOnClickListener{
            val thirdIntent = Intent(this,My0103::class.java)
            startActivity(thirdIntent)

        }
        binding.btnNotice.setOnClickListener{
            val fourIntent = Intent(this,My0104::class.java)
            startActivity(fourIntent)
        }

        binding.btnReport.setOnClickListener {
            val email = Intent(Intent.ACTION_SEND)
            email.type = "plain/Text"
            email.putExtra(Intent.EXTRA_EMAIL, R.string.email)
            email.putExtra(Intent.EXTRA_SUBJECT, "<" + getString(R.string.app_name) + " " + getString(R.string.report) + ">")
            email.putExtra(Intent.EXTRA_TEXT, "기기명 (Device):\n안드로이드 OS (Android OS):\n내용 (Content):\n")
            email.type = "message/rfc822"
            startActivity(email)

        }
        fun onBackPressed(){
            return
        }
    }
}


