package com.example.firebasekotlin_login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar FirebaseAuth
        auth = FirebaseAuth.getInstance()

        // Asignar las vistas a las variables
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        btnRegister = findViewById(R.id.btnRegister)

        // Establecer el listener para el botón de inicio de sesión
        btnLogin.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            // Iniciar sesión del usuario con correo electrónico y contraseña
            auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Inicio de sesión exitoso, navegar a HomeActivity
                        val intent = Intent(this@MainActivity, HomeActivity::class.java)
                        startActivity(intent)
                        finish() // Cerrar esta actividad para evitar volver al inicio de sesión
                    } else {
                        // Si el inicio de sesión falla, mostrar un mensaje al usuario
                        Toast.makeText(
                            baseContext, "Inicio de sesión fallido.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
        // Establecer el listener para el botón de registro
        btnRegister.setOnClickListener {
            // Redirigir al usuario a la actividad de registro
            val intent = Intent(this@MainActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}

