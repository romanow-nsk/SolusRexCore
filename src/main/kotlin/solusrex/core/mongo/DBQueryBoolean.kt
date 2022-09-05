package solusrex.core.mongo

import com.mongodb.BasicDBObject
import solusrex.core.mongo.I_DBQuery.Companion.ModeEQ
import solusrex.core.mongo.I_DBQuery.Companion.sqlCmpList

class DBQueryBoolean(field: String, value: Boolean) : I_DBQuery {
    val cmpMode: Int
    val field: String
    val value: Boolean
    init {
        cmpMode = ModeEQ
        this.field = field
        this.value = value
        }
    override fun query(): BasicDBObject = BasicDBObject(field, value)
    override fun where(): String = field + sqlCmpList.get(cmpMode) + value
        }
