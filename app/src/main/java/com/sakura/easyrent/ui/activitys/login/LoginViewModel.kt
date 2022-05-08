package com.sakura.easyrent.ui.activitys.login

import androidx.lifecycle.ViewModel
import com.sakura.easyrent.control.repositories.APIRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: APIRepository) : ViewModel()