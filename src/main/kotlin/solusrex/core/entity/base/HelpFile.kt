package solusrex.core.entity.base

import solusrex.core.baseenities.artifact.Artifact
import solusrex.core.entity.Entity
import solusrex.core.entity.EntityLink
import java.util.*

class HelpFile : Entity() {
    var title = ""
    var tagList = "" // Массив идентификаторов тегов
    private var itemFile: EntityLink<Artifact> = EntityLink(Artifact::class.java)
    fun isTagPresent(tag: String): Boolean {
        return createTagMap(tagList)[tag] != null
        }
    fun calcTagCount(src: ArrayList<String>): Int {
        var count = 0
        val xx = createTagMap(tagList)
        for (zz in src) if (xx[zz] != null) count++
        return count
        }
    fun isAllTagsPresent(): Boolean {
        val tags = createTagArray()
        if (tags.size == 0) return true
        val xx = createTagMap(tagList)
        for (zz in tags) if (xx[zz] == null) return false
        return true
        }
    fun getItemFile(): EntityLink<Artifact> {
        return itemFile
        }
    fun setItemFile(itemFile2: EntityLink<Artifact>) {
        this.itemFile = itemFile2
        }
    fun addTag(tag: String) {
        if (isTagPresent(tag)) return
        if (tagList.length == 0) tagList = tag else tagList = "$tagList,$tag"
        }
    fun removeTag(tag: String) {
        var zz = ""
        for (ss in createTagArray()) {
            if (ss == tag) continue
            zz = if (zz.length == 0) ss else "$zz,$ss"
            }
        tagList = zz
        }
    fun createTagArray(): ArrayList<String> {
        val out = ArrayList<String>()
        val tokenizer = StringTokenizer(tagList, ",")
        while (tokenizer.hasMoreElements()) {
            out.add(tokenizer.nextToken())
            }
        return out
        }

    fun createTagMap(zz: String): HashMap<String, Any?> {
        val oo = Any()
        val out = HashMap<String, Any?>()
        val tokenizer = StringTokenizer(zz, ",")
        while (tokenizer.hasMoreElements()) {
            out[tokenizer.nextToken()] = oo
        }
        return out
    }
    override fun toFullString(): String {
        return title + " [" + tagList + "] " + itemFile.title()
        }

    companion object {
        //-----------------------------------------------------------------------------------
        @JvmStatic
        fun main(ss: Array<String>) {
            val ff = HelpFile()
            ff.tagList = "aaa,bbb,ccc"
            val zz: ArrayList<String> = ff.createTagArray()
            println(zz.size)
        }
    }
}
