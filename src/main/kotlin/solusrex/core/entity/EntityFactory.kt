package solusrex.core.entity

class EntityFactory {
    private val foreMap: HashMap<Class<*>?, String?> = HashMap()
    private val invertMap: HashMap<String?, Class<*>?> = HashMap()
    init {
        init()
        }
    fun init() {}
    fun put(name: String?, clazz: Class<*>?) {
        foreMap[clazz] = name
        invertMap[name] = clazz
        }

    fun getClassForSimpleName(name: String): Class<*>? {
        val ss: Array<Class<*>?> = foreMap.keys.toTypedArray()
        for (oo in ss) {
            val cc = oo as Class<*>
            if (cc.simpleName == name) return cc
            }
            return null
        }
    operator fun get(name: String?): Class<*>? {
        return invertMap[name]
        }

    operator fun get(cls: Class<*>?): String? {
        return foreMap[cls]
        }

    fun getEntityNameBySimpleClass(name: String): String? {
        val zz = getClassForSimpleName(name)
        return foreMap[zz]
        }

    fun classList(): ArrayList<Class<*>> {
        val cc: Array<Class<*>?> = arrayOfNulls(foreMap.size)
        foreMap.keys.addAll(cc)
        val out = ArrayList<Class<*>>()
        for (clazz in cc) {
            if (clazz != null) {
                out.add(clazz)
                }
            }
        out.sortWith(
            Comparator { o1, o2 -> o1.simpleName.compareTo(o2.simpleName) })
        return out
        }

    fun nameList(): ArrayList<String?> {
        val cc = arrayOfNulls<String>(invertMap.size)
        invertMap.keys.addAll(cc)
        var found = false
        do {
            found = false
            for (i in 1 until cc.size) if (cc[i - 1]!!.compareTo(cc[i]!!) > 0) {
                val zz = cc[i - 1]
                cc[i - 1] = cc[i]
                cc[i] = zz
                found = true
                }
            } while (found)
        val out = ArrayList<String?>()
        for (i in cc.indices) out.add(cc[i])
        return out
        }

    fun size(): Int {
        return foreMap.size
        }
}