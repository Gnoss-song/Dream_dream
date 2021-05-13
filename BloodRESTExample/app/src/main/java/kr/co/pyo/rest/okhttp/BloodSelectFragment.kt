package kr.co.pyo.rest.okhttp

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kr.co.pyo.rest.common.BLOOD_SELECT_TARGET_PATH
import kr.co.pyo.rest.data.BloodModel
import kr.co.pyo.rest.databinding.FragmentBloodSelectBinding
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class BloodSelectFragment : Fragment() {
    companion object {
        fun newInstance(): BloodSelectFragment {
            return BloodSelectFragment()
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
        Thread {
            val toServer: OkHttpClient
            lateinit var response: Response
            try {
                //URL Setting Get 방식에선 주로 이런 방식으로 세팅한다
                val targetURL: HttpUrl = OkHttpManager.getOkHttpUrl(BLOOD_SELECT_TARGET_PATH)

                toServer = OkHttpManager.getOkHttpClient()
                val request: Request = Request.Builder()
                    .url(targetURL)
                    .build()
                toServer.newCall(request).execute().also { response = it }

                if (response.isSuccessful) {
                    val gson = Gson()
                    val bloodModel = gson.fromJson(response.body!!.string(), BloodModel::class.java)
                    val bloodList = bloodModel.bloods

                    activity?.runOnUiThread {
                        with(binding.bloodSelectRV){
                            layoutManager = LinearLayoutManager(context)
                            adapter = BloodRecyclerAdapter(bloodList, activity as Activity)
                        }
                    }

                } else {
                    activity?.runOnUiThread {
                        Toast.makeText(context, "HTTP 문제 발생", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                activity?.runOnUiThread {
                    Toast.makeText(context, "예외발생 $e", Toast.LENGTH_SHORT).show()
                }
            }
            activity?.runOnUiThread {
                binding.progressbar.visibility = View.GONE
            }
        }.start()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}