package com.example.sokogarden

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.loopj.android.http.RequestParams

class PaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_payment)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        Find the views by use of their ids
        val txtname = findViewById<TextView>(R.id.txtProductName)
        val txtcost = findViewById<TextView>(R.id.txtProductCost)
        val imgproduct = findViewById<ImageView>(R.id.imgProduct)
        val txtdescription = findViewById<TextView>(R.id.txtProductDescription)


//        Retrive the data passed from the previous activity
        val name = intent.getStringExtra("product_name")
        val cost = intent.getIntExtra("product_cost", 0)
        val product_photo = intent.getStringExtra("product_photo")
        val description = intent.getStringExtra("product_description")

//        Update the textviews with the data passed from the previous activity
        txtname.text = name
        txtcost.text = "ksh $cost"
        txtdescription.text = description

//        specify the image url
        val imageUrl = "https://tabbyondego.alwaysdata.net/static/images/$product_photo"

        Glide.with(this)
            .load(imageUrl )
            .placeholder(R.drawable.ic_launcher_background) // Make sure you have a placeholder image
            .into(imgproduct)

//        Find the edittext and the pay now button by the use of their ids
        val phone = findViewById<TextView>(R.id.phone)
        val btnpay = findViewById<Button>(R.id.pay)

//        Set click listener for the button
        btnpay.setOnClickListener {
//            Specify the endpoint for making payment
            val api = "https://tabbyondego.alwaysdata.net/api/mpesa_payment"

//        Create a request params
            val data = RequestParams()

//        Insert the data into the requestparams
            data.put("amount",cost)
            data.put("phone",phone.text.toString().trim())

//        Import the helper class
            val helper = ApiHelper(applicationContext)

//        Access the post function inside the helper class
            helper.post(api,data)




        }


    }
}