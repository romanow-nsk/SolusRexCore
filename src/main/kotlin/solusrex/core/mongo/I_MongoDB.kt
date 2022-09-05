package solusrex.core.mongo

import com.mongodb.BasicDBObject
import solusrex.core.common.UniException
import solusrex.core.constants.values.CoreValues
import solusrex.core.entity.Entity
import solusrex.core.entity.EntityList
import solusrex.core.entity.EntityNamed

abstract class I_MongoDB {
    abstract val driverName: String?
    @Throws(UniException::class)
    abstract fun openDB(port: Int): Boolean
    abstract val isOpen: Boolean
    abstract fun closeDB()
    @Throws(UniException::class)
    abstract fun clearDB(): String?
    @Throws(UniException::class)
    abstract fun afterRestoreDB(): String?
    @Throws(UniException::class)
    abstract fun clearTable(table: String?): String?
    @Throws(UniException::class)
    abstract fun createIndex(entity: Entity?, name: String?)
    @Throws(UniException::class)
    abstract fun getCountByQuery(ent: Entity?, query: BasicDBObject?): Int
    @Throws(UniException::class)
    abstract fun getAllByQuery(count: Int, ent: Entity?, query: BasicDBObject?, level: Int): EntityList<Entity?>?
    @Throws(UniException::class)
    abstract fun delete(entity: Entity?, id: Long, mode: Boolean): Boolean
    @Throws(UniException::class)
    abstract fun getByIdAndUpdate(ent: Entity?, id: Long, todo: I_ChangeRecord?): Boolean
    @Throws(UniException::class)
    abstract fun getById(ent: Entity?, id: Long, level: Int, mode: Boolean): Boolean
    @Throws(UniException::class)
    abstract fun dropTable(ent: Entity?)
    @Throws(UniException::class)
    abstract fun add(ent: Entity?, level: Int, ownOid: Boolean): Long
    @Throws(UniException::class)
    abstract fun update(ent: Entity?, level: Int)
    @Throws(UniException::class)
    abstract fun getAllRecords(ent: Entity?, level: Int, ): EntityList<Entity?>?
    @Throws(UniException::class)
    abstract fun getListForPattern(ent: Entity?, pattern: String?): EntityList<EntityNamed?>?
    @Throws(UniException::class)
    abstract fun nextOid(ent: Entity?, fromEntity: Boolean): Long
    @Throws(UniException::class)
    abstract fun lastOid(ent: Entity?): Long
    @Throws(UniException::class)
    abstract fun remove(entity: Entity?, id: Long)

    //----------------------- Альтернативное query ---------------------------------------------------------
    @Throws(UniException::class)
    abstract fun getAllByQuery(
        count: Int,
        ent: Entity?,
        query: I_DBQuery?,
        level: Int,
    ): EntityList<Entity?>?

    @Throws(UniException::class)
    abstract fun getCountByQuery(ent: Entity?, query: I_DBQuery?): Int
    @Throws(UniException::class)
    open fun getAllByQuery(ent: Entity?, query: I_DBQuery?): EntityList<Entity?>? {
        return getAllByQuery(0, ent, query, 0)
        }
    @Throws(UniException::class)
    open fun getAllByQuery(count: Int, ent: Entity?, query: I_DBQuery?): EntityList<Entity?>? {
        return getAllByQuery(count, ent, query, 0)
        }
    @Throws(UniException::class)
    open fun getAllByQuery(ent: Entity?, query: I_DBQuery?, level: Int): EntityList<Entity?>? {
        return getAllByQuery(0, ent, query, level)
        }
    open val isSQLDataBase: Boolean
        get() = false
    @Throws(UniException::class)
    open fun execSQL(sql: String?) {
        throw UniException.noFunc("SQL-запросы не поддерживаются") as Throwable
        }
    //------------------------ Синхронизированное обновление поля ПО ВСЕЙ БД 628
    @Throws(UniException::class)
    abstract fun updateField(src: Entity?, id: Long, fname: String?, prefix: String?): Boolean
    @Throws(UniException::class)
    open fun updateField(src: Entity, fname: String?, prefix: String?): Boolean {
        return updateField(src, src.oid, fname, prefix)
        }
    @Throws(UniException::class)
    open fun updateField(src: Entity, fname: String?): Boolean {
        return updateField(src, src.oid, fname, "")
        }
    //------------------------ Умолчания ----------------------------------
    //public boolean getById(Entity ent, long id, int level, boolean mode,  String pathList) throws UniException{
    //    return getById(ent,id,level,mode,pathList,null);
    //    }
    @Throws(UniException::class)
    open fun remove(entity: Entity) {
        remove(entity, entity.oid)
        }
    @Synchronized
    @Throws(UniException::class)
    open fun nextOid(ent: Entity?): Long {
        return nextOid(ent, false)
        }
    @Throws(UniException::class)
    open fun getAll(ent: Entity?, mode: Int, level: Int): EntityList<Entity?>? {
        return when (mode) {
            CoreValues.GetAllModeTotal -> getAllRecords(ent, level)
            CoreValues.GetAllModeActual -> getAllByQuery(0, ent, DBQueryBoolean("valid", true), level)
            CoreValues.GetAllModeDeleted -> getAllByQuery(0, ent, DBQueryBoolean("valid", false), level)
            else -> throw UniException.bug("MongoDB:Illegal get mode=$mode") as Throwable
        }
    }

    @Throws(UniException::class)
    open fun add(ent: Entity?, level: Int): Long {
        return add(ent, level, false)
        }
    @Throws(UniException::class)
    open fun add(ent: Entity?): Long {
        return add(ent, 0)
        }
    @Throws(UniException::class)
    open fun update(ent: Entity?) {
        update(ent, 0)
        }
    @Throws(UniException::class)
    open fun delete(entity: Entity): Boolean {
        return delete(entity, entity.oid, false)
        }
    @Throws(UniException::class)
    open fun delete(entity: Entity?, id: Long): Boolean {
        return delete(entity, id, false)
        }
    @Throws(UniException::class)
    open fun getAll(ent: Entity?): EntityList<Entity?>? {
        return getAll(ent, CoreValues.GetAllModeActual, 0)
        }
    @Throws(UniException::class)
    open fun getById(ent: Entity?, id: Long): Boolean {
        return getById(ent, id, 0, false)
        }
    @Throws(UniException::class)
    open fun getById(ent: Entity?, id: Long, level: Int): Boolean {
        return getById(ent, id, level, false)
        }
    }