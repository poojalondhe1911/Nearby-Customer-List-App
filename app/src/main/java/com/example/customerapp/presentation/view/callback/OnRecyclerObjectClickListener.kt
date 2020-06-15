package com.example.customerapp.presentation.view.callback

interface OnRecyclerObjectClickListener<in T>  {
    fun onItemClicked(item: T, position: Int)
}