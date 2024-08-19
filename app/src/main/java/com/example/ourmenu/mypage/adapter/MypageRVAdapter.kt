package com.example.ourmenu.mypage.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ourmenu.R
import com.example.ourmenu.data.PostData
import com.example.ourmenu.data.community.CommunityResponseData
import com.example.ourmenu.databinding.ItemPostBinding
import java.time.LocalDateTime

class MypageRVAdapter(
    var items: ArrayList<CommunityResponseData>,
    val context: Context,
    val itemClickListener: (CommunityResponseData) -> Unit,
) : RecyclerView.Adapter<MypageRVAdapter.ViewHolder>() {
    inner class ViewHolder(
        private val binding: ItemPostBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CommunityResponseData) {
            if (!item.userImgUrl.isNullOrBlank()) {
                Glide.with(context)
                    .load(item.userImgUrl)
                    .into(binding.sivItemPostProfile)
            }
            if(!item.articleThumbnail.isNullOrBlank()){
                Glide.with(context)
                    .load(item.articleThumbnail)
                    .into(binding.sivItemPostThumbnail)
            }else{
                binding.sivItemPostThumbnail.setImageResource(R.drawable.default_image)
            }
            binding.tvItemPostTitle.text = item.articleTitle
            binding.tvItemPostContent.text = item.articleContent
            binding.tvItemPostUsername.text = item.userNickname
            Log.d("오류",LocalDateTime.now().toString())
            binding.tvItemPostTime.text = item.createdBy.take(10)
            binding.tvItemPostViewCount.text = item.articleViews.toString()
            binding.tvItemPostCount.text = item.menusCount.toString()
            binding.root.setOnClickListener { itemClickListener(item) }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
    ) {
        holder.bind(items[position])
    }
}
