package com.hgshkt.androidtask5.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.hgshkt.androidtask5.R

class ErrorFragment : Fragment() {

    lateinit var textView: TextView
    lateinit var button: Button

    var errorMessage: String = "Error"
    var reloadButtonClick: () -> Unit = {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.error_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
        updateUI()

        button.setOnClickListener {
            reloadButtonClick()
        }
    }

    private fun updateUI() {
        textView.text = errorMessage
    }

    private fun init(view: View) {
        textView = view.findViewById(R.id.errorMessage)
        button = view.findViewById(R.id.reloadButton)
    }
}