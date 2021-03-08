package com.globant.openbankassignment.ui.base

import android.app.AlertDialog
import androidx.fragment.app.Fragment
import com.globant.openbankassignment.R

open class BaseFragment: Fragment() {

   protected fun showAlertMessage(title:String,message:String){
       AlertDialog.Builder(context)
           .setTitle(title)
           .setMessage(message)
           .setPositiveButton(
               R.string.lbl_ok
           ) { dialog, which ->

               dialog.dismiss()
           }
           .show()
   }
}