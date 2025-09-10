package org.dosys.project

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform}!"
    }
}