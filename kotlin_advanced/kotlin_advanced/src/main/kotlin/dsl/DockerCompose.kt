package dsl

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun main() {
    val yml = dockerCompose {
        version { 3 }
        service(name = "db") {
            image { "mysql:5.7" }
        }
    }

    println(yml.render(" "))
}


fun dockerCompose(init: DockerCompose.() -> Unit) : DockerCompose {
    val dockerCompose = DockerCompose()
    dockerCompose.init()
    return dockerCompose
}

class DockerCompose {
    private var version: Int by onceNotNull()
    private val services = mutableListOf<Service>()

    fun version(init: () -> Int) {
        version = init()
    }

    fun service(name : String, init: Service.() -> Unit) {
        val service = Service(name)
        service.init()
        services.add(service)
    }

    fun render(indent: String) : String {
        return StringBuilder().apply {
            appendNew("version : '$version'", indent)
            appendNew("services:")
            appendNew(services.joinToString("\n") { it.render(indent) }.addIndent(indent, 1))
        }.toString()
    }

}

fun StringBuilder.appendNew(str: String, indent: String = "", times: Int = 0) {
    (1..times).forEach { _ -> this.append(indent) }
    this.append(str)
    this.append("\n")
}

fun String.addIndent(indent: String, times: Int = 0) : String {
    val allIndent = (1..times)
        .joinToString("") { indent }

    return this.split("\n")
        .joinToString("\n") { "$allIndent$it" }
}


class Service(val name : String) {
    private var image: String by onceNotNull()
    fun image(init: () -> String) {
        image = init()
    }

    fun render(indent: String) : String {
        return StringBuilder().apply {
            appendNew("$name:")
            appendNew("image : '$image'", indent, 1)
        }.toString()
    }
}


fun <T> onceNotNull() = object : ReadWriteProperty<Any?, T>{
    private var value: T? = null

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return value ?: throw IllegalStateException("변수가 초기화되지 않았습니다.")
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        if(this.value != null) {
            throw IllegalStateException("변수가 이미 초기화되었습니다.")
        }
        this.value = value
    }

}
