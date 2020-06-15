package com.example.customerapp.presentation.view.ui.customerlist

import android.os.Bundle
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.customerapp.R
import com.example.customerapp.presentation.view.callback.OnRecyclerObjectClickListener
import com.example.customerapp.presentation.view.model.CustomerUIModel
import com.example.customerapp.presentation.view.ui.customerlist.adapter.CustomerListAdapter
import com.example.customerapp.presentation.view.viewmodel.CustomerListViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fillter_customer_layout.*
import javax.inject.Inject


class CustomerListActivity : AppCompatActivity(){


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: CustomerListViewModel
    private lateinit var customerListAdapter: CustomerListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_main)
        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        viewModel=  ViewModelProviders.of(this,viewModelFactory)
            .get(CustomerListViewModel::class.java)
        setSpinner()
        setList()
        setListner()
    }

    private fun observeViewModel(viewModel: CustomerListViewModel) {

        viewModel.getCustomerData(spinnerCustomer.selectedItem as Int).observe(this,
            Observer {
                loadList(it)
            }
        )
    }

    private fun setListner(){
        btnFilter.setOnClickListener {
            viewModel.setCustomerUpdatedFilterInfo(spinnerCustomer.selectedItem as Int,
                txtLat.text.toString(),
                txtLong.text.toString())
        }
    }
    private fun setList(){
        val layoutManager = LinearLayoutManager(this)
        recyclerViewCustomer.layoutManager = layoutManager
        customerListAdapter = CustomerListAdapter()
        recyclerViewCustomer.adapter = customerListAdapter
        customerListAdapter.setListener(object :
            OnRecyclerObjectClickListener<CustomerUIModel> {
            override fun onItemClicked(item: CustomerUIModel, position: Int) {
                Toast.makeText(this@CustomerListActivity,getString(R.string.invite_sent),Toast.LENGTH_LONG).show()
            }
        })
        val dividerItemDecoration = DividerItemDecoration(
            recyclerViewCustomer.context,
            layoutManager.orientation
        )
        recyclerViewCustomer.addItemDecoration(dividerItemDecoration)
    }

    private fun loadList(custmorList: List<CustomerUIModel>) {
        customerListAdapter.setItems(custmorList)
        customerListAdapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        observeViewModel(viewModel)
    }

    private fun setSpinner(){
        val dataAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item,  viewModel.getDistanceValues())
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCustomer.adapter = dataAdapter
        txtLat.text = viewModel.getDefaultLat().toString()
        txtLong.text = viewModel.getDefaultLong().toString()

    }


}

