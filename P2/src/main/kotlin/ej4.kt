 fun ladoCuadrado(): Int {
    println("¿Cuánto mide el lado del cuadrado?")
     val ladoCuadrado = readln().toInt()
     return ladoCuadrado
}

 fun calcularAreaOSuperficie(){
     val lado = ladoCuadrado()
     println("¿Desea calcular el area o la superficie?(s/n)")
     val decision = readln()
     if(decision.equals("s"))
     {
        println("¿Quiere calcular el area o la superficie? Pulse 1 o 2 respectivamente.")
         val decision2 = readln().toInt();
         if(decision2.equals(1)){
            val area = lado * lado
             println("El area es de: $area")

         }else if(decision2.equals(2)){
             val superficie = lado * 4
             println("La superficie es $superficie")
         }else{
             println("No es una respuesta correcta.")
         }
     }else if(decision.equals("n")){
        println("Entiendo. Se cerrará el programa.")

     }else{
         println("La respuesta no es correcta.")
     }
 }

 fun main(){
     calcularAreaOSuperficie()
 }