package commands.actions

import todos.Todos

internal fun deleteTodoAction(todoId: Int) {
    val updatedTodos = Todos.todos.filter { it.id != todoId }
    Todos.updateTodos(updatedTodos)
    println("The task was successfully deleted!")
}