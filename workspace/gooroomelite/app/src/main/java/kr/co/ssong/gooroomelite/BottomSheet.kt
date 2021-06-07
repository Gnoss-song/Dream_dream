package kr.co.ssong.gooroomelite

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kr.co.ssong.gooroomelite.databinding.DialogBottomSheetBinding

class BottomSheet(val owner : AppCompatActivity) : BottomSheetDialogFragment() {
    private lateinit var binding : DialogBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DialogBottomSheetBinding.inflate(layoutInflater)
        val manager = GridLayoutManager(context,5)
        with(binding.recyclerView) {
            layoutManager = manager
            adapter = BottomSheetAdapter(itemdata(),owner)
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        view?.findViewById<Button>(R.id.button_bottom_sheet)?.setOnClickListener {
            dismiss()
        }
    }

    private fun itemdata(): MutableList<Tag> {
        val itemdata = mutableListOf<Tag>()
        itemdata.add(Tag(R.drawable.ic_baseline_control_point_24,getString(R.string.text)))

        return itemdata
    }
}