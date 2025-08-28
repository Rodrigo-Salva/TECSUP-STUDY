package org.springboot.jpa.app.Acuario


// interfaz
interface AccionPez {
    fun comer()
}

abstract class Pez {
    abstract val color: String
}

// Clases hijas
class Tiburon : Pez(), AccionPez {
    override val color: String = "gris"
    override fun comer() {
        println("cazar y comer peces")
    }
}

class PezPayaso : Pez(), AccionPez {
    override val color: String = "dorado"
    override fun comer() {
        println("comer algas")
    }
}

fun crearPeces() {
    println("¿Qué pez quieres crear? (tiburon / pezpayaso)")
    val opcion = readLine()?.lowercase()

    when (opcion) {
        "tiburon" -> {
            val tiburon = Tiburon()
            println("Creaste un Tiburón de color: ${tiburon.color}")
            tiburon.comer()
        }
        "pezpayaso" -> {
            val pezPayaso = PezPayaso()
            println("Creaste un Pez Payaso de color: ${pezPayaso.color}")
            pezPayaso.comer()
        }
        else -> println("Opción no válida")
    }
}

fun main() {
    println("=== Bienvenido al Simulador de Acuarios ===")
    println("1. Construir Acuario")
    println("2. Crear Peces")
    println("3. Salir")

    var opcion: String?
    do {
        print("\nElige una opción: ")
        opcion = readLine()

        when (opcion) {
            "1" -> construirAcuario()
            "2" -> crearPeces()
            "3" -> println("Saliendo del simulador")
            else -> println("Opción inválida, intenta de nuevo")
        }
    } while (opcion != "3")
}
