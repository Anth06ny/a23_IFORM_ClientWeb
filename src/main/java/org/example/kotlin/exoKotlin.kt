fun main() {

    var v1 = "toto"
    println(v1.uppercase())

    var v2:String? = "toto"
    println(v2?.uppercase())

    var v3:String? = null
    println(v3?.uppercase())

    var v4 =  v3 + v3
    println(v4)

   var res =  boulangerie(1, nbBag = 3)
    println("res=$res")

}

fun boulangerie(nbC : Int = 0, nbS: Int = 0, nbBag : Int = 0)
    = nbC * PRIX_CROISSANT  + nbS * PRIX_SAND + nbBag * PRIX_BAGUETTE


fun min(a : Int, b:Int, c:Int) = if(a < b && a < c) a else if(b < a && b < c ) b else c
fun pair(c : Int) = c %2 == 0
fun myPrint(text :String) = println("#$text#")