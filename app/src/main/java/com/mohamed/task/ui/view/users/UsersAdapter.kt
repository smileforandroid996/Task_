package com.mohamed.task.ui.view.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mohamed.task.R
import com.mohamed.task.databinding.UserItemBinding
import com.mohamed.task.domain.entities.UserEntity
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


class UsersAdapter : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    private val users: MutableList<UserEntity> = ArrayList()
    private var layoutInflater: LayoutInflater? = null

    var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {

        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context)
        }

        val binding: UserItemBinding = DataBindingUtil.inflate(layoutInflater!!,
            R.layout.user_item, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val user = users[position]
        viewHolder.binding.userName.text = user.login
        viewHolder.binding.htmlUrl.text = user.htmlUrl

          Picasso.get()
            .load(user.avatarUrl)
            .into(viewHolder.binding.userImage, object : Callback {
                override fun onSuccess() {
                    viewHolder.binding.imageProgress.hide()
                }

                override fun onError(e: Exception?) {
                    viewHolder.binding.imageProgress.hide()
                }
            }
            )

        viewHolder.binding.itemView.setOnClickListener {
            onItemClickListener?.setOnItemClickListener(user)
        }

    }

    override fun getItemCount() = users.size

    fun submitList(newMovies: List<UserEntity>?) {
        newMovies?.let {
            users.addAll(it)
            notifyDataSetChanged()
        }
    }

    @Suppress("unused")
    fun clear() {
        users.clear()
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun setOnItemClickListener(userEntity: UserEntity)
    }

    class ViewHolder(val binding: UserItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}
