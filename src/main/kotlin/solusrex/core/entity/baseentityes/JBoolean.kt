package solusrex.core.entity.baseentityes

import solusrex.core.dao.DAO

class JBoolean : DAO {
    constructor() {}
    private var value = false
    constructor(`val`: Boolean) {
        value = `val`
        }
    fun value(): Boolean {
        return value
        }
}