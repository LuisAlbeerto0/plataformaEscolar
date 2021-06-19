package com.example.plataformaescolar.clases

data class Usuario(
    var nombre: String? = null,
    var noControl: String? = null,
    var carrera: String? = null,
    var semestre: String? = null,
    var contrasena: String? = null
){
    //instancia

    companion object{
        var json : String =
            "{'nombre': 'Luis Alberto," +
                    "'noControl': '117121095'," +
                    "'carrera': 'TICS'," +
                    "'semestre': '9'," +
                    "'contrasena': '123'}"
    }
}
