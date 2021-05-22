package com.example.navbar.ui.userProfile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.navbar.R
import com.example.navbar.ui.changePassword.changePasswordFragment
import kotlinx.android.synthetic.main.fragment_user_profile.view.*

class userProfileFragment : Fragment(R.layout.fragment_user_profile) {

    private lateinit var profileViewModel: userProfileViewModel
    var name: String? = ""
    var surname: String? = ""
    var email: String? = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        profileViewModel =
            ViewModelProvider(this).get(userProfileViewModel::class.java)
        var root = inflater.inflate(R.layout.fragment_user_profile, container, false)
        var textView: TextView = root.findViewById(R.id.text_userProfile)
        //var view = inflater.inflate(R.layout.fragment_user_profile, container, false)
        profileViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        name = arguments?.getString("message")
        surname = arguments?.getString("message2")
        email = arguments?.getString("message3")
        root.userName.text = name
        root.userSurname.text = surname
        root.userEmail.text = email
//        root.userProfileName.text = displayMessage2
//        root.userProfileSurname.text = displayMessage3
//        root.userProfileEmail.text = displayMessage
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = view.findViewById<Button>(R.id.userProfileChangePass)
        button.setOnClickListener {
            val nextFrag = changePasswordFragment()
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.nav_host_fragment, nextFrag)?.addToBackStack(null)?.commit()
        }

    }
}