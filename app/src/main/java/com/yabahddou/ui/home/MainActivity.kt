package com.yabahddou.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yabahddou.R
import com.yabahddou.common.Resource
import com.yabahddou.common.extensions.observe
import com.yabahddou.common.viewModel
import com.yabahddou.di.injector
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel {
        injector.homeViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.fetchUser()
        observeViewModel()
    }

    @SuppressLint("SetTextI18n")
    private fun observeViewModel() {
        observe(viewModel.user) { userLoadStatus ->
            when (userLoadStatus) {
                Resource.Loading -> {
                    // Handle loading event
                    txtStatus.text = "Loading..."
                }
                is Resource.Success -> {
                    // Handle success event
                    val user = userLoadStatus.data
                    txtStatus.text = "Got user"
                }
                is Resource.Failure -> {
                    // Handle error event
                    txtStatus.text = "Seems like there is no data !"
                }
            }
        }
    }
}
