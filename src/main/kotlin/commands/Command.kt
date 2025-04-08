package commands

import commands.actions.*

/** Represents a CLI command with a name, description, and an action to execute. */
enum class Command(
    /** The command name (used to invoke it). */
    val commandName: String,
    /** A short description of what the command does. */
    val description: String,
    /** The action to execute when the command is run. */
    private val action: (List<String>) -> Unit,
) {
    /** Help */
    Help(
        commandName = "help",
        description = """
            Displays a help menu.
            Usage: help
            """.trimIndent(),
        action = { _ ->
            for (command in entries) {
                println()
                println(command.commandName)
                println(command.description)
            }
            println()
        },
    ),

    /** Adds a todoItem the user provides */
    AddTodo(
        commandName = "addTodo",
        description = """
            Adds a new todo to the list. 
            Usage: addTodo <text>
            Example: addTodo "Buy groceries"
            """.trimIndent(),
        action = action@{ args ->
            val todoText = args.getOrNull(0)?.takeIf { it.isNotBlank() }
                ?: run {
                    println(
                        """
                        ❌ Error: Missing todo text.
                        Expected: addTodo <text>
                        """.trimIndent()
                    )
                    return@action
                }
            addTodoAction(todoText)
        }
    ),

    /** Lists all saved todos */
    ViewTodos(
        commandName = "viewTodos",
        description = """
            Displays all current todos.
            Usage: viewTodos
            """.trimIndent(),
        action = { _ -> viewTodosAction() }
    ),

    /** Marks a todoItem as done using its ID */
    MarkTodoDone(
        commandName = "markTodoDone",
        description = """
            Marks a todo as completed.
            Usage: markTodoDone <id>
            Example: markTodoDone 1
            """.trimIndent(),
        action = action@{ args ->
            val todoId = args.getOrNull(0)?.toIntOrNull()
                ?: run {
                    println(
                        """
                        ❌ Error: Please provide a valid numeric ID.
                        Expected: markTodoDone <id>
                        """.trimIndent()
                    )
                    return@action
                }
            markTodoDoneAction(todoId)
        }
    ),

    /** Deletes a todoItem using its ID or position */
    DeleteTodo(
        commandName = "deleteTodo",
        description = """
            Removes a todo from the list.
            Usage: deleteTodo <id>
            Example: deleteTodo 2
            """.trimIndent(),
        action = action@{ args ->
            val todoId = args.getOrNull(0)?.toIntOrNull()
                ?: run {
                    println(
                        """
                        ❌ Error: Please provide a valid numeric ID.
                        Expected: deleteTodo <id>
                        """.trimIndent()
                    )
                    return@action
                }
            deleteTodoAction(todoId)
        }
    ),

    /** Closes the CLI app */
    Exit(
        commandName = "exit",
        description = """
            Exits the application.
            Usage: exit
            """.trimIndent(),
        action = { _ -> exitAction() }
    );

    /** Executes the command with the given arguments. */
    fun execute(arguments: List<String>): Unit = action(arguments)
}