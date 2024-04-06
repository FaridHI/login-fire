package com.example.firebasekotlin_login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var btnAddToCart: Button
    private lateinit var btnCart: Button
    private lateinit var adapter: ProductAdapter // Asegúrate de tener tu adaptador de productos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        recyclerView = findViewById(R.id.recyclerView)
        btnAddToCart = findViewById(R.id.btnAddToCart)
        btnCart = findViewById(R.id.btnCart)

        // Configurar el RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ProductAdapter(getSampleProducts()) // Asegúrate de tener una lista de productos
        recyclerView.adapter = adapter

        // Agregar producto al carrito cuando se haga clic en el botón correspondiente
        btnAddToCart.setOnClickListener {
            // Aquí deberías implementar la lógica para agregar productos al carrito
        }

        // Ir al carrito cuando se haga clic en el botón correspondiente
        btnCart.setOnClickListener {
            // Aquí deberías implementar la lógica para ir al carrito
        }
    }

    // Esta es una función de ejemplo para generar productos de muestra
    private fun getSampleProducts(): List<Product> {
        val products = mutableListOf<Product>()
        for (i in 1..10) {
            val product = Product("Producto $i", "Descripción del Producto $i", 10.0 * i) // Esto es solo un ejemplo, asegúrate de tener tu clase Product real
            products.add(product)
        }
        return products
    }

    data class Product(val name: String, val description: String, val price: Double)

    inner class ProductAdapter(private val products: List<Product>) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val nameTextView: TextView = itemView.findViewById(R.id.productNameTextView)
            val descriptionTextView: TextView = itemView.findViewById(R.id.productDescriptionTextView)
            val priceTextView: TextView = itemView.findViewById(R.id.productPriceTextView)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.tem_product, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val product = products[position]
            holder.nameTextView.text = product.name
            holder.descriptionTextView.text = product.description
            holder.priceTextView.text = "$${product.price}"
        }

        override fun getItemCount(): Int {
            return products.size
        }
    }
}
