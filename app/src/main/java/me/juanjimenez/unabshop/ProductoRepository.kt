package me.juanjimenez.unabshop

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

object ProductoRepository {

    private val db = Firebase.firestore
    private const val COLLECTION = "productos"

    // Crear / Agregar producto
    fun agregarProducto(
        producto: Producto,
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit
    ) {
        db.collection(COLLECTION)
            .add(producto)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onError(it) }
    }

    // Listar productos
    fun obtenerProductos(onResult: (List<Producto>) -> Unit) {
        db.collection(COLLECTION)
            .get()
            .addOnSuccessListener { result ->
                val lista = result.map { doc ->
                    doc.toObject(Producto::class.java).copy(id = doc.id)
                }
                onResult(lista)
            }
    }

    // Eliminar producto por id
    fun eliminarProducto(id: String, onSuccess: () -> Unit) {
        db.collection(COLLECTION)
            .document(id)
            .delete()
            .addOnSuccessListener { onSuccess() }
    }
}

private fun Any.collection(collection: String) {}
