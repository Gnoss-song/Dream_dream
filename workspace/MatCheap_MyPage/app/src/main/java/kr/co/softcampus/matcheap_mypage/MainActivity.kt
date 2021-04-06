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
            fun onBackPressed(){
                return
            }
        })

        val secondIntent = Intent(this,My0102::class.java)
        val thirdIntent = Intent(this,My0103::class.java)
        val forIntent = Intent(this,My0104::class.java)
        val fifthIntent = Intent(this,My0105::class.java)

        binding.btnFavorite.setOnClickListener{
            startActivity(secondIntent)
        }

        binding.btnMyreview.setOnClickListener{
            startActivity(thirdIntent)
        }

        binding.btnNotice.setOnClickListener{
            startActivity(forIntent)
        }

        binding.btnVersion.setOnClickListener{
            startActivity(fifthIntent)
        }


        }
}


