package com.example.customerapp.presentation.view.ui.customerlist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.customerapp.R
import com.example.customerapp.presentation.view.callback.OnRecyclerObjectClickListener
import com.example.customerapp.presentation.view.model.CustomerUIModel

class CustomerListAdapter() : RecyclerView.Adapter<CustomerListAdapter.ViewHolder>() {

    lateinit var  listner: OnRecyclerObjectClickListener<CustomerUIModel>
    private val listdata: MutableList<CustomerUIModel> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.list_item_customer, parent, false)
        return ViewHolder(
            listItem
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val myListData = listdata[position]
        holder.txtTitle.text = myListData.name
        holder.customerLayout.setOnClickListener {
            listner.onItemClicked(myListData,position)
        }
    }

    override fun getItemCount(): Int {
        return listdata.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtTitle: TextView = itemView.findViewById(R.id.txtTitle) as TextView
        var customerLayout:ConstraintLayout = itemView.findViewById(R.id.customerLayout) as ConstraintLayout
    }

    fun setListener(onRecyclerObjectClickListener: OnRecyclerObjectClickListener<CustomerUIModel>) {
        listner = onRecyclerObjectClickListener
    }
    fun setItems(customerList:List<CustomerUIModel>){
        listdata.clear()
        listdata.addAll(customerList)
    }
}