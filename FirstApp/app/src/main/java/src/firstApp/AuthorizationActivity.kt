package src.firstApp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns.*
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class AuthorizationActivity : AppCompatActivity() {

    val APP_PREFERENCES = "userMemory"
    val APP_PREFERENCES_EMAIL = "email"

    lateinit var memory: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        memory = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTitle("Authorization");
        if (memory.contains(APP_PREFERENCES_EMAIL)) {
            val profileActivity = Intent(this, ProfileActivity::class.java)
            profileActivity.putExtra(ProfileActivity.EMAIL, memory.getString(APP_PREFERENCES_EMAIL, ""))
            startActivity(profileActivity)
        }
    }

    fun check(view : View) {
        if (profileEmail.text.toString().isEmpty()) {
            Toast.makeText(this, "Email is empty!", Toast.LENGTH_SHORT).show()
        }
        else if (pass.text.toString().isEmpty())
            Toast.makeText(this, "Password is empty!", Toast.LENGTH_SHORT).show()
        else if (!validate()) {
            Toast.makeText(this, "Wrong email or password pattern!", Toast.LENGTH_SHORT).show()
        }
        else if (!registered())
            Toast.makeText(this, "User not found!", Toast.LENGTH_SHORT).show()
        else {
            val profileActivity = Intent(this, ProfileActivity::class.java)
            profileActivity.putExtra(ProfileActivity.EMAIL, profileEmail.text.toString())

            val edit = memory.edit();
            edit.putString(APP_PREFERENCES_EMAIL, profileEmail.text.toString())
            edit.apply()
            startActivity(profileActivity)
        }
    }

    fun validate() : Boolean {
        val passReg = Regex(pattern = "^(?=^.{6,}\$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*\$")
        return  EMAIL_ADDRESS.matcher(profileEmail.text.toString()).matches() && passReg.containsMatchIn(pass.text.toString())
    }

    fun registered() :Boolean {
        var users = UsersData()
        var user = users.containsUser(profileEmail.text.toString())
        if (user != null)
            return user.password.equals(pass.text.toString())
        return false
    }
}
