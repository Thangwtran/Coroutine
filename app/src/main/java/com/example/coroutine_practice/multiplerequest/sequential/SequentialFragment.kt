package com.example.coroutine_practice.multiplerequest.sequential

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.coroutine_practice.base.BaseFragment
import com.example.coroutine_practice.databinding.FragmentSequentialBinding

class SequentialFragment : BaseFragment<FragmentSequentialBinding>() {
    private val viewmodel by viewModels<SequentialViewModel>()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSequentialBinding {
        return FragmentSequentialBinding.inflate(inflater, container, false)
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
    }

    override fun observeData() {
        super.observeData()
    }


}