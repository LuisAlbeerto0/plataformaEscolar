package com.example.plataformaescolar

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.plataformaescolar.clases.AdapterReticula
import com.example.plataformaescolar.clases.Calificacion
import com.example.plataformaescolar.databinding.ActivityEleccionBinding
import org.json.JSONObject

class EleccionActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: ActivityEleccionBinding

    private var numSemestre = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEleccionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val miJson = intent.getStringExtra("verano")
        val jsonAlumnoVerano = JSONObject(miJson)
        val arrayAlumnoVerano = jsonAlumnoVerano.getJSONArray("usuarios")
        val arrayMateriaVerano = jsonAlumnoVerano.getJSONArray("Proyectos")
        val m = arrayMateriaVerano.getJSONObject(0)
        var usuario = intent.getStringExtra("usuario")
        val jsonUsuario = JSONObject(usuario)
        binding.spinnerSemestres.onItemSelectedListener = this

        binding.btnRegistar.setOnClickListener {
            val usuario = jsonUsuario.getString("noControl")
            for (i in 0..(arrayAlumnoVerano.length()-1)){
                var control =  arrayAlumnoVerano.getJSONObject(i)
                if (usuario==control.getString("noControl")){
                    Toast.makeText(this, "Ya se encuentra registrado", Toast.LENGTH_SHORT).show()


                }else{

                  val i  = binding.spinnerSemestres.selectedItem
                when(i){
                    "Semestre 1" -> {
                       val jsonAlumnoVeranoEdit = JSONObject()
                        jsonAlumnoVeranoEdit.put("nombre",jsonUsuario.getString("nombre"))
                        jsonAlumnoVeranoEdit.put("noControl",jsonUsuario.getString("noControl"))
                        jsonAlumnoVeranoEdit.put("noReferencia",jsonUsuario.getString("NoReferencia"))
                        jsonAlumnoVeranoEdit.put("proyecto","Energia limpia")
                        val alumno = jsonAlumnoVerano.getJSONArray("usuarios")
                        alumno.put(jsonAlumnoVeranoEdit)
                     var n = m.getInt("energialimpia") +1
                       m.put("energialimpia", n)
                        Toast.makeText(this, "Has registrado el Poyecto", Toast.LENGTH_SHORT).show()
                    }
                    "Semestre 2" -> {
                        val jsonAlumnoVeranoEdit = JSONObject()
                        jsonAlumnoVeranoEdit.put("nombre",jsonUsuario.getString("nombre"))
                        jsonAlumnoVeranoEdit.put("noControl",jsonUsuario.getString("noControl"))
                        jsonAlumnoVeranoEdit.put("noReferencia",jsonUsuario.getString("NoReferencia"))
                        jsonAlumnoVeranoEdit.put("proyecto","Agua para todos")
                        val alumno = jsonAlumnoVerano.getJSONArray("usuarios")
                        alumno.put(jsonAlumnoVeranoEdit)
                        var n = m.getInt("aguaparatodos") +1
                        m.put("aguaparatodos", n)
                        Toast.makeText(this, "Has registrado el Proyecto", Toast.LENGTH_SHORT).show()
                    }
                    "Semestre 3" -> {
                        val jsonAlumnoVeranoEdit = JSONObject()
                        jsonAlumnoVeranoEdit.put("nombre",jsonUsuario.getString("nombre"))
                        jsonAlumnoVeranoEdit.put("noControl",jsonUsuario.getString("noControl"))
                        jsonAlumnoVeranoEdit.put("noReferencia",jsonUsuario.getString("NoReferencia"))
                        jsonAlumnoVeranoEdit.put("proyecto","Tics en el campo")
                        val alumno = jsonAlumnoVerano.getJSONArray("usuarios")
                        alumno.put(jsonAlumnoVeranoEdit)
                        var n = m.getInt("ticsenelcampo") +1
                        m.put("ticsenelcampo", n)
                        Toast.makeText(this, "Has registrado el Proyecto", Toast.LENGTH_SHORT).show()
                    }

                }


                    val intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra("usuario", jsonUsuario.toString())
                    intent.putExtra("verano",jsonAlumnoVerano.toString())
                    startActivity(intent)
                    finish()
                }

            }

        }
    }


    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val json1 = resources.getString(R.string.jsonProyectos)
        val jsonMaterias1 = JSONObject(json1)

        val lista : MutableList<Calificacion> = mutableListOf()
        numSemestre = resources.getStringArray(R.array.numSemestres)[p2]

        when(p2){
            0 -> {
                var arrayMaterias = jsonMaterias1.getJSONArray("p1")
                    val jsonMateria = arrayMaterias.getJSONObject(0)
                    lista.add(Calificacion(jsonMateria.getString("nombre")))
                    lista.add(Calificacion(jsonMateria.getString("desc")))
                    lista.add(Calificacion(jsonMateria.getString("enca")))

            }
            1 -> {
                var arrayMaterias = jsonMaterias1.getJSONArray("p2")
                    val jsonMateria = arrayMaterias.getJSONObject(0)
                    lista.add(Calificacion(jsonMateria.getString("nombre")))
                    lista.add(Calificacion(jsonMateria.getString("desc")))
                    lista.add(Calificacion(jsonMateria.getString("enca")))

            }
            2 -> {
                var arrayMaterias = jsonMaterias1.getJSONArray("p3")
                    val jsonMateria = arrayMaterias.getJSONObject(0)
                    lista.add(Calificacion(jsonMateria.getString("nombre")))
                    lista.add(Calificacion(jsonMateria.getString("desc")))
                    lista.add(Calificacion(jsonMateria.getString("enca")))

            }
        }
        binding.listViewMaterias.adapter = AdapterReticula(this, R.layout.lista_reticula, lista)

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {}
}