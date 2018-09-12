package com.bannad927.oocr.utils

import com.alibaba.druid.util.StringUtils
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by: 程彬彬
 * Date: 2018/8/24
 * Describe:
 */
class DateUtils {


    /**
     * 字符串转日期
     */
    fun stringToDate(date: String?, pattern: String): Date? {
        var pattern = pattern
        if (date == null) {
            return null
        }
        if (StringUtils.isEmpty(pattern)) {
            pattern = "yyyy-MM-dd HH:mm:ss"
        }
        try {
            val sdf = SimpleDateFormat(pattern)
            return sdf.parse(date)
        } catch (e: Exception) {
            return null
        }

    }

    /**
     * 日期转字符串
     */
    fun dateToString(date: Date?): String? {
        if (date == null) {
            return null
        }
        val pattern = "yyyy-MM-dd HH:mm:ss"
        val sdf = SimpleDateFormat(pattern)
        try {
            return sdf.format(date)
        } catch (e: Exception) {
            return null
        }

    }

    fun dateToStr(date: Date?): String? {
        if (date == null) {
            return null
        }
       val pattern = "yyyyMM"
        val sdf = SimpleDateFormat(pattern)
        try {
            return sdf.format(date)
        } catch (e: Exception) {
            return null
        }

    }

    fun  getNowTime(pattern: String): String? {
        val date = Date()
        val sdf = SimpleDateFormat(pattern)
        try {
            return sdf.format(date)
        } catch (e: Exception) {
            return null
        }

    }

    /**
     * 14位时间字符串 精确到秒

     * @return
     */
    fun _yyyyMMddHHmmss(): String? {
        val pattern = "yyyyMMddHHmmss"
        val sdf = SimpleDateFormat(pattern)
        try {
            return sdf.format(Date())
        } catch (e: Exception) {
            return null
        }

    }

    /**
     * 时间差类型
     * @param startDate
     * *
     * @param endDate
     * *
     * @return
     */
    fun dateFormat(startDate: String, endDate: String): String {
        val start = stringToDate(startDate, "yyyy-MM-dd HH:mm")
        val end = stringToDate(endDate, "yyyy-MM-dd HH:mm")
        val time = end!!.time - start!!.time + 1000
        val days = time / 1000 / 60 / 60 / 24
        if (days >= 1 && days <= 30) {
            return "day" // 天
        } else if (days > 30) {
            return "month" //月
        } else {
            return "hour" // 时
        }
    }
}