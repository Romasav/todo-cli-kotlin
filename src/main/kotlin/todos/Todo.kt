package todos

import kotlinx.serialization.Serializable

@Serializable
data class Todo(
    val id: Int,
    val text: String,
    val isDone: Boolean,
) {
    fun prettyToString(): String {
        return "$id:    $text    ${if (isDone) "✅" else ""}"
    }
}