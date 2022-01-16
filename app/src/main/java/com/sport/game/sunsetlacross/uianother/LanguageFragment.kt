package com.sport.game.sunsetlacross.uianother

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sport.game.sunsetlacross.R
import kotlinx.android.synthetic.main.fragment_language.back
import kotlinx.android.synthetic.main.fragment_language.englishFlag
import kotlinx.android.synthetic.main.fragment_language.russianBack
import kotlinx.android.synthetic.main.fragment_language.russianFlag

var count = 1

class LanguageFragment : Fragment(R.layout.fragment_language) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        back.setOnClickListener {
            findNavController().navigate(
                R.id.action_languageFragment_to_mainMenuFragment
            )
        }

        russianBack.setOnClickListener {
            findNavController().navigate(
                R.id.action_languageFragment_to_mainMenuFragment
            )
        }

        englishFlag.setOnClickListener {
            back.visibility = View.VISIBLE
            russianBack.visibility = View.INVISIBLE

            count = 1
        }
//
        russianFlag.setOnClickListener {
            russianBack.visibility = View.VISIBLE
            back.visibility = View.INVISIBLE

            count = 2
        }
    }
}