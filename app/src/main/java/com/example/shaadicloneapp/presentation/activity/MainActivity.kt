package com.example.shaadicloneapp.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.shaadicloneapp.R
import com.example.shaadicloneapp.data.model.Name
import com.example.shaadicloneapp.data.model.User
import com.example.shaadicloneapp.data.utils.NetworkResponse
import com.example.shaadicloneapp.databinding.ActivityMainBinding
import com.example.shaadicloneapp.presentation.adapter.UserListAdapter
import com.example.shaadicloneapp.presentation.viewModel.UserViewModel
import com.example.shaadicloneapp.presentation.viewModel.UserViewModelFactory
import com.example.shaadicloneapp.utils.Constants
import com.example.shaadicloneapp.utils.SliderLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), UserListAdapter.Interaction {
    private lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var factory: UserViewModelFactory
    lateinit var viewModel: UserViewModel
    private val result = 10
    private lateinit var userAdapter: UserListAdapter
    private var userList: List<User> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this, factory).get(UserViewModel::class.java)
        setUpAdapter()
        getUsers()
    }

    private fun getUsers() {
        viewModel.getUsers(result.toString())
        viewModel.users.observe(this) { response ->
            when (response) {
                is NetworkResponse.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        userList = it.results
                        userAdapter.differ.submitList(it.results)
                    }
                }
                is NetworkResponse.Error -> {
                    hideProgressBar()
                    response.message?.let {
                        if (it.equals(Constants.NO_INTERNET_MSG)) {
                            Timber.d("Internet not available show local data")
                            getSavedUsers()
                        }else Timber.d("Error msg: $it")
                    }
                }

                is NetworkResponse.Loading -> {
                    viewModel.getSavedUsers().observe(this) {
                        if (it.isNullOrEmpty()) {
                            showProgressBar()
                        } else {
                            hideProgressBar()
                            userAdapter.differ.submitList(it)
                        }
                    }

                }

            }
        }
    }


    private fun getSavedUsers() {
        viewModel.getSavedUsers().observe(this) {
            it?.let {
                userList = it
                userAdapter.differ.submitList(it)
            }
        }
    }

    private fun setUpAdapter() {
        userAdapter = UserListAdapter(interaction = this)
        binding.rvUsers.layoutManager = SliderLayoutManager(this).apply {
            callback = object : SliderLayoutManager.OnItemSelectedListener {
                override fun onItemSelected(layoutPosition: Int) {

                }
            }
        }
        binding.rvUsers.adapter = userAdapter
        binding.rvUsers.onFlingListener = null
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.INVISIBLE
    }

    override fun onAcceptBtnClicked(position: Int, selectedUser: User) {
        userList.forEach {
            if (it.email == selectedUser.email) {
                it.hasAccepted = true
            }
        }
        viewModel.updateAcceptedField(true, selectedUser.email)
    }

    override fun onDeclineBtnClicked(position: Int, selectedUser: User) {
        userList.forEach {
            if (it == selectedUser) {
                it.hasDeclined = true
            }
        }
        viewModel.updateDeclineField(true, selectedUser.email)
    }
}