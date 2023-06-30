package com.example.innobuzzassignment.Adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.innobuzzassignment.R
import com.example.innobuzzassignment.fragments.DetailedPostFrag
import com.example.innobuzzassignment.model.PostsItem
import com.google.android.material.card.MaterialCardView

class PostsAdapter(val context : Context) : RecyclerView.Adapter<PostsAdapter.Holder>() {
    private var list : List<PostsItem> = emptyList()
    fun setMovies(list : List<PostsItem> ){
        this.list = list
        notifyDataSetChanged()
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var textView : TextView = itemView.findViewById(R.id.textViewCardView)
        var cardView : MaterialCardView = itemView.findViewById(R.id.cardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.posts_cardview,parent,false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.textView.text = list[position].title
        holder.cardView.setOnClickListener {
            val manager = (holder.itemView.context as FragmentActivity).supportFragmentManager
            val args = Bundle()
            args.putString("title",list[position].title)
            args.putString("body",list[position].body)
            val detailedPostFrag = DetailedPostFrag()
            detailedPostFrag.arguments = args
            manager.beginTransaction().replace(R.id.fragmentContainer,detailedPostFrag,"detailed_frag").addToBackStack(null).commit()
        }
    }
}