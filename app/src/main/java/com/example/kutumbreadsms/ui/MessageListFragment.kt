package com.example.kutumbreadsms.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.example.kutumbreadsms.R
import com.example.kutumbreadsms.databinding.FragmentMessageListBinding
import com.example.kutumbreadsms.util.getViewModelFactory
import timber.log.Timber
import androidx.recyclerview.widget.LinearLayoutManager





class MessageListFragment : Fragment() {

    private val viewModel by viewModels<MainViewModel> { getViewModelFactory() }

    private lateinit var viewDataBinding: FragmentMessageListBinding

    private lateinit var listAdapter: SectionAdapter

    private val MY_PERMISSIONS_REQUEST_READ_SMS  =10

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_message_list, container, false)
        viewDataBinding = FragmentMessageListBinding.bind(root).apply {
            viewmodel = viewModel
        }
        // Set the lifecycle owner to the lifecycle of the view
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        if(checkPermission()) {
            setupListAdapter()
//        }
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
        viewModel!!.refreshList()
    }

    fun checkPermission(): Boolean {
        val permission1 = Manifest.permission.RECEIVE_SMS
        val permission2 = Manifest.permission.READ_SMS
        val grant1: Int? = activity?.let { ContextCompat.checkSelfPermission(it, permission1) }
        val grant2: Int? = activity?.let { ContextCompat.checkSelfPermission(it, permission2) }
        if (grant1 != PackageManager.PERMISSION_GRANTED || grant2 != PackageManager.PERMISSION_GRANTED) {
            val permission_list = arrayOfNulls<String>(2)
            permission_list[0] = permission1
            permission_list[1] = permission2
            activity?.let {
                ActivityCompat.requestPermissions(
                    it,
                    permission_list,
                    MY_PERMISSIONS_REQUEST_READ_SMS
                )
            }
        } else {
            return true
        }
        return false
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_READ_SMS -> {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.size > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    setupListAdapter()
                } else {
                    checkPermission()
                }
                return
            }
        }
    }
}