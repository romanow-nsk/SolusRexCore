package solusrex.core.entity

open class EntityBack : Entity {
    var parentName : String = ""
    var parentOid: Long = 0
    constructor() {}
    constructor(nm: String, id: Long) {
        parentName = nm
        parentOid = id
        }
    constructor(parent: Entity) {
        parentName = parent.javaClass.getSimpleName()
        parentOid = parent.oid
        }
    fun setParent(parent: Entity) {
        parentName = parent.javaClass.getSimpleName()
        parentOid = parent.oid
        }
    override fun toFullString(): String {
        return super.toFullString() + if (parentName.length != 0) "$parentName[$parentOid] " else ""
        }
    }