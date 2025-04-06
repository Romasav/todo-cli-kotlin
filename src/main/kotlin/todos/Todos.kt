package todos

import kotlinx.serialization.json.Json
import java.io.File

object Todos {

    private const val FILE_PATH = "todos.json"

    val todos: List<Todo>
        get() = getTodosFromFile()

    fun addTodo(todoText: String?) {
        val currentTodos = getTodosFromFile()
        val maxId = currentTodos.maxOfOrNull { it.id } ?: 0
        val newId = maxId + 1
        val newTodo = Todo(
            id = newId,
            text = todoText ?: "todo $newId",
            isDone = false,
        )
        val updatedTodos = currentTodos + newTodo
        writeTodosToFile(updatedTodos)
    }

    private fun getTodosFromFile(): List<Todo> {
        val file = File(FILE_PATH)
        if (!file.exists()) return emptyList()

        val json = file.readText()
        return Json.decodeFromString(json)
    }

    fun updateTodos(todos: List<Todo>): Unit = writeTodosToFile(todos)

    private fun writeTodosToFile(todos: List<Todo>) {
        val json = Json.encodeToString(todos)
        File(FILE_PATH).writeText(json)
    }
}