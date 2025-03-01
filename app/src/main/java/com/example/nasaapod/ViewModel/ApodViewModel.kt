package com.example.nasaapod.ViewModel

import android.app.Application
import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.nasaapod.Data.ApodModel
import com.example.nasaapod.Data.ApodRepository
import com.example.nasaapod.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

class ApodViewModel(application: Application) : AndroidViewModel(application) {
    private val _apodState = MutableStateFlow(ApodUiState())
    val apodState: StateFlow<ApodUiState> = _apodState.asStateFlow()

    private val apodRepository = ApodRepository()
    private val context = application

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun fetchApod() {
        viewModelScope.launch {
            _apodState.update { it.copy(isLoading = true, error = null) }

            try {
                val result = apodRepository.getApod()
                _apodState.update {
                    it.copy(
                        apod = result,
                        isLoading = false,
                        error = null
                    )
                }
            } catch (e: IOException) {
                _apodState.update {
                    it.copy(
                        isLoading = false,
                        error = context.getString(R.string.network_error)
                    )
                }
            } catch (e: HttpException) {
                _apodState.update {
                    it.copy(
                        isLoading = false,
                        error = context.getString(R.string.http_error, e.message)
                    )
                }
            } catch (e: Exception) {
                _apodState.update {
                    it.copy(
                        isLoading = false,
                        error = context.getString(R.string.unknown_error, e.message)
                    )
                }
            }
        }
    }
}

data class ApodUiState(
    val apod: ApodModel? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)