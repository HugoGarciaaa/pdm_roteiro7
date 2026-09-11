package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ToggleButton
import androidx.fragment.app.Fragment

class SecurityFragment : Fragment() {

    interface SecurityListener {
        fun onSecurityToggled(active: Boolean)
    }

    private var listener: SecurityListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SecurityListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement SecurityListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_security, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tbSecurity = view.findViewById<ToggleButton>(R.id.tbSecurity)

        tbSecurity.setOnClickListener {
            listener?.onSecurityToggled(tbSecurity.isChecked)
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}
