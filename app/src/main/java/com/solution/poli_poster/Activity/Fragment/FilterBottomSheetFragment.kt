package com.solution.poli_poster.Activity.Activity

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.solution.poli_poster.R

class FilterBottomSheetFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for the bottom sheet
        return inflater.inflate(R.layout.popup_filter_design, container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Create the dialog using BottomSheetDialog
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog

        // Apply custom rounded corners when the dialog is shown
        dialog.setOnShowListener {
            val bottomSheet = dialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet?.let { sheet ->
                // Set rounded background for the dialog
                sheet.setBackgroundResource(R.drawable.rounded_corner_background)

                // Adjust behavior to fit content and expand properly
                val behavior = BottomSheetBehavior.from(sheet)
                behavior.isFitToContents = true
            }
        }

        return dialog
    }
}
