# 1. Write an example program to demonstrate that package com.horstmann.impatient is not the same as package com; package horstmann; package impatient

    See src/Chapter7-Question1.scala

# 2. Write a puzzler that baffles your Scala friends, using a package com that isn’t at the top level.

    See src/Chapter7-Question2b.scala

    compile with:  scalac Chapter7-Question2a.scala Chapter7-Question2b.scala 
    then run with: scala com.rapidascent.ch7.WTF

# 3. Write a package random with functions nextInt(): Int, nextDouble(): Double, and setSeed(seed: Int): Unit. To generate random numbers, use the linear congruential generator next = previous × a + b mod 2n, where a = 1664525, b = 1013904223, and n = 32.

# 4. Why do you think the Scala language designers provided the package object syntax instead of simply letting you add functions and variables to a package?

# 5. What is the meaning of private[com] def giveRaise(rate: Double)? Is it useful?

# 6. Write a program that copies all elements from a Java hash map into a Scala hash map. Use imports to rename both classes.

# 7. In the preceding exercise, move all imports into the innermost scope possible.

# 8. What is the effect of import java._ import javax._ Is this a good idea?

# 9. Write a program that imports the java.lang.System class, reads the user name from the user.name system property, reads a password from the Console object, and prints a message to the standard error stream if the password is not "secret". Otherwise, print a greeting to the standard output stream. Do not use any other imports, and do not use any qualified names (with dots).

# 10. Apart from StringBuilder, what other members of java.lang does the scala package override?
