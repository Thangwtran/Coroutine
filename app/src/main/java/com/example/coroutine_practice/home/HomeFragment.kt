package com.example.coroutine_practice.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.coroutine_practice.R
import com.example.coroutine_practice.base.BaseFragment
import com.example.coroutine_practice.databinding.FragmentHomeBinding
import com.example.coroutine_practice.multiplerequest.concurrency.ConcurrencyFragment
import com.example.coroutine_practice.multiplerequest.sequential.SequentialFragment
import com.example.coroutine_practice.singlerequest.SingleFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun initData() {
        super.initData()
    }

    override fun initView() {
        super.initView()
        binding.btnSingle.setOnClickListener {
            openScreen(SingleFragment())
        }
        binding.btnSequentital.setOnClickListener {
            openScreen(SequentialFragment())
        }
        binding.btnConcurency.setOnClickListener {
            openScreen(ConcurrencyFragment())
        }
    }

    override fun initListeners() {
        super.initListeners()
    }

    override fun observeData() {
        super.observeData()
    }

    private fun openScreen(fragment: Fragment) {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(fragment.javaClass.name).commit()
    }

}