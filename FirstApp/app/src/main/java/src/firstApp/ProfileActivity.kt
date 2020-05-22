package src.firstApp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    val APP_PREFERENCES = "userMemory"
    val APP_PREFERENCES_EMAIL = "email"

    lateinit var memory: SharedPreferences

    companion object {
        const val EMAIL : String = "sample"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        var users = UsersData()
        profileEmail.text = intent.getStringExtra(EMAIL)
        var user = users.containsUser(intent.getStringExtra(EMAIL))
        name.text = user?.name
        surname.text = user?.surname
        profileEmail.text = user?.email
    }

    fun exit (view: View) {
        memory = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        memory.edit().clear().apply()
        startActivity(Intent(this, AuthorizationActivity::class.java))
    }
}
