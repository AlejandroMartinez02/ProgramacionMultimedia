fun main(){
    println("Ingresa una palabra")
    val palabra1 = readln()
    println("Ingresa otra palabra")
    val palabra2 = readln()
    val long1 : Int = contarCaracteres(palabra1)
    val long2 : Int = contarCaracteres(palabra2)
    if(long1 > long2)
        println("$palabra1 tiene más caracteres")
    else if(long2 > long1)
        println("$palabra2 tiene mas caracteres")
    else
        println("$palabra1 y $palabra2 tienen los mismos caracteres")
}

fun contarCaracteres(palabra : String) : Int
{
    val carac = palabra.toCharArray()
    return carac.size
}