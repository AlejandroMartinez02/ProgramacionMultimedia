fun tablaMultiplicar(numero :Int, termino : Int = 10){
    for(multiplo in 0..termino){
        println("$numero * $multiplo = ${numero*multiplo}")
    }
}

fun main(){
    println("¿Qué número desea multiplicar?")
    val num = readln().toInt()
    println("¿Cuántas veces desea multiplicarlo?")
    val nVeces = readln().toInt()

    if(nVeces <= 0){
        tablaMultiplicar(num)
    }else
        tablaMultiplicar(num, nVeces)
}