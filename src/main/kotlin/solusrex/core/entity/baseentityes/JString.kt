package solusrex.core.entity.baseentityes

import solusrex.core.dao.DAO

class JString : DAO {
    var value = ""
    constructor(`val`: String) {
        value = `val`
        }
    constructor() {}
    override fun toString(): String {
        return value
        }
}