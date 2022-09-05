package solusrex.core.dao.access

import org.bson.Document
import solusrex.core.common.UniException
import solusrex.core.constants.CoreSingleton
import solusrex.core.dao.DAO
import solusrex.core.dao.TableItem
import solusrex.core.entity.EntityField
import solusrex.core.mongo.I_MongoDB

class DAOint : I_DAOAccess {
    @Transient
    private var fld: ArrayList<EntityField>? = null
    //---------------------------------------------------------------------------------------------------------------
    @Throws(UniException::class)
    open fun getFields(): ArrayList<EntityField>? {
        if (fld != null)
            return fld
        val item: TableItem? = CoreSingleton.get().entityFactory.getItemForSimpleName(javaClass.simpleName)
        fld = item!!.fields
        return fld
        }
    //-----------------------------------------------------------------------------------------------------------------
    override fun getField(ff: EntityField, dao: DAO) {
        ff.value = "" + ff.field.getInt(dao)
        }
    override fun getDBValue(ff: EntityField, dao: DAO, prefix: String, out: Document, level: Int, mongo: I_MongoDB) {
        try {
            ff.field.setInt(this, out.getInteger(prefix + ff.name))
            } catch (ee: Exception) {
                ff.field.setInt(this, 0)
                throw UniException.bug(ee) as Throwable
                }
        }
}