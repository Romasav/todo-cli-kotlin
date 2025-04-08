package commands.actions

import todos.Todos

internal fun addTodoAction(todoText: String) {
    Todos.addTodo(todoText)
    println("Todo successfully added!")
}