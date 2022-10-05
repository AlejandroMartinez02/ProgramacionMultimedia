fun main(){
    var numEnteros : Array<Int> = Array(8, {0})
    var mayor36 = 0;
    var mayor50 = 0

    for(index in 0 until 8){
        println("Ingresa un número:")
        var num = readln().toInt()
        numEnteros.set(index, num)
        if(num > 36){
            mayor36 += num
            if(num >50)
                mayor50++
        }


    }
    println("La suma de los números es ${numEnteros.sum()}")
    println("La suma de los valores mayores a 36 es de $mayor36")
    println("Hay $mayor50 números mayores de 50")

}

