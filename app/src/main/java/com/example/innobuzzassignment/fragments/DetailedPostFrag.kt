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
        val args = arguments
        val title = args?.getString("title")
        val body = args?.getString("body")

        titleView.text = title
        bodyView.text = body
    }
}