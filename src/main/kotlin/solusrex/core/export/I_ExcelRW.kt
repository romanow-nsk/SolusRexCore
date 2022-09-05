package solusrex.core.export

import org.apache.poi.ss.usermodel.Row
import solusrex.core.common.Counter
import solusrex.core.common.UniException

interface I_ExcelRW {
    @Throws(UniException::class)
    fun putXLSValues(row: Row?, cnt: Counter?)
    @Throws(UniException::class)
    fun getXLSValues(row: Row?, prefix: String?, colMap: HashMap<String?, Int?>?)
    @Throws(UniException::class)
    fun putXLSHeader(prefix: String?, list: ArrayList<String?>?)
}