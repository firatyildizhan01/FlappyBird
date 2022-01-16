package com.sport.game.sunsetlacross.uianother

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sport.game.sunsetlacross.R
import com.sport.game.sunsetlacross.game.continueMusic
import kotlinx.android.synthetic.main.fragment_settings.*

//var count = 1

class SettingsFragment : Fragment(R.layout.fragment_settings) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        soundTextOn.setOnClickListener {
            soundTextOn.setTextColor(Color.parseColor("#F8E64C"))
            soundTextOff.setTextColor(Color.parseColor("#33F8E64C"))
            mediaPlayer.start();

        }

        back.setOnClickListener {
            findNavController().navigate(
                R.id.action_settingsFragment_to_mainMenuFragment
            )
        }

        russianBack.setOnClickListener {
            findNavController().navigate(
                R.id.action_settingsFragment_to_mainMenuFragment
            )
        }

        if (count == 1) {

            soundText.visibility = View.VISIBLE
            //Sound
            russianSoundText.visibility = View.INVISIBLE
            vibration.visibility = View.VISIBLE
            //Vibration
            russianVibration.visibility = View.INVISIBLE
            back.visibility = View.VISIBLE
            //Back
            russianBack.visibility = View.INVISIBLE
        } else {

            russianSoundText.visibility = View.VISIBLE
            //Sound
            soundText.visibility = View.INVISIBLE
            vibration.visibility = View.INVISIBLE
            //Vibration
            russianVibration.visibility = View.VISIBLE
            back.visibility = View.INVISIBLE
            russianBack.visibility = View.VISIBLE
        }


        soundTextOff.setOnClickListener {

            soundTextOn.setTextColor(Color.parseColor("#33F8E64C"))
            soundTextOff.setTextColor(Color.parseColor("#F8E64C"))
            mediaPlayer.pause();

        }

        vibrationTextOn.setOnClickListener {
            vibrationTextOn.setTextColor(Color.parseColor("#F8E64C"))
            vibrationTextOff.setTextColor(Color.parseColor("#33F8E64C"))
            continueMusic = true

        }

        vibrationTextOff.setOnClickListener {

            vibrationTextOn.setTextColor(Color.parseColor("#33F8E64C"))
            vibrationTextOff.setTextColor(Color.parseColor("#F8E64C"))
            continueMusic = false

        }
    }


}



