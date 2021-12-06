package com.example.kutumbreadsms.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kutumbreadsms.data.SectionData


@BindingAdapter("app:sectionItems")
fun setItems(listView: RecyclerView, items: List<SectionData>?) {
//    items?.let {
//        (listView.adapter as PrAdapter).submitList(items)
//    }
}
