class Persona(var nombre: String, var edad: Int,)
{
    fun imprimir()
    {
       println("Nombre: $nombre \n Edad: $edad")
    }

    fun mayorEdad()
    {
        if(this.edad < 18){
            println("No es mayor de edad")
        }else{
            println("Es mayor de edad")
        }
    }
}

fun main(){
    val alu1 = Persona("Alex", 20)
    val alu2 = Persona("David", 20)
    alu1.imprimir()
    alu2.imprimir()
}