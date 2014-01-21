class MapDuplicator {
    import java.util.{HashMap => JavaHashMap}

    def javaMapToScalaMap(javaMap: JavaHashMap[_, _]) = { 
        import scala.collection.mutable.{HashMap => ScalaHashMap}
        import scala.collection.JavaConversions._

        ScalaHashMap() ++= mapAsScalaMap(javaMap)
    }
}

object CopyMapExercise extends App {
    import java.util.{HashMap => JavaHashMap}

    val javaMap = new JavaHashMap[String, String]()
    javaMap.put( "one", "two" )
    javaMap.put( "three", "four" )

    Console.println("Original Java map:    " + javaMap)

    val duplicator = new MapDuplicator
    val scalaMap = duplicator.javaMapToScalaMap(javaMap)

    Console.println("Scala copy:           " + scalaMap)

    javaMap.put( "five", "six" )
    Console.println("Changed Java map:     " + javaMap)

    Console.println("Unchanged Scala copy: " + scalaMap)
}
