# 1. Write an object Conversions with methods inchesToCentimeters, gallonsToLiters, and milesToKilometers.

    object Conversions {
         def inchesToCentimeters(inches: Double) = inches * 2.54
         def gallonsToLiters(gallons: Double) = gallons * 3.78541
         def milesToKilometers(miles: Double) = miles * 1.60934
    }

# 2. The preceding problem wasn’t very object-oriented. Provide a general super- class UnitConversion and define objects InchesToCentimeters, GallonsToLiters, and MilesToKilometers that extend it.

    // pass 1:

    :paste

    abstract class UnitConversion {
         def convert(value: Double): Double
    }

    object InchesToCentimeters extends UnitConversion {
         def convert(value: Double) = value * 2.54
    }

    object GallonsToLitres extends UnitConversion {
         def convert(value: Double) = value * 3.78541
    }

    object milesToKilometers extends UnitConversion {
         def convert (value: Double) = value * 1.60934
    }
 
# 3. Define an Origin object that extends java.awt.Point. Why is this not actually a good idea? (Have a close look at the methods of the Point class.)

    import java.awt.Point
    object Origin extends Point {} 

    // Point is mutable, thus probably not thread safe, thus not a good choice for a singleton

# 4. Define a Point class with a companion object so that you can construct Point instances as Point(3, 4), without using new.

    :paste

    class Point(x: Int, y: Int)

    object Point {
         def apply(x: Int, y: Int): Point = new Point(x,y)
    }

# 5. Write a Scala application, using the App trait, that prints the command-line arguments in reverse order, separated by spaces. For example, scala Reverse Hello World should print World Hello.

    // in Reverse.scala, compiled with scalac Reverse.scala
    object Reverse extends App {
         args.reverse.mkString(" ")
    }

# 6. Write an enumeration describing the four playing card suits so that the toString method returns ♣, ♦, ♥, or ♠.

    object Suits extends Enumeration {
         val club = Value("♣")
         val diamond = Value("♦")
         val spade = Value(“♠")
         val heart = Value("♥")
    }
     
# 7. Implement a function that checks whether a card suit value from the preceding exercise is red.

    object Suits extends Enumeration {
         val club = Value("♣")
         val diamond = Value("♦")
         val spade = Value("♠")
         val heart = Value("♥")

         def isRed(suit: Suits.Value) = suit == Suits.diamond  || suit == Suits.heart
         def isRedSelf = this.Value == Suits.diamond  || this.Value == Suits.heart 
    } 

# 8. Write an enumeration describing the eight corners of the RGB color cube. As IDs, use the color values (for example, 0xff0000 for Red). 

    /*
    R, G, B
    Black: 0, 0, 0: 000000
    Blue: 0, 0, 1: 0000FF
    Green: 0, 1, 0: 00FF00
    GreenBlue: 0, 1, 1: 00FFFF
    Red: 1, 0, 0: FF0000
    RedBlue: 1, 0, 1: FF00FF
    RedGreen: 1, 1, 0: FFFF00
    White: 1, 1, 1: FFFFFF
    */

    object ColorCubeCorners extends Enumeration {
         val Black = Value(0x000000)
         val Blue = Value(0x0000ff)
         val Green = Value(0x00ff00)
         val GreenBlue = Value(0x00ffff)
         val Red = Value(0xff0000)
         val RedBlue = Value(0xff00ff)
         val RedGreen = Value(0xffff00)
         val White = Value(0xffffff)
    }
