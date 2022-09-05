package solusrex.core.entity

import com.google.gson.Gson
import com.thoughtworks.xstream.XStream
import solusrex.core.common.I_Name
import solusrex.core.common.I_XStream
import solusrex.core.dao.DAO
import solusrex.core.export.I_ExcelRW
import solusrex.core.mongo.I_MongoRW

open class Entity : DAO, I_XStream, I_Name {
    var oid: Long = 0
    var isValid = true
    fun toFullString(): String {
        return "[$oid] " }
    override fun toString(): String {
        return "[$oid] "
        }
    fun toShortString(): String {
        return "" }
    val title: String
        get() = toString()
    constructor(id0: Long) {
        oid = id0 }
    constructor() {
        oid = 0 }
    val titleLevel: Int
        get() = 0
    fun toJSON(): String {
        val gs = Gson()
        return gs.toJson(this)
        }
    //-------------------------------------------------------------------------------------------------------------
    override fun setAliases(xs: XStream?) {
        xs!!.alias("Entity", Entity::class.java)
        xs!!.useAttributeFor(Entity::class.java, "oid")
        }
    fun objectName(): String {            // ПОЛИМОРФНЫЙ ВЫЗОВ ДЛЯ ШАБЛОНА
        return name
        }
    override val name: String
        get() = ""
    //------------------------------------------------------------------------------------------------- Для мапов ------
    val keyNum: Long
        get() = -1
}
