fun main(){
    val coleccion1 = Array<Int>(4, {0})
    val coleccion2 = Array<Int>(4, {0})
    val sumaColecciones = Array<Int>(4, {0})
    for(index in 0 until 4)
    {
        println("Ingresa un número para el primer array")
        coleccion1.set(index, readln().toInt())
        println("Ingresa un número para el segundo array")
        coleccion2.set(index, readln().toInt())
        sumaColecciones.set(index, coleccion1[index]+coleccion2[index])
    }

}