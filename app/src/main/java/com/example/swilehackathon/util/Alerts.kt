package com.example.swilehackathon.util

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.drawable.Drawable

fun createAlertDialog(
    context: Context,
    title: String,
    message: String,
    cancelable: Boolean,
    positiveButtonLabel: String = "OK",
    positiveOnClickListener: DialogInterface.OnClickListener? = null,
    negativeButtonLabel: String? = null,
    negativeOnClickListener: DialogInterface.OnClickListener? = null,
    drawable: Drawable? = null
): AlertDialog {
    val alertDialog = AlertDialog.Builder(context)
    alertDialog.setTitle(title)
    alertDialog.setMessage(message)
    alertDialog.setCancelable(cancelable)
    alertDialog.setPositiveButton(positiveButtonLabel, positiveOnClickListener)
    negativeButtonLabel?.let { alertDialog.setNegativeButton(it, negativeOnClickListener) }
    drawable?.let { alertDialog.setIcon(it) }
    return alertDialog.create()
}

fun showErrorDialog(context: Context, message: String) {
    createAlertDialog(context, "Error", message, false).show()
}