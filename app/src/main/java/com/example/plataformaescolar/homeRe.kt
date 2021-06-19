package com.example.plataformaescolar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.plataformaescolar.databinding.ActivityHomeBinding
import com.example.plataformaescolar.databinding.ActivityReinscripcionBinding

class homeRe : AppCompatActivity() {
    private lateinit var binding: ActivityReinscripcionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReinscripcionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var stringUsuario = intent.getStringExtra("usuario")
        var stringVerano = intent.getStringExtra("verano")


        binding.btnEleccion.setOnClickListener {
            val intent = Intent(this,EleccionActivity::class.java)
            intent.putExtra("usuario", stringUsuario)
            intent.putExtra("verano",stringVerano)
            startActivity(intent)
        }


        binding.btnResidencia.setOnClickListener {
            val intent = Intent(this,Residencia::class.java)
            intent.putExtra("usuario", stringUsuario)
            println("valor" +stringUsuario)
            startActivity(intent)
        }


    }
}