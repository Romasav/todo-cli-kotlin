package commands.actions

import todos.Todos

internal fun markTodoDoneAction(todoId: Int) {
    val updatedTodos = Todos.todos.map { if (it.id == todoId) it.copy(isDone = true) else it }
    Todos.updateTodos(updatedTodos)
    println("The task was successfully marked Done!")
}