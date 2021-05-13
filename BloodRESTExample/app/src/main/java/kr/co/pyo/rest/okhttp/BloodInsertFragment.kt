@file:Suppress("DEPRECATION")

package kr.co.pyo.rest.okhttp

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import kr.co.pyo.rest.R
import kr.co.pyo.rest.common.SERVER_URL_BLOOD_INSERT
import kr.co.pyo.rest.data.BloodEntity
import kr.co.pyo.rest.databinding.FragmentInsertBloodBinding
import okhttp3.*
import org.json.JSONException
import org.json.JSONObject


class BloodInsertFragment : Fragment() {
    companion object {
        fun newInstance(): BloodInsertFragment {
            return BloodInsertFragment()
        }
    }

    /**
     * Fragment 바인딩은 반드시 onDestroyView 에서 자원을 해제한다
     */

    private var _binding: FragmentInsertBloodBinding? = null
    private val binding get() = _binding!!

    private val bloodDTO = BloodEntity()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInsertBloodBinding.inflate(inflater, container, false)

        val bloodTypeAdapter = activity?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.blood_array_item,
                android.R.layout.simple_dropdown_item_1line
            )
        }
        with(binding.bloodTypeAT){
            setAdapter(bloodTypeAdapter)
            setOnItemClickListener { adapterView, _, position, _ ->
                    bloodDTO.bloodType = adapterView.getItemAtPosition(position) as String
            }
        }

        val bloodStatusAdapter = activity?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.status_type_array_item,
                android.R.layout.simple_dropdown_item_1line
            )
        }
        with(binding.patientStatusAT){
            setAdapter(bloodStatusAdapter)
            setOnItemClickListener { adapterView, _, position, _ ->
                bloodDTO.bloodType = adapterView.getItemAtPosition(position) as String
            }
        }

        val patientRelationShipAdapter = activity?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.relation_array_item,
                android.R.layout.simple_dropdown_item_1line
            )
        }
        with(binding.patientRelationShipAT){
            setAdapter(patientRelationShipAdapter)
            setOnItemClickListener { adapterView, _, position, _ ->
                bloodDTO.relationText = adapterView.getItemAtPosition(position) as String
            }
        }

        val donationAdapter = activity?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.donation_type_array_item,
                android.R.layout.simple_dropdown_item_1line
            )
        }
        with(binding.donationAT){
            setAdapter(donationAdapter)
            setOnItemClickListener { adapterView, _, position, _ ->
                bloodDTO.donationType = adapterView.getItemAtPosition(position) as String
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.sendBtn.setOnClickListener {
            if(checkValidate()){
                bloodServerSend()
            }
        }
    }
    private fun checkValidate() : Boolean{
        var flag = false
        with(binding.bloodTypeAT){
            if(text.toString().isNotEmpty()){
                bloodDTO.bloodType = text.toString()
                flag = true
            }else{
                requestFocus()
            }
        }
        with(binding.patientNameET){
            if(text.toString().isNotEmpty()){
                bloodDTO.patientName = text.toString()
                flag = true
            }else{
                requestFocus()
            }
        }
        with(binding.bloodNumberET){
            if(text.toString().isNotEmpty()){
                bloodDTO.bloodValue = text.toString()
                flag = true
            }else{
                requestFocus()
            }
        }
        with(binding.hospitalNameET){
            if(text.toString().isNotEmpty()){
                bloodDTO.hospitalName = text.toString()
                flag = true
            }else{
                requestFocus()
            }
        }
        with(binding.hospitalPhoneNumberET){
            if(text.toString().isNotEmpty()){
                bloodDTO.hospitalPhone = text.toString()
                flag = true
            }else{
                requestFocus()
            }
        }
        with(binding.guardianNameET){
            if(text.toString().isNotEmpty()){
                bloodDTO.careName = text.toString()
                flag = true
            }else{
                requestFocus()
            }
        }
        with(binding.emergencyPhoneET){
            if(text.toString().isNotEmpty()){
                bloodDTO.careName = text.toString()
                flag = true
            }else{
                requestFocus()
            }
        }
        if(!flag){
            Toast.makeText(context, "입력하지 않은값이 있네요", Toast.LENGTH_SHORT).show()
        }
        return flag
    }
    private fun bloodServerSend(){
        val progressDialog = ProgressDialog.show(
            context,
            "서버입력중", "잠시만 기다려 주세요 ...", true
        )
        /**
         * 쓰레드 기반으로 시작한다
         */
        Thread{
            val flag: Boolean
            lateinit var response: Response
            val toServer: OkHttpClient

            try {
                toServer = OkHttpManager.getOkHttpClient()
                //요청 Form 세팅
                val postBody: RequestBody = FormBody.Builder()
                    .add("patientName", bloodDTO.patientName)
                    .add("bloodType", bloodDTO.bloodType)
                    .add("statusText", bloodDTO.statusText)
                    .add("donationType", bloodDTO.donationType)
                    .add("bloodValue", bloodDTO.bloodValue)
                    .add("hospital", bloodDTO.hospitalName)
                    .add("hospitalPhone", bloodDTO.hospitalPhone)
                    .add("relationText", bloodDTO.relationText)
                    .add("careName", bloodDTO.careName)
                    .add("carePhone", bloodDTO.carePhone)
                    .build()
                //요청 세팅(form(Query String) 방식의 포스트)
                val request: Request = Request.Builder()
                    .url(SERVER_URL_BLOOD_INSERT)
                    .post(postBody)
                    .build()
                //동기 방식
                response = toServer.newCall(request).execute()
                flag = response.isSuccessful
                var responseJSON = ""
                if (flag) { //성공했다면
                    responseJSON = response.body!!.string()
                    try {
                        val jsonObject = JSONObject(responseJSON)
                        activity?.runOnUiThread {
                            Toast.makeText(
                                context,
                                jsonObject.optString("result"),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } catch (json: JSONException) {
                        Log.e("ERROR", json.toString())
                    }
                } else {
                    activity?.runOnUiThread {
                        Toast.makeText(context, "HTTP 에러 발생", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                activity?.runOnUiThread {
                    Toast.makeText(context, "예외발생 $e", Toast.LENGTH_SHORT).show()
                }
            }
            activity?.runOnUiThread {
                progressDialog.dismiss()
            }
        }.start()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}