import scala.util.Random

package object random {
    val generator = new LinearCongruentialGenerator

    def nextInt(): Int = { generator.next }
    def nextDouble(): Double = { generator.next }
    def setSeed(seed: Int) { generator.seed = seed }
}

package random {
    class LinearCongruentialGenerator(val n:Int = 32, val a:Int = 1664525, val b:Int = 1013904223 ) {
        private var previous = Random.nextInt

        def seed = previous
        def seed_=(newSeed:Int) { previous = newSeed }

        def next = { 
            previous = previous * a + b % (2 * n)
            previous 
        }
    }
}

