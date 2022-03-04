package com.example.androidkotlin

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface EmployeeDao {
    // data acces object

    @Insert
    suspend fun insert(employeeEntity: EmployeeEntity)

    @Update
    suspend fun update(employeeEntity: EmployeeEntity)

    @Delete
    suspend fun delete(employeeEntity: EmployeeEntity)

//    @Update
//    suspend fun insert(employeeEntity: EmployeeEntity)

    @Query(value = "SELECT * FROM `employee-table`")
    fun fetchAllEmployees(): Flow<List<EmployeeEntity>>

    @Query(value = "SELECT * FROM `employee-table` where id=:id")
    fun fetchEmployeeById(id: Int): Flow<EmployeeEntity>


}