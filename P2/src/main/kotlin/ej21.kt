fun main(){
    val bonoloto = mutableSetOf<Int>(6, (1..6).random())
    var contador = 1
    while(true){
        val cartones = mutableSetOf<Int>(6, (1..6).random())
        if(bonoloto.equals(cartones)){
            println("¡TE HA TOCADO! ¡Solo has necesitado $contador cartones, es decir, ${contador*6} números!")
            break
        }else
            contador++

    }
}