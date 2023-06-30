package com.example.innobuzzassignment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.innobuzzassignment.R

class DetailedPostFrag : Fragment() {
    private lateinit var titleView : TextView
    private lateinit var bodyView : TextView
    private lateinit var userIdView: TextView
    private lateinit var idView: TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detailedfrag_layout,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        titleView = view.findViewById(R.id.titleTextView)
        bodyView = view.findViewById(R.id.bodyTextView)
        userIdView = view.findViewById(R.id.userIdText)
        idView = view.findViewById(R.id.postIdText)
        val args = arguments
        val title = args?.getString("title")
        val body = args?.getString("body")
        val userId = args?.getString("userId")
        val id = args?.getString("id")

        titleView.text = title
        bodyView.text = body
        userIdView.text = "User ID: $userId"
        idView.text = "ID: $id"
    }
}