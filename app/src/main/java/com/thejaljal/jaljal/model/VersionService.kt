package com.thejaljal.jaljal.model

/**
 * Created by no.1 on 2017-11-03.
 */

data class VersionService(val result:Boolean?, val message:String?, val data: Data?) {
    data class Data(val appStatus: AppStatus, val ServiceStatus: ServiceStatus)

    data class AppStatus(
            var lastVersion: String?,
            var nowVersion: String?,
            var apiUrl: String?,
            var useYn: String?,
            var updateYn: String?)
    data class ServiceStatus(
            var serviceSeq: Int?,
            var statusMsg: String?,
            var serviceYn: String?,
            var delYn: String?,
            var serviceMemo: String?,
            var regDt: String?)
}