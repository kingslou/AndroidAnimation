package com.example.androidanimation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidanimation.utils.StatusBarUtils
import kotlinx.android.synthetic.main.attivity_recycle_page.*
import java.util.*

/**
 * @Author LuoJi
 * @Date 2020/12/31-8:59
 * @Desc
 */
class RecycleViewPage : AppCompatActivity() {

    private val intArray :MutableList<Int> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.attivity_recycle_page)

        for(index in 1..20){
            intArray.add(index)
        }

        val mAdapter = MAdapter()

        val layoutManger = GridLayoutManager(this,2,RecyclerView.HORIZONTAL,false)

        recyclerView.layoutManager = layoutManger

        recyclerView.adapter = mAdapter
        indicator.bindRecyclerView(recyclerView)
        StatusBarUtils.setDeepStatusBar(true, this)
    }

    private inner class MAdapter : RecyclerView.Adapter<MViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MViewHolder =
                MViewHolder(
                        LayoutInflater.from(parent.context).inflate(
                                R.layout.item_test,
                                parent,
                                false
                        )
                )


        override fun getItemCount(): Int = intArray.size

        override fun onBindViewHolder(holder: MViewHolder, position: Int) {

            holder.tvText?.text = intArray[position].toString()+"什么鬼"


        }

    }

    private inner class MViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var tvText : TextView? =  findViewById(R.id.tvText)

    }
}