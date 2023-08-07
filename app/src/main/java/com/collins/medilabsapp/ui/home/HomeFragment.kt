package com.collins.medilabsapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.collins.medilabsapp.databinding.FragmentHomeBinding
import com.collins.medilabsapp.helpers.PrefsHelper
import org.json.JSONObject

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        //Code Here
        val userObject = PrefsHelper.getPrefs(requireContext(), "userObject")
        val user = JSONObject(userObject)// convert to JSON Object
        //Text View 1
        val surname = _binding!!.surname // find view
        surname.text = "Surname:"+user.getString("surname")

        val others = _binding!!.others // find view
        others.text = "Others:"+user.getString("others")

        val email = _binding!!.email // find view
        email.text = "Email: "+user.getString("email")

        val phone = _binding!!.phone // find view
        phone.text = "Phone: "+user.getString("phone")

        val dob = _binding!!.dob // find view
        dob.text = "dob: "+user.getString("dob")

        val gender = _binding!!.gender // find view
        gender.text = "gender: "+user.getString("gender")

        val reg_date = _binding!!.regDate // find view
        reg_date.text = "Reg_date: "+user.getString("reg_date")
        //gender, dob, red_date, email



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}