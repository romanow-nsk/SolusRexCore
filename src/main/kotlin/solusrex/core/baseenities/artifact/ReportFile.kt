package solusrex.core.baseenities.artifact

import solusrex.core.constants.values.CoreValues
import solusrex.core.entity.Entity
import solusrex.core.entity.EntityLink

class ReportFile : Entity {
    constructor() {}
    var reportHeader = ""
    var reportType: Int = CoreValues.RepOther
    var archive = false
    var artifact: EntityLink<Artifact> = EntityLink<Artifact>(Artifact::class.java)
    constructor(reportHeader0: String) {
        reportHeader = reportHeader0
        }
    constructor(reportHeader: String, oid: Long) {
        this.reportHeader = reportHeader
        artifact.oid = oid
        }
    constructor(reportHeader: String, art: Artifact?) {
        this.reportHeader = reportHeader
        artifact.setOidRef(art)
        }
    override fun title(): String {
        return reportHeader
        }
    override fun toShortString(): String {
        return reportHeader + " от " + artifact.ref!!.date.dateTimeToString()
        }
    override fun toString(): String {
        return reportHeader + " " + artifact.toString()
        }
}
