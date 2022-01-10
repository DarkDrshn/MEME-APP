package com.example.mimiapp

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    private var mimilink : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadmimitoapp()
    }

    private fun loadmimitoapp(){
        // Instantiate the RequestQueue.
        loaderanimation.visibility = View.VISIBLE
        val queue = Volley.newRequestQueue(this)
        val url = "https://meme-api.herokuapp.com/gimme"

        // Request a string response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener{ response ->
                mimilink = response.getString("url")
                Glide.with(this ).load(mimilink).listener(object: RequestListener<Drawable>{
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        loaderanimation.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        loaderanimation.visibility = View.GONE
                        return false
                    }

                }).into(RealMimiShow) },
            {
                Toast.makeText(this,"Something DIDnt went Wrong Your Phone is SHITT",Toast.LENGTH_LONG).show()

            }
        )

        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)
    }
    fun shareclicked(view: android.view.View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT,"I Found Unique Meme.. LMAO.. CheckOut Here: $mimilink")
        val selectrr = Intent.createChooser(intent,"Share Using...")
        startActivity(selectrr)
    }
    fun nextclicked(view: android.view.View) {
        loadmimitoapp()
    }
}