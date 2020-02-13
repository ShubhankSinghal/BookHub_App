package com.shubhank.bookhub.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shubhank.bookhub.R
import com.shubhank.bookhub.model.book

class recyclerDashboardAdapter(context: Context, val itemList: ArrayList<book>): RecyclerView.Adapter<recyclerDashboardAdapter.DashboardViewHolder>() {

    class DashboardViewHolder(view:View) : RecyclerView.ViewHolder(view){
        val textBookName: TextView = view.findViewById(R.id.txtBookName)
        val textBookAuthor: TextView = view.findViewById(R.id.txtBookAuthor)
        val textBookPrice: TextView = view.findViewById(R.id.txtBookPrice)
        val textBookRating: TextView = view.findViewById(R.id.txtBookRating)
        val imgBookImage: TextView = view.findViewById(R.id.imgBookImage)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_dashboard_single_row, parent, false)

        return DashboardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        val book = itemList[position]
        holder.textBookName.text = book.bookName
        holder.textBookAuthor.text = book.bookAuthor
        holder.textBookPrice.text = book.bookCost
        holder.textBookRating.text = book.bookRating
        holder.imgBookImage.setBackgroundResource(book.bookImage)
    }

}