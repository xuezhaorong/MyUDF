package com.example.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project.databinding.ActivityMainBinding
import com.example.project.diffutil.UserAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var viewModel: UserViewModel

    companion object{
        val TAG = "TAG"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!::binding.isInitialized){
            binding = ActivityMainBinding.inflate(layoutInflater)
        }
        setContentView(binding.root)

        binding.recyclerview.layoutManager = LinearLayoutManager(this)

        val userAdapter = UserAdapter(this)
        binding.recyclerview.adapter = userAdapter

        // 创建viewModel实例
        viewModel = ViewModelProvider(this)[UserViewModel::class.java]

        // 对所有数据进行监控
        viewModel.initSelectedAllUsersData()

        lifecycleScope.launch {
            viewModel.selectedAllUserData.collect {
                userAdapter.submitList(it)
                Log.d(TAG, "collect in Activity: $it") }
        }

        binding.ButtonMyButton.setOnClickListener{
            viewModel.insertData()

        }






    }
}