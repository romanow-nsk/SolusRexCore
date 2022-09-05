package solusrex.core.mongo

import solusrex.core.common.UniException

interface I_MongoRW {
    @Throws(UniException::class)
    fun putDBValues(prefix: String, document: org.bson.Document, level: Int, mongo: I_MongoDB)
    @Throws(UniException::class)
    fun getDBValues(prefix: String, res: org.bson.Document, level: Int, mongo: I_MongoDB)
}