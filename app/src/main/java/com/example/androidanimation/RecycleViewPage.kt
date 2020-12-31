package com.example.androidanimation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.androidanimation.utils.StatusBarUtils
import kotlinx.android.synthetic.main.attivity_recycle_page.*

/**
 * @Author LuoJi
 * @Date 2020/12/31-8:59
 * @Desc
 */
class RecycleViewPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.attivity_recycle_page)
        val mAdapter = MAdapter()
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


        override fun getItemCount(): Int = 15

        override fun onBindViewHolder(holder: MViewHolder, position: Int) {
        }

    }

    private inner class MViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}