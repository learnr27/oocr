package com.bannad927.oocr.service


import com.bannad927.oocr.entity.VehicleLicenseEntity
import com.bannad927.oocr.repository.VehicleLicenseRepository
import com.bannad927.oocr.utils.JSONUtil
import org.json.JSONObject
import org.springframework.stereotype.Service
import javax.annotation.Resource

/**
 * Created by: 程彬彬
 * Date: 2018/8/27
 * Describe: 解析
 */
@Service
class VehicleLicenseService {

    @Resource
    lateinit var vehicleLicenseRepository: VehicleLicenseRepository

    fun analysisData(res: JSONObject,filePath:String): VehicleLicenseEntity {
        var vehicleLicense = VehicleLicenseEntity()
        val resultMap = JSONUtil().readObject<Map<*, *>>(res.toString(), Map::class.java)
        if(null!=resultMap.get("error_code")){
             return vehicleLicense
        }
        val wordsResult = resultMap.get("words_result") as Map<*, *>

        var carCode = ""
        var address = ""
        var brandModel = ""
        var issuingDate = ""
        var carType = ""
        var car_user_name = ""
        var use_nuture = ""
        var engine_number = ""
        var car_plate_num = ""
        var register_date = ""

        for (keyStr in wordsResult.keys) {
            if ("号牌号码" == keyStr) {
                car_plate_num = (wordsResult["号牌号码"] as Map<*, *>)["words"].toString()
                vehicleLicense.plateNo = car_plate_num
            }
            if ("车辆识别代号" == keyStr) {
                carCode = (wordsResult["车辆识别代号"] as Map<*, *>)["words"].toString()
                vehicleLicense.vin = carCode
            }
            if ("住址" == keyStr) {
                address = (wordsResult["住址"] as Map<*, *>)["words"].toString()
                vehicleLicense.address = address
            }
            if ("品牌型号" == keyStr) {
                brandModel = (wordsResult["品牌型号"] as Map<*, *>)["words"].toString()
                vehicleLicense.brandModel = brandModel
            }
            if ("发证日期" == keyStr) {
                issuingDate = (wordsResult["发证日期"] as Map<*, *>)["words"].toString()
                vehicleLicense.issueDate = issuingDate
            }
            if ("车辆类型" == keyStr) {
                carType = (wordsResult["车辆类型"] as Map<*, *>)["words"].toString()
                vehicleLicense.vehicleType = carType
            }
            if ("所有人" == keyStr) {
                car_user_name = (wordsResult["所有人"] as Map<*, *>)["words"].toString()
                vehicleLicense.vehicleOwner = car_user_name
            }
            if ("使用性质" == keyStr) {
                use_nuture = (wordsResult["使用性质"] as Map<*, *>)["words"].toString()
                vehicleLicense.useCharacter = use_nuture
            }
            if ("发动机号码" == keyStr) {
                engine_number = (wordsResult["发动机号码"] as Map<*, *>)["words"].toString()
                vehicleLicense.engineNo = engine_number
            }
            if ("注册日期" == keyStr) {
                register_date = (wordsResult["注册日期"] as Map<*, *>)["words"].toString()
                vehicleLicense.registerDate = register_date
            }
        }
        vehicleLicense.filePath=filePath
        vehicleLicenseRepository.save(vehicleLicense)
        return vehicleLicense
    }
}