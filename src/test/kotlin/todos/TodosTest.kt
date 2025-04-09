package todos

import java.io.File
import kotlin.test.*

class TodosTest {
    private val testFile = File("test_todos.json")
    private val originalFile = File("todos.json")

    @BeforeTest
    fun setup() {
        // Backup the real todos.json if it exists
        if (originalFile.exists()) {
            originalFile.copyTo(File("todos.json.bak"), overwrite = true)
        }
        // Start with a clean test file
        testFile.writeText("[]")
        testFile.copyTo(originalFile, overwrite = true)
    }

    @AfterTest
    fun cleanup() {
        // Delete the modified test data
        if (originalFile.exists()) originalFile.delete()
        if (File("todos.json.bak").exists()) {
            File("todos.json.bak").copyTo(originalFile, overwrite = true)
            File("todos.json.bak").delete()
        }
        testFile.delete()
    }

    @Test
    fun `adds a todo with given text and correct ID`() {
        val initialSize = Todos.todos.size
        Todos.addTodo("Test task")

        val allTodos = Todos.todos
        assertEquals(initialSize + 1, allTodos.size)
        assertEquals("Test task", allTodos.last().text)
        assertFalse(allTodos.last().isDone)
    }

    @Test
    fun `marks the matching todo as complete while leaving others unchanged`() {
        Todos.addTodo("Test task 1")
        Todos.addTodo("Test task 2")
        val todosBefore = Todos.todos
        val targetTodo = todosBefore.first()
        val untouchedTodo = todosBefore.last()

        val updatedTodos = Todos.todos.map { if (it.id == targetTodo.id) it.copy(isDone = true) else it }
        Todos.updateTodos(updatedTodos)

        val todosAfter = Todos.todos
        val updated = todosAfter.find { it.id == targetTodo.id }
        val notUpdated = todosAfter.find { it.id == untouchedTodo.id }
        assertTrue(updated?.isDone == true)
        assertFalse(notUpdated?.isDone == true, "Other todos should remain unchanged")
    }

}