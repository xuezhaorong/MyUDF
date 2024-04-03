package com.example.project.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.example.project.room.User

class UserDiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        // 判断两个对象是否代表同一条目
        return oldItem.userId == newItem.userId
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        // 判断两个条目在视觉上是否相同
        return oldItem == newItem
    }
}
