fun main(){
    val edades = mutableListOf<Int>(60,40,15,23,84,22)
    edades.contains(60)
    edades.removeIf { it < 10 }
}