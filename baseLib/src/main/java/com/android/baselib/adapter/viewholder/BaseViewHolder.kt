package com.android.baselib.adapter.viewholder


import android.view.View
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.baseproject.util.CodeSnippet


abstract class BaseViewHolder<T, VB : ViewDataBinding> : RecyclerView.ViewHolder {


     var codeSnippet = CodeSnippet(itemView.context)

    var data: T? = null
        set(value) {
            field = value
            data?.let { populateData(it) }
        }

    protected lateinit var viewDataBinding: VB

    open  var lastItemPosition = 0

     var TAG = javaClass.simpleName

     constructor(viewDataBinding: VB) : super(viewDataBinding.root) {
        this.viewDataBinding = viewDataBinding
    }

    fun showMessage(message:String){
        Toast.makeText(itemView.context,message,Toast.LENGTH_SHORT).show()
    }

     constructor(itemView: View) : super(itemView)

     abstract fun populateData(data: T)

}