package com.collins.medilabsapp

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.collins.medilabsapp.constants.Constants
import com.collins.medilabsapp.helpers.ApiHelper
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class SignUpActivity : AppCompatActivity() {
    private lateinit var buttonDatePicker: Button
    private lateinit var editTextDate: EditText
    private lateinit var spinner: Spinner
    private lateinit var selectedItemText: TextView
    private lateinit var password: EditText
    private lateinit var confirmPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val linktologin = findViewById<MaterialTextView>(R.id.linktologin)
        linktologin.setOnClickListener{
            startActivity(Intent(applicationContext, SignInActivity::class.java))
        }

        buttonDatePicker = findViewById(R.id.buttonDatePicker)
        editTextDate = findViewById(R.id.editTextDate)
        spinner = findViewById(R.id.spinner)
        selectedItemText = findViewById(R.id.selectedItemText)
        password = findViewById(R.id.password)
        confirmPassword = findViewById(R.id.confirmPassword)

        buttonDatePicker.setOnClickListener {
            showDatePickerDialog()
        }

        // Sample data for the spinner
        val data = listOf("1", "2", "3", "4", "5")//pending, Must Be Dynamic
        // Create an ArrayAdapter using the sample data
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, data)
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Set the adapter to the spinner
        spinner.adapter = adapter

        val create = findViewById<MaterialButton>(R.id.create)
        create.setOnClickListener {

            //Push/Post data to APi.
            val surname = findViewById<TextInputEditText>(R.id.surname)
            val others = findViewById<TextInputEditText>(R.id.others)
            val email = findViewById<TextInputEditText>(R.id.email)
            val phone = findViewById<TextInputEditText>(R.id.phone)
            val password = findViewById<TextInputEditText>(R.id.password)
            val confirm = findViewById<TextInputEditText>(R.id.confirmPassword)
            val female = findViewById<RadioButton>(R.id.radioFemale)
            val male = findViewById<RadioButton>(R.id.radioMale)
            var gender = "N/A"
            if (female.isSelected) {
                gender = "Female"
            }
            if (male.isSelected) {
                gender = "Male"
            }

            if (password.text.toString() != confirm.text.toString()) {
                Toast.makeText(applicationContext, "Password Not Matching", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val api = Constants.BASE_URL+"/member_signup"
                val helper = ApiHelper(applicationContext)
                val body = JSONObject()
                body.put("surname", surname.text.toString())
                body.put("others", others.text.toString())
                body.put("email", email.text.toString())
                body.put("phone", phone.text.toString())
                body.put("dob", editTextDate.toString())
                body.put("password", password.text.toString())
                body.put("gender", gender)
                body.put("location_id", spinner.selectedItem.toString())

                helper.post(api, body, object : ApiHelper.CallBack {
                    override fun onSuccess(result: JSONArray?) {
                    }

                    override fun onSuccess(result: JSONObject?) {
                        Toast.makeText(applicationContext, result.toString(),
                            Toast.LENGTH_SHORT).show()
                    }

                    override fun onFailure(result: String?) {
                    }

                })
            }


            // Validate password when user finishes typing
            password.setOnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) {
                    validatePassword()
                }
            }

            // Confirm password when user finishes typing
            confirmPassword.setOnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) {
                    confirmPassword()
                }
            }
        }//close on click
    }//end oncreate

    //other functions
    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        // Create a date picker dialog and set the current date as the default selection
        val datePickerDialog = DatePickerDialog(
            this,
            { _: DatePicker, year: Int, month: Int, day: Int ->
                val selectedDate = formatDate(year, month, day)
                editTextDate.setText(selectedDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        // Show the date picker dialog
        datePickerDialog.show()
    }

    private fun formatDate(year: Int, month: Int, day: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }

    private fun validatePassword() {
        val passwordValidity = password.text.toString()
        // Implement your password validation logic here
        // For example, you can check if the password meets certain requirements (e.g., minimum length, contains uppercase, lowercase, and special characters)
        val isPasswordValid = password.length() >= 8 && passwordValidity.matches(".*[A-Z].*".toRegex()) &&
                passwordValidity.matches(".*[a-z].*".toRegex()) && passwordValidity.matches(".*\\d.*".toRegex()) &&
                passwordValidity.matches(".*[^A-Za-z0-9].*".toRegex())

        if (!isPasswordValid) {

            // Password does not meet requirements, show an error or warning message
            password.error =
                "Password should contain at least 8 characters, including uppercase, lowercase, digits, and special characters."
        }
    }

    private fun confirmPassword() {
        val passwordconfirm = password.text.toString()
        val confirmPassword = confirmPassword.text.toString()

        if (passwordconfirm != confirmPassword) {
            // Passwords do not match, show an error or warning message
            password.error
        }
    }
}
