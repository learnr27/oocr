package com.bannad927.oocr.common

import lombok.Data
import lombok.Getter
import lombok.Setter

/**
 * Created by: 程彬彬
 * Date: 2018/8/24
 * Describe:test
 */
@Data
@Getter
@Setter
class Result {
    var success: Boolean? = true
    var msg: String? = ""
    var code: String? = ""
    var content: Any? = ""

    fun success(content: Any?): Result {
        val result = Result()
        result.success=true
        result.code="1"
        result.msg=""
        result.content=content
        return result
    }

    fun error(code: String, msg: String): Result {
        val result = Result()
        result.code=code
        result.msg=msg
        result.success=false
        return result
    }

}