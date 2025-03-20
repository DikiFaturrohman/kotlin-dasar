fun main(){

    fun sayHello(name: String = ""): String{
        return if(name == "") {
            "Hello Bro!"
        } else {
            "Hello $name"
        }
    }

    println(sayHello())
    println(sayHello("Diki"))
}