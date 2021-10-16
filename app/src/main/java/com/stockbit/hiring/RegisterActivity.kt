package com.stockbit.hiring

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.stockbit.hiring.model.User

class RegisterActivity : AppCompatActivity() {

    private val activity = this@RegisterActivity

    lateinit var edtUsername: TextInputEditText
    lateinit var edtPassword: TextInputEditText
    lateinit var edtEmail: TextInputEditText
    lateinit var layoutUsername: TextInputLayout
    lateinit var layoutPassword: TextInputLayout
    lateinit var layoutEmail: TextInputLayout
    lateinit var btnRegist: Button

    lateinit var txtLogin: TextView

    private lateinit var inputValidation: InputValidation
    lateinit var databaseHelper: DatabaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initViews()
        initListener()
        initObject()

        txtLogin = findViewById(R.id.txtLogin)
        txtLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }


    }

    private fun initObject() {
        inputValidation = InputValidation(activity)
        databaseHelper = DatabaseHelper(activity)
    }

    private fun initListener() {
        btnRegist.setOnClickListener { postDataToSqlite() }

    }

    private fun postDataToSqlite() {
        if (!inputValidation!!.isInputEditTextFilled(edtUsername, layoutUsername, getString(R.string.error_message_name))) {
            return
        }
        if (!inputValidation!!.isInputEditTextFilled(edtEmail, layoutEmail, getString(R.string.error_message_email))) {
            return
        }
        if (!inputValidation!!.isInputEditTextFilled(edtPassword, layoutPassword, getString(R.string.error_message_password))) {
            return
        }
        if (!databaseHelper!!.checkUser(edtEmail!!.text.toString().trim(), edtPassword!!.text.toString().trim())) {
            var user = User(name = edtUsername!!.text.toString().trim(),
                email = edtEmail!!.text.toString().trim(),
                password = edtPassword!!.text.toString().trim())
            databaseHelper!!.addUser(user)
            Toast.makeText(this, R.string.success_message, Toast.LENGTH_SHORT).show()
            emptyInputEditText()
        } else {
            // Snack Bar to show error message that record already exists
            Toast.makeText(this, R.string.error_message_email, Toast.LENGTH_SHORT).show()
        }
    }

    private fun emptyInputEditText() {
        edtUsername!!.text = null
        edtEmail!!.text = null
        edtPassword!!.text = null
    }

    private fun initViews() {
        layoutUsername = findViewById(R.id.layoutUsername)
        layoutEmail = findViewById(R.id.layoutEmail)
        layoutPassword = findViewById(R.id.layoutPassword)
        edtUsername = findViewById(R.id.edtUsername)
        edtEmail = findViewById(R.id.edtEmail)
        edtPassword = findViewById(R.id.edtPassword)
        btnRegist = findViewById(R.id.btnRegister)
    }



}


