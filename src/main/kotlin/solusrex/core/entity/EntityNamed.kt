package solusrex.core.entity

class EntityNamed : Entity() {
    override fun title() = ""
    override fun toString(): String {
        return "$oid ${title()}"
    }
}