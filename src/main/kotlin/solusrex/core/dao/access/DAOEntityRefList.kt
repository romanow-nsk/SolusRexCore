package solusrex.core.dao.access

import org.bson.Document
import solusrex.core.common.UniException
import solusrex.core.constants.CoreSingleton
import solusrex.core.constants.values.CoreValues
import solusrex.core.dao.DAO
import solusrex.core.dao.TableItem
import solusrex.core.entity.Entity
import solusrex.core.entity.EntityField
import solusrex.core.entity.EntityLink
import solusrex.core.entity.EntityRefList
import solusrex.core.mongo.DBQueryList
import solusrex.core.mongo.I_MongoDB

class DAOEntityRefList : I_DAOAccess {
    //-----------------------------------------------------------------------------------------------------------------
    override fun getField(ff: EntityField, dao: DAO) {
        ff.value = ""
        }
    override fun getDBValue(ff: EntityField, dao: DAO, prefix: String, out: Document, level: Int, mongo: I_MongoDB) {
        // Делается отдельно в DAO
        val list2 = ff.field.get(this) as EntityRefList<*>
        val cc = list2.typeT
        if (cc==null || level==null)
            return
        val par1 = cc!!.newInstance() as Entity;
        val par2 = (this as Entity).oid;
        val query = DBQueryList().add("valid", true).add(this::class.java.getSimpleName(), par2);
        //System.out.println("LinkRefList: "+cc.getSimpleName()+"."+this.getClass().getSimpleName()+"["+par2+"]");
        val vv = mongo!!.getAllByQuery(par1, query, level - 1);
        list2.set(vv as Nothing);
        }
}