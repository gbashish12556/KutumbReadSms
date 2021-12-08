package com.example.kutumbreadsms.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.kutumbreadsms.R
import com.example.kutumbreadsms.data.SmsData


class MesageDetailFragment : Fragment() {

    private var sender: TextView? = null
    private var message: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view:View =  inflater.inflate(R.layout.fragment_mesage_detail, container, false)
        sender = view.findViewById(R.id.sender)
        message = view.findViewById(R.id.msessage)
        return view;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(arguments !=null && requireArguments().getSerializable("smsData") != null){
            var smsData:SmsData = requireArguments().getSerializable("smsData") as SmsData
            sender!!.setText(smsData.sender)
            message!!.setText(smsData.message)
        }
    }
}