package solusrex.core.entity

class EntityLinkList<T : Entity?> : ArrayList<EntityLink<T>?> {
    @Transient
    var typeT: Class<*>? = null
    @Transient
    private var numMap: HashMap<Long, T>? = null
    @Transient
    private var nameMap: HashMap<String, T>? = null
    fun createMap() {
        nameMap = HashMap()
        numMap = HashMap()
        for (pp in this) {
            val ent = pp!!.ref ?: continue
            nameMap!![ent.title()] = ent as T
            numMap!![ent.keyNum()] = ent as T
            }
        }
    fun getByNumber(key: Int): T? {
        return if (numMap == null) null else numMap!!.get(key as Long)
        }
    fun getByTitle(key: String): T? {
        return if (nameMap == null) null else nameMap!![key]
        }
    constructor(src: EntityLinkList<T>) {
        clear()
        for (ff in src) {
            add(ff!!.oid)
            }
        }
    override fun toString(): String {
        var out = ""
        for (i in 0 until size) {
            if (i != 0) out += "\n"
            out += get(i).toString()
            }
        return out
        }
    fun toShortString(): String? {
        var out: String? = ""
        for (i in 0 until size) {
            if (i != 0) out += "\n"
            out += get(i)!!.toShortString()
            }
        return out
        }
    fun toFullString(): String? {
        var out: String? = ""
        for (i in 0 until size) {
            if (i != 0) out += "\n"
            out += get(i)!!.toFullString()
            }
        return out
        }
    fun toNameString(): String {
        var out = ""
        for (i in 0 until size) {
            val uu: T = get(i)!!.ref ?: continue
            if (i != 0) out += ", "
            out += uu!!.objectName()
            }
        return out
        }
    fun removeById(id: Long): Boolean {
        for (i in 0 until size) {
            if (get(i)!!.oid == id) {
                removeAt(i)
                return true
                }
            }
        return false
        }
    fun getById(id: Long): EntityLink<*>? {
        for (i in 0 until size) {
            if (get(i)!!.oid == id) {
                return get(i)
                }
            }
        return null
        }
    fun removeAllRefs() {
        for (vv in this) vv!!.ref = null
        }
    fun forEachLink(ff : I_ExecLink<*>) {
        for (vv in this) {
            if (vv!!.oid != 0L) ff.exec(vv as Nothing)
            }
        }
    // Пропускает id=0, старшими байтами вперед
    fun getIdListBinary(): String{
            var sz = 0
            for (vv in this) if (vv!!.oid != 0L) sz++
            val out = CharArray(sz * 4)
            var k = 0
            for (vv in this) {
                var ll = vv!!.oid
                if (ll == 0L) continue
                for (j in 0..3) {
                    out[k + 3 - j] = Char(ll.toUShort()) // Старршими байтами вперед
                    ll = ll shr 16
                }
                k += 4
               }
            return String(out)
            }
    // Пропускает id=0
    fun getIdListStringOld(): String{
            var count = 0
            var out = ""
            if (size == 0) return out
            for (i in 0 until size) {
                if (get(i)!!.oid == 0L) continue
                if (count != 0) out += ","
                out += get(i)!!.oid
                count++
                }
            return out
            }
    // Пропускает id=0
    fun getIdListString(): String {
            var count = 0
            val out = StringBuffer()
            if (size == 0) return out.toString()
            for (i in 0 until size) {
                if (get(i)!!.oid == 0L) continue
                if (count != 0) out.append(',')
                out.append(get(i)!!.oid)
                count++
                }
            return out.toString()
            }
    constructor() {}
    constructor(type: Class<*>?) {
        typeT = type
        }
    constructor(ss: String) {
        parseIdList(ss)
        }
    fun parseIdListBinary(ss: String) {
        clear()
        val cc = ss.toCharArray()
        var i = 0
        while (i < cc.size) {
            var vv: Long = 0
            var j = 0
            while (j < 4) {
                vv = vv shl 16 or cc[i].code.toLong() and 0x0FFFF // Старшими байтами вперед
                j++
                i++
                }
            add(EntityLink(vv))
            }
        }
    fun parseIdList(ss: String) {
        var ss = ss
        clear()
        if (ss.length == 0) return
        if (ss.startsWith("[")) ss = ss.substring(2)
        if (ss.endsWith("]")) ss = ss.substring(0, ss.length - 1)
        if (ss.length == 0) return
        while (true) {
            val idx = ss.indexOf(",")
            if (idx == -1) {
                ss = ss.trim { it <= ' ' }
                add(EntityLink(0))
                return
            }
            val zz = ss.substring(0, idx).trim { it <= ' ' }
            add(EntityLink(zz.toLong()))
            ss = ss.substring(idx + 1)
        }
    }

    fun parseIdListNew(ss: String) {
        clear()
        if (ss.length == 0) return
        val cc = ss.toCharArray()
        var i = 0
        var k = cc.size - 1 // Последний
        if (cc[i] == '[') i++
        if (cc[k] == ']') k--
        var m = 0
        for (j in i..k) if (cc[j] != ' ') cc[m++] = cc[j]
        if (m == 0) return
        i = 0
        while (i < m) {
            var out: Long = 0
            while (i < m && cc[i] != ',') out = out * 10 + (cc[i++] - '0')
            add(EntityLink(out))
            if (i < m) i++
            }
        }

    constructor(idList: ArrayList<Long>) {
        for (ll in idList) add(EntityLink(ll))
        }
    fun getRefById(id: Long): T? {
        for (xx in this) if (id == xx!!.oid) return xx!!.ref
        return null
        }
    fun addOidRef(ent: Entity) {
        add(EntityLink(ent.oid, ent as T))
        }
    fun add(oid: Long) {
        add(EntityLink(oid))
        }
    val title: String
        get() = if (size == 0) "" else get(0)!!.title() + if (size == 1) "" else "[$size] "
    fun sortByTitle() {
        sortWith(Comparator { o1, o2 -> o1!!.title()!!.compareTo(o2!!.title()!!) })
        }
    fun sortByOid() {
        sortWith(Comparator { o1, o2 ->
            val vv = o1!!.oid - o2!!.oid
            if (vv == 0L) return@Comparator 0
            if (vv < 0) return@Comparator -1 else 1
        })
    }
}
