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

    private val MY_PERMISSIONS_REQUEST_READ_SMS  =10

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
        refreshList()
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

    private fun refreshList(){
        if (checkPermission()) {
            viewModel!!.refreshList()
        }
    }

    fun checkPermission(): Boolean {
        val permission1 = Manifest.permission.RECEIVE_SMS
        val permission2 = Manifest.permission.READ_SMS
        activity?.let {
            val grant1: Int? = ContextCompat.checkSelfPermission(it, permission1)
            val grant2: Int? = ContextCompat.checkSelfPermission(it, permission2)
            if (grant1 != PackageManager.PERMISSION_GRANTED || grant2 != PackageManager.PERMISSION_GRANTED) {
                val permission_list = arrayOfNulls<String>(2)
                permission_list[0] = permission1
                permission_list[1] = permission2
                ActivityCompat.requestPermissions(
                    it,
                    permission_list,
                    MY_PERMISSIONS_REQUEST_READ_SMS
                )
            } else {
                return true
            }
        }
        return false
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == MY_PERMISSIONS_REQUEST_READ_SMS){
            refreshList()
        }
    }

}