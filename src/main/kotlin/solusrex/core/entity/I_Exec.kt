package solusrex.core.entity

interface I_Exec<T : Entity?> {
    fun exec(vv: T)
    }