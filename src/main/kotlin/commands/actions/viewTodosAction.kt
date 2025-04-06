package commands.actions

import todos.Todos

internal fun viewTodosAction() {
    println("Here are all the todos:")
    val todos = Todos.todos
    if (todos.isEmpty()) {
        println("Your todo list is empty 👀")
        return
    }
    for (todo in todos) {
        println(todo.prettyToString())
    }
}