class Punto(puntoX : Int, puntoY : Int) {
    private var puntoX : Int
    private var puntoY : Int

    init{
        this.puntoX = puntoX
        this.puntoY = puntoY
    }

    fun indicarCuadrante() : String{
        var salida : String = ""
        if(puntoX > 0 && puntoY > 0)
          salida = "Punto en el primer cuadrante"
        else if(puntoX < 0 && puntoY > 0)
            salida = "Punto en el segundo cuadrante"
        else if(puntoX < 0 && puntoY < 0)
            salida = "Punto en el tercer cuadrante"
        else if(puntoX > 0 && puntoY < 0 )
           salida = "Punto en el cuarto cuadrante"
        else
            salida = "Punto encima de un eje"

        return salida
    }
}

fun main(){
    val punto1 = Punto(1,1)
    val punto2 = Punto(-1,2)
    val punto3 = Punto(-1,-1)
    val punto4 = Punto(1, -1)
    val punto5 = Punto(0,1)

    println(punto1.indicarCuadrante())
    println(punto2.indicarCuadrante())
    println(punto3.indicarCuadrante())
    println(punto4.indicarCuadrante())
    println(punto5.indicarCuadrante())


}