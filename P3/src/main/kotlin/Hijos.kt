class Hijos {
    var edades = arrayOf(5,0)

    fun cargarArray(){
        for(index in 0 until edades.size){
            println("Ingresa un número")
            edades[index] = readln().toInt()
        }
        mayorEdad(edades)
    }
    fun mayorEdad(edades : Array<Int>){
        var mayor = 0
        for(index in edades){
            if(index > mayor)
                mayor = index
        }
        println("La edad más alta es $mayor")
    }

    fun promedioEdades(edades : Array<Int>){
        println("El promedio de edades es de ${edades.sum()/edades.size}")
    }
}