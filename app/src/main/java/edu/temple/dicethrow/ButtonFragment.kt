package edu.temple.dicethrow

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider

class ButtonFragment : Fragment() {

    private val dieViewModel: DieViewModel by lazy {
        ViewModelProvider(requireActivity())[DieViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_button, container, false).apply {
            findViewById<Button>(R.id.rollButton).setOnClickListener {

                // Generate a random die roll
                dieViewModel.rollDie()

                // Notify activity
                (requireActivity() as ButtonInterface).buttonClicked()
            }
        }
    }

    interface ButtonInterface {
        fun buttonClicked()
    }


}

