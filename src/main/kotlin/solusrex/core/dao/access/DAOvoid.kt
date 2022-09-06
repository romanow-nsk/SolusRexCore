package solusrex.core.dao.access

import org.bson.Document
import solusrex.core.common.UniException
import solusrex.core.constants.CoreSingleton
import solusrex.core.dao.DAO
import solusrex.core.dao.TableItem
import solusrex.core.entity.EntityField
import solusrex.core.mongo.I_MongoDB

class DAOvoid : I_DAOAccess {
    //-----------------------------------------------------------------------------------------------------------------
    override fun getField(ff: EntityField, dao: DAO) {
        }
    override fun getDBValue(ff: EntityField, dao: DAO, prefix: String, out: Document, level: Int, mongo: I_MongoDB) {
        }
}