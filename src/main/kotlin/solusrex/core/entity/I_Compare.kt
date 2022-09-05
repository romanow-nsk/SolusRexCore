package solusrex.core.entity

import solusrex.core.entity.Entity

interface I_Compare {
    fun compare(one: Entity?, two: Entity?): Int
    }