# 1. In the Scala REPL, type 3. followed by the Tab key. What methods can be applied?

    scala> 3.
    !=             ##             %              &              *              +              -              /             
    <              <<             <=             <init>         ==             >              >=             >>            
    >>>            ^              asInstanceOf   equals         getClass       hashCode       isInstanceOf   toByte        
    toChar         toDouble       toFloat        toInt          toLong         toShort        toString       unary_+       
    unary_-        unary_~        |

# 2. In the Scala REPL, compute the square root of 3, and then square that value. By how much does the result differ from 3? (Hint: The res variables are your friend.)

    scala> Math.sqrt(3)
    res4: Double = 1.7320508075688772

    scala> res4 * res4
    res5: Double = 2.9999999999999996

    scala> 3 - res5
    res6: Double = 4.440892098500626E-16

# 3. Are the res variables val or var?

    val

    scala> res6 = 5
    <console>:10: error: reassignment to val
        res6 = 5

# 4. Scala lets you multiply a string with a number—try out "crazy" * 3 in the REPL.  What does this operation do? Where can you find it in Scaladoc?


    scala> "crazy"*3
    res7: String = crazycrazycrazy
    
    scaladoc: http://www.scala-lang.org/api/current/#scala.collection.immutable.StringOps

# 5. What does 10 max 2 mean? In which class is the max method defined?

    10 max 2 calls the method max with parameter 2 on object 10: 10.max(2)
    See http://www.scala-lang.org/api/current/#scala.Int

# 6. Using BigInt, compute 21024.

    scala> 2.pow(1024)
    res5: scala.math.BigInt = 179769313486231590772930519078902473361797697894230657273430081157732675805500963132708477322407536021120113879871393357658789768814416622492847430639474124377767893424865485276302219601246094119453082952085005768838150682342462881473913110540827237163350510684586298239947245938479716304835356329624224137216

# 7. What do you need to import so that you can get a random prime as probablePrime(100, Random), without any qualifiers before probablePrime and Random?

    scala> import BigInt.probablePrime
    import BigInt.probablePrime

    scala> import scala.util.Random
    import scala.util.Random

    scala> probablePrime(100, Random)
    res2: scala.math.BigInt = 1249803962502049967248183599107

# 8. One way to create random file or directory names is to produce a random BigInt and convert it to base 36, yielding a string such as "qsnvbevtomcj38o06kul". Poke around Scaladoc to find a way of doing this in Scala.

    scala> BigInt(200, Random)
    res11: scala.math.BigInt = 633209540087535737553864400178474548649933031131992856817340
    
    scala> res11.toString(36)
    res12: String = 4lbxaaqqwvqww6yu02tyry7hhap7ucunileegu4

# 9. How do you get the first character of a string in Scala? The last character?

    first: 
        scala> "boo"(0)
        res14: Char = b

    last:
        scala> "boo".last
        res18: Char = o

# 10. What do the take, drop, takeRight, and dropRight string functions do? What advantage or disadvantage do they have over using substring?

    take: gets first n elements; 
    drop: drops the first n elements; adv.: as for take
    takeRight, dropRight: same, but works from the right
    
    advantages: fewer params & takes/drops the whole string if it is longer than n
