package com.example.starwars.utils

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.support.v7.app.AppCompatActivity

class loading : AppCompatActivity() {

    var progDailog: ProgressDialog? = null

    //metodo que apresenta loading
    // passa como parametro o metodo que irá ser implementado na atividade de maneira a correr neste thread
    fun showLoading(context: Context, function: () -> Unit) {
        runOnUiThread {
            progDailog = ProgressDialog(context, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT)
            progDailog?.setMessage("Aguarde por favor...")
            progDailog?.isIndeterminate = false
            progDailog?.setProgressStyle(ProgressDialog.STYLE_SPINNER)
            progDailog?.setCancelable(false)
            progDailog?.show()

            Thread(Runnable() {

                run() {
                    try {
                        Thread.sleep(100)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    Handler(Looper.getMainLooper()).post(Runnable() {
                        run() {
                            function()
                            progDailog?.dismiss()
                        }
                    })

                }
            }).start()
        }
    }



}