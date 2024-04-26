package com.adso.calculadora

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.adso.calculadora.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),  View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private var operand1: Double = Double.NaN
    private var operand2: Double = Double.NaN
    private var currentOperator = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn0.setOnClickListener(this)
        binding.btn1.setOnClickListener(this)
        binding.btn2.setOnClickListener(this)
        binding.btn3.setOnClickListener(this)
        binding.btn4.setOnClickListener(this)
        binding.btn5.setOnClickListener(this)
        binding.btn6.setOnClickListener(this)
        binding.btn7.setOnClickListener(this)
        binding.btn8.setOnClickListener(this)
        binding.btn9.setOnClickListener(this)
        binding.btnAdd.setOnClickListener(this)
        binding.btnSubtract.setOnClickListener(this)
        binding.btnMultiply.setOnClickListener(this)
        binding.btnDivide.setOnClickListener(this)
        binding.btnEquals.setOnClickListener(this)
        binding.btnDelete.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn0 -> {
                appendNumber("0")
            }
            R.id.btn1 -> {
                appendNumber("1")
            }
            R.id.btn2 -> {
                appendNumber("2")
            }
            R.id.btn3 -> {
                appendNumber("3")
            }
            R.id.btn4 -> {
                appendNumber("4")
            }
            R.id.btn5 -> {
                appendNumber("5")
            }
            R.id.btn6 -> {
                appendNumber("6")
            }
            R.id.btn7 -> {
                appendNumber("7")
            }
            R.id.btn8 -> {
                appendNumber("8")
            }
            R.id.btn9 -> {
                appendNumber("9")
            }
            R.id.btnAdd -> {
                setOperator("+")
            }
            R.id.btnSubtract -> {
                setOperator("-")
            }
            R.id.btnMultiply -> {
                setOperator("*")
            }
            R.id.btnDivide -> {
                setOperator("/")
            }
            R.id.btnEquals -> {
                calculateResult()
            }
            R.id.btnDelete -> {
                deleteNumber()
            }
        }
    }

    private fun appendNumber(number: String) {
        if (binding.tvResult.text.toString() == "Error") {
            binding.tvResult.text = ""
        }
        binding.tvResult.append(number)
    }

    private fun setOperator(operator: String) {
        if (!binding.tvResult.text.isNullOrEmpty()) {
            operand1 = binding.tvResult.text.toString().toDouble()
            binding.tvResult.text = ""
            currentOperator = operator
        }
    }
    private fun deleteNumber() {
        val currentText = binding.tvResult.text.toString()
        if (currentText.isNotEmpty()) {
            // Elimina el último dígito
            binding.tvResult.text = currentText.substring(0, currentText.length - 1)
        }
    }
    private fun calculateResult() {
        if (!binding.tvResult.text.isNullOrEmpty() && !currentOperator.isEmpty()) {
            operand2 = binding.tvResult.text.toString().toDouble()
            var result = 0.0
            when (currentOperator) {
                "+" -> result = operand1 + operand2
                "-" -> result = operand1 - operand2
                "*" -> result = operand1 * operand2
                "/" -> {
                    if (operand2 != 0.0) {
                        result = operand1 / operand2
                    } else {
                        binding.tvResult.text = "Error"
                        return
                    }
                }
            }
            binding.tvResult.text = result.toString()
        }
    }
}