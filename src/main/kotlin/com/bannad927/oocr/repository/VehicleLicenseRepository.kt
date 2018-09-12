package com.bannad927.oocr.repository

import com.bannad927.oocr.entity.VehicleLicenseEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

/**
 * Created by: 程彬彬
 * Date: 2018/8/27
 * Describe:
 */

interface VehicleLicenseRepository : JpaRepository<VehicleLicenseEntity, Integer> {

    fun findById(id: Int): VehicleLicenseEntity

    fun findByVehicleOwner(vehicleOwner: String): List<VehicleLicenseEntity>

    @Modifying
    @Query("UPDATE VehicleLicenseEntity SET plateNo=:plateNo WHERE id=:id")
    fun updateVehicleLicenseByPlateNoAndId(@Param("plateNo") plateNo: String, @Param("id") id: Int)

}