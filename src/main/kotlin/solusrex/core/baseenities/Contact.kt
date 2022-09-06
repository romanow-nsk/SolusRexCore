package solusrex.core.baseenities

import solusrex.core.common.I_Name
import solusrex.core.common.I_TypeName
import solusrex.core.dao.DAO

open class Contact : DAO(), I_TypeName, I_Name {
    var value: String? = ""
    fun Contact(vv: String?) {
        value = vv
        }
    fun Contact() {}
    override fun name(): String? {
        return if (!valid()) "???" else value
        }
    override fun typeName(): String? {
        return "???"
        }
    override fun typeId(): Int {
        return -1
        }
    override fun typeClass(): Class<*>? {
        return Any::class.java
        }
    open fun valid(): Boolean {
        return false
        }
    open fun parseAndSet(ss: String?): Boolean {
        return false
        }
    override fun toString(): String {
        return value!!
        }
    fun toStringValue(): String? {
        return value
        }
    @Throws(Exception::class)
    fun parseValue(ss: String?) {
        value = ss
        }
    }