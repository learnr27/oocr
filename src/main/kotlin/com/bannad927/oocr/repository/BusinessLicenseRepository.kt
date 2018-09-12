package com.bannad927.oocr.repository

import com.bannad927.oocr.entity.BusinessLicenseEntity
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by: 程彬彬
 * Date: 2018/9/3
 * Describe:
 */
interface BusinessLicenseRepository : JpaRepository<BusinessLicenseEntity, Integer> {
}