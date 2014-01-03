# 1. Improve the Counter class in Section 5.1, “Simple Classes and Parameterless MethodsWhy No Multiple Inheritance?,” on page 49 so that it doesn’t turn negative at Int.MaxValue.

    // make the value long
    class Counter {
         private var value: Long = 0 // You must initialize the field
         def increment() { value += 1 } // Methods are public by default 
         def current = value

         def forceCurrent(n: Int) = { value = n } // To help tests
    }

# 2. Write a class BankAccount with methods deposit and withdraw, and a read-only property balance.

    class BankAccount {
         private var currentBalance = 0.0
         def deposit(amount: Double) { currentBalance += amount }
         def withdraw(amount: Double) { currentBalance -= amount }

         def balance = currentBalance
    }

# 3. Write a class Time with read-only properties hours and minutes and a method before(other: Time): Boolean that checks whether this time comes before the other. A Time object should be constructed as new Time(hrs, min), where hrs is in military time format (between 0 and 23).

    class Time( val hrs: Int, val min: Int ) {
         if( hrs < 0 ||  hrs > 23 )
              throw new IllegalArgumentException( "Hours = %d is out of range [0,23]".format(hrs) )
         if( min < 0 ||  min > 59 )
              throw new IllegalArgumentException( "Minutes = %d is out of range [0,59]".format(min) )

         def before(other: Time) = ( hrs*60 + other.hrs ) < ( other.hrs * 60 + other.min )
    }

# 4. Reimplement the Time class from the preceding exercise so that the internal representation is the number of minutes since midnight (between 0 and 24 × 60 – 1). Do not change the public interface. That is, client code should be unaffected by your change.

    // doesn't quite maintain signature as new Time( hours = 6, 0 ) will fail
    class Time( hours_in: Int, minutes_in: Int ) {
         if( hours_in < 0 || hours_in > 23 ) 
              throw new IllegalArgumentException( "Hours = %d is out of range [0,23]".format(hours_in) )
         if( minutes_in < 0 || minutes_in > 59 ) 
              throw new IllegalArgumentException( "Minutes = %d is out of range [0,59]".format(minutes_in) )

         private val time = hours_in * 60 + minutes_in

         def before(other: Time) = time < other.time
         def hours: Int = time / 60
         def minutes = time - hours * 60
    }

    // try again. Keeps constructor signature the same, but adds Time() constructor and requires time to be var vs. val
    class Time {
         def this( hours: Int, minutes: Int ) {
              this()
              if( hours < 0 || hours > 23 ) 
                   throw new IllegalArgumentException( "Hours = %d is out of range [0,23]".format(hours) )
              if( minutes < 0 || minutes > 59 ) 
                   throw new IllegalArgumentException( "Minutes = %d is out of range [0,59]".format(minutes) )
              time = hours * 60 + minutes
         }

         private var time = 0

         def before(other: Time) = time < other.time
         def hours: Int = time / 60
         def minutes = time - hours * 60
    }
 
# 5. Make a class Student with read-write JavaBeans properties name (of type String) and id (of type Long). What methods are generated? (Use javap to check.) Can you call the JavaBeans getters and setters in Scala? Should you?

    import scala.reflect.BeanProperty
    class Student {
         var name0: String = ""

         @BeanProperty
         var id: Long = 0

         @BeanProperty
         def name = name0
    }
    getId          getName        id             id_=        name           name_=        
    setId          setName

    No, you probably shouldn't as implementing a custom getter and setter with the same signature won't be automatically generated

# 6. In the Person class of Section 5.1, “Simple Classes and Parameterless MethodsWhy No Multiple Inheritance?,” on page 49, provide a primary constructor that turns negative ages to 0.

    class Person(private var privateAge: Int = 0) {
         privateAge = if( privateAge < 0 ) 0 else privateAge

         def age = privateAge
         def age_=(newValue: Int) {
              if (newValue > privateAge) privateAge = newValue; // Can’t get younger 
         }
    }

# 7. Write a class Person with a primary constructor that accepts a string containing a first name, a space, and a last name, such as new Person("Fred Smith"). Supply read-only properties firstName and lastName. Should the primary constructor parameter be a var, a val, or a plain parameter? Why?

    class Person(name: String) {
         val firstName = name.split(' ')(0)
         val lastName = name.split(' ')(1)
    }

    Plain parameter because the name value isn't needed

# 8. Make a class Car with read-only properties for manufacturer, model name, and model year, and a read-write property for the license plate. Supply four constructors. All require the manufacturer and model name. Optionally, model year and license plate can also be specified in the constructor. If not, the model year is set to -1 and the license plate to the empty string. Which constructor are you choosing as the primary constructor? Why?

    class Car(val manufacturer: String, val model: String, val year: Int, val plate: String) {
         def this(manufacturer: String, model: String, plate: String) {
              this(manufacturer, model, -1, plate)
         }

         def this(manufacturer: String, model: String, year: Int) {
              this(manufacturer, model, year, "")
         }

         def this(manufacturer: String, model: String) {
              this(manufacturer, model, -1, "")
         }
    }

# 9. Reimplement the class of the preceding exercise in Java, C#, or C++ (your choice). How much shorter is the Scala class?

    public class Car {
         private final String manufacturer;
         // Add getters and setters

         private final String model;
         // Add getters and setters

         private finale int year;
         // Add getters and setters

         private String plate;
         // Add getters and setters

         public Car(String manufacturer, String model, int year, String plate) {
              this.manufacturer = manufacturer;    
              this.model = model;
              this.year = year;
              this.plate = plate;
         }

         public Car(String manufacturer, String model) {
              this(manufacturer, model, -1, "");
         }

         public Car(String manufacturer, String model, int year) {
              this(manufacturer, model, year, "");
         }

         public Car(String manufacturer, String model, String plate) {
              this(manufacturer, model, -1, plate);
         }
    }

# 10. Consider the following class. Rewrite it to use explicit fields and a default primary constructor. Which form do you prefer? Why?

class Employee(val name: String, var salary: Double) { 
    def this() { this("John Q. Public", 0.0) }
}

    class Employee {
         private var privateName = "John Q. Public"
         private var privateSalary = 0.0

         def name = privateName
         def salary = privateSalary

         def this(name: String, salary: Double) = {
              this()
              privateName = name
              privateSalary = salary
         }
    }

    The former because it's less code and you don't have to use var's and getters to suppress the setters
