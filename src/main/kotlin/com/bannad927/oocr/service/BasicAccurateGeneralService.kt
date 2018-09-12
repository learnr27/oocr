package com.bannad927.oocr.service

import com.bannad927.oocr.utils.JSONUtil
import org.json.JSONArray
import org.json.JSONObject
import org.springframework.stereotype.Service
import java.util.LinkedHashMap

/**
 * Created by: 程彬彬
 * Date: 2018/8/31
 * Describe:
 */
@Service
class BasicAccurateGeneralService {

    fun analysisData(res: JSONObject):String{
        var data:String=""
        val resultMap = JSONUtil().readObject<Map<*, *>>(res.toString(), Map::class.java)
        if(null!=resultMap.get("error_code")){
            return ""
        }
        var count:Int =resultMap.get("words_result_num") as Int
        if (count > 0){

            val args= resultMap.get("words_result")  as ArrayList<*>
            for (arg in args) {
            print(arg)
                data=data+ (arg as LinkedHashMap<*, *>).get("words")
            }
        }
        return data
    }
}