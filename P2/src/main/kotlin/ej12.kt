fun promedioAlturas()
{
    val alturas = arrayOf<Float>(1.72F, 1.85F, 1.65F, 1.7F, 1.68F, 1.82F, 1.63F, 1.75F)
    var promedio = alturas.sum()/alturas.size
    var mayorProm = 0
    var menorProm = 0
    for(altura in alturas)
    {
        if(altura < promedio)
            menorProm++
        else
            mayorProm++

    }
    println("Hay $mayorProm personas por encima del promedio y $menorProm por debajo del promedio")
}

fun main(){
    promedioAlturas()
}