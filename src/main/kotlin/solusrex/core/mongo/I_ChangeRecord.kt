package solusrex.core.mongo

import solusrex.core.entity.Entity

interface I_ChangeRecord {
    fun changeRecord(ent: Entity?): Boolean
    }
