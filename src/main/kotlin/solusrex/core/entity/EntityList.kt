package solusrex.core.entity

import com.google.gson.Gson
import solusrex.core.common.DBRequest
import solusrex.core.common.UniException

open class EntityList<T : Entity?> : ArrayList<T> {
    constructor() {}
    fun getById(userId: Long): Entity? {
        for (uu in this) if (uu!!.oid=== userId) return uu
        return null
        }
    fun getIdxById(userId: Long): Int {
        var idx = 0
        for (uu in this) {
            if (uu!!.oid=== userId) return idx
            idx++
            }
        return -1
        }
    constructor(src: EntityList<Entity>) {
        for (ent in src) add(ent as T)
        }
    fun convert(): ArrayList<Entity> {
        val out = ArrayList<Entity>()
        for (uu in this) out.add(uu!!)
        return out
        }
    fun add(two: EntityList<T>) {
        for (ent in two) add(ent)
        }
    override fun toString(): String {
        var out = ""
        for (i in 0 until size) {
            val uu: Entity? = get(i)
            if (i != 0) out += "\n"
            out += uu!!.title()
            }
        return out
        }

    fun toShortString(): String {
        var out = ""
        for (i in 0 until size) {
            val uu: Entity? = get(i)
            if (i != 0) out += "\n"
            out += uu!!.toShortString()
            }
        return out
        }
    fun toFullString(): String {
        var out = ""
        for (i in 0 until size) {
            val uu: Entity? = get(i)
            if (i != 0) out += "\n"
            out += uu!!.toFullString()
            }
        return out
        }
    fun toNameString(): String {
        var out = ""
        for (i in 0 until size) {
            val uu: Entity? = get(i)
            if (i != 0) out += ", "
            out += uu!!.objectName()
            }
        return out
        }

    fun sortByTitle() {
        sort(object : I_Compare {
            override fun compare(one: Entity?, two: Entity?): Int {
                return one!!.title().compareTo(two!!.title())
                }
            })
        }

    fun sortById() {
        sort(object : I_Compare {
            override fun compare(one: Entity?, two: Entity?): Int {
                val res = one!!.oid-two!!.oid
                if (res==0L) return 0
                if (res>0) return 1
                return  -1
                }
            })
        }

    fun sort(cmp: I_Compare) {
        sort(cmp, false)
        }

    fun sort(cmp: I_Compare, dec: Boolean) {
        if (size <= 1) return
        val tt = arrayOfNulls<Entity>(size)
        this.toArray(tt)
        for (i in 1 until tt.size) {
            var j = i
            while (j > 0 && cmp.compare(tt[j], tt[j - 1]) < 0) {
                val cc = tt[j]
                tt[j] = tt[j - 1]
                tt[j - 1] = cc
                j--
                }
            }
        clear()
        for (i in tt.indices)
            if (dec)
                add(tt[tt.size - i - 1] as T)
            else
                add(tt[i] as T)
            }

    @Throws(UniException::class)
    fun load(list: ArrayList<DBRequest>) {
        clear()
        val gson = Gson()
        for (vv in list) {
            val ent: Entity = vv.get(gson)
            add(ent as T)
        }
    }
}