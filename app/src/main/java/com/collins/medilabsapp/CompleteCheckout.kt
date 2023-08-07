package com.collins.medilabsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.collins.medilabsapp.constants.Constants
import com.collins.medilabsapp.helpers.ApiHelper
import com.collins.medilabsapp.helpers.PrefsHelper
import org.json.JSONArray
import org.json.JSONObject

class CompleteCheckout : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete_checkout)

        //Get the required payload to make bookings from the shared preferences
        val member_id = PrefsHelper.getPrefs(this, "member_id")
        val date = PrefsHelper.getPrefs(this, "appointment_date")
        val time = PrefsHelper.getPrefs(this, "appointment_time")
        val booked_for = PrefsHelper.getPrefs(this, "booked_for")
        val where_taken = PrefsHelper.getPrefs(this, "where_taken")
        val latitude = PrefsHelper.getPrefs(this, "latitude")
        val longitude = PrefsHelper.getPrefs(this, "longitude")
        val dependant_id = 4
        val test_id = 1
        val lab_id = 4
        val invoice_no = "5454545"

        val helper = ApiHelper(this)
        val api = Constants.BASE_URL + "/make_booking"
        val body = JSONObject()
        body.put("member_id", member_id)
        body.put("appointment_date", date)
        body.put("appointment_time", time)
        body.put("booked_for", booked_for)
        body.put("where_taken", where_taken)
        body.put("latitude", latitude)
        body.put("longitude", longitude)
        body.put("dependant_id", dependant_id)
        body.put("test_id", test_id)
        body.put("lab_id", lab_id)
        body.put("invoice_no", invoice_no)

        helper.post(api, body, object : ApiHelper.CallBack {
            override fun onSuccess(result: JSONArray?) {
            }
            //user: bob101
            //pass: Qwerty1234
            override fun onSuccess(result: JSONObject?) {
                Toast.makeText(applicationContext, result.toString(),
                    Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(result: String?) {
            }

        });

    }
}