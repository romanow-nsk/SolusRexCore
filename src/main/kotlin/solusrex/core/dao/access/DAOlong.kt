package solusrex.core.dao.access

import org.bson.Document
import solusrex.core.common.UniException
import solusrex.core.constants.CoreSingleton
import solusrex.core.dao.DAO
import solusrex.core.dao.TableItem
import solusrex.core.entity.EntityField
import solusrex.core.mongo.I_MongoDB

class DAOlong : I_DAOAccess {
    //-----------------------------------------------------------------------------------------------------------------
    override fun getField(ff: EntityField, dao: DAO) {
        ff.value = "" + ff.field.getLong(dao)
        }
    override fun getDBValue(ff: EntityField, dao: DAO, prefix: String, out: Document, level: Int, mongo: I_MongoDB) {
        try {
            ff.field.setLong(this, out.getLong(prefix + ff.name))
            } catch (ee: Exception) {
                ff.field.setLong(this, 0)
                throw UniException.bug(ee) as Throwable
                }
        }
}