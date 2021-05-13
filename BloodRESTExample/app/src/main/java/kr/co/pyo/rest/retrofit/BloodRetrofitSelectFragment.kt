package kr.co.pyo.rest.retrofit

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresPermission
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kr.co.pyo.rest.common.BLOOD_SELECT_TARGET_PATH
import kr.co.pyo.rest.data.BloodEntity
import kr.co.pyo.rest.data.BloodModel
import kr.co.pyo.rest.databinding.FragmentBloodSelectBinding
import kr.co.pyo.rest.okhttp.BloodRecyclerAdapter
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Call
import retrofit2.Callback

class BloodRetrofitSelectFragment : Fragment() {
    companion object {
        fun newInstance(): BloodRetrofitSelectFragment {
            return BloodRetrofitSelectFragment()
        }
    }

    private var _binding: FragmentBloodSelectBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBloodSelectBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        selectBlood()
    }

    private fun selectBlood() {
        binding.progressbar.visibility = View.VISIBLE

        val call: Call<BloodModel> = RetrofitManager.BloodService.bloodService.requestBloodSelect()

        call.enqueue(object: Callback<BloodModel>{
            override fun onResponse(
                call: Call<BloodModel>,
                response: retrofit2.Response<BloodModel>
            ) {
                if(response.isSuccessful){
                    val bloodModel = response.body() as BloodModel
                    displayBlood(bloodModel.bloods)
                }
            }
            override fun onFailure(call: Call<BloodModel>, t: Throwable) {

            }
        })
    }
    private fun displayBlood(bloodList: MutableList<BloodEntity>){
        binding.progressbar.visibility = View.GONE
        with(binding.bloodSelectRV){
            layoutManager = LinearLayoutManager(context)
            adapter = BloodRecyclerAdapter(bloodList, activity as Activity)
        }
    }
}