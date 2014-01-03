# 1. Set up a map of prices for a number of gizmos that you covet. Then produce a second map with the same keys and the prices at a 10 percent discount.

    val gizmos = Map[Any, Double]("iphone" -> 550, "mac" -> 2500.00, "ipad" -> 800)
    for((k,v) <- gizmos) yield (k, v * .9)

# 2. Write a program that reads words from a file. Use a mutable map to count how often each word appears. To read the words, simply use a java.util.Scanner: "val in = new java.util.Scanner(new java.io.File("myfile.txt")); while (in.hasNext()) process in.next()" or look at Chapter 9 for a Scalaesque way.  At the end, print out all words and their counts.

    def read(file: String) = {
         val words = scala.collection.mutable.Map[Any, Int]()
         val in = new java.util.Scanner(new java.io.File(file))
         while (in.hasNext()) {
              val word = in.next() 
              words(word) = words.getOrElse(word, 0) + 1
         }

         for((k,v) <- words)
              println(k + ": " + v)
    }

# 3. Repeat the preceding exercise with an immutable map.

    def read(file: String) = {
         var words = Map[String, Int]()
         val in = new java.util.Scanner(new java.io.File(file))
         while (in.hasNext()) {
              val word = in.next()
              words = words + (word -> (words.getOrElse(word, 0) + 1))
         }

         for((k,v) <- words)
              println(k + ": " + v)
    }

# 4. Repeat the preceding exercise with a sorted map, so that the words are printed in sorted order.

    def read(file: String) = {
         var words = scala.collection.SortedMap[String, Int]()
         val in = new java.util.Scanner(new java.io.File(file))
         while (in.hasNext()) {
              val word = in.next()
              words = words + (word -> (words.getOrElse(word, 0) + 1))
         }

         for((k,v) <- words)
              println(k + ": " + v)
    }

# 5. Repeat the preceding exercise with a java.util.TreeMap that you adapt to the Scala API.

    import scala.collection.JavaConversions.mapAsScalaMap
    def read(file: String) = {
         var words = new java.util.TreeMap[String, Int]()
         val in = new java.util.Scanner(new java.io.File(file))
         while (in.hasNext()) {
              val word = in.next() 
              words(word) = words.getOrElse(word, 0) + 1
         }

         for((k,v) <- words)
              println(k + ": " + v)
    }

# 6. Define a linked hash map that maps "Monday" to java.util.Calendar.MONDAY, and similarly for the other weekdays. Demonstrate that the elements are visited in insertion order.

    val map = scala.collection.mutable.LinkedHashMap[String,Int]("Monday"->java.util.Calendar.MONDAY, "Tuesday"->java.util.Calendar.TUESDAY,
    "Wednesday"->java.util.Calendar.WEDNESDAY, "Thursday"->java.util.Calendar.THURSDAY, "Friday"->java.util.Calendar.FRIDAY,
    "Saturday"->java.util.Calendar.SATURDAY,"Sunday"->java.util.Calendar.SUNDAY) 


    scala> for((k,v)<-map) println(k + " = " + v)
    Monday = 2
    Tuesday = 3
    Wednesday = 4
    Thursday = 5
    Friday = 6
    Saturday = 7
    Sunday = 1

# 7. Print a table of all Java properties: You need to find the length of the longest key before you can print the table.

    import scala.collection.JavaConversions.propertiesAsScalaMap 

    val props: scala.collection.Map[String,String] = System.getProperties() 

    val fieldSize = props.keySet.reduce((longest, candidate) => if( longest.length > candidate.length ) longest else candidate ).length
    // for((k,v) <- props) println(k + " " * (fieldSize - k.length) + " | " + v) 
    val keyFormat = "%-" + fieldSize + "s | "
    for((k,v) <- props) println(keyFormat.format(k) + v) 

# 8. Write a function minmax(values: Array[Int]) that returns a pair containing the smallest and largest values in the array.

    def minmax(values: Array[Int]) = {
         (values.min, values.max)
    }

# 9. Write a function lteqgt(values: Array[Int], v: Int) that returns a triple containing the counts of values less than v, equal to v, and greater than v.

    // first try:

    def  lteqgt(values: Array[Int], v: Int) = {
         (
              ( for( avalue <- values if avalue < v ) yield v ).size,
              ( for( avalue <- values if avalue == v ) yield v ).size,
              ( for( avalue <- values if avalue > v ) yield v ).size
         )
    }

    // second try (1 iteration, but more cumbersome looking)

    def  lteqgt(values: Array[Int], v: Int) = {
         var res = ( 0, 0, 0 )
         for( value <- values ) {
              if( value < v ) res = ( res._1 + 1, res._2, res._3 )
              else if( value == v ) res = ( res._1, res._2 + 1, res._3 )
              else if( value > v ) res = ( res._1, res._2, res._3  + 1 )
         }
         res
    }

    // meh, 3rd try

    def  lteqgt(values: Array[Int], v: Int) = {
         var res = ( 0, 0, 0 )
         for( value <- values ) {
              res = (  if(value < v) res_.1 + 1 else res._1, 
                          if(value == v) res._2 + 1 else res._2, 
                          if(value > v) res._3 + 1 else res._3 )
         }
         res
    }

    // worse, one last try with foldLeft:

    def  lteqgt(values: Array[Int], v: Int) = {
         values.foldLeft( (0,0,0) ) (
          (res, cur) => 
              if (cur < v)            (res._1+1, res._2, res._3)
              else if (cur==v)     (res._1,res._2+1,res._3) 
              else                     (res._1,res._2,res._3+1) 
    }
     
    // and again

    def  lteqgt(values: Array[Int], v: Int) = {
         val res = values.foldLeft( Array(0,0,0) ) (
              (res, cur) => {
                   if(cur<v) res(0) += 1
                   else if(cur==v) res(1) += 1
                   else if(cur>v) res(2) += 1
                   res
              } )
         (res(0), res(1), res(2))
    }
    val a = Array(1,2,3,4,5)
    lteqgt(a, 3)

    // and finally:

    def  lteqgt(values: Array[Int], v: Int) = {
         values.foldLeft( (0,0,0) ) ( (res, cur) => 
              ( res._1 + { if (cur<v) 1 else 0 }, 
                res._2 + { if (cur==v) 1 else 0 }, 
                res._3 + { if (cur>v) 1 else 0 } ) )
    }

# 10. What happens when you zip together two strings, such as "Hello".zip("World")? Come up with a plausible use case.

    scala> "Hello".zip("World")
    res58: scala.collection.immutable.IndexedSeq[(Char, Char)] = Vector((H,W), (e,o), (l,r), (l,l), (o,d))

    It pairs up the first, second, and so on characters of each string.
