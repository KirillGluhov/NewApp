package com.example.newapp

import android.content.Context
import android.view.View
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

class OrdinaryButton(private var name: String) {

    public fun makeButton(context: Context) : Button
    {
        val buttonUp = Button(context).apply {
            id = View.generateViewId()
            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                startToStart = ConstraintSet.PARENT_ID
                endToEnd = ConstraintSet.PARENT_ID
                topToTop = ConstraintSet.PARENT_ID
                topMargin = (10 * context.resources.displayMetrics.density).toInt()
                bottomMargin = (10 * context.resources.displayMetrics.density).toInt()
                leftMargin = (10 * context.resources.displayMetrics.density).toInt()
                rightMargin = (10 * context.resources.displayMetrics.density).toInt()
            }
            text = name;
            setBackgroundResource(R.drawable.button_choose_color);
            setTextAppearance(R.style.text);
        }

        return buttonUp;
    }
}