package com.example.shaadicloneapp.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shaadicloneapp.R
import com.example.shaadicloneapp.data.model.Name
import com.example.shaadicloneapp.data.model.User
import com.example.shaadicloneapp.databinding.ListItemUsersBinding
import com.squareup.picasso.Picasso
import timber.log.Timber
import java.text.SimpleDateFormat
import kotlin.coroutines.coroutineContext

class UserListAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {
    private val callback = object : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.name == newItem.name
        }


        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, callback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemUsersBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.list_item_users,
            parent,
            false
        )
        return UserViewHolder(binding, interaction)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = differ.currentList[position]
        return holder.bind(user)
    }

    inner class UserViewHolder(
        val binding: ListItemUsersBinding,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: User) {
            val myContext = binding.root.context
            Picasso.get()
                .load(item.picture.large)
                .transform(
                    jp.wasabeef.picasso.transformations.RoundedCornersTransformation(
                        6,
                        0
                    )
                )
                .into(binding.ivUserImage)
            binding.tvUserName.text = myContext.getString(R.string.first_last_name)
                .format(item.name.first, item.name.last)
            binding.tvUserAge.text = myContext.getString(R.string.age_yrs).format(item.dob.age)
            binding.tvUserLocation.text = myContext.getString(R.string.city_state_country)
                .format(item.location.city, item.location.state, item.location.country)
            val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm")
            val output: String = formatter.format(parser.parse(item.dob.date))
            binding.tvUserDob.text = output
            binding.tvUserGender.text = item.gender

            if (item.hasAccepted) {
                setAcceptedUI(myContext)
            } else if (item.hasDeclined) {
                setDeclinedUI(myContext)
            } else {
                binding.cvAccept.visibility = View.VISIBLE
                binding.cvDecline.visibility = View.VISIBLE
                binding.cvMessage.visibility = View.GONE
            }
            binding.cvAccept.setOnClickListener {
                interaction?.onAcceptBtnClicked(adapterPosition, item)
                setAcceptedUI(myContext)
            }

            binding.cvDecline.setOnClickListener {
                interaction?.onDeclineBtnClicked(adapterPosition, item)
                setDeclinedUI(myContext)
            }

        }

        private fun setAcceptedUI(myContext: Context) {
            binding.cvAccept.visibility = View.GONE
            binding.cvDecline.visibility = View.GONE
            binding.cvMessage.visibility = View.VISIBLE
            binding.tvMessage.text = myContext.getString(R.string.member_accepted)
            binding.tvMessage.setTextColor(myContext.getColor(R.color.green))
        }

        private fun setDeclinedUI(myContext: Context) {
            binding.cvAccept.visibility = View.GONE
            binding.cvDecline.visibility = View.GONE
            binding.cvMessage.visibility = View.VISIBLE
            binding.tvMessage.text = myContext.getString(R.string.member_declined)
            binding.tvMessage.setTextColor(myContext.getColor(R.color.red))
        }
    }

    interface Interaction {
        fun onAcceptBtnClicked(position: Int, selectedUser: User)
        fun onDeclineBtnClicked(position: Int, selectedUser: User)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}