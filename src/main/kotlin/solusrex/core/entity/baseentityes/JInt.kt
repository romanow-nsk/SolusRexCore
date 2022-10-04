package solusrex.core.entity.baseentityes

import solusrex.core.dao.DAO

class JInt : DAO {
    var value = 0
    constructor(`val`: Int) {
        value = `val`
        }
    override fun toString(): String {
        return "" + value
        }
    constructor() {}
}