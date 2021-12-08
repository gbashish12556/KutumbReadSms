package com.example.kutumbreadsms.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.kutumbreadsms.R
import com.example.kutumbreadsms.databinding.FragmentMessageListBinding
import com.example.kutumbreadsms.util.getViewModelFactory
import timber.log.Timber
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kutumbreadsms.SmsApplication
import com.example.kutumbreadsms.ViewModelFactory


class MessageListFragment : Fragment() {

    lateinit var viewModel:MainViewModel

    private lateinit var viewDataBinding: FragmentMessageListBinding

    private lateinit var listAdapter: SectionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_message_list, container, false)

        viewModel = activity?.let { getViewModelFactory(it,savedInstanceState) }!!

        viewDataBinding = FragmentMessageListBinding.bind(root).apply {
            viewmodel = viewModel
        }
        // Set the lifecycle owner to the lifecycle of the view
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            setupListAdapter()
            viewModel.smsData.observe(viewLifecycleOwner, Observer { smsData->
                if(smsData != null) {
                    findNavController().navigate(R.id.action_messageListFragment_to_mesageDetailFragment)
                }
            })
    }
    private fun setupListAdapter() {
        val viewModel = viewDataBinding.viewmodel
        if (viewModel != null) {
            listAdapter = SectionAdapter(viewModel, activity as MainActivity)
            val llm = LinearLayoutManager(activity)
            llm.orientation = LinearLayoutManager.VERTICAL
            viewDataBinding.recyclerView.setLayoutManager(llm)
            viewDataBinding.recyclerView.adapter = listAdapter
        } else {
            Timber.w("ViewModel not initialized when attempting to set up adapter.")
        }
    }



}