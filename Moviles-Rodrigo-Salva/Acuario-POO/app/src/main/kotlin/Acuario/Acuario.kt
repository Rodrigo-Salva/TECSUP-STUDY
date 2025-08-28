package org.springboot.jpa.app.Acuario

import kotlin.math.PI

open class Acuario(open var largo:Int = 100, open var ancho:Int = 20, open var alto:Int = 40) {

    open val forma = "Rectangulo"

    open var agua: Double = 0.0
    get() = volumen * 0.9

    open var volumen: Int
    get() = ancho * alto * largo / 1000  // 1000 cm^3 = 1 l
    set(valor) {
        alto = (valor * 100)/(ancho * largo)
    }

    constructor(numeroDePeces: Int): this() {
        // 2,000 cm^3 por pez + espacio extra para que no se derrame
        val tanque = numeroDePeces * 2000 * 1.1
        alto = (tanque / (largo * ancho)).toInt()

    }

    init {
        println("Inicializando acuario")
    }

    init {
        // 1 litro = 1000 cm^3
        println("Volumen: ${ancho * largo * alto / 1000} l")
    }


    fun imprimirTamano() {
        println(forma)

        println( "Ancho: $ancho cm " +
            "Largo: $largo cm " +
            "Alto: $alto cm ")
        println("Volumen: $volumen l Agua: $agua l (${agua/volumen*100.0}% lleno)")
    }
}

class TanqueTorre(override var alto: Int, var diametro: Int):
    Acuario(alto = alto, ancho = diametro, largo = diametro) {
    override var volumen: Int
        // área elíptica = π * r1 * r2
        get() = (ancho/2 * largo/2 * alto / 1000 * PI).toInt()
        set(valor) {
            alto = ((valor * 1000 / PI) / (ancho/2 * largo/2)).toInt()
        }

    override var agua = volumen * 0.8
    override val forma = "cilindro"

}


fun construirAcuario() {
    //val miAcuario = Acuario()
    //miAcuario.imprimirTamano()

    // altura y largo por defecto
    val acuario2 = Acuario(ancho = 25)
    acuario2.imprimirTamano()

    // ancho por defecto
    val acuario3 = Acuario(alto = 35, largo = 110)
    acuario3.imprimirTamano()

    //Todo personalizado
    val acuario4 = Acuario(ancho = 25, alto = 35, largo = 110)
    acuario4.imprimirTamano()

    val acuario6 = Acuario(numeroDePeces = 29)
    acuario4.imprimirTamano()
    println("Volumen: ${acuario6.ancho * acuario6.largo * acuario6.alto / 1000} l")

    val acuario7 = Acuario(numeroDePeces =  29)
    acuario7.imprimirTamano()
    acuario7.volumen = 70
    acuario7.imprimirTamano()

    val acuario8 = Acuario(largo = 25, ancho = 25, alto = 40)
    acuario8.imprimirTamano()

    val miAcuario = Acuario(ancho = 25, largo = 25, alto = 40)
    miAcuario.imprimirTamano()
    val miTorre = TanqueTorre(diametro = 25, alto = 40)
    miTorre.imprimirTamano()

}

fun main() {
    construirAcuario()
}