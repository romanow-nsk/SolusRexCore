package solusrex.core.constants

class ConstGroup(val group: String?) {
    private val map = HashMap<Int, ConstValue>()
    operator fun get(key: Int): ConstValue? {
        return map[key]
        }
    fun put(cc: ConstValue) {
        val key = cc.value
        val vv = get(key)
        if (vv != null) map.remove(key)
        map[key] = cc
        }
    fun createList(): ConstList {
        val out = ConstList()
        val oo: Array<Any> = map.values.toTypedArray()
        for (vv in oo) out.add(vv as ConstValue)
        return out
        }
    override fun toString(): String {
        var out = "Group $group\n"
        for (cc in createList()) out += """
        $cc""".trimIndent()
        return out
        }
}