package com.asm.logindemo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.asm.logindemo.databinding.ActivityMainBinding
import com.asm.logindemo.extension.addTextChangedListener
import com.asm.logindemo.extension.setOnClickListener
import com.asm.logindemo.utils.ToastUtils

class MainActivity : BaseActivity() {

    var _binding: ActivityMainBinding? = null

    val binding: ActivityMainBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListener()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initListener() {
        binding.etPhoneNumber.addTextChangedListener { afterTextChanged = { changeStatus() } }
        binding.etCode.addTextChangedListener { afterTextChanged = { changeStatus() } }

        setOnClickListener(binding.tvUserLogin, binding.tvObtainCode) {
            when (this) {
                binding.tvObtainCode -> {
                    binding.tvObtainCode.start()
                    ToastUtils.showShortMessage("短信验证码已发送")
                }
                binding.tvUserLogin -> {
                }
                else -> {
                }
            }
        }
    }

    private fun changeStatus() {
        binding.tvObtainCode.isEnabled = binding.etPhoneNumber.length() == 11
        binding.tvUserLogin.isEnabled =
            binding.etPhoneNumber.length() == 11 && binding.etCode.length() == 6
    }

    companion object {

        fun start(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }
}