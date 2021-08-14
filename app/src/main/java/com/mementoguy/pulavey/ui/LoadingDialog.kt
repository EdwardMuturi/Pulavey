package com.mementoguy.pulavey.ui

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import com.mementoguy.pulavey.R

/**
 * Created by Edward Muturi on 27/04/2021.
 */
class LoadingDialog(myActivity: Activity) {
    private val activity: Activity = myActivity
    private lateinit var dialog: AlertDialog

    fun startLoadingDialog(){
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)

        val inflater= activity.layoutInflater
        builder.setView(inflater.inflate(R.layout.layout_custom_dialog, null))
        builder.setCancelable(false)

        dialog= builder.create()
        dialog.show()
    }

    fun dismissDialog(){
        dialog.dismiss()
    }


}