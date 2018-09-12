package com.bannad927.oocr.entity

import java.util.*
import javax.persistence.*

/**
 * Created by: 程彬彬
 * Date: 2018/9/3
 * Describe:
 */
@Entity
@Table(name = "business_license")
class BusinessLicenseEntity {
    @get:Id
    @get:Column(name = "id")
    var id: Int = 0
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @get:Column(name = "company_name")
    var companyName: String? = ""

    @get:Column(name = "legal_person")
    var legalPerson: String? = ""

    @get:Column(name = "address")
    var address: String? = ""

    @get:Column(name = "valid_period")
    var validPeriod: String? = ""

    @get:Column(name = "license_no")
    var licenseNo: String? = ""

    @get:Column(name = "social_credit_code")
    var socialCreditCode: String? = ""

    @get:Column(name = "file_path")
    var filePath: String? = ""

    @get:Column(name = "phone")
    var phone: String? = ""

    @get:Column(name = "create_time")
    var createTime: Date? = Date()

    @get:Column(name = "update_time")
    var updateTime: Date? = Date()


}
