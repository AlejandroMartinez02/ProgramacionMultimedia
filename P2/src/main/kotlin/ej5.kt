fun main(){
    println("Escribe un texto")
    val clave1 = readln();
    println("Escribe otro texto")
    val clave2 = readln();
    compararStrings(clave1, clave2)
}

fun compararStrings(clave1 : String, clave2 : String ){
    if(clave1.equals(clave2))
        println("Estos textos son iguales")
    else
        println("Estos textos son distintos")
}