package com.ruiwenliu.kotlin.moudle.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.TextViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.ruiwenliu.kotlin.R
import com.ruiwenliu.kotlin.moudle.ui.bean.MenuItemData
import kotlinx.android.synthetic.main.item_menu.view.*

class MenuAdapter constructor(private  val contxt:Context,private  val list:List<MenuItemData>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
         return  RecyclerViewHolder(LayoutInflater.from(contxt).inflate(R.layout.item_menu,parent,false));
    }

    override fun getItemCount(): Int {
        return  list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is RecyclerViewHolder){
           holder.textView.text=list.get(position).name
            holder.textView.setOnClickListener({
                Toast.makeText(contxt,"宽度是："+holder.textView.width, Toast.LENGTH_SHORT).show()
            })
        }
    }

    class RecyclerViewHolder(view:View) : RecyclerView.ViewHolder(view){
        var textView: AppCompatTextView = view.findViewById(R.id.tv_menu)

    }
}
