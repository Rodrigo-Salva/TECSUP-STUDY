package org.springboot.jpa.app

//representa a los dispositivos moviles
abstract class SmartDevice(val name: String, val category: String) {

    var deviceStatus = "offline"
        protected set

    open fun turnOn() {
        deviceStatus = "on"
        println("$name está encendido.")
    }

    open fun turnOff() {
        deviceStatus = "off"
        println("$name está apagado.")
    }
}

// Clase hija = tv
class SmartTvDevice(
    name: String,
    category: String,
    var speakerVolume: Int = 2,
    var channelNumber: Int = 1
) : SmartDevice(name, category) {

    override fun turnOn() {
        super.turnOn()
        println("Smart TV $name encendida. Canal actual: $channelNumber, volumen: $speakerVolume.")
    }

    override fun turnOff() {
        super.turnOff()
        println("Smart TV $name apagada.")
    }

    fun increaseVolume() {
        if (speakerVolume < 100) speakerVolume++
        println("Volumen actual: $speakerVolume")
    }

    fun changeChannel(number: Int) {
        channelNumber = number
        println("Cambiando al canal $channelNumber")
    }
}

// Clase hija = lam
class SmartLightDevice(
    name: String,
    category: String,
    var brightnessLevel: Int = 0
) : SmartDevice(name, category) {

    override fun turnOn() {
        super.turnOn()
        brightnessLevel = 2
        println("Lámpara $name encendida con brillo $brightnessLevel.")
    }

    override fun turnOff() {
        super.turnOff()
        brightnessLevel = 0
        println("Lámpara $name apagada.")
    }

    fun increaseBrightness() {
        if (brightnessLevel < 10) brightnessLevel++
        println("Brillo actual: $brightnessLevel")
    }
}

// Clase que representa la "Casa Inteligente"
class SmartHome(
    val smartTv: SmartTvDevice,
    val smartLight: SmartLightDevice
) {
    fun turnOnAllDevices() {
        smartTv.turnOn()
        smartLight.turnOn()
    }

    fun turnOffAllDevices() {
        smartTv.turnOff()
        smartLight.turnOff()
    }

    fun increaseTvVolume() {
        smartTv.increaseVolume()
    }

    fun changeTvChannel(channel: Int) {
        smartTv.changeChannel(channel)
    }

    fun increaseLightBrightness() {
        smartLight.increaseBrightness()
    }
}

fun main() {
    val tv = SmartTvDevice("Android TV", "Entertainment")
    val lamp = SmartLightDevice("Philips Hue", "Lighting")

    val smartHome = SmartHome(tv, lamp)

    println("=== Encendiendo todos los dispositivos ===")
    smartHome.turnOnAllDevices()

    println("\n=== Subiendo volumen de la TV ===")
    smartHome.increaseTvVolume()

    println("\n=== Cambiando canal de la TV ===")
    smartHome.changeTvChannel(5)

    println("\n=== Aumentando brillo de la lámpara ===")
    smartHome.increaseLightBrightness()

    println("\n=== Apagando todos los dispositivos ===")
    smartHome.turnOffAllDevices()
}
