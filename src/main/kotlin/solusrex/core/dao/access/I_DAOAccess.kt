package solusrex.core.dao.access

import solusrex.core.common.UniException
import solusrex.core.dao.DAO
import solusrex.core.entity.EntityField
import solusrex.core.mongo.I_MongoDB

interface I_DAOAccess {
    fun getField(ff : EntityField, dao : DAO)
    @Throws(UniException::class)
    fun getDBValue(ff : EntityField, dao : DAO,prefix : String, out : org.bson.Document, level : Int, mongo : I_MongoDB);
    }