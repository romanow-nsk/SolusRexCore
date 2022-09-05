package solusrex.core.entity

import solusrex.core.dao.TableItem

class EntityIndexedFactory {
    private val foreMap: HashMap<Class<*>?, TableItem?> = HashMap()
    private val invertMap: HashMap<String?, TableItem?> = HashMap()
    private val simpleNameMap: HashMap<String?, TableItem?> = HashMap()
    fun put(item: TableItem) {
        val shortName: String = item.clazz!!.getSimpleName()
        val old: TableItem? = simpleNameMap[shortName]
        if (old != null) {       // Замена по коротким именам
            simpleNameMap.remove(shortName)
            invertMap.remove(old.name)
            foreMap.remove(old.clazz)
            }
        foreMap[item.clazz] = item
        invertMap[item.name] = item
        simpleNameMap[item.clazz!!.getSimpleName()] = item
        }
    fun getClassForSimpleName(name: String?): Class<*>? {
        val item: TableItem? = getItemForSimpleName(name)
        return if (item == null) null else item.clazz
        }
    fun getItemForSimpleName(name: String?): TableItem? {
        return simpleNameMap[name]
        }
    operator fun get(name: String?): Class<*>? {
        val item: TableItem? = invertMap[name]
        return if (item == null) null else item.clazz
        }
    operator fun get(cls: Class<*>?): String {
        return foreMap[cls]!!.name
        }
    fun getEntityNameBySimpleClass(name: String?): String? {
        val item: TableItem? = getItemForSimpleName(name)
        return if (item == null) null else item.name
        }
    fun classList(): ArrayList<TableItem?> {
        return classList(false)
        }
    fun classList(className: Boolean): ArrayList<TableItem?> {
        val cc: Array<TableItem?> = arrayOfNulls<TableItem>(foreMap.size)
        foreMap.values.addAll(cc)
        val out: ArrayList<TableItem?> = ArrayList<TableItem?>()
        for (i in cc.indices) {
            if (cc[i]!!.isTable) out.add(cc[i])
            }
        out.sortWith(Comparator<TableItem?> { o1, o2 ->
            if (className) o1!!.clazz!!.javaClass.getSimpleName().compareTo(o2!!.clazz!!.javaClass.getSimpleName()) else o1!!.name.compareTo(o2!!.name)
            })
        return out
        }
    fun nameList(): ArrayList<String> {
        val cc: Array<TableItem?> = arrayOfNulls<TableItem>(invertMap.size)
        invertMap.values.addAll(cc)
        val out = ArrayList<String>()
        for (i in cc.indices) if (cc[i]!!.isTable) out.add(cc[i]!!.name)
        out.sortWith(Comparator { o1, o2 -> o1.compareTo(o2) })
        return out
        }
    fun size(): Int {
        return foreMap.size
        }
}