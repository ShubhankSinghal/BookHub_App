package com.shubhank.bookhub.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.shubhank.bookhub.R
import com.squareup.picasso.Picasso
import okhttp3.Headers
import org.json.JSONObject

class DescriptionActivity : AppCompatActivity() {

    lateinit var txtBookName: TextView
    lateinit var txtBookAuthor: TextView
    lateinit var txtBookPrice: TextView
    lateinit var txtBookRating: TextView
    lateinit var imgBookImage: ImageView
    lateinit var textBookDesc: TextView
    lateinit var btnAddToFavorites: TextView
    lateinit var progressLayout: RelativeLayout
    lateinit var progressBar: ProgressBar

    var bookId: String? = "100"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)

        txtBookName = findViewById(R.id.txtBookName)
        txtBookAuthor = findViewById(R.id.txtBookAuthor)
        txtBookPrice = findViewById(R.id.txtBookPrice)
        txtBookRating = findViewById(R.id.txtBookRating)
        imgBookImage = findViewById(R.id.imgBookImage)
        btnAddToFavorites = findViewById(R.id.btnAddToFavorite)
        textBookDesc = findViewById(R.id.txtBookDescription)
        progressBar = findViewById(R.id.progressBar)
        progressBar.visibility = View.VISIBLE
        progressLayout = findViewById(R.id.progressLayout)
        progressLayout.visibility = View.VISIBLE

        if (intent != null) {
            bookId = intent.getStringExtra("book_id")
        } else {
            finish()
            Toast.makeText(
                this@DescriptionActivity,
                "Some unexpected error occured!",
                Toast.LENGTH_SHORT
            ).show()
        }

        if (bookId == "100") {
            finish()
            Toast.makeText(
                this@DescriptionActivity,
                "Some unexpected error occured!",
                Toast.LENGTH_SHORT
            ).show()
        }

        val queue = Volley.newRequestQueue(this@DescriptionActivity)
        val url = "http://13.235.250.119/v1/book/get_book/"

        val jsonParams = JSONObject()
        jsonParams.put("book_id", bookId)

        val jsonRequest =
            object : JsonObjectRequest(Request.Method.POST, url, jsonParams, Response.Listener {

                try {

                    val success = it.getBoolean("success")
                    if (success) {
                        val bookJSONObject = it.getJSONObject("book_data")
                        progressLayout.visibility = View.GONE

                        Picasso.get().load(bookJSONObject.getString("image"))
                            .error(R.drawable.default_book_cover).into(imgBookImage)
                        txtBookName.text = bookJSONObject.getString("name")
                        txtBookAuthor.text = bookJSONObject.getString("author")
                        txtBookRating.text = bookJSONObject.getString("rating")
                        txtBookPrice.text = bookJSONObject.getString("price")
                        textBookDesc.text = bookJSONObject.getString("description")
                    } else {
                        Toast.makeText(
                            this@DescriptionActivity,
                            "Some Error has Occurred",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                } catch (e: Exception) {

                    Toast.makeText(
                        this@DescriptionActivity,
                        "Some Error has Occurred",
                        Toast.LENGTH_SHORT
                    ).show()

                }

            }, Response.ErrorListener {

                Toast.makeText(
                    this@DescriptionActivity,
                    "Volley error $it!!!",
                    Toast.LENGTH_SHORT
                ).show()
            }) {
                override fun getHeaders(): MutableMap<String, String> {
                    val headers = HashMap<String, String>()
                    headers["Content-type"] = "application/Json"
                    headers["tokens"] = "6f5311403e6661"
                    return headers
                }
            }
    }
}
