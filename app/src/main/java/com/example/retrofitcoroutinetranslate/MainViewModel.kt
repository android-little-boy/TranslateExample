package com.example.retrofitcoroutinetranslate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitcoroutinetranslate.network.ApiResult
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _translateResult: MutableLiveData<String> = MutableLiveData()
    val translateResult: LiveData<String>
        get() = _translateResult
    var word:String=""
    fun translate(word: String) {
        /**
         * 使用挂起函数需要一个入口，这个入口设置在了这个viewModel 里
         * viewModelScope需要引用'androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.0-beta01'或更高版本
         *
         */
        viewModelScope.launch {
            when (val result = TranslateApi.retrofitService.translate(word)) {
                is ApiResult.Success -> {
                    _translateResult.value = result.data!!.translateResult[0][0].tgt
                }
                is ApiResult.Failure -> {
                    _translateResult.value =
                        "errorCode: ${result.errorCode} errorMsg: ${result.errorMsg}"
                }
            }
        }
    }
}