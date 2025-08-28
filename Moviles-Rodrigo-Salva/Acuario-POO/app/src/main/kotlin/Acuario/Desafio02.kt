package org.springboot.jpa.app.Acuario

// Clase base abstract
abstract class SmartDevice(val name: String, val category: String) {

    var deviceStatus = "offline"
        protected set

    var deviceTurnOnCount: Int = 0
        protected set

    abstract val deviceType: String

    open fun turnOn() {
        deviceStatus = "on"
        deviceTurnOnCount++
        println("$name está encendido.")
    }

    open fun turnOff() {
        deviceStatus = "off"
        println("$name está apagado.")
    }

    fun printDeviceInfo() {
        println("Device name: $name, category: $category, type: $deviceType")
    }
}

// Clase hija tv
class SmartTvDevice(
    name: String,
    category: String,
    var speakerVolume: Int = 2,
    var channelNumber: Int = 1
) : SmartDevice(name, category) {

    override val deviceType: String = "Smart TV"

    override fun turnOn() {
        super.turnOn()
        println("Smart TV $name encendida. Canal actual: $channelNumber, volumen: $speakerVolume.")
    }

    override fun turnOff() {
        super.turnOff()
        println("Smart TV $name apagada.")
    }

    fun increaseVolume() {
        if (deviceStatus == "on" && speakerVolume < 100) {
            speakerVolume++
            println("Volumen actual: $speakerVolume")
        }
    }

    fun decreaseVolume() {
        if (deviceStatus == "on" && speakerVolume > 0) {
            speakerVolume--
            println("Volumen actual: $speakerVolume")
        }
    }

    fun changeChannel(number: Int) {
        if (deviceStatus == "on") {
            channelNumber = number
            println("Cambiando al canal $channelNumber")
        }
    }

    fun previousChannel() {
        if (deviceStatus == "on" && channelNumber > 1) {
            channelNumber--
            println("Cambiando al canal $channelNumber")
        }
    }
}

// Clase hija: Lamp
class SmartLightDevice(
    name: String,
    category: String,
    var brightnessLevel: Int = 0
) : SmartDevice(name, category) {

    override val deviceType: String = "Smart Light"

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
        if (deviceStatus == "on" && brightnessLevel < 10) {
            brightnessLevel++
            println("Brillo actual: $brightnessLevel")
        }
    }

    fun decreaseBrightness() {
        if (deviceStatus == "on" && brightnessLevel > 0) {
            brightnessLevel--
            println("Brillo actual: $brightnessLevel")
        }
    }
}

// Clase SmartHome
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

    fun increaseTvVolume() = smartTv.increaseVolume()
    fun decreaseTvVolume() = smartTv.decreaseVolume()
    fun changeTvChannel(channel: Int) = smartTv.changeChannel(channel)
    fun changeTvChannelToPrevious() = smartTv.previousChannel()

    fun increaseLightBrightness() = smartLight.increaseBrightness()
    fun decreaseLightBrightness() = smartLight.decreaseBrightness()

    fun printSmartTvInfo() = smartTv.printDeviceInfo()
    fun printSmartLightInfo() = smartLight.printDeviceInfo()
}

// Función principal
fun main() {
    val tv = SmartTvDevice("Android tV", "Entertainment")
    val lamp = SmartLightDevice("Philips Hue", "Lighting")

    val smartHome = SmartHome(tv, lamp)

    println("=== Encendiendo todos los dispositivos ===")
    smartHome.turnOnAllDevices()

    println("\n=== Probando TV ===")
    smartHome.increaseTvVolume()
    smartHome.decreaseTvVolume()
    smartHome.changeTvChannel(5)
    smartHome.changeTvChannelToPrevious()
    smartHome.printSmartTvInfo()

    println("\n=== Probando Lámpara ===")
    smartHome.increaseLightBrightness()
    smartHome.decreaseLightBrightness()
    smartHome.printSmartLightInfo()

    println("\n=== Apagando todos los dispositivos ===")
    smartHome.turnOffAllDevices()

    println("\nveces que la tv se encendió: ${tv.deviceTurnOnCount}")
    println("veces que la lámpara se encendió: ${lamp.deviceTurnOnCount}")
}
