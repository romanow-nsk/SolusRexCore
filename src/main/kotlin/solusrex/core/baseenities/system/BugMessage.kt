package solusrex.core.baseenities.system

import solusrex.core.baseenities.OwnDateTime
import solusrex.core.baseenities.user.User
import solusrex.core.entity.Entity
import solusrex.core.entity.EntityLink

class BugMessage : Entity {
    val user: EntityLink<User> = EntityLink(User::class.java)
    var date: OwnDateTime = OwnDateTime()
    var message = ""
        private set

    constructor(userId: Long, date: OwnDateTime, message: String) {
        user.oid = userId
        this.date = date
        this.message = message
        }
    constructor(userId: Long, message: String) {
        user.oid = userId
        this.message = message
        }
    constructor(message: String) {
        this.message = message
        }
    constructor() {}
    override fun title(): String {
        return ""+date +" "+ user.title()
        }
    override fun toString(): String {
        return "${title()} $message".trimIndent()
        }
    //--------------------------------------------------------------------------------------------------
    val userId: EntityLink<User>
        get() = user
    }
