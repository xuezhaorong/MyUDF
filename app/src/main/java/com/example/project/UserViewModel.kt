package com.example.project

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.room.User
import com.example.project.room.UserDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userDao:UserDao
) : ViewModel(){


    companion object{
        val TAG = "TAG"
    }

    private val _selectedUserData = MutableStateFlow<User?>(User())
    val selectedUserData: Flow<User?>
        get() = _selectedUserData


    private val _selectedAllUserData = MutableStateFlow<List<User?>>(emptyList())
    val selectedAllUserData: Flow<List<User?>>
        get() = _selectedAllUserData

    fun initSelectedUserData(userId: Int) {
        Log.d(TAG, "start subscribe userId: $userId")
        viewModelScope.launch {
            userDao.queryByUserId(userId) // 调用 Dao 层方法
                .flowOn(Dispatchers.IO)
                .distinctUntilChanged()// 如上逻辑在 IO Dispatcher 上运行
                .collect {
                    Log.d(TAG, "collect in ViewModel: $it")
                    _selectedUserData.emit(it)
                }
        }
    }

    // 建立UserList的Flow
    fun initSelectedAllUsersData(){
        viewModelScope.launch {
            userDao.getAll()
                .flowOn(Dispatchers.IO)
                .conflate()
                .distinctUntilChanged()
                .collect(){
                    Log.d(TAG, "collect in ViewModel: $it")
                    _selectedAllUserData.emit(it)
                }
        }
    }


    // 插入新数据
    fun insertData() {
        viewModelScope.launch(Dispatchers.IO) {
            val insertUser = User( firstName = "firstName", lastName = "lastName")
            Log.d(TAG, "insertData: $insertUser")
            userDao.insert(insertUser)
        }
    }
}


