class Alumno()
{
    var nombre : String
    var nota : Float
    init {
        println("¿Cuál es el nombre del alumno?")
        this.nombre = readln()
        println("¿Cuál es la nota del alumno?")
        this.nota = readln().toFloat()
    }

    fun imprimir(){
        println("Nombre: $nombre \n Nota: $nota")
    }

    fun notaRegular(){
        if(nota >=4)
            println("Está regular")
    }
}