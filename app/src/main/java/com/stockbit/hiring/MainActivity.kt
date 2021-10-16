package com.stockbit.hiring

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stockbit.hiring.adapter.CoinAdapter
import com.stockbit.hiring.adapter.TopAdapter
import com.stockbit.hiring.databinding.ActivityMainBinding
import com.stockbit.hiring.model.Data
import com.stockbit.hiring.rest.ApiClient
import com.stockbit.hiring.viewmodel.CoinViewModel

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), CoinAdapter.OnItemClickCallback {

    private lateinit var binding: ActivityMainBinding
    private lateinit var coinViewModel: CoinViewModel
    private lateinit var coinAdapter: CoinAdapter
    lateinit var rvTop: RecyclerView
    lateinit var topAdapter: TopAdapter
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Loading..")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAdapter()
        setUpViewModel()
        getData()

//        showTop()

    }

    private fun setAdapter() {
        coinAdapter = CoinAdapter(ArrayList(), this)
        binding.rvTop.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = coinAdapter
        }
    }


    private fun setUpViewModel() {
        coinViewModel = ViewModelProvider(this).get(CoinViewModel::class.java)
    }

    private fun getData() {
        coinViewModel.getCoin().observe(this, {
            coinAdapter.setData(it)
            Log.e("coinData", "$it", )
        })
        coinViewModel.getCoin()
    }

    override fun onItemClick(data: Data) {
        startActivity(
            Intent(this, DetailActivity::class.java)
                .putExtra(DetailActivity.PHOTO,"https://www.cryptocompare.com/" + data.CoinInfo.ImageUrl)
                .putExtra(DetailActivity.NAME, data.CoinInfo.Name)
                .putExtra(DetailActivity.FULLNAME, data.CoinInfo.FullName)
                .putExtra(DetailActivity.PRICE, data.DISPLAY.IDR.PRICE)
                .putExtra(DetailActivity.OPENDAY, data.DISPLAY.IDR.OPENHOUR)
                .putExtra(DetailActivity.HIGHDAY, data.DISPLAY.IDR.HIGHHOUR)
                .putExtra(DetailActivity.LOWDAY, data.DISPLAY.IDR.LOWHOUR)
                .putExtra(DetailActivity.TOPTIERVOLUME24HOURTO, data.DISPLAY.IDR.TOPTIERVOLUME24HOURTO)
        )
    }
}
