import java.lang.System._

object app extends App {
    val properties = getProperties()
    val user = properties.get("user.name")

    val password = Console.readLine("Your password? ")

    if( password == "secret" )
        println("Greetings " + user)
    else {
        err.println("wrong password " + user) 
    }
}
