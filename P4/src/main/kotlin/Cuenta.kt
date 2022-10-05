abstract class Cuenta(var nombreTitular : String, var numeroCuenta : Int, var dineroDisponible : Int ) {
    fun verDineroDisponible() = println("Tiene $dineroDisponible€ en la cuenta")


    fun imprimir() = println("El dueño de la cuenta es $nombreTitular y tiene $dineroDisponible€")




}

class CajaAhorro(nombre : String, numCuenta : Int, dinero : Int, var gananciasPasivas : Int) : Cuenta(nombre, numCuenta, dinero){
    fun imprimirGananciasPasivas(){
        println("Llevas $gananciasPasivas€ de ganancias pasivas, ¡sigue así!")
    }
}

class PlazoFijo(nombre : String, numCuenta : Int, dinero : Int, var plazoDias : Int, var intereses : Int, var dineroDebido : Int) : Cuenta(nombre, numCuenta, dinero){
    fun imprimirIntereses(){
        println("Tienes unos intereses del $intereses%")
    }

    fun deudas(){
        println("Usted debe $dineroDebido€ que debe pagar en $plazoDias días. Tiene $dineroDisponible€ disponibles")
    }
}

fun main(){
    val cajaAhorro = CajaAhorro("Alejandro", 345345345, 40000, 2)
    val plazoFijo = PlazoFijo("Mario", 2123123, 20000, 40, 5, 1500)
}