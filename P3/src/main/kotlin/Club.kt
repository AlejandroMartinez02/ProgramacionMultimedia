class Club(val socio1: Socio = Socio("Alex", 4),
           val socio2 : Socio = Socio("Salva", 1),
           var socio3 : Socio = Socio("Mario", 3) ) {
    fun imprimirMasAntiguo(){
        if(socio1.antiguedad > socio2.antiguedad){
            if(socio1.antiguedad > socio3.antiguedad)
                println("Nombre: ${socio1.nombre} \n Antigüedad: ${socio1.antiguedad}")
            else
                println("Nombre: ${socio3.nombre} \n Antigüedad: ${socio3.antiguedad}")

        }else if(socio2.antiguedad > socio3.antiguedad)
            println("Nombre: ${socio2.nombre} \n Antigüedad: ${socio2.antiguedad}")
        else
            println("Nombre: ${socio3.nombre} \n Antigüedad: ${socio3.antiguedad}")

    }

}

class Socio(var nombre : String, var antiguedad : Int){

}

fun main(){
    var club = Club()
    club.imprimirMasAntiguo()
}