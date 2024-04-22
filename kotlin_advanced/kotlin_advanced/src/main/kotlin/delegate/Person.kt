package delegate


fun main() {
    val person = Person("권준호")

}

class Person(
    private val name : String,
) {

    val isKim : Boolean
        get() = this.name.startsWith("김")

    val maskingName : String
        get() = name[0] + (1 until name.length).joinToString("") { "*" }
}

class Person2 {
    private lateinit var name : String

    val isKim : Boolean
        get() = this.name.startsWith("김")

    val maskingName : String
        get() = name[0] + (1 until name.length).joinToString("") { "*" }
}



class Person4 {
    private val name : String by lazy {
        println("name 초기화 중")
        Thread.sleep(2_000L)
        "김수한무"
    }

    val isKim : Boolean
        get() = this.name.startsWith("김")

    val maskingName : String
        get() = name[0] + (1 until name.length).joinToString("") { "*" }
}


class Person5 {
    private val delegateProperty = LazyInitProperty {
        Thread.sleep(2_000L)
        "김수한무"
    }

    val name : String
        get() = delegateProperty.value
}

class LazyInitProperty<T>(val init : () -> T) {
    private var _value : T? = null
    val value: T
        get() {
            if (_value == null) {
                _value = init()
            }
            return _value!!
        }
}