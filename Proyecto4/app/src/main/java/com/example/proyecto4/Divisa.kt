package com.example.proyecto4

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.proyecto4.databinding.DivisaBinding

class Divisa : AppCompatActivity() {

    // Variable de binding para el layout divisa.xml
    private lateinit var binding: DivisaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflamos el layout 'divisa.xml' usando el binding
        binding = DivisaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar el Spinner para las divisas
        val currencies = arrayOf("USD", "EUR", "MXN")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adapter

        // Conversión de Soles
        binding.convertButton.setOnClickListener {
            val amount = binding.monto.text.toString().toDoubleOrNull()
            val targetCurrency = binding.spinner.selectedItem.toString()

            if (amount != null && targetCurrency.isNotEmpty()) {
                // Tasas de conversión desde Soles (PEN)
                val conversionRate = when (targetCurrency) {
                    "USD" -> 0.27
                    "EUR" -> 0.25
                    "MXN" -> 4.70
                    else -> 1.0 // Moneda por defecto (Soles)
                }
                val result = amount * conversionRate
                binding.resultado.setText("%.2f".format(result))
            } else {
                binding.resultado.setText("Error") // En caso de valores inválidos
            }
        }
        //obtenemos el valor de username
        val username = intent.getStringExtra("username")

        //mostramos el msje de bienvenida
        binding.tvWelcome.text = "Bienvenido $username"

        //configuramos el boton logout
        binding.btnLogout.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

}