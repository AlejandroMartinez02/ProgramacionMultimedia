fun main(args: Array<String>) {
    /*1
    val a = 1
    val b = 2
    var suma = a + b
    println(suma)
    suma = a * b
    println(suma)
*/

    /*2
    val ladoDelCuadrado = 75
    val perimetro = ladoDelCuadrado * 4
    val superficie = ladoDelCuadrado * ladoDelCuadrado

    println("El perímetro es:$perimetro")
    println("La superficies es: $superficie")
    */
    /*3

    val peso1 : Float = readln().toFloat()
    val peso2 : Float = readln().toFloat()
    val peso3 : Float = readln().toFloat()

    println((peso1 + peso2 + peso3) / 3)
  */

    /* 4
    println("Escribe un número")
    val numero1 : Int = readln().toInt()
    println("Escribe otro numero")
    val numero2 : Int = readln().toInt()
    println("La suma de estos numeros es:" + (numero1 + numero2))
    println("El producto de estos numeros es:" + (numero1 * numero2))
    */
    /* 5
    val lado : Int = 5
    println("El perímetro del cuadrado es " + lado*4)*/

    /*6
    println("Ingresa un número")
    val numero1 : Int = readln().toInt()
    println("Ingresa otro número")
    val numero2 : Int = readln().toInt()
    println("Ingresa otro número")
    val numero3 : Int = readln().toInt()
    println("Ingresa otro número")
    val numero4 : Int = readln().toInt()

    println("La suma de los dos primeros es " + (numero1 + numero2))
    println("El producto de los dos siguientes es " + numero3 * numero4)
*/
    /* 7
    println("Ingresa un número")
    val numero1 : Int = readln().toInt()
    println("Ingresa otro número")
    val numero2 : Int = readln().toInt()
    println("Ingresa otro número")
    val numero3 : Int = readln().toInt()
    println("Ingresa otro número")
    val numero4 : Int = readln().toInt()
    val suma = numero1+numero2+numero3+numero4
    val promedio = suma / 4

    println("La suma de los cuatro números es $suma")
    println("El promedio de los cuatro números es $promedio")
*/
    /*8
    println("¿Cuál es tu sueldo?")
    val sueldo : Float  = readln().toFloat()

    if(sueldo > 3000)
    {
        println("Debes pagar impuestos")
    }else{
        println("Perfecto, no debes impuestos")
    }*/
    /*9
    println("Ingresa un número")
    val numero1 : Int = readln().toInt()
    println("Ingresa otro número")
    val numero2 : Int = readln().toInt()

    if(numero1 > numero2)
        println("$numero1 es mayor que $numero2")
    else if(numero2 > numero1)
        println("$numero2 es mayor que $numero1")
    else
        println("Son el mismo número")*/
    /*10

    println("Ingresa un número")
    val numero1 : Int = readln().toInt()
    println("Ingresa otro número")
    val numero2 : Int = readln().toInt()

    if(numero1 < numero2){
        val suma = numero2 + numero1
        val resta = numero2 - numero1
        println("La suma de los números es $suma y la resta es $resta")
    }else {
        val division = numero1 / numero2
        val producto = numero1 * numero2
        println("La división de los numeros es $division y el producto es $producto")
    }
*/
    /*11
    println("Ingresa una nota")
    val nota1 : Int = readln().toInt()
    println("Ingresa otra nota")
    val nota2 : Int = readln().toInt()
    println("Ingresa otra nota")
    val nota3 : Int = readln().toInt()

    val promedio : Float = (nota1+nota2+nota3).toFloat()/3

    if(promedio > 7)
        println("Promocionado")
*/
    /*12
  println("Ingresa un numero entre 1 y 99")
  var x = 0
  var numero : Int = readln().toInt()
  while(x){
    if(numero < 100 && numero > 0)
        x = 1
    else {
      println("Eso no es un numero entre el 1 y el 99, inténtalo de nuevo")
      numero = readln().toInt()
    }
  }
  numero = numero/10

  if(numero)
    println("Tiene una única cifra")
  else
    println("Tiene dos cifras")

*/

    /* 13

    println("Ingresa un numero entre 1 y 99")
    var x = 0
    var numero : Int = readln().toInt()
    while(x){
        if(numero < 100 && numero > 0)
            x = 1
        else {
            println("Eso no es un numero entre el 1 y el 99, inténtalo de nuevo")
            numero = readln().toInt()
        }
    }
    var conteo : Int = 0
    x = 0
    while(x.equals(0)) {
        numero /= 10
        conteo++
        if (numero.equals(0))
            x = 1
    }
    println("El numero tiene $conteo cifras")
*/

   /* 14
   println("¿Cuántas preguntas son?")
    val preguntas :Float = readln().toFloat()
    println("¿Cuántas ha acertado?")
    val correctas : Float = readln().toFloat()

    val porcentaje = (correctas/preguntas) * 100
    println(porcentaje)

    if(porcentaje < 50)
        println("Fuera de nivel")
    else if( porcentaje < 75)
        println("Nivel regular")
    else if(porcentaje < 90)
        println("Nivel medio")
    else
        println("Nivel máximo")*/
/*15
    println("¿Cuál es la coordenada x?")
    val x : Int = readln().toInt()
    println("¿Cuál es la coordenada y?")
    val y = readln().toInt()

    if(x > 0 && y > 0)
        println("Punto en el primer cuadrante")
    else if(x < 0 && y > 0)
        println("Punto en el segundo cuadrante")
    else if(x > 0 && y < 0)
        println("Punto en el tercer cuadrante")
    else
        println("Punto en el cuarto cuadrante")
*/
 /*16
  var lista = mutableListOf<Int>()

   for(i in 0..2)
   {
       println("Escribe un número")
       var num = readln().toInt()
       lista.add(num)
   }
    lista = lista.sorted().toMutableList()
    println("$lista")
    println("El número mayor es ${lista.last()} y el menor es ${lista.get(0)}")
*/
/*17
    for(i in 1..777){
        println(i)
    }
    */

   /* 18
   var suma = 0

    for(i in 0..11)
    {
        println("Escribe un número(Llevamos $i de 12)")
        suma += readln().toInt()
    }

    val promedio = suma/12

    println("La suma de todos esos números es $suma y el promedio es $promedio")
*/
/*19
    println("¿Cuántas piezas tiene el lote?")
    val cantidadPiezas = readln().toInt()
    var cantidadPiezasAptas = 0
    var longitudPiezaActual : Float
    for(i in 1..cantidadPiezas)
    {
        println("¿Qué longitud tiene la siguiente pieza?")
        longitudPiezaActual = readln().toFloat()
        if(longitudPiezaActual >= 1.2 && longitudPiezaActual <= 1.3)
            cantidadPiezasAptas++
    }

    println("El total de piezas aptas es de $cantidadPiezasAptas")
*/
/*20.1

    var lista1 = mutableListOf<Int>()
    var suma1 = 0
    var lista2 = mutableListOf<Int>()
    var suma2 = 0

    for(i in 1..5)
    {
        println("Escribe un número para la lista 1")
        var numero = readln().toInt()
        lista1.add(numero)
        suma1 += numero
    }

    for(i in 1..5)
    {
        println("Escribe un número para la lista 2")
        var numero = readln().toInt()
        lista2.add(numero)
        suma2+=numero
    }

    if(suma1 > suma2)
        println("Lista 1 mayor")
    else if(suma2 > suma1)
        println("Lista2 mayor")
    else
        println("Listas iguales")
*/

/*20.2
    var lista1 = mutableListOf<String>()
    var suma1 = 0
    var lista2 = mutableListOf<String>()
    var suma2 = 0

    for(i in 1..5)
    {
        println("Escribe un valor para la lista 1")
        var valor = readln()
        lista1.add(valor)
        suma1 += valor.length
    }


    for(i in 1..5)
    {
        println("Escribe un valor para la lista 2")
        var valor = readln()
        lista2.add(valor)
        suma2 += valor.length
    }

    if(suma1 > suma2)
        println("Lista 1 mayor")
    else if(suma2 > suma1)
        println("Lista2 mayor")
    else
        println("Listas iguales")*/

   /*22
    println("¿Cuántas personas tiene la empresa?")
    var empleados = readln().toInt()
    var sueldosBajos = 0
    var sueldosAltos = 0
    var sueldoActual : Float
    var sumaTotalSueldos = 0F

   while(empleados != 0){
       println("¿Cuál es el sueldo del siguiente trabajador?")
       sueldoActual = readln().toFloat()

      if(sueldoActual >= 100 && sueldoActual <= 500){
          empleados--
          if(sueldoActual < 300)
              sueldosBajos++
          else
              sueldosAltos++
          sumaTotalSueldos+=sueldoActual
      }else{
          println("No es una cifra correcta, vuelve a intentarlo")
      }
   }

    println("Hay $sueldosBajos empleados con sueldo bajo, mientras que hay $sueldosAltos con salario alto. Tiene un gasto" +
            " total de $sumaTotalSueldos€")*/
}