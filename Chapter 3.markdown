# 1. Write a code snippet that sets a to an array of n random integers between 0 (inclusive) and n (exclusive). 

    // first pass:

    def createArray(n: Integer)  = {
        val a = new scala.collection.mutable.ArrayBuffer[Int](n)
        for( i <- 0 until n ) {
            a += Random.nextInt(n)
        }
        a.toArray
    }

    // second pass:

    def createArray(n: Integer) = { Array.fill[Int](n){Random.nextInt(n)} }

# 2. Write a loop that swaps adjacent elements of an array of integers. For example, Array(1, 2, 3, 4, 5) becomes Array(2, 1, 4, 3, 5). 

    // first pass:

    val b = createArray(5) // from above
    val c = new scala.collection.mutable.ArrayBuffer[Int]; 
    for( i <- b.grouped(2) ) { c ++= i.reverse }

    // second pass:

    b.grouped(2).map( _.reverse ).toArray.flatten

    // OOPS. re-read the requirements. Must swap in place!

    val a = Array(1,2,3,4,5) 
    def pairSwap(a: Array[Int]) = {
        for( i <- 0 until a.size by 2 ) {
            if( i < a.size - 1 ) {
                val t = a(i)
                a(i) = a(i+1)
                a(i+1) = t
            }
        }
    }
    pairSwap(a)
    a

# 3. Repeat the preceding assignment, but produce a new array with the swapped values. Use for/yield. 

    val a = Array(1,2,3,4,5) 
    def pairSwap(a: Array[Int]) = {
        for( i <- 0 until a.size ) yield if( i == a.size - 1 ) a(i) else if( i % 2 == 0 ) a(i+1) else a(i-1)
    }
    pairSwap(a)

# 4. Given an array of integers, produce a new array that contains all positive values of the original array, in their original order, followed by all values that are zero or negative, in their original order. 

    // first try:

    val a = Array( 1, -4, 3, 5, -2, 6, 0, -33)
    def reorder(a: Array[Int]) = {
        val positive = for( element <- a if element > 0 ) yield element
        val negative = for( element <- a if element <= 0 ) yield element
        positive ++ negative
    }
    reorder(a)

# 5. How do you compute the average of an Array[Double]? 

    scala> Array[Double](1, 2, 3).sum/3
    res39: Double = 2.0

    scala> val a = Array[Double](1, 2, 3)
    a: Array[Double] = Array(1.0, 2.0, 3.0)

    scala> val average = a.sum/a.size
    average: Double = 2.0

# 6. How do you rearrange the elements of an Array[Int] so that they appear in reverse sorted order? How do you do the same with an ArrayBuffer[Int]?

    // Sort in place:
    val a = Array( 0, 4, 5, 2 )
    scala.util.Sorting.quickSort[Int](a)(Ordering[Int].reverse)
    a
    res48: Array[Int] = Array(5, 4, 2, 0)

    // You cannot sort an ArrayBuffer in place (as implied by question) but this will get you close:
    val a = scala.collection.mutable.ArrayBuffer( 0, 4, 5, 2 )
    a.sorted(Ordering[Int].reverse) // or a.sortWith(_>_)
    res46: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(5, 4, 2, 0)

# 7. Write a code snippet that produces all values from an array with duplicates removed. (Hint: Look at Scaladoc.) 

    scala> val a = Array( 1, -4, 3, 5, -2, 6, 0, -33, -33, 6)
    a: Array[Int] = Array(1, -4, 3, 5, -2, 6, 0, -33, -33, 6)

    scala> a.distinct
    res43: Array[Int] = Array(1, -4, 3, 5, -2, 6, 0, -33)

# 8. Rewrite the example at the end of Section 3.4, “Transforming Arrays,” on page 32. Collect indexes of the negative elements, reverse the sequence, drop the last index, and call a.remove(i) for each index. Compare the efficiency of this approach with the two approaches in Section 3.4.

    val a = scala.collection.mutable.ArrayBuffer(1, 2, -3, 4, -6, -8, 3, 10)
    val first = false
    val indices = for ( i <- 0 until a.length if a(i) < 0 ) yield i
    val trimmed_indices = indices.reverse.init
    for( i <- trimmed_indices ) a.remove(i)

    It's comparable to the first, but more concise, but not as efficient as the second.

# 9. Make a collection of all time zones returned by java.util.TimeZone.getAvailableIDs that are in America. Strip off the "America/" prefix and sort the result.

    java.util.TimeZone.getAvailableIDs.filter{ _.startsWith("America/")}.map{_.stripPrefix("America/")}.sorted 

# 10. Import java.awt.datatransfer._ and make an object of type SystemFlavorMap with the call 'val flavors = SystemFlavorMap.getDefaultFlavorMap().asInstanceOf[SystemFlavorMap]' Then call the getNativesForFlavor method with parameter DataFlavor.imageFlavor and get the return value as a Scala buffer. (Why this obscure class? It's hard to find uses of java.util.List in the standard Java library.)

    import java.awt.datatransfer._
    val flavors = SystemFlavorMap.getDefaultFlavorMap().asInstanceOf[SystemFlavorMap]

    import scala.collection.JavaConversions.asScalaBuffer 
    import scala.collection.mutable.Buffer 

    val b : Buffer[String] = flavors.getNativesForFlavor(java.awt.datatransfer.DataFlavor.imageFlavor) 
