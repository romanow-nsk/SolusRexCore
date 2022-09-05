package solusrex.core.mongo

import com.mongodb.BasicDBObject
import solusrex.core.mongo.I_DBQuery.Companion.ModeEQ
import solusrex.core.mongo.I_DBQuery.Companion.cmpList
import solusrex.core.mongo.I_DBQuery.Companion.sqlCmpList

class DBQueryString : I_DBQuery {
    val cmpMode: Int
    val field: String
    val value: String

    constructor(cmpMode: Int, field: String, value: String) {
        this.cmpMode = cmpMode
        this.field = field
        this.value = value
    }

    constructor(field: String, value: String) {
        cmpMode = ModeEQ
        this.field = field
        this.value = value
        }
    override fun query(): BasicDBObject {
        if (cmpMode == ModeEQ)
            return BasicDBObject(field, value)
        else
            return BasicDBObject(field, BasicDBObject(cmpList.get(cmpMode), value))
        }
    override fun where(): String {
        return field + sqlCmpList.get(cmpMode).toString() + "\'" + value + "\'"
        }
}