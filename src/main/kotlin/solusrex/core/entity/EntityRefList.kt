package solusrex.core.entity

class EntityRefList<T : Entity?> : ArrayList<T> {
    @Transient
    var typeT: Class<*>? = null
    @Transient
    private var numMap: java.util.HashMap<Long, T>? = null
    @Transient
    private var nameMap: java.util.HashMap<String, T>? = null
    @Transient
    private var titleMap: java.util.HashMap<String, T>? = null
    fun createMap() {
        nameMap = HashMap()
        numMap = HashMap()
        titleMap = HashMap()
        for (ent in this) {
            if (ent == null) continue
            titleMap!![ent.title] = ent as T
            nameMap!![ent.name] = ent as T
            numMap!![ent.keyNum] = ent as T
            }
        sortByKeyNum()
        }
    fun getByNumber(key: Long): T? {
        return if (numMap == null) null else numMap!![key]
        }
    fun getByTitle(key: String): T? {
        return if (titleMap == null) null else titleMap!![key]
        }
    fun getByName(key: String): T? {
        return if (nameMap == null) null else nameMap!![key]
        }
    constructor(src: EntityRefList<T>) {
        clear()
        for (ff in src) {
            add(ff)
            }
        }
    fun set(src: EntityList<T>) {
        clear()
        for (ent in src) add(ent)
        }
    override fun toString(): String {
        var out = ""
        for (i in 0 until size) {
            if (i != 0) out += "\n"
            out += get(i).toString()
            }
        return out
        }
    fun toShortString(): String {
        var out = ""
        for (i in 0 until size) {
            if (i != 0) out += "\n"
            out += get(i)!!.toShortString()
            }
        return out
        }
    fun toFullString(): String {
        var out = ""
        for (i in 0 until size) {
            if (i != 0) out += "\n"
            out += get(i)!!.toFullString()
            }
        return out
        }
    fun toNameString(): String {
        var out = ""
        for (i in 0 until size) {
            val uu: T = get(i) ?: continue
            if (i != 0) out += ", "
            out += uu!!.objectName()
            }
        return out
        }
    fun removeById(id: Long): Boolean {
        for (i in 0 until size) {
            if (get(i)!!.oid === id) {
                removeAt(i)
                return true
                }
            }
        return false
        }
    fun getById(id: Long): T? {
        for (i in 0 until size) {
            if (get(i)!!.oid === id) {
                return get(i)
                }
            }
        return null
        }
    fun removeAllRefs() {
        clear()
        }
    constructor() {}
    constructor(type: Class<*>?) {
        typeT = type
        }
    fun addRef(ent: Entity) {
        add(ent as T)
        }
    val title: String
        get() = if (size == 0) "" else get(0)!!.title + if (size == 1) "" else "[$size] "

    fun sortByTitle() {
        sortWith(Comparator<Entity?> { o1, o2 -> o1!!.title.compareTo(o2!!.title) })
        }

    @JvmOverloads
    fun sortByOid(inc: Boolean = false) {
        sortWith(Comparator<Entity?> { o1, o2 ->
            var vv: Long = o1!!.oid - o2!!.oid
            if (vv == 0L) 0
            if (inc) vv = -vv
            if (vv < 0) -1 else 1
        })
    }

    fun sortByKeyNum() {
        sortWith(Comparator<Entity?> { o1, o2 ->
            val vv: Long = o1!!.keyNum - o2!!.keyNum
            if (vv == 0L) 0
            if (vv < 0) -1 else 1
        })
    }
}
