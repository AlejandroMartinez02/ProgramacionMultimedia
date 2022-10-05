fun encontrarMenor(){
    println("Escribe el primer número")
    var numeroUsuario = readln().toInt();
    var menor = numeroUsuario

    for(i in 0..1){
        println("Escribe otro número")
        numeroUsuario = readln().toInt();
        if(numeroUsuario < menor){
            menor = numeroUsuario
        }
        println("El número menor es $menor")
    }

    fun main(){
        for(i in 0..1)
            encontrarMenor()
    }
}