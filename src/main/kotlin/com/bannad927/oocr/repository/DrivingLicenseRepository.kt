package com.bannad927.oocr.repository

import com.bannad927.oocr.entity.DrivingLicenseEntity
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by: 程彬彬
 * Date: 2018/9/3
 * Describe:
 */
interface DrivingLicenseRepository : JpaRepository<DrivingLicenseEntity, Integer> {
}