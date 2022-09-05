package solusrex.core.jdbc

import java.sql.ResultSet

interface I_OnRecord {
    fun onRecord(rs: ResultSet?)
    }