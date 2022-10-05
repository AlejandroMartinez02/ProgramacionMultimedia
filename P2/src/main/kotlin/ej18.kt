fun main(){
    val valores = List<Int>(100,{(1..20).random()})

    var contador1_4 = 0
    var contador5_8 = 0
    var contador10_13 = 0
    println(valores)
    for(index in 0 until 100)
    {
        if(valores.get(index) <= 4)
            contador1_4++
        else if(valores.get(index) <= 8)
            contador5_8++
        else if(valores.get(index) >= 10 && valores.get(index) <= 13)
            contador10_13++
        else if(valores.get(index) == 20)
            println("El número 20 está presente")

    }
    println("$contador1_4, $contador5_8, $contador10_13")
}