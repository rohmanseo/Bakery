package com.icodeu.bakeryapp.presentation.home.rv_adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.icodeu.bakeryapp.R
import com.icodeu.bakeryapp.domain.model.Bread

class RecommedRVAdapter(private val interaction: RecommendedItemInterface? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<com.icodeu.bakeryapp.domain.model.Bread>() {

        override fun areItemsTheSame(oldItem: com.icodeu.bakeryapp.domain.model.Bread, newItem: com.icodeu.bakeryapp.domain.model.Bread): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: com.icodeu.bakeryapp.domain.model.Bread, newItem: com.icodeu.bakeryapp.domain.model.Bread): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_carousel_recommended,
                parent,
                false
            ),
            interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                holder.bind(differ.currentList.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<com.icodeu.bakeryapp.domain.model.Bread>) {
        differ.submitList(list)
    }

    class ViewHolder
    constructor(
        itemView: View,
        private val interaction: RecommendedItemInterface?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: com.icodeu.bakeryapp.domain.model.Bread) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }
            val img = this.findViewById(R.id.imgCarousel) as ImageView
            val name = this.findViewById(R.id.tvName) as TextView
            val rating = this.findViewById(R.id.ratingItem) as RatingBar
            val price = this.findViewById(R.id.tvPrice) as TextView

            name.setText(item.name)
            rating.rating = item.rating?.toFloat() ?: 1F
            val priceString = item.shortPrice().toString() + "K"
            price.text = priceString
            Glide.with(itemView.context)
                .load(item.validImage())
                .into(img)

        }
    }

    interface RecommendedItemInterface {
        fun onItemSelected(position: Int, item: com.icodeu.bakeryapp.domain.model.Bread)
    }
}
