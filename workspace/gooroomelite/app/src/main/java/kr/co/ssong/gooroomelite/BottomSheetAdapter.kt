package kr.co.ssong.gooroomelite

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class BottomSheetAdapter(val itemlist:MutableList<Tag>,val owner : AppCompatActivity) : RecyclerView.Adapter<BottomSheetAdapter.ViewHolderClass>() {
    private lateinit var color : String

    inner class ViewHolderClass(itemView:View) : RecyclerView.ViewHolder(itemView){
        val img = itemView.findViewById<ImageView>(R.id.img)
        val text = itemView.findViewById<TextView>(R.id.text)
        fun bind (itemlist:Tag){
            img.setImageResource(itemlist.img)
            text.text = text.text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.dialog,parent,false)
        return ViewHolderClass(root)
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        holder.bind(itemlist[position])
        holder.itemView.setOnClickListener {
            //다이얼로그띄우기 : EditText + 색 고르는거.//
                val mLogoutView =
                    LayoutInflater.from(owner).inflate(R.layout.selectcolor, null)
                val mBuilder = androidx.appcompat.app.AlertDialog.Builder(owner).setView(mLogoutView)
                val mAlertDialog = mBuilder.show().apply {
                    window?.setBackgroundDrawable(null)
                }
                val okButton = mLogoutView.findViewById<Button>(R.id.btn_ok)
                val cancelButton = mLogoutView.findViewById<Button>(R.id.btn_no)

                okButton.setOnClickListener {
                    //텍스트랑 컬러를 putextra로 보내서 표기하기.
                    val intent = Intent(owner,BottomSheet::class.java)
                    intent.putExtra("text",mLogoutView.findViewById<EditText>(R.id.editText).text)


                    Log.e("TEST","${mLogoutView.findViewById<EditText>(R.id.editText).text}")

                    Toast.makeText(owner, "설정되었습니다.", Toast.LENGTH_SHORT).show()


                    mAlertDialog.dismiss()
                }
                cancelButton.setOnClickListener {
                    Toast.makeText(owner, "취소되었습니다.", Toast.LENGTH_SHORT).show()
                    mAlertDialog.dismiss()
                }
            }

            Toast.makeText(owner,"테스트",Toast.LENGTH_SHORT).show()
        }


    override fun getItemCount():Int  {
        return itemlist.size
    }
}
