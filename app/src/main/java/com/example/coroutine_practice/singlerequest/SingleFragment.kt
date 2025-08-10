package com.example.coroutine_practice.singlerequest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.coroutine_practice.base.BaseFragment
import com.example.coroutine_practice.databinding.FragmentSingleBinding

class SingleFragment : BaseFragment<FragmentSingleBinding>() {
    private val viewmodel by viewModels<SingleViewModel>()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSingleBinding {
        return FragmentSingleBinding.inflate(inflater, container, false)
    }

    override fun initData() {
        super.initData()
        viewmodel.fetchData()
    }

    override fun initView() {
        super.initView()
    }

    override fun initListeners() {
        super.initListeners()
        binding.btnCancel.setOnClickListener {
            viewmodel.cancelJob()
        }
    }

    override fun observeData() {
        super.observeData()
        viewmodel.data.observe(viewLifecycleOwner){
            binding.tvResult.text = "Result: $it"
        }

        viewmodel.error.observe(viewLifecycleOwner){
            binding.tvResult.text = it
        }
    }


}