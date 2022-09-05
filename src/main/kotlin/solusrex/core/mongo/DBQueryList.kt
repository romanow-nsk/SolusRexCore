package solusrex.core.mongo

import com.google.gson.Gson
import com.mongodb.BasicDBObject
import solusrex.core.mongo.I_DBQuery.Companion.modeList
import solusrex.core.mongo.I_DBQuery.Companion.sqlModeList

class DBQueryList : I_DBQuery {
    private val obj: MutableList<I_DBQuery> = ArrayList()
    private var mode: Int = I_DBQuery.ModeAnd
    constructor() {}
    constructor(mode0: Int) {
        mode = mode0
        }
    fun add(cc: I_DBQuery): DBQueryList {
        obj.add(cc)
        return this
        }
    fun add(field: String?, value: Int): DBQueryList {
        add(DBQueryInt(field!!, value))
        return this
        }
    fun add(field: String?, value: String?): DBQueryList {
        add(DBQueryString(field!!, value!!))
        return this
    }
    fun add(field: String?, value: Long): DBQueryList {
        add(DBQueryLong(field!!, value))
        return this
        }
    fun add(field: String?, value: Boolean): DBQueryList {
        add(DBQueryBoolean(field!!, value))
        return this
        }
    fun add(mode: Int, field: String?, value: String?): DBQueryList {
        add(DBQueryString(mode, field!!, value!!))
        return this
        }
    fun add(mode: Int, field: String?, value: Int): DBQueryList {
        add(DBQueryInt(mode, field!!, value))
        return this
        }
    fun add(mode: Int, field: String?, value: Long): DBQueryList {
        add(DBQueryLong(mode, field!!, value))
        return this
        }
    fun add(mode: Int, field: String?, value: Long, defValue: Long): DBQueryList {
        if (value != defValue) add(DBQueryLong(mode, field!!, value))
        return this
        }
    override fun query(): BasicDBObject {
            val query = BasicDBObject()
            val list = ArrayList<BasicDBObject?>()
            for (vv in obj) list.add(vv.query())
            query[modeList.get(mode)] = list
            return query
            }
    override fun where(): String {
            var first = true
            val out = StringBuffer()
            out.append("(")
            for (vv in obj) {
                if (first) first = false else out.append(" " + sqlModeList.get(mode).toString() + " ")
                out.append(vv.where())
                }
            out.append(")")
            return out.toString()
        }
    companion object {
        @JvmStatic
        fun main(aaa: Array<String>) {
            val queryList = DBQueryList().add("a", 12).add("b", "??").add(I_DBQuery.ModeGT, "x", 12)
            val queryList2 = DBQueryList(I_DBQuery.ModeOr).add(queryList).add("valid", false)
            val gson = Gson()
            println(gson.toJson(queryList))
            println(gson.toJson(queryList2)) // Десериализовать не получится
            val xStream = DBXStream()
            val s1: String = xStream.toXML(queryList)
            println(s1)
            val s2: String = xStream.toXML(queryList2)
            println(s2)
            val queryList3 = xStream.fromXML(s2) as DBQueryList
            println(queryList3)
        }
    }

}