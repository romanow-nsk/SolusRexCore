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
    open fun toFullString(): String {
        return "[$oid] " }
    override fun toString(): String {
        return "[$oid] "
        }
    open fun toShortString(): String {
        return "" }
    open fun title(): String{
        return toString()
        }
    constructor(id0: Long) {
        oid = id0 }
    constructor() {
        oid = 0 }
    fun titleLevel(): Int{
        return 0
        }
    fun toJSON(): String {
        val gs = Gson()
        return gs.toJson(this)
        }
    //-------------------------------------------------------------------------------------------------------------
    override fun setAliases(xs: XStream) {
        xs!!.alias("Entity", Entity::class.java)
        xs!!.useAttributeFor(Entity::class.java, "oid")
        }
    open fun objectName(): String{            // ПОЛИМОРФНЫЙ ВЫЗОВ ДЛЯ ШАБЛОНА
        return name()
        }
    override fun name() : String{
        return ""
        }
    //------------------------------------------------------------------------------------------------- Для мапов ------
    fun keyNum() : Long {
        return -1L
        }
}
