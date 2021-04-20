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
import com.example.sensors.databinding.FragmentAccelerometerBinding

class AccelerometerFragment : Fragment() {

    lateinit var binding: FragmentAccelerometerBinding
    lateinit var sensorManager: SensorManager
    lateinit var myAccelerometerSensor: Sensor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_accelerometer, container, false)
        sensorManager = context?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        myAccelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        sensorManager.registerListener(proximityListener, myAccelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL)

        return binding.root
    }

    private var proximityListener = object: SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        }
        override fun onSensorChanged(event: SensorEvent?) {
            if(event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
                binding.textViewXAxis.text = "X: ".plus(event.values[0].toString())
                binding.textViewYAxis.text = "Y: ".plus(event.values[1].toString())
                binding.textViewZAxis.text = "Z: ".plus(event.values[2].toString())
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        sensorManager.unregisterListener(proximityListener)
    }
}