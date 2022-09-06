package solusrex.core.dao.access

import org.bson.Document
import solusrex.core.common.UniException
import solusrex.core.dao.DAO
import solusrex.core.entity.Entity
import solusrex.core.entity.EntityField
import solusrex.core.entity.EntityLink
import solusrex.core.entity.EntityLinkList
import solusrex.core.mongo.I_MongoDB

class DAOEntityLinkList : I_DAOAccess {
    //-----------------------------------------------------------------------------------------------------------------
    override fun getField(ff: EntityField, dao: DAO) {
        val list: EntityLinkList<*> = ff.field[this] as EntityLinkList<*>
        ff.value = list.getIdListString()
        }
    override fun getDBValue(ff: EntityField, dao: DAO, prefix: String, out: Document, level: Int, mongo: I_MongoDB) {
        var link: EntityLink<*> = EntityLink<Entity>()
        try {
            link = ff.field[this] as EntityLink<Entity>
            link.oid = (out[prefix + ff.name] as Long?)!!.toLong()
            val cc: Class<*>? = link.typeT;
            if (cc==null)
                return
            if (!(level != 0 && link.oid != 0L))
                return
            val two: Entity = link.typeT!!.newInstance() as Entity
            if (!mongo.getById(two, link.oid, level - 1, false)) {
                //System.out.println("Не найден " + cname + " id=" + link.getOid());
                link.oid=0
                link.ref=null
                throw UniException.user("Не найден ${dao.javaClass.simpleName}.${ff.name} id=${link.oid}") as Throwable
                }
            else{
                link.ref = two as Nothing
                }
            } catch (ee: Exception) {
                link.oid=0
                throw UniException.bug(ee) as Throwable
                }
        }
}