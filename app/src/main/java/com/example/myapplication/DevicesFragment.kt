package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.fragment.app.Fragment

class DevicesFragment : Fragment() {

    interface DeviceListener {
        fun onDeviceToggled(name: String, active: Boolean)
    }

    private var listener: DeviceListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is DeviceListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement DeviceListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_devices, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val swLights = view.findViewById<Switch>(R.id.swLights)
        val swAC = view.findViewById<Switch>(R.id.swAC)

        swLights.setOnCheckedChangeListener { _, isChecked ->
            listener?.onDeviceToggled("Luzes", isChecked)
        }

        swAC.setOnCheckedChangeListener { _, isChecked ->
            listener?.onDeviceToggled("Ar Condicionado", isChecked)
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}
