package commands.actions

import kotlin.system.exitProcess

internal fun exitAction() {
    println("Goodbye! 👋")
    exitProcess(0)
}