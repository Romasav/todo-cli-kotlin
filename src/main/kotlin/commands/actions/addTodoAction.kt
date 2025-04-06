package commands.actions

import todos.Todos

internal fun addTodoAction(arguments: List<String>) {
    val todoText = arguments.getOrNull(0)?.takeIf { it.isNotBlank() }
        ?: run {
            println("Error: Missing todo text.")
            return
        }
    Todos.addTodo(todoText)
    println("Todo successfully added!")
}