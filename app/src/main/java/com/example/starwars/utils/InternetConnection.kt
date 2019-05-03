package com.example.starwars.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.widget.TextView
import java.util.*


class InternetConnection : Activity() {
    //-----------------------------------------
    //
    // Verifica se o dispositivoID tem ligação à internet
    //-------------------------------------------
    //
    fun isConnected(context: Context): Boolean {

        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netinfo = Objects.requireNonNull(cm).activeNetworkInfo
        val networks = cm.allNetworks

        if (netinfo != null && netinfo.isConnected) {
            val wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
            val mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)

            //Verifica se tem  dados moveis ou wifi ativos
            return ((mobile != null && mobile.isConnected) || (wifi != null && wifi.isConnected))


        } else
            return false
    }

    fun buildDialog(context: Context, function: () -> Unit, functionBack: () -> Unit) {
        val builder = AlertDialog.Builder(context, AlertDialog.THEME_HOLO_LIGHT)
        val textView = TextView(context)

        textView.textSize = 18f

        textView.setTextColor(context.resources.getColor(com.example.starwars.R.color.black))
        textView.setPadding(55, 25, 55, 5)

        textView.text =
            "\nSem ligação à internet.\n"

        builder.setCustomTitle(textView)

        builder.setCancelable(false)

        builder.setPositiveButton("Cancelar") { dialog, which -> functionBack() }

        builder.setNegativeButton("Conectar Wi-Fi") { dialog, which ->
            val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
            wifiManager.isWifiEnabled = true



            //check if connected!
            while (!isConnected(context.applicationContext)) {

                Thread.sleep(1000)
            }

            function()


        }

        builder.show()
    }



}