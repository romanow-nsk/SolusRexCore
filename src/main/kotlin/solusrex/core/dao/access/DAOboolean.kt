package solusrex.core.dao.access

import org.bson.Document
import solusrex.core.common.UniException
import solusrex.core.constants.CoreSingleton
import solusrex.core.dao.DAO
import solusrex.core.dao.TableItem
import solusrex.core.entity.EntityField
import solusrex.core.mongo.I_MongoDB

class DAOboolean : I_DAOAccess {
    //-----------------------------------------------------------------------------------------------------------------
    override fun getField(ff: EntityField, dao: DAO) {
        ff.value = "" + ff.field.getBoolean(dao)
        }
    override fun getDBValue(ff: EntityField, dao: DAO, prefix: String, out: Document, level: Int, mongo: I_MongoDB) {
        try {
            ff.field.setBoolean(this, out.getBoolean(prefix + ff.name))
            } catch (ee: Exception) {
                ff.field.setBoolean(this, false)
                throw UniException.bug(ee) as Throwable
                }
        }
}