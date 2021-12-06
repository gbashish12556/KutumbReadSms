package com.example.kutumbreadsms.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kutumbreadsms.data.SectionData
import com.example.kutumbreadsms.data.SmsData


@BindingAdapter("app:sectionItems")
fun setItems1(listView: RecyclerView, items: List<SectionData>?) {
    items?.let {
        (listView.adapter as SectionAdapter).submitList(items)
    }
}

@BindingAdapter("app:smsItems")
fun setItems2(listView: RecyclerView, items: List<SmsData>?) {
    items?.let {
        (listView.adapter as SmsAdapter).submitList(items)
    }
}
