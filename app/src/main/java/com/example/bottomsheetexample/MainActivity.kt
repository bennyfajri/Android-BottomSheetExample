package com.example.bottomsheetexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_sheet_persistent.*

class MainActivity : AppCompatActivity() {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.isHideable = false
        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                btnBottomSheetPersistent.text = when (newState) {
                    BottomSheetBehavior.STATE_EXPANDED -> "Bottom Sheet is Expanded"
                    BottomSheetBehavior.STATE_COLLAPSED -> "Bottom Sheet is Collapsed"
                    BottomSheetBehavior.STATE_DRAGGING -> "Bottom Sheet is Dragging"
                    BottomSheetBehavior.STATE_HIDDEN -> "Bottom Sheet is Hidden"
                    BottomSheetBehavior.STATE_SETTLING -> "Bottom Sheet is Hidden"
                    else -> "Persistent Bottom Sheet"
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }
        })

        btnBottomSheetPersistent.setOnClickListener {
            val state =
                if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED)
                    BottomSheetBehavior.STATE_COLLAPSED
                else
                    BottomSheetBehavior.STATE_EXPANDED
            bottomSheetBehavior.state = state
        }

        btnBottomSheetModal.setOnClickListener {
            BottomSheetDialogFragment().apply {
                show(supportFragmentManager, tag)
            }
        }
    }
}