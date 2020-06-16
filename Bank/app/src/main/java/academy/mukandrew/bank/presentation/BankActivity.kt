package academy.mukandrew.bank.presentation

import academy.mukandrew.bank.R
import academy.mukandrew.bank.presentation.auth.AuthFragment
import academy.mukandrew.bank.presentation.home.HomeFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class BankActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        goToAuthPage()
    }

    fun goToHomePage() = changeCurrentFragment(HomeFragment())

    fun goToAuthPage() = changeCurrentFragment(AuthFragment())

    private fun changeCurrentFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentHost, fragment)
            .commitNow()
    }
}