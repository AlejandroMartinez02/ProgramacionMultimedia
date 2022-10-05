fun main(){
    println("¿Cuántos elementos tiene el array?")
    val n = readln().toInt()
    val suma = sumaArray( crearArray(n))
    println("La suma es de $suma")
}

fun crearArray(n : Int) : Array<Int>
{
    val arrayVariante = Array<Int>(n){it +1}
    return arrayVariante;
}

fun sumaArray(coleccion : Array<Int>) = coleccion.sum()
