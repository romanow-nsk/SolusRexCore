package solusrex.core.entity

interface I_ExecLink<T : EntityLink<out Entity?>?> {
    fun exec(vv: T)
    }