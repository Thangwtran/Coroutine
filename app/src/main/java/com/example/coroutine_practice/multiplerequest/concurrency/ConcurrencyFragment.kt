package com.example.coroutine_practice.multiplerequest.concurrency

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.coroutine_practice.base.BaseFragment
import com.example.coroutine_practice.databinding.FragmentConcurrencyBinding


class ConcurrencyFragment : BaseFragment<FragmentConcurrencyBinding>() {
    private val viewmodel by viewModels<ConcurrencyViewModel>()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentConcurrencyBinding {
        return FragmentConcurrencyBinding.inflate(inflater, container, false)
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