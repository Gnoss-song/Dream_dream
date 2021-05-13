/*
 *  혈액요청 환자의 정보를 나타냄
 */
package kr.co.pyo.rest.data

data class BloodEntity(

    var patientName: String = "",

    var bloodType: String = "",
    var statusText: String = "",
    var donationType: String = "",
    var bloodValue: String = "",
    var hospitalName: String = "",
    var hospitalPhone: String = "",
    var relationText: String = "",
    var careName: String = "",
    var carePhone: String = "",
    var bloodId: Int = -1,
    var insertDate: String = ""
)