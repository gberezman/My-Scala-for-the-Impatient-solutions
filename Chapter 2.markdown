# 1. The signum of a number is 1 if the number is positive, –1 if it is negative, and 0 if it is zero. Write a function that computes this value.

    scala> signum(1)
    res34: Int = 1

    scala> signum(-1)
    res35: Int = -1

    scala> signum(0)
    res36: Int = 0

# 2. What is the value of an empty block expression {}? What is its type?

    value is (), type is Unit

    scala> val v = {}
    v: Unit = ()

# 3. Come up with one situation where the assignment x = y = 1 is valid in Scala.
(Hint: Pick a suitable type for x.)

    scala> var x: Unit = ()
    x: Unit = ()

    scala> var y: Int = 0;
    y: Int = 0

    scala> x=y=1
    x: Unit = ()

# 4. Write a Scala equivalent for the Java loop: for (int i = 10; i >= 0; i--) System.out.println(i);

    scala> for( i <- 10 to 1 by -1 ) { println(i) }
    10
    9
    8
    7
    6
    5
    4
    3
    2
    1
    // also: for( i <- (1 to 10).reverse ) { println(i) }

# 5. Write a procedure countdown(n: Int) that prints the numbers from n to 0.

    scala> def countdown(n: Int) { for( i <- n to 0 by -1 ) { println(i) } }
    countdown: (n: Int)Unit

    scala> countdown(5)
    5
    4
    3
    2
    1
    0

# 6. Write a for loop for computing the product of the Unicode codes of all letters in a string. For example, the product of the characters in "Hello" is 9415087488L.

    var v: Long = 1; for( i <- 0 until "Hello".length ) { v = "Hello".codePointAt(i) * v }
    v: Long = 9415087488

# 7. Solve the preceding exercise without writing a loop. (Hint: Look at the StringOps Scaladoc.)

    scala> var p:Long = 1; "Hello".foreach((c:Char)=>p*=c.toInt)
    p: Long = 9415087488


# 8. Write a function product(s : String) that computes the product, as described in the preceding exercises.

    scala> def signum(num: Int) = { if(num > 0) 1 else if (num < 0) -1 else 0 }
    signum: (num: Int)Int

    scala> def codePointProduct(s: String) = { var p:Long = 1; s.foreach((c:Char)=>p*=c.toInt); p }
    codePointProduct: (s: String)Long

    scala> codePointProduct("Hello")
    res7: Long = 9415087488

# 9. Make the function of the preceding exercise a recursive function.

    scala> def codePointProduct(s: String): Long = { if( s.length == 0 ) 1 else s.last.toInt * codePointProduct(s.init) }
    codePointProduct: (s: String)Long

    scala> codePointProduct("Hello")
    res6: Long = 9415087488

# 10. Write a function that computes xn, where n is an integer. Use the following recursive definition:

    • xn = y2 if n is even and positive, where y = xn / 2.
    • xn = x· xn – 1 if n is odd and positive. • x0 = 1.
    • xn = 1 / x–n if n is negative.
    Don’t use a return statement.

    tests:

    2, 0 = 0
    2, 1 = 2*2^0 = 2
    2, 2 = (2^(2/2) * 2^(2/2)) = 2 * 2 = 4
    2, -1 = 1/2 = .5

    def expon( x:Double, n:Int ):Double =  { if( n == 0 ) 1 else if ( n < 0 ) 1 / expon( x, -n ) else if( n % 2 == 1 ) x * expon(x, n - 1)  else expon( x, n/2 ) * expon(x, n/2) }
    expon: (x: Double, n: Int)Double

    scala> expon(2,0)
    res9: Double = 1.0

    scala> expon(2,1)
    res10: Double = 2.0

    scala> expon(2,-1)
    res11: Double = 0.5

    scala> expon(2,2)
    res12: Double = 4.0

    scala> expon(2,-1)
    res13: Double = 0.5
