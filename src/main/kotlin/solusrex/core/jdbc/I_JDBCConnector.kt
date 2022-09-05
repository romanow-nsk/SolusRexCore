package solusrex.core.jdbc

import solusrex.core.common.ParamList
import solusrex.core.common.UniException
import java.sql.ResultSet

interface I_JDBCConnector {
    fun getDriverName(): String?
    fun isConnected(): Boolean
    @Throws(UniException::class)
    fun connect(params: ParamList?)

    @Throws(UniException::class)
    fun reconnect()

    @Throws(UniException::class)
    fun connect(file: String?)

    //---- Вызывается только из синхронизированного кода ---------------------------
    @Throws(UniException::class)
    fun testConnect()

    @Throws(UniException::class)
    fun open()

    @Throws(UniException::class)
    fun close()

    @Throws(UniException::class)
    fun execSQL(sql: String?)

    @Throws(UniException::class)
    fun insert(sql: String?, ownId: Boolean): Long

    @Throws(UniException::class)
    fun selectOne(sql: String?): ResultSet?

    @Throws(UniException::class)
    fun selectOne(sql: String?, back: I_OnRecord?)

    @Throws(UniException::class)
    fun selectMany(sql: String?, back: I_OnRecord?)

    @Throws(UniException::class)
    fun newRecord(tname: String?): Long
    fun getText1(): String?
    fun getText2(): String?
    fun canGenerateKey(): Boolean
}