package com.shubhank.bookhub.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.shubhank.bookhub.R
import com.shubhank.bookhub.model.Book
import com.squareup.picasso.Picasso

class recyclerDashboardAdapter(val context: Context, val itemList: ArrayList<Book>) :
    RecyclerView.Adapter<recyclerDashboardAdapter.DashboardViewHolder>() {

    class DashboardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textBookName: TextView = view.findViewById(R.id.txtBookName)
        val textBookAuthor: TextView = view.findViewById(R.id.txtBookAuthor)
        val textBookPrice: TextView = view.findViewById(R.id.txtBookPrice)
        val textBookRating: TextView = view.findViewById(R.id.txtBookRating)
        val imgBookImage: ImageView = view.findViewById(R.id.imgBookImage)
        val llContent: LinearLayout = view.findViewById(R.id.llContent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_dashboard_single_row, parent, false)

        return DashboardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        val book = itemList[position]
        holder.textBookName.text = book.bookName
        holder.textBookAuthor.text = book.bookAuthor
        holder.textBookPrice.text = book.bookPrice
        holder.textBookRating.text = book.bookRating
        /*holder.imgBookImage.setImageResource(book.bookImage)*/
        Picasso.get().load(book.bookImage).error(R.drawable.default_book_cover).into(holder.imgBookImage);
        holder.llContent.setOnClickListener {
            Toast.makeText(context, "Clicked on ${holder.textBookName.text}", Toast.LENGTH_SHORT)
                .show()
        }
    }

}