fun main(){
    val productos = mapOf("Champú" to 8.2F, "Solomillo" to 26F, "Lentejas" to 2.5F, "Leche" to 1.2F, "Jamon York" to 1F)
    imprimirProductos(productos)
    productosMayores20(productos)
}

fun imprimirProductos(listaProd : Map<String, Float>){
    println("IMPRIMIENDO TODOS LOS PRODUCTOS")
    for(product in listaProd){
        println("${product.key}, ${product.value}")
    }
}

fun productosMayores20(listaProd : Map<String, Float>){
    var mayores = 0
    for(product in listaProd){
       if(product.value > 20){
           mayores++
       }
    }
    println("Hay un total de $mayores articulos mayores a 20")
}