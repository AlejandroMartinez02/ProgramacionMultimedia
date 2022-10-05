fun main() {
    presentacion();
    suma();
    despedida();
}

fun presentacion(){
    println("--------------------------------- \n " +
            "¡Bienvenido al programa! \n" +
            "---------------------------------")
}

fun despedida(){
    println("--------------------------------- \n " +
            "¡Hasta la vista! \n" +
            "---------------------------------")
}

fun suma(){
    println("Introduce un número:")
    val numero1 = readln().toInt()
    println("Introduce otro número:")
    val numero2 = readln().toInt()
    val suma = numero1 + numero2;
    println("La suma de estos número es $suma")
}