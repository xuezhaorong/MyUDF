package com.example.project.diffutil

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.project.databinding.UserItemBinding
import com.example.project.room.User

class UserAdapter(private val context: Context) : ListAdapter<User, UserAdapter.UserViewHolder>(UserDiffCallback()) {


    inner class UserViewHolder(val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root);

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        // 创建 ViewHolder
        val binding: UserItemBinding =
            UserItemBinding.inflate(LayoutInflater.from(context), parent, false)
        val holder = UserViewHolder(binding)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position) // 从 ListAdapter 获取条目
        // 绑定数据到 ViewHolder
        holder.binding.TextViewUserId.text = user.userId.toString()
        holder.binding.TextViewUserFirstName.text = user.firstName.toString()
        holder.binding.TextViewUserLastName.text = user.lastName.toString()
    }

}