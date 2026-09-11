package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment() {

    interface ProfileListener {
        fun onNameChanged(name: String)
    }

    private var listener: ProfileListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ProfileListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement ProfileListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val etUserName = view.findViewById<EditText>(R.id.etUserName)
        val btnSave = view.findViewById<Button>(R.id.btnSave)

        btnSave.setOnClickListener {
            val name = etUserName.text.toString()
            listener?.onNameChanged(name)
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}
