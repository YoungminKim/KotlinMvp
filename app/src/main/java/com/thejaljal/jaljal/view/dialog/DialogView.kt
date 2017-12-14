package com.thejaljal.jaljal.view.dialog

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.os.Handler
import android.os.Message

/**
 * Created by no.1 on 2017-11-13.
 */



object DialogView {

    var mDialog: ProgressDialog? = null

    /**
     * 프로그래스 다이얼로그 Show
     */
    fun progressDialogShow(ctx: Context) {

        try {
            if (mDialog != null) {
                progressDialogClose()
            }

            mDialog = ProgressDialog(ctx)
            mDialog?.isIndeterminate = false
            mDialog?.setMessage("잠시만 기다려주세요")
            mDialog?.setCancelable(false)
            mDialog?.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun progressDialogClose() {
        mDialog?.dismiss()
        mDialog = null

    }

    fun oneButtonDialogShow(context: Context, title: String?,
                            message: String?, btn1: String, handler: Handler?, msg1: Int) {
        val builder = AlertDialog.Builder(context)
        builder.setIcon(null)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setCancelable(false)
        builder.setPositiveButton(btn1) { dialog, which ->
            handler?.sendEmptyMessage(msg1)
            dialog.dismiss()
        }
        builder.create()
        builder.show()
    }

    fun oneButtonDialogShow(context: Context, icon: Int,
                            title: String, message: String, btn1: String, handler: Handler?,
                            msg1: Int) {
        val builder = AlertDialog.Builder(context)
        builder.setIcon(icon)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setCancelable(false)
        builder.setPositiveButton(btn1) { dialog, which ->
            handler?.sendEmptyMessage(msg1)
            dialog.dismiss()
        }
        builder.create()
        builder.show()

    }

    fun oneButtonDialogShow(ctx: Context, icon: Int, title: String,
                            content: String, btn1: String, handler: Handler?, msg1: Int,
                            msg2: Any?) {
        val dialog = AlertDialog.Builder(ctx)
        dialog.setIcon(icon)
        dialog.setTitle(title)
        dialog.setMessage(content)
        dialog.setCancelable(false)
        dialog.setPositiveButton(btn1) { dialog, which ->
            if (handler != null) {
                if (msg2 == null) {
                    handler.sendEmptyMessage(msg1)
                } else {
                    val message = Message.obtain()
                    message.what = msg1
                    message.obj = msg2
                    handler.sendMessage(message)
                }
            }
            dialog.dismiss()
        }
        dialog.create()
        dialog.show()
    }

    fun twoButtonDialogShow(context: Context,
                            title: String?, message: String?, btn1: String, btn2: String,
                            handler: Handler?, msg1: Int, msg2: Int) {
        val builder = AlertDialog.Builder(context)
        //builder.setIcon(icon);
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setCancelable(false)
        builder.setPositiveButton(btn1) { dialog, which ->
            handler?.sendEmptyMessage(msg1)
            dialog.dismiss()
        }
        builder.setNegativeButton(btn2) { dialog, which ->
            handler?.sendEmptyMessage(msg2)
            dialog.dismiss()
        }
        builder.create()
        builder.show()

    }

    fun twoButtonDialogShow(context: Context, icon: Int,
                            title: String, message: String, btn1: String, btn2: String,
                            handler: Handler?, msg1: Int, msg2: Int, flag: Boolean) {
        val builder = AlertDialog.Builder(context)
        //builder.setIcon(icon);
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setCancelable(flag)
        builder.setPositiveButton(btn1) { dialog, which ->
            handler?.sendEmptyMessage(msg1)
            dialog.dismiss()
        }
        builder.setNegativeButton(btn2) { dialog, which ->
            handler?.sendEmptyMessage(msg2)
            dialog.dismiss()
        }
        builder.create()
        builder.show()

    }

    fun twoButtonDialogShow(ctx: Context, icon: Int, title: String,
                            content: String, btn1: String, btn2: String, handler: Handler?,
                            msg1: Int, msg2: Int, `object`: Any) {
        val dialog = AlertDialog.Builder(ctx)
        dialog.setIcon(icon)
        dialog.setTitle(title)
        dialog.setMessage(content)
        dialog.setCancelable(false)
        dialog.setPositiveButton(btn1) { dialog, which ->
            if (handler != null) {

                val message = Message.obtain()
                message.what = msg1
                message.obj = `object`
                handler.sendMessage(message)

            }
            dialog.dismiss()
        }
        dialog.setNegativeButton(btn2) { dialog, which ->
            handler?.sendEmptyMessage(msg2)
            dialog.dismiss()
        }

        dialog.create()
        dialog.show()
    }

    fun threeButtonDialogShow(context: Context, icon: Int,
                              title: String, message: String, btn1: String, btn2: String,
                              btn3: String, handler: Handler?, msg1: Int, msg2: Int,
                              msg3: Int) {
        val builder = AlertDialog.Builder(context)
        builder.setIcon(icon)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setCancelable(false)
        builder.setPositiveButton(btn1) { dialog, which ->
            handler?.sendEmptyMessage(msg1)
            dialog.dismiss()
        }
        builder.setNeutralButton(btn2) { dialog, which ->
            handler?.sendEmptyMessage(msg2)
            dialog.dismiss()
        }
        builder.setNegativeButton(btn3) { dialog, which ->
            handler?.sendEmptyMessage(msg3)
            dialog.dismiss()
        }
        builder.create()
        builder.show()

    }

}
