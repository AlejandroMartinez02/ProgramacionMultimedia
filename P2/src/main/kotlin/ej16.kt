fun main(){
    println("Escribe la longitud del array")
    val n = readln().toInt()
    var coleccion = Array(n,{0})
    for(index in 0 until n){
        println("Ingrese un número")
        coleccion.set(index, readln().toInt())
    }

    coleccion = coleccion.sortedArray()
    val numMenor = coleccion[0]
    if(coleccion.contains(numMenor))
        println("El número menor se repite")



}