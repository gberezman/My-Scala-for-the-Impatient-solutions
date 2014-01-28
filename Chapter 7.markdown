# 1. Write an example program to demonstrate that package com.horstmann.impatient is not the same as package com; package horstmann; package impatient

    See src/Chapter7-Question1.scala

# 2. Write a puzzler that baffles your Scala friends, using a package com that isn’t at the top level.

    See src/Chapter7-Question2b.scala

    compile with:  scalac Chapter7-Question2a.scala Chapter7-Question2b.scala 
    then run with: scala com.rapidascent.ch7.WTF

# 3. Write a package random with functions nextInt(): Int, nextDouble(): Double, and setSeed(seed: Int): Unit. To generate random numbers, use the linear congruential generator next = previous × a + b mod 2n, where a = 1664525, b = 1013904223, and n = 32.

    See src/Chapter7-Question3.scala

# 4. Why do you think the Scala language designers provided the package object syntax instead of simply letting you add functions and variables to a package?

    * It forces all of the functions and variables to be declared together. Conceptually this is cleaner and is also avoids a lot of potential issues on collisions and such.
    * It keeps the implementation model for this functionality consistent with the rest of the language.

# 5. What is the meaning of private[com] def giveRaise(rate: Double)? Is it useful?

    It makes the giveRaise function visible to any classes or objects in the com package. It is potentially useful. It allows some behaviour or data of a class or object to be exposed only to other classes and objects that are presumably closely related, as indicated by putting them in the referenced package.

# 6. Write a program that copies all elements from a Java hash map into a Scala hash map. Use imports to rename both classes.

    See src/Chapter7-Question6.scala

# 7. In the preceding exercise, move all imports into the innermost scope possible.

    See src/Chapter7-Question7.scala

# 8. What is the effect of import java._ import javax._ Is this a good idea?

    It imports all members of the java and javax packages into the currect scope.

    This probably isn't a good idea just due to potential collisions. scala is implicitly imported, for example, so now you have java.util and scala.util both accessible via 'util.'

# 9. Write a program that imports the java.lang.System class, reads the user name from the user.name system property, reads a password from the Console object, and prints a message to the standard error stream if the password is not "secret". Otherwise, print a greeting to the standard output stream. Do not use any other imports, and do not use any qualified names (with dots).

    See src/Chapter7-Question9.scala

    I couldn't see how to output to stderr without using a dot. I triedusing the System.setOut() method, but this doesn't seem to override stdout for scala's print alias. OTOH, I can use Console.withOut (or setOut), but this requires an import or a dot!

# 10. Apart from StringBuilder, what other members of java.lang does the scala package override?

    Entries are found in the 'scala' package: Byte, Double, Float, Long, Short. There are also many type aliases.

    This was just from a visual inspection and comparison. Might have missed a couple.
