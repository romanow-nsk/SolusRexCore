package solusrex.core.entity

class EntityNamed : Entity() {
    fun title() = ""
    override fun toString(): String {
        return "$oid $title"
    }
}