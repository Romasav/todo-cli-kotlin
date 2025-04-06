import commands.Command

/**
 * The start of a program.
 */
fun main() {
    println("Hello, this is todo-cli!")
    println()
    while (true) {
        val rawCommand = readln()
        val commandParts = rawCommand.split(" ")
        val commandString = commandParts[0]
        val arguments = commandParts.drop(1)
        val command = Command.entries.find { it.commandName == commandString }
        if (command != null) {
            command.execute(arguments)
        } else {
            println("Unknown command")
        }
    }
}