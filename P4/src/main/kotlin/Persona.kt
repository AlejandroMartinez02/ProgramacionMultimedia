open class Persona(nom : String, age : Int) {
     var nombre : String = nom
        get() = field
        private set(name : String){
            field = name
        }
    var edad : Int = age
        get() = field
        private set(age : Int){
            if(age > 0)
                field = age
            else
                field = 10
    }

    fun imprimirDatos(){
        println("¡HOLA! Soy $nombre y tengo $edad años :D")
    }
}


fun main(){
    val persona1 = Persona("Alex", 20)
    persona1.imprimirDatos()
    var edadPersona1 = persona1.edad
    val empleado = Empleado("Salva", 40, 1200)
    empleado.pagarImpuestos()
}


class Empleado(nombre: String, edad : Int, var sueldo : Int) : Persona(nombre, edad) {

    fun pagarImpuestos() {
        if (sueldo < 3000)
            println("No debes pagar impuestos")
        else
            println("Debes pagar impuestos")
    }
}