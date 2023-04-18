package com.stonecap.wardrobe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stonecap.wardrobe.core.network.RembgNetworkApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    rembgNetworkApi: RembgNetworkApi,
) : ViewModel() {

    init {
        viewModelScope.launch {
            rembgNetworkApi.segregateClothesPicture(ByteArray(1))
        }
    }
}
