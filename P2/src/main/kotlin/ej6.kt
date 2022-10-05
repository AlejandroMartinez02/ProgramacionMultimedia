fun main(){
    println("Ingresa un número")
    val numero1 = readln().toInt()
    println("Ingresa un número")
    val numero2 = readln().toInt()
    println("Ingresa un número")
    val numero3 = readln().toInt()
    menorMayor(numero1, numero2, numero3)
}

fun menorMayor(num1 : Int, num2 : Int, num3 : Int){
    val paraOrdenar = listOf<Int>(num1,num2,num3).sorted()
    for(i in 0 until 3)
        println(paraOrdenar[i])
}