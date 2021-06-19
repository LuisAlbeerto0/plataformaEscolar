package com.example.plataformaescolar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.plataformaescolar.databinding.ActivityMainBinding
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val miJson = resources.getString(R.string.jsonUsuarios)
        val jsonUsuarios = JSONObject(miJson)
        val arrayUsuarios = jsonUsuarios.getJSONArray("usuarios")
       val miJson2 = resources.getString(R.string.AlumnosVerano)
        val jsonVerano = JSONObject(miJson2)
        binding.btnLogin.setOnClickListener {
            val noControl = binding.noControlLogin.text.toString()
            val contrasena = binding.contrasenaLogin.text.toString()

            var encontrado = false
            for (i in 0..(arrayUsuarios.length()-1)){
                val jsonUsuario = arrayUsuarios.getJSONObject(i)
                if (jsonUsuario.getString("noControl").trim().equals(noControl.trim())
                        && jsonUsuario.getString("contrasena").trim().equals(contrasena.trim())){
                    encontrado = true
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra("usuario", jsonUsuario.toString())
                    intent.putExtra("verano",jsonVerano.toString())
                    startActivity(intent)
                    finish()
                }
            }
            if(!encontrado){
                showAlert()
            }

        }

        binding.redirigirSignin.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            intent.putExtra("BD", miJson)
            startActivity(intent)
        }
    }


    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Numero de control o contraseña incorrecta")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}