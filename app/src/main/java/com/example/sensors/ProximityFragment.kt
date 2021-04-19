package com.example.sensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import com.example.sensors.databinding.FragmentProximityBinding

class ProximityFragment : Fragment() {

    lateinit var binding: FragmentProximityBinding
    lateinit var sensorManager: SensorManager
    lateinit var myProximitySensor: Sensor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_proximity, container, false)
        sensorManager = context?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        myProximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)

        if (myProximitySensor == null) {
            binding.sensorValue.text = "No Sensor"
        } else {
            sensorManager.registerListener(proximityListener, myProximitySensor, SensorManager.SENSOR_DELAY_NORMAL)
        }

        return binding.root
    }

    private var proximityListener = object: SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        }

        override fun onSensorChanged(event: SensorEvent?) {
            val params = activity?.window?.attributes

            if(event?.sensor?.type == Sensor.TYPE_PROXIMITY) {
                if (params != null) {
                    params.flags = params.flags or WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                    params.screenBrightness = -1f
                    activity?.window?.attributes = params
                    binding.sensorValue.text = event.values[0].toString()
                }
                if (event.values[0] == 0f) {
                    binding.sensorDistance.text = getString(R.string.near)
                } else {
                    binding.sensorDistance.text = getString(R.string.far)
                }
            }
        }
    }
}