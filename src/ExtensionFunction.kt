fun String.hello(): String = "Hello $this"

fun String.printHelloo(): Unit = println("Hello $this")

fun main() {
    val name = "Diki"

    println(name.hello())

    name.printHelloo()

    "Faturrohman".printHelloo()
}