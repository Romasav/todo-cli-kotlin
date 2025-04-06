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
    /** Adds a todoItem the user provides */
    AddTodo(
        commandName = "addTodo",
        description = "Adds a new todo to the list.",
        action = ::addTodoAction
    ),

    /** Lists all saved todos */
    ViewTodos(
        commandName = "viewTodos",
        description = "Displays all current todos.",
        noArgAction = ::viewTodosAction
    ),

    /** Marks a todoItem as done using its ID */
    MarkTodoDone(
        commandName = "markTodoDone",
        description = "Marks a todo as completed.",
        action = ::markTodoDoneAction
    ),

    /** Deletes a todoItem using its ID or position */
    DeleteTodo(
        commandName = "deleteTodo",
        description = "Removes a todo from the list.",
        action = ::deleteTodoAction
    ),

    /** Closes the CLI app */
    Exit(
        commandName = "exit",
        description = "Exits the application.",
        noArgAction = ::exitAction
    );

    constructor(
        commandName: String,
        description: String,
        noArgAction: () -> Unit,
    ) : this(commandName, description, { _ -> noArgAction() })

    /** Executes the command with the given arguments. */
    fun execute(arguments: List<String>): Unit = action(arguments)
}