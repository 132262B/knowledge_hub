package generic

fun main() {
    val goldFishCage = Cage2<GoldFish>()
    goldFishCage.put(GoldFish("금붕어"))

    val fishCage = Cage2<Fish>()
    fishCage.moveFrom(goldFishCage)

    val fishCage2 = Cage2<Fish>()
    val goldFishCage2 = Cage2<GoldFish>()
    goldFishCage2.put(GoldFish("금붕어"))
    goldFishCage2.moveTo(fishCage2)

    val fishCage3: Cage3<Fish> = Cage3()
    val animalCage3: Cage3<Animal> = fishCage3

    val cage5 = Cage5(mutableListOf(Eagle(), Sparrow()))
    cage5.printAfterSort()
}

class Cage {
    private val animals: MutableList<Animal> = mutableListOf()

    fun getFirst(): Animal {
        return animals.first()
    }

    fun put(animal: Animal) {
        this.animals.add(animal)
    }

    fun moveFrom(cage: Cage) {
        this.animals.addAll(cage.animals)
    }
}

class Cage2<T> {
    private val animals: MutableList<T> = mutableListOf()

    fun getFirst(): T {
        return animals.first()
    }

    fun put(animal: T) {
        this.animals.add(animal)
    }

    fun moveFrom(cage: Cage2<out T>) {
        this.animals.addAll(cage.animals)
    }

    fun moveTo(cage: Cage2<in T>) {
        cage.animals.addAll(this.animals)
        this.animals.clear()
    }
}

class Cage3<out T> {
    private val animals: MutableList<T> = mutableListOf()

    fun getFirst(): T {
        return this.animals.first()
    }

    fun getAll(): List<T> {
        return this.animals
    }
}

class Cage4<in T> {
    private val animals: MutableList<T> = mutableListOf()

    fun put(animal: T) {
        this.animals.add(animal)
    }

    fun putAll(animal: List<T>) {
        this.animals.addAll(animal)
    }
}

class Cage5<T>(
    private val animals: MutableList<T> = mutableListOf(),
) where T : Animal, T : Comparable<T> {
    fun printAfterSort() {
        this.animals.sorted()
            .map { it.name }
            .let { println(it) }
    }

    fun getFirst(): T {
        return animals.first()
    }

    fun put(animal: T) {
        this.animals.add(animal)
    }

    fun moveFrom(cage: Cage5<T>) {
        this.animals.addAll(cage.animals)
    }

    fun moveTo(cage: Cage5<T>) {
        cage.animals.addAll(this.animals)
        this.animals.clear()
    }
}
