package com {
    package horstmann {
        object Inner {
            val aVariable = 1
        }

        package impatient {
            object Test {
                println(Inner.aVariable)
            }
        }
    }
}

package com.horstmann.impatient {
    object Test2 {
        println(Inner.aVariable) // won't compile
    }
}

