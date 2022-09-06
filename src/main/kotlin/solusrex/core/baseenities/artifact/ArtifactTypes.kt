package solusrex.core.baseenities.artifact

import solusrex.core.constants.values.CoreValues
import java.net.URLConnection
import java.util.*

class ArtifactTypes {
    private val extTypes = HashMap<String, Int>()
    constructor(){
        extTypes.put("mp3",CoreValues.ArtifactAudioType)
        extTypes.put("wav",CoreValues.ArtifactAudioType)
        extTypes.put("doc",CoreValues.ArtifactDocType)
        extTypes.put("docx",CoreValues.ArtifactDocType)
        extTypes.put("pdf",CoreValues.ArtifactDocType)
        extTypes.put("txt",CoreValues.ArtifactTextType)
        extTypes.put("mpg",CoreValues.ArtifactVideoType)
        extTypes.put("mp4",CoreValues.ArtifactVideoType)
        extTypes.put("3gp",CoreValues.ArtifactVideoType)
        extTypes.put("jpg",CoreValues.ArtifactImageType)
        extTypes.put("png",CoreValues.ArtifactImageType)
        }
    fun getMimeType(ext: String): String? {
        val type = URLConnection.getFileNameMap().getContentTypeFor("a.$ext")
        return type ?: "application/$ext"
        }
    fun getArtifactType(ext: String): Int {
        val type = extTypes[ext.lowercase(Locale.getDefault())]
        return type ?: CoreValues.ArtifactOtherType
        }
    }