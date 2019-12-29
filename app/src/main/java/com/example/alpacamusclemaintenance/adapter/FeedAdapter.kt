package com.example.alpacamusclemaintenance.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.alpacamusclemaintenance.R
import com.example.alpacamusclemaintenance.databinding.ListItemFeedBinding
import com.example.alpacamusclemaintenance.ui.WebViewFragment
import com.example.alpacamusclemaintenance.vo.Feed

class FeedAdapter : RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {

  private lateinit var binding: ListItemFeedBinding
  private var feedList: List<Feed> = emptyList()

  fun setFeedList(feedList: List<Feed>) {
    this.feedList = feedList
    notifyItemRangeInserted(0, feedList.size)
  }

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): FeedViewHolder {
    binding = ListItemFeedBinding.inflate(
      LayoutInflater.from(parent.context),
      parent,
      false
    )
    return FeedViewHolder(binding)
  }

  override fun onBindViewHolder(
    holder: FeedViewHolder,
    position: Int
  ) {
    val context = binding.root.context
    val selectedFeed = feedList[position]

    Glide
      .with(context)
      .load(selectedFeed.user.profileImageUrl)
      .transition(DrawableTransitionOptions.withCrossFade())
      .into(holder.binding.profileImage)

    holder
      .binding
      .apply {
        feed = selectedFeed
        onClicked = View.OnClickListener {
          val args = bundleOf(
            WebViewFragment.ARG_URL to selectedFeed.url
          )
          holder
            .itemView
            .findNavController()
            .navigate(R.id.action_feedFragment_to_webViewFragment, args)
        }
        executePendingBindings()
      }
  }

  override fun getItemCount(): Int = feedList.size

  class FeedViewHolder(val binding: ListItemFeedBinding) : RecyclerView.ViewHolder(binding.root)
}
