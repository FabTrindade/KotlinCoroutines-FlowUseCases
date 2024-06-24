package com.fabscorp.coroutineusecasesonandroid.usecases.coroutines.usecase15

import android.os.Bundle
import androidx.activity.viewModels
import com.fabscorp.coroutineusecasesonandroid.base.BaseActivity
import com.fabscorp.coroutineusecasesonandroid.base.useCase15Description
import com.fabscorp.coroutineusecasesonandroid.databinding.ActivityWorkmangerBinding

class WorkManagerActivity : BaseActivity() {

    override fun getToolbarTitle() = useCase15Description

    private val binding by lazy { ActivityWorkmangerBinding.inflate(layoutInflater) }
    private val viewModel: WorkManagerViewModel by viewModels {
        ViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.performAnalyticsRequest()
    }
}