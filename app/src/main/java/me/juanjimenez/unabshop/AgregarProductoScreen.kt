package me.juanjimenez.unabshop

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgregarProductoScreen(onClickBack: () -> Unit = {}) {

    val context = LocalContext.current

    var nombre by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var precioText by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Agregar Producto", fontSize = 22.sp) },
                navigationIcon = {
                    IconButton(onClick = { onClickBack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = descripcion,
                onValueChange = { descripcion = it },
                label = { Text("Descripción") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = precioText,
                onValueChange = { precioText = it },
                label = { Text("Precio") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                val precio = precioText.toDoubleOrNull() ?: 0.0
                val producto = Producto(
                    nombre = nombre,
                    descripcion = descripcion,
                    precio = precio
                )

                ProductoRepository.agregarProducto(
                    producto,
                    onSuccess = {
                        Toast.makeText(context, "Producto agregado correctamente", Toast.LENGTH_SHORT).show()
                        nombre = ""
                        descripcion = ""
                        precioText = ""
                    },
                    onError = {
                        Toast.makeText(context, "Error al agregar producto", Toast.LENGTH_SHORT).show()
                    }
                )
            }) {
                Text("Guardar Producto")
            }
        }
    }
}

fun onClickBack() {
    TODO("Not yet implemented")
}
