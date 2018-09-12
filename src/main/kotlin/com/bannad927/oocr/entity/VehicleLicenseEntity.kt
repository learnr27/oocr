package com.bannad927.oocr.entity

import java.util.*
import javax.persistence.*

/**
 * Created by: 程彬彬
 * Date: 2018/8/27
 * Describe: 行驶证
 */
@Entity
@Table(name = "vehicle_license")
class VehicleLicenseEntity {

    @get:Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    var id: Int? = 0

    @get:Column(name = "plate_no")
    var plateNo: String? = ""

    @get:Column(name = "vehicle_type")
    var vehicleType: String? = ""
   
    @get:Column(name = "vehicle_owner")
    var vehicleOwner: String? = ""
   
    @get:Column(name = "address")
    var address: String? = ""
   
    @get:Column(name = "use_character")
    var useCharacter: String? = ""
   
    @get:Column(name = "brand_model")
    var brandModel: String? = ""
   
    @get:Column(name = "vin")
    var vin: String? = ""
   
    @get:Column(name = "engine_no")
    var engineNo: String? = ""
   
    @get:Column(name = "register_date")
    var registerDate: String? = ""
   
    @get:Column(name = "issue_date")
    var issueDate: String? = ""
   
    @get:Column(name = "file_path")
    var filePath: String? = ""
   
    @get:Column(name = "phone")
    var phone: String? = ""
   
    @get:Column(name = "create_time")
    var createTime: Date? = Date()
   
    @get:Column(name = "update_time")
    var updateTime: Date? = Date()

}
