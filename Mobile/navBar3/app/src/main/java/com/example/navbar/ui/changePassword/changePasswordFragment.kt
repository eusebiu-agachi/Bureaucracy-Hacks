package com.example.navbar.ui.changePassword

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.navbar.R
import com.example.navbar.ui.changePassword.changePasswordModel.changePasswordPost
import com.example.navbar.ui.changePassword.changePasswordRepository.changePasswordRepository
import com.example.navbar.ui.home.HomeFragment

class changePasswordFragment : Fragment() {

    private lateinit var passwordViewModel: changePasswordViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val repository = changePasswordRepository()
        val viewModelFactory = changePasswordViewModelFactory(repository)
        passwordViewModel = ViewModelProvider(this, viewModelFactory).get(changePasswordViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_change_password, container, false)
        val textView: TextView = root.findViewById(R.id.text_changePassword)
        passwordViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        /**
        val myPost = Post("mgabriela001@yahoo.com", "password", "password")
        passwordViewModel.pushPost(myPost)
        passwordViewModel.myResponse.observe(viewLifecycleOwner, Observer {response ->
            if (response.isSuccessful) {
                Log.d("Response", response.body()?.userEmail.toString())
                textView.text = response.body()?.userEmail.toString()
                Log.d("Response", response.body()?.userNewPassword.toString())
                Log.d("Response", response.body()?.userConfirmNewPassword.toString())
                Toast.makeText(activity, textView.text, Toast.LENGTH_LONG).show()
                Log.d("Response", response.code().toString())

                //Log.d("Response", response.body()?.myUserId.toString())
                //Log.d("Response", response.body()?.id.toString())
                //Log.d("Response", response.body()?.title.toString())
                //textView.text = response.body()?.title!!
                //Log.d("Response", response.body()?.body.toString())
                //Log.d("Response", response.code().toString())
                //Toast.makeText(activity, textView.text, Toast.LENGTH_LONG).show()

            } else {
                Log.d("Response", response.errorBody().toString())
                textView.text = response.code().toString()
                Toast.makeText(activity, textView.text, Toast.LENGTH_LONG).show()
            }
        })
        */
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textBox1 = view.findViewById<EditText>(R.id.changePassEmail)
        val continut1 = textBox1.text.toString()

        val textBox2 = view.findViewById<EditText>(R.id.changePassParola1)
        val continut2 = textBox2.text.toString()

        val textBox3 = view.findViewById<EditText>(R.id.changePassParola2)
        val continut3 = textBox3.text.toString()

        if (continut1.isEmpty()) {
            textBox1.error = "Introdu adresa de email!"
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(textBox1.text.toString()).matches()) {
            textBox1.error = "Adresa de email nu este corectă!"
        } else {
            if (continut2.isEmpty()) {
                textBox2.error = "Introdu noua parolă!"
            } else if (continut3.isEmpty()) {
                textBox3.error = "Introdu noua parolă!"
            } else {
                if (continut2 == continut3 && continut2.isNotEmpty() && continut1.isNotEmpty()) {
                    //val nextFrag = HomeFragment()
                    Toast.makeText(activity, "Parolă schimbată cu succes", Toast.LENGTH_LONG).show()
                    //activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.nav_host_fragment, nextFrag)?.addToBackStack(null)?.commit()
                } else {
                    Toast.makeText(activity, "Parolele nu coincid!", Toast.LENGTH_LONG).show()
                }
            }
        }

        val buttonConfirma = view.findViewById<Button>(R.id.changePassConfirma)
        buttonConfirma.setOnClickListener() {
            val textView = view.findViewById<TextView>(R.id.text_changePassword)

           // val myPost = changePasswordPost("agachi.eusebiu@yahoo.com", "password", "password")
            val myPost = changePasswordPost(email = continut1, newPassword = continut2, confirmNewPassword = continut3)
            passwordViewModel.pushPost(myPost)
            passwordViewModel.myResponse.observe(viewLifecycleOwner, Observer {response ->
                if (response.isSuccessful) {
                    Log.d("Response", response.body()?.email.toString())
                    textView.text = response.body()?.email.toString()
                    Log.d("Response", response.body()?.newPassword.toString())
                    Log.d("Response", response.body()?.confirmNewPassword.toString())
                    Toast.makeText(activity, textView.text, Toast.LENGTH_LONG).show()
                    Log.d("Response", response.code().toString())
                    //Log.d("Response", response.body()?.myUserId.toString())
                    //Log.d("Response", response.body()?.id.toString())
                    //Log.d("Response", response.body()?.title.toString())
                    //textView.text = response.body()?.title!!
                    //Log.d("Response", response.body()?.body.toString())
                    //Log.d("Response", response.code().toString())
                    //Toast.makeText(activity, textView.text, Toast.LENGTH_LONG).show()

                } else {
                    Log.d("Response", response.errorBody().toString())
                    textView.text = response.code().toString()
                    //Toast.makeText(activity, textView.text, Toast.LENGTH_LONG).show()
                    Toast.makeText(activity, continut1, Toast.LENGTH_LONG).show()
                }
            })

        }


        val buttonAnuleaza = view.findViewById<Button>(R.id.changePassAnuleaza)
        buttonAnuleaza.setOnClickListener {
            val nextFrag = HomeFragment()
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.nav_host_fragment, nextFrag)?.addToBackStack(null)?.commit()
        }
    }
}