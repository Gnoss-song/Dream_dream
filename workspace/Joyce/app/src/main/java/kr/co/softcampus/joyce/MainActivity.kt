package kr.co.softcampus.joyce

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kr.co.softcampus.joyce.databinding.ActivityMainBinding
@SuppressLint("StaticFieldLeak")
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    lateinit var db : MemoDatabase
    var memoList = listOf<MemoEntity>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = MemoDatabase.getInstance(this)!!

        binding.button.setOnClickListener {
            val memo = MemoEntity(null,binding.edittextMemo.text.toString())
            insertMemo(memo)
        }
        binding.recyclerview.layoutManager=LinearLayoutManager(this)

    }

    //1. Insert Data
    //2. Get Data
    //3. Delete Data

    //4. Set RecyclerView


    fun insertMemo(memo : MemoEntity){
        //1. MainThread vs Background Thread

        val insertTask = object : AsyncTask<Unit,Unit,Unit>(){
            //insertTask를 execute를 할때 무슨일을 할지 정의를 하는것.
            override fun doInBackground(vararg params: Unit?) {
                db.memoDao().insert(memo)
            }

            //execute후에 뭘할지.
            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                getAllMemos()
            }
        }
    }
    fun getAllMemos(){
        val getTask = (object : AsyncTask<Unit,Unit,Unit>() {
            override fun doInBackground(vararg params: Unit?) {
                memoList=db.memoDao().getAll()
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                setRecyclerView(memoList)
            }
        }).execute()
    }
    fun deleteMemo(){

    }
    fun setRecyclerView(memoList:List<MemoEntity>){

    }
}