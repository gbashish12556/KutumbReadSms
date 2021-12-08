package com.example.kutumbreadsms.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.kutumbreadsms.R
import com.example.kutumbreadsms.SmsApplication
import com.example.kutumbreadsms.ViewModelFactory
import com.example.kutumbreadsms.data.SmsData
import com.example.kutumbreadsms.databinding.FragmentMesageDetailBinding
import com.example.kutumbreadsms.databinding.FragmentMessageListBinding
import com.example.kutumbreadsms.util.getViewModelFactory
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController


class MesageDetailFragment : Fragment() {

    lateinit var viewModel:MainViewModel

    private lateinit var viewDataBinding: FragmentMesageDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_mesage_detail, container, false)

        viewModel = activity?.let { getViewModelFactory(it,savedInstanceState) }!!

        viewDataBinding = FragmentMesageDetailBinding.bind(root).apply {
            viewmodel = viewModel
        }
        // Set the lifecycle owner to the lifecycle of the view
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    goToListFrag()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        view.findViewById<ImageView>(R.id.backButton).setOnClickListener(View.OnClickListener {
            goToListFrag()
        })
    }

    fun goToListFrag(){
        viewModel.setSmsData(null)
        findNavController().navigate(R.id.action_mesageDetailFragment_to_messageListFragment)
    }

}