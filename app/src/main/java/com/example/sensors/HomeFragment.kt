package com.example.sensors

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.sensors.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.buttonProximity.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_proximityFragment)
        }
        binding.buttonAccelerometer.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_accelerometerFragment)
        }
        binding.buttonLight.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_lightFragment)
        }

        return binding.root
    }
}