class Empleado(name : String, sueldo : Int){
    val nombre = "Nombre: $name"
    val sueldo =  sueldo

}

fun main(){
    val empleados = mutableListOf<Empleado>(Empleado("Alex", 1200), Empleado("Juanma", 1300), Empleado("Jeremias", 1400))
    var sumaSueldos = 0
    for(i in 0 until empleados.size){
        println(empleados[i].nombre + "\n Sueldo:" + empleados[i].sueldo)
        sumaSueldos += empleados[i].sueldo
    }
    println(sumaSueldos)
    sumaSueldos = 0
    empleados.add(Empleado("Cody", 1500))
    for(i in 0 until empleados.size)
        sumaSueldos += empleados[i].sueldo
    println(sumaSueldos)

}