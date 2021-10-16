package com.stockbit.hiring

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.load
import com.stockbit.hiring.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val PHOTO = "photo"
        const val NAME = "name"
        const val FULLNAME = "fullname"
        const val PRICE = "price"
        const val OPENDAY = "openday"
        const val HIGHDAY = "highday"
        const val LOWDAY = "lowday"
        const val TOPTIERVOLUME24HOURTO = "toptiervolume24hourto"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val img = intent.getStringExtra(PHOTO)
        val name = intent.getStringExtra(NAME)
        val fullname = intent.getStringExtra(FULLNAME)
        val price = intent.getStringExtra(PRICE)
        val openday = intent.getStringExtra(OPENDAY)
        val highday = intent.getStringExtra(HIGHDAY)
        val lowday = intent.getStringExtra(LOWDAY)
        val toptier = intent.getStringExtra(TOPTIERVOLUME24HOURTO)

        binding.imgDetail.load(img) {
            crossfade(true)
            crossfade(1000)
            placeholder(android.R.color.darker_gray)
            error(R.drawable.ic_launcher_background)
        }

//        binding.txtName.text = name
        binding.txtFullname.text = fullname
        binding.txtPrice.text = price
        binding.txtOpenDay.text = openday
        binding.txtHighDay.text = highday
        binding.txtLowDay.text = lowday
        binding.txtTopinDay.text = toptier

    }
}