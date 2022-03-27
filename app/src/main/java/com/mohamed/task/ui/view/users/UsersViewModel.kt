package com.mohamed.task.ui.view.users

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohamed.task.domain.entities.UserEntity
import com.mohamed.task.domain.usecases.GetUsersUseCase
import com.mohamed.task.ui.utils.AppUtils
import com.mohamed.task.ui.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.collect



@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    val users = MutableLiveData<Resource<List<UserEntity>>>()

    fun geUsers() {
        users.postValue(Resource.loading(data = null))
        viewModelScope.launch(Dispatchers.IO) {
            try {
                getUsersUseCase.build().collect {
                    users.postValue(Resource.success(data = it))
                }
            } catch (e: Throwable) {
                users.postValue(Resource.error(data = null, message = AppUtils().handleError(e)))
            }
        }
    }
}