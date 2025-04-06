package commands.actions

import todos.Todos

internal fun deleteTodoAction(arguments: List<String>) {
    val todoId = arguments.getOrNull(0)?.toIntOrNull()
        ?: run {
            println("Error: Please provide a valid numeric ID.")
            return
        }
    val updatedTodos = Todos.todos.filter { it.id != todoId }
    Todos.updateTodos(updatedTodos)
    println("The task was successfully deleted!")
}