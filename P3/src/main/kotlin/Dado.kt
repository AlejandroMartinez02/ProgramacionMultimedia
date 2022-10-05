class Dado(var valorDado : Int) {

    fun tirar(){
        this.valorDado = (1..6).random()
    }

    fun imprimir(){
        println("El dado tiene un valor de $valorDado")
    }
}

class Juego(var dados: List<Dado> = listOf<Dado>(Dado(1), Dado(1), Dado(1))){

    fun jugar(){

            for(dado in dados){
                dado.tirar()
            }
            if(dados[0].valorDado == dados[1].valorDado && dados[2].valorDado == dados[0].valorDado){
                println("¡GANAS!")
            }else
                println("¡PIERDES!")
        }
}

fun main(){
    val juego = Juego()
    juego.jugar()

}