package commands.actions

import todos.Todos

internal fun markTodoDoneAction(arguments: List<String>) {
    val todoId = arguments.getOrNull(0)?.toIntOrNull()
        ?: run {
            println("Error: Please provide a valid numeric ID.")
            return
        }
    val updatedTodos = Todos.todos.map { if (it.id == todoId) it.copy(isDone = true) else it }
    Todos.updateTodos(updatedTodos)
    println("The task was successfully marked Done!")
}