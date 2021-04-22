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
import com.example.sensors.databinding.FragmentGyroScopeBinding

class GyroScopeFragment : Fragment() {
    lateinit var binding: FragmentGyroScopeBinding
    lateinit var sensorManager: SensorManager
    lateinit var myGyroSensor: Sensor

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_gyro_scope, container, false)
        sensorManager = context?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        myGyroSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

        sensorManager.registerListener(gyroListener, myGyroSensor, SensorManager.SENSOR_DELAY_NORMAL)

        return binding.root
    }

    private var gyroListener = object: SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        }

        override fun onSensorChanged(event: SensorEvent?) {
            if(event?.sensor?.type == Sensor.TYPE_GYROSCOPE) {
                if(event.values[2] > 0.5f) {
                    binding.textViewGyroScope.text = getString(R.string.left)
                } else if (event.values[2] < -0.5f) {
                    binding.textViewGyroScope.text = getString(R.string.right)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        sensorManager.unregisterListener(gyroListener)
    }

}