import commands.Command

/**
 * The start of a program.
 */
fun main() {
    println("Hello, this is todo-cli!")
    println()
    while (true) {
        val rawCommand = readln()
        val regex = Regex("""[^\s"]+|"([^"]*)"""")
        val matches = regex.findAll(rawCommand)
        val commandParts = matches.map { match ->
            match.groups[1]?.value ?: match.value
        }.toList()
        val commandString = commandParts.firstOrNull()
        if (commandString == null) {
            println("Error: Command not found!")
            continue
        }
        val arguments = commandParts.drop(1)
        val command = Command.entries.find { it.commandName == commandString }
        if (command != null) {
            command.execute(arguments)
        } else {
            println("Unknown command")
        }
    }
}