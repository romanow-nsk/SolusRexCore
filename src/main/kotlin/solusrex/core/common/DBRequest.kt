package solusrex.core.common

import com.google.gson.Gson
import solusrex.core.constants.CoreSingleton
import solusrex.core.dao.DAO
import solusrex.core.entity.Entity

class DBRequest : DAO {
    var className = ""
        private set
    private var jsonObject = ""
    constructor(className: String, jsonObject: String) {
        this.className = className
        this.jsonObject = jsonObject
        }
    constructor() {}
    constructor(ent: Entity, gson: Gson) {
        put(ent, gson)
        }
    fun put(ent: Entity, gson: Gson) {
        className = ent::class.java.getSimpleName()
        jsonObject = gson.toJson(ent)
        }
    @Throws(UniException::class)
    operator fun get(gson: Gson): Entity {
        val cc: Class<*> = CoreSingleton.get().entityFactory.getClassForSimpleName(className)
            ?: throw UniException.bug("Illegal class $className")!!
        return try {
            gson.fromJson<Any>(jsonObject, cc) as Entity
            } catch (ee: Exception) {
                throw UniException.bug("Illegal object of$className")!!
                }
    }
}