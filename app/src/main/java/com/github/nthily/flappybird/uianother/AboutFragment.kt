package com.myproject.sunsetlacross.uianother

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.myproject.sunsetlacross.R
import kotlinx.android.synthetic.main.fragment_about.*

class AboutFragment : Fragment(R.layout.fragment_about) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        englishAboutBack.setOnClickListener {
            findNavController().navigate(
                R.id.action_aboutFragment_to_mainMenuFragment
            )
        }

        russianAboutBack.setOnClickListener {
            findNavController().navigate(
                R.id.action_aboutFragment_to_mainMenuFragment
            )
        }

        if (count == 1) {
            russianAboutBack.visibility = View.INVISIBLE
            englishAboutBack.visibility = View.VISIBLE
            russianLongText.visibility = View.INVISIBLE
            englishLongText.visibility = View.VISIBLE
        } else {
            russianAboutBack.visibility = View.VISIBLE
            englishAboutBack.visibility = View.INVISIBLE
            russianLongText.visibility = View.VISIBLE
            englishLongText.visibility = View.INVISIBLE
        }

    }

}