@file:Suppress("DEPRECATION")

package kr.co.pyo.rest.retrofit

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import kr.co.pyo.rest.R
import kr.co.pyo.rest.data.BloodEntity
import kr.co.pyo.rest.data.OkFailResult
import kr.co.pyo.rest.databinding.FragmentInsertBloodBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BloodRetrofitInsertFragment : Fragment() {
    companion object {
        fun newInstance(): BloodRetrofitInsertFragment {
            return BloodRetrofitInsertFragment()
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
        with(binding.bloodTypeAT) {
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
        with(binding.patientStatusAT) {
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
        with(binding.patientRelationShipAT) {
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
        with(binding.donationAT) {
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
            if (checkValidate()) {
                bloodServerSend()
            }
        }
    }

    private fun checkValidate(): Boolean {
        var flag = false
        with(binding.bloodTypeAT) {
            if (text.toString().isNotEmpty()) {
                bloodDTO.bloodType = text.toString()
                flag = true
            } else {
                requestFocus()
            }
        }
        with(binding.patientNameET) {
            if (text.toString().isNotEmpty()) {
                bloodDTO.patientName = text.toString()
                flag = true
            } else {
                requestFocus()
            }
        }
        with(binding.bloodNumberET) {
            if (text.toString().isNotEmpty()) {
                bloodDTO.bloodValue = text.toString()
                flag = true
            } else {
                requestFocus()
            }
        }
        with(binding.hospitalNameET) {
            if (text.toString().isNotEmpty()) {
                bloodDTO.hospitalName = text.toString()
                flag = true
            } else {
                requestFocus()
            }
        }
        with(binding.hospitalPhoneNumberET) {
            if (text.toString().isNotEmpty()) {
                bloodDTO.hospitalPhone = text.toString()
                flag = true
            } else {
                requestFocus()
            }
        }
        with(binding.guardianNameET) {
            if (text.toString().isNotEmpty()) {
                bloodDTO.careName = text.toString()
                flag = true
            } else {
                requestFocus()
            }
        }
        with(binding.emergencyPhoneET) {
            if (text.toString().isNotEmpty()) {
                bloodDTO.careName = text.toString()
                flag = true
            } else {
                requestFocus()
            }
        }
        if (!flag) {
            Toast.makeText(context, "입력하지 않은값이 있네요", Toast.LENGTH_SHORT).show()
        }
        return flag
    }
    private lateinit var progressDialog: ProgressDialog
    private fun bloodServerSend() {
        progressDialog = ProgressDialog.show(
            context,
            "서버입력중", "잠시만 기다려 주세요 ...", true
        )

        val bloodService = RetrofitManager.BloodService.bloodService
        val result: Call<OkFailResult> = bloodService.insertBlood(
            bloodDTO.patientName,
            bloodDTO.bloodType,
            bloodDTO.statusText,
            bloodDTO.donationType,
            bloodDTO.bloodValue,
            bloodDTO.hospitalName,
            bloodDTO.hospitalPhone,
            bloodDTO.relationText,
            bloodDTO.careName,
            bloodDTO.carePhone
        )
        result.enqueue(object : Callback<OkFailResult> {
            override fun onResponse(
                call: Call<OkFailResult>,
                response: Response<OkFailResult>
            ) {
                 if(response.isSuccessful){
                     val okFail = response.body()
                     okFail?.let{ displayForeground(it) }
                 }

            }
            override fun onFailure(call: Call<OkFailResult>, t: Throwable) {
                activity?.runOnUiThread {
                    progressDialog.dismiss()
                }
            }
        })
    }
    private fun displayForeground(okFail: OkFailResult){
        progressDialog.dismiss()
        Toast.makeText(context, okFail.result, Toast.LENGTH_SHORT).show()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}