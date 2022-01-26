package com.toledorobia.cariocascore.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.toledorobia.cariocascore.domain.model.TestData

class HomeViewModel : ViewModel() {
    private val _testData = MutableLiveData<List<TestData>>()
    val testData: LiveData<List<TestData>>
        get() = _testData


    fun getTestData() {
        val items = mutableListOf<TestData>()

        for (i in 1..200) {
            items.add(TestData(i, "Prueba item ${i}"))
        }

        _testData.postValue(items)
    }

//    private val _text = MutableLiveData<String>().apply {
//        value = "This is home Fragment"
//    }
//    val text: LiveData<String> = _text
}