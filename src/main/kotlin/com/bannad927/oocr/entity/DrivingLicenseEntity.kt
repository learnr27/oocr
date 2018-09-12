package com.bannad927.oocr.entity

import java.util.*
import javax.persistence.*

/**
 * Created by: 程彬彬
 * Date: 2018/9/3
 * Describe:
 */
@Entity
@Table(name = "driving_license")
class DrivingLicenseEntity {

    @get:Id
    @get:Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0

    @get:Column(name = "license_no")
    var licenseNo: String? = ""

    @get:Column(name = "name")
    var name: String? = ""

    @get:Column(name = "sex")
    var sex: String? = ""

    @get:Column(name = "nationality")
    var nationality: String? = ""

    @get:Column(name = "address")
    var address: String? = ""

    @get:Column(name = "birthday")
    var birthday: String? = ""

    @get:Column(name = "first_issue")
    var firstIssue: String? = ""

    @get:Column(name = "driving_type")
    var drivingType: String? = ""

    @get:Column(name = "valid_for")
    var validFor: String? = ""

    @get:Column(name = "valid_from")
    var validFrom: String? = ""

    @get:Column(name = "file_path")
    var filePath: String? = ""

    @get:Column(name = "phone")
    var phone: String? = ""

    @get:Column(name = "create_time")
    var createTime: Date? = Date()

    @get:Column(name = "update_time")
    var updateTime: Date? = Date()

}
