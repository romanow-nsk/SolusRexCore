package solusrex.core.mongo

import com.mongodb.BasicDBObject

interface I_DBQuery {
    fun query(): BasicDBObject?
    fun where(): String?
    companion object {
        const val ModeAnd = 0
        const val ModeOr = 1
        const val ModeGTE = 0
        const val ModeLT = 1
        const val ModeLTE = 2
        const val ModeEQ = 3
        const val ModeNEQ = 4
        const val ModeGT = 5
        val sqlModeList = arrayOf("AND", "OR")
        val modeList = arrayOf("\$and", "\$or")
        val sqlCmpList = arrayOf(">=", "<", "<", "=", "!=", ">")
        val cmpList = arrayOf("\$gte", "\$lt", "\$lte", "\$eq", "\$ne", "\$gt")
    }
}