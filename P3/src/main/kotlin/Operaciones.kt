class Operaciones
{
    fun pedirNumeros(){
        println("Ingresa un número")
        val num1 = readln().toInt()
        println("Ingresa un número")
        val num2 = readln().toInt()
        suma(num1,num2)
        producto(num1,num2)
    }

    fun suma(num1 : Int, num2 : Int){
        println("La suma es ${num1+num2}")
    }

    fun producto(num1 : Int, num2 : Int){
        println("El producto es ${num1*num2}")
    }

}