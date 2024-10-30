package edu.temple.dicethrow

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


/*
Our DieThrow application has been refactored to move the dieRoll() logic
into the ViewModel instead of the Fragment.
Study the DieViewModel, ButtonFragment, and DieFragment classes to
see the changes.

Follow the requirements below to have this app function
in both portrait and landscape configurations.
The Activity layout files for both Portrait and Landscape are already provided
*/

class MainActivity : AppCompatActivity(), ButtonFragment.ButtonInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* TODO 1: Load fragment(s)
            - Show only Button Fragment if portrait
            - show both fragments if Landscape
          */

        // if savedInstanceState is empty
        if (savedInstanceState == null) {
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                // Load only ButtonFragment in portrait mode
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container1, ButtonFragment())
                    .addToBackStack(null)
                    .commit()
            } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)  {
                // Load both fragments in landscape mode
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container1, ButtonFragment())  // Button Fragment
                    .replace(R.id.container2, DieFragment())      // Die Fragment
                    .commit()
            }
        }
        // when savedInstanceState hold data
        else {
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                // Load only ButtonFragment in portrait mode
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container1, ButtonFragment())
                    .addToBackStack(null)
                    .commit()
            } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                // Load both fragments in landscape mode
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container1, ButtonFragment())  // Button Fragment
                    .replace(R.id.container2, DieFragment())      // Die Fragment
                    .commit()
            }
        }

    }

    /* TODO 2: switch fragments if portrait (no need to switch fragments if Landscape)
        */
    // Remember to place Fragment transactions on BackStack so then can be reversed
    override fun buttonClicked() {

        /*
        // switch them
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container1, DieFragment())
                .addToBackStack(null) // Add to back stack to allow reversing
                .commit()
        }

         */

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            // Remove DieFragment if it's currently displayed
            val dieFragment = supportFragmentManager.findFragmentById(R.id.container2)
            if (dieFragment != null) {
                supportFragmentManager.beginTransaction()
                    .remove(dieFragment)
                    .commit()
            }

            // Now replace ButtonFragment with DieFragment in container1
            supportFragmentManager.beginTransaction()
                .replace(R.id.container1, DieFragment())
                .addToBackStack(null) // Add to back stack to allow reversing
                .commit()
        }



    }


}

