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
import androidx.databinding.DataBindingUtil
import com.example.sensors.databinding.FragmentLightBinding

class LightFragment : Fragment() {

    lateinit var binding: FragmentLightBinding
    lateinit var sensorManager: SensorManager
    lateinit var myLightSensor: Sensor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_light, container, false)
        sensorManager = context?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        myLightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)

        sensorManager.registerListener(lightListener, myLightSensor, SensorManager.SENSOR_DELAY_NORMAL)


        return binding.root
    }

    private var lightListener = object: SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        }

        override fun onSensorChanged(event: SensorEvent?) {
            if(event?.sensor?.type == Sensor.TYPE_LIGHT) {
                binding.textViewLight.text = event.values[0].toString()
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        sensorManager.unregisterListener(lightListener)
    }
}