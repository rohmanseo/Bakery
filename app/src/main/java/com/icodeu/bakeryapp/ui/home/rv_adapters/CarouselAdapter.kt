package com.icodeu.bakeryapp.ui.home.rv_adapters

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
import com.icodeu.bakeryapp.models.Bread

class CarouselAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Bread>() {

        override fun areItemsTheSame(oldItem: Bread, newItem: Bread): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Bread, newItem: Bread): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_carousel,
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

    fun submitList(list: List<Bread>) {
        differ.submitList(list)
    }

    class ViewHolder
    constructor(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Bread) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }
            val img = this.findViewById(R.id.imgCarousel) as ImageView
/*            val name = this.findViewById(R.id.tvName) as TextView
            val rating = this.findViewById(R.id.ratingItem) as RatingBar
            val price = this.findViewById(R.id.tvPrice) as TextView

            name.setText(item.name)
            rating.rating = item.rating?.toFloat() ?: 1F
            val priceString = item.shortPrice().toString() + "K"
            price.text = priceString*/
            Glide.with(itemView.context)
                .load(item.validImage())
                .into(img)

        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: Bread)
    }
}
