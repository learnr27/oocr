package com.bannad927.oocr.entity

import java.util.*
import javax.persistence.*

/**
 * Created by: 程彬彬
 * Date: 2018/9/3
 * Describe:
 */
@Entity
@Table(name = "bank_card")
class BankCardEntity {

    @get:Id
    @get:Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    var id: Int = 0

    @get:Column(name = "bank_card_number")
    var bankCardNumber: String? = ""

    @get:Column(name = "bank_name")
    var bankName: String? = ""

    @get:Column(name = "bank_card_type")
    var bankCardType: String? = ""

    @get:Column(name = "phone")
    var phone: String? = ""

    @get:Column(name = "file_path")
    var filePath: String? = ""

    @get:Column(name = "create_time")
    var createTime: Date? = Date()

    @get:Column(name = "update_time")
    var updateTime: Date? = Date()


}
