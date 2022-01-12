package com.github.nthily.flappybird.uianother

import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.github.nthily.flappybird.R
import kotlinx.android.synthetic.main.fragment_main_menu.*
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import com.github.nthily.flappybird.MainActivity


class MainMenuFragment : Fragment(R.layout.fragment_main_menu) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        settings.setOnClickListener {
            findNavController().navigate(
                R.id.action_mainMenuFragment_to_settingsFragment
            )
        }

        settingsLanguage.setOnClickListener {
            findNavController().navigate(
                R.id.action_mainMenuFragment_to_languageFragment
            )
        }

        russianSettings.setOnClickListener {
            findNavController().navigate(
                R.id.action_mainMenuFragment_to_settingsFragment
            )
        }

        aboutLacros.setOnClickListener {
            findNavController().navigate(
                R.id.action_mainMenuFragment_to_aboutFragment
            )
        }

        russianAboutLacros.setOnClickListener {
            findNavController().navigate(
                R.id.action_mainMenuFragment_to_aboutFragment
            )
        }
        textStart.setOnClickListener {
            val intent = Intent()
            intent.setClass(activity!!, MainActivity::class.java)
            activity!!.startActivity(intent)
        }

        textRussianStart.setOnClickListener {
            val intent = Intent()
            intent.setClass(activity!!, MainActivity::class.java)
            activity!!.startActivity(intent)
        }


        if (count == 1) {
            textRussianStart.visibility = View.INVISIBLE
            textStart.visibility = View.VISIBLE
            settings.visibility = View.VISIBLE
            russianSettings.visibility = View.INVISIBLE
            russianAboutLacros.visibility = View.INVISIBLE
            aboutLacros.visibility = View.VISIBLE
        } else {
            textRussianStart.visibility = View.VISIBLE
            textStart.visibility = View.INVISIBLE
            settings.visibility = View.INVISIBLE
            russianSettings.visibility = View.VISIBLE
            aboutLacros.visibility = View.INVISIBLE
            russianAboutLacros.visibility = View.VISIBLE
        }
    }


}