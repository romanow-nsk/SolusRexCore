package solusrex.core.baseenities.artifact

import com.google.gson.Gson
import com.thoughtworks.xstream.XStream
import org.eclipse.sisu.space.CloningClassSpace.originalName
import org.joda.time.format.DateTimeFormat
import solusrex.core.baseenities.OwnDateTime
import solusrex.core.constants.CoreSingleton
import solusrex.core.constants.values.CoreValues
import solusrex.core.entity.EntityBack

class Artifact : EntityBack {
    var type: Int = 0           // Тип файла
    val name: String = ""       // Название артефакта
    var original = FileNameExt("", "")
    var date = OwnDateTime()    // Дата/время создания
    var fileSize: Long = 0      // Размер файла
    var fileLost = false        // Файл отсутствует
    //----------------------------------------------------------------------------------------
    constructor() {
        date = OwnDateTime()
        }
    constructor(id: Long) {
        oid = id
        }
    constructor(name: String, fsize: Long) {
        fileSize = fsize
        original = FileNameExt("", name)
        }
    fun typeName() : String{
        val ss = CoreSingleton.get().constMap.getConstValue("ArtifactType", type)
        if (ss!=null)
            return ss.title!!
        else
            return ""
        }
    fun getTitle(): String? {
        return typeName() + " " + name + " [" + original + "] "
        }
    fun directoryName(): String {
        val ss = CoreSingleton.get().constMap.getConstValue("ArtifactType", type)
        if (ss!=null)
            return ss.className!!
        else
            return ""
        }
    fun setOriginalName(ss: String) {
        original.name = ss
        }
    fun setOriginalExt(ss: String) {
        original.ext = ss
        }
    fun createArtifactServerPath(): String? {
        return ""+type + "_" + directoryName() + "/" + createArtifactFileName()
        }
    fun createArtifactServerDir(): String? {
        return ""+type + "_" + directoryName()
        }
    fun createArtifactFileName(): String {
        return date.date()!!.toString(DateTimeFormat.forPattern("yyyyMMdd_HHmmss")) + "_" + oid + "(" + original.fileName() + ")" + "." + original.ext
        }
    override fun toString(): String {
        return name + " " + typeName() + "_" + createArtifactFileName() + " [" + fileSize + "]"
        }
    override fun setAliases(xs: XStream) {
        }
    override fun toFullString(): String {
        return super.toFullString() + name + " " + typeName() + "_" + createArtifactFileName() + " [" + fileSize + "]"
        }
    fun isFile(): Boolean {
        return true
        }
    fun setTypeFromExt(){
        type = CoreSingleton.get().artifactTypes.getArtifactType(original.ext)
        }
    companion object{
        @JvmStatic
        open fun main(a: Array<String>) {
            val art = Artifact("aaa.txt",1234)
            art.setTypeFromExt()
            println(art.toFullString())
        }
    }
}