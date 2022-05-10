package com.mellow.alt.screen.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mellow.alt.R
import com.mellow.alt.application.App
import com.mellow.alt.custom.OnLastAddedListener
import com.mellow.alt.databinding.ActivityLogInBinding
import com.mellow.alt.net.service.UserService
import com.mellow.alt.screen.basic.swipe.SwipeActivity
import org.kodein.di.instance

class LogInActivity : AppCompatActivity(R.layout.activity_log_in) {

    private val userService by App.di.instance<UserService>()

    private val binding by viewBinding(ActivityLogInBinding::bind)

    private var phoneNumber: String = ""

    companion object {
        fun getIntent(context: Context) = Intent(context, LogInActivity::class.java)
    }

    private lateinit var viewModel: LogInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[LogInViewModel::class.java]

        showPhone()
        initCodeListeners()
        initPhoneListeners()
        initRegisterListeners()
    }


    private fun initCodeListeners() {
        binding.inclCode.vChangePhoneNumber.setOnClickListener {
            showPhone()
        }

        binding.inclCode.vCode.setOnLastAddedListener(object : OnLastAddedListener {
            override fun onLastAdded(s: String) {
                viewModel.sendCodeRegister(s)
            }
        })
    }

    private fun initRegisterListeners() {
        viewModel.loggedIn.observe(this, {
            if (it) {
                val intent = SwipeActivity.getIntent(this)
                startActivity(intent)
            }
        })
    }

    private fun initPhoneListeners() {
        viewModel.userExistsLiveData.observe(this, { userExists ->
            if (userExists) {
                showCode()
            } else {
                showRegistration()
            }
        })

        binding.inclPhone.vEtNumber.setOnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_DEL) {
                if (binding.inclPhone.vEtNumber.text?.length ?: 0 == 0) {
                    binding.inclPhone.vEtOperatorCode.requestFocus()
                }
            }
            false
        }

        binding.inclPhone.vEtOperatorCode.addTextChangedListener { text: Editable? ->
            text?.let {
                if (it.length == 2) {
                    binding.inclPhone.vEtNumber.requestFocus()
                }
            }

            checkIfPhoneNumberFilledAndEnableBtn()
        }

        binding.inclPhone.vEtNumber.addTextChangedListener { text: Editable? ->
            text?.let {
                if (it.length == 7) {
                    binding.inclPhone.vEtNumber.clearFocus()
                    val imm =
                        baseContext.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                    imm?.hideSoftInputFromWindow(binding.inclPhone.vEtNumber.windowToken, 0)
                }
            }

            checkIfPhoneNumberFilledAndEnableBtn()
        }

        binding.inclPhone.vBtnSendCode.setOnClickListener {
            phoneNumber = binding.inclPhone.vTvCountryCode.text.toString() +
                    binding.inclPhone.vEtOperatorCode.text.toString() +
                    binding.inclPhone.vEtNumber.text.toString()

            if (phoneNumber.length == 12) {
                binding.inclCode.vTvCodeWasSendOnNumber.text =
                    getString(R.string.code_was_sent, phoneNumber)
            }
        }
    }

    private fun showCode() {
        hideAllIncl()

        binding.inclCode.root.visibility = View.VISIBLE
    }

    private fun showPhone() {
        hideAllIncl()

        binding.inclPhone.root.visibility = View.VISIBLE
    }

    private fun hideAllIncl() {
        binding.inclPhone.root.visibility = View.GONE
        binding.inclCode.root.visibility = View.GONE
        binding.inclRegistration.root.visibility = View.GONE
    }

    private fun showRegistration() {
        hideAllIncl()

        binding.inclRegistration.root.visibility = View.VISIBLE
    }

    private fun checkIfPhoneNumberFilledAndEnableBtn() {
        binding.inclPhone.vBtnSendCode.isEnabled =
            binding.inclPhone.vEtOperatorCode.text?.length ?: 0 == 2 &&
                    binding.inclPhone.vEtNumber.text?.length ?: 0 == 7
    }
}