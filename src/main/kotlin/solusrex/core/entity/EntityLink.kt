package solusrex.core.entity

class EntityLink<T : Entity?> {
    var oid: Long = 0L
    var ref: T? = null
    @Transient
    var typeT: Class<*>? = null // Класс параметра шаблона для рефлексионной загрузки ссылок
    fun EntityLink() {}
    fun EntityLink(type0: Class<*>?) {
        typeT = type0 }
    constructor(vv: T?) {
        ref = vv }
    constructor(id: Long) {
        oid = id }
    constructor() {}
    constructor(id: Long, ref0: T?) {
        oid = id
        ref = ref0 }
    fun setOidRef(id: Long, ref0: T?) {
        oid = id
        ref = ref0 }
    fun clear() {
        ref = null
        oid = 0
        }
    fun setOidRef(ref0: T?) {
        if (ref0 == null) {
            oid = 0
            ref = null
            }
        else{
            oid = ref0.oid
            ref = ref0
            }
        }
    //------------------------------------------------------------------------------------------------------------------
    override fun toString(): String {
        return ""+oid + if (ref == null) "" else ": " + ref.toString()
        }
    fun toShortString(): String? {
        return if (ref == null) "" else ref!!.toShortString()
        }
    fun toFullString(): String? {
        if (oid == 0L) return ""
        return if (ref == null) "$oid=???" else ref!!.toFullString()
        }

    fun title(): String? {
        return if (ref == null) "..." else ref!!.title()
        }
}