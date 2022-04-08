package com.everest.movieapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast

fun Context.isConnected ():Boolean
{
    val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return  connectivityManager.activeNetworkInfo!=null && connectivityManager.activeNetworkInfo!!.isConnected
}

fun Context.MakeToast( message:String){
    Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
}