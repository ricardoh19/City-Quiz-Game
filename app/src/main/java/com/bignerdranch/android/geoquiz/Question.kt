package com.bignerdranch.android.geoquiz

import androidx.annotation.StringRes

data class Question(@StringRes val textResId:Int, val answer: String,
                    val choice1:String,val choice2:String,val choice3:String, val choice4: String)
