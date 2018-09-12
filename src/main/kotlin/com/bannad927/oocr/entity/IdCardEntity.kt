package com.bannad927.oocr.entity

import java.util.*
import javax.persistence.*

/**
 * Created by: 程彬彬
 * Date: 2018/8/31
 * Describe:
 */
@Entity
@Table(name = "id_card")
class IdCardEntity {

    @get:Id
    @get:Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0

    @get:Column(name = "address")
    var address: String? = ""

    @get:Column(name = "card_no")
    var cardNo: String? = ""

    @get:Column(name = "birthday")
    var birthday: String? = ""

    @get:Column(name = "name")
    var name: String? = ""

    @get:Column(name = "gender")
    var gender: String? = ""

    @get:Column(name = "nation")
    var nation: String? = ""

    @get:Column(name = "file_path")
    var filePath: String? = ""

    @get:Column(name = "create_time")
    var createTime: Date? = Date()

    @get:Column(name = "update_time")
    var updateTime: Date? = Date()


}
