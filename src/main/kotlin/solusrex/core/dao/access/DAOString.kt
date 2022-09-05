package solusrex.core.dao.access

import org.bson.Document
import solusrex.core.common.UniException
import solusrex.core.dao.DAO
import solusrex.core.entity.EntityField
import solusrex.core.mongo.I_MongoDB

class DAOString : I_DAOAccess {
    override fun getField(ff: EntityField, dao: DAO) {
        ff.value = "" + ff.field.get(this)
        }
    override fun getDBValue(ff: EntityField, dao: DAO, prefix: String, out: org.bson.Document, level: Int, mongo: I_MongoDB) {
        try {
            ff.field.set(this, out.get(prefix + ff.name))
            } catch (ee: Exception) {
                ff.field.set(this, "")
                throw UniException.bug(ee) as Throwable
                }
            }
}