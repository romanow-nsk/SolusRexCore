package solusrex.core.common

class Counter {
    var idx = 0
        get() = field++
        private set
        }