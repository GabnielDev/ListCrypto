package com.stockbit.hiring

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {

    private val activity = this@LoginActivity
    private lateinit var layoutEmail: TextInputLayout
    private lateinit var layoutPassword: TextInputLayout

    private lateinit var edtEmail: TextInputEditText
    private lateinit var edtPassword: TextInputEditText

    private lateinit var btnLogin: Button

    private lateinit var txtRegist: TextView

    private lateinit var inputValidation: InputValidation
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar!!.hide()
        initViews()
        initListeners()
        initObject()

        txtRegist = findViewById(R.id.txtRegist)
        txtRegist.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

    }

    private fun initObject() {
        databaseHelper = DatabaseHelper(activity)
        inputValidation = InputValidation(activity)
    }

    private fun initListeners() {
        btnLogin!!.setOnClickListener { verifyFromSQLite() }

    }

    private fun verifyFromSQLite() {
        if (!inputValidation!!.isInputEditTextFilled(edtEmail!!, layoutEmail!!, getString(R.string.error_message_email))) {
            return
        }
        if (!inputValidation!!.isInputEditTextEmail(edtEmail!!, layoutEmail!!, getString(R.string.error_message_email))) {
            return
        }
        if (!inputValidation!!.isInputEditTextFilled(edtPassword!!, layoutPassword!!, getString(R.string.error_message_email))) {
            return
        }
        if (databaseHelper!!.checkUser(edtEmail!!.text.toString().trim { it <= ' ' }, edtPassword!!.text.toString().trim { it <= ' ' })) {
            val accountsIntent = Intent(activity, MainActivity::class.java)
            emptyInputEditText()
            startActivity(accountsIntent)
        } else {
            Toast.makeText(this, R.string.error_valid_email_password, Toast.LENGTH_SHORT).show()
        }
    }

    private fun initViews() {
        layoutEmail = findViewById(R.id.layoutEmail)
        layoutPassword = findViewById(R.id.layoutPassword)
        edtEmail = findViewById(R.id.edtEmail)
        edtPassword = findViewById(R.id.edtPassword)
        btnLogin = findViewById(R.id.btnLogin)
    }

    private fun emptyInputEditText() {
        edtEmail!!.text = null
        edtPassword!!.text = null
    }

}