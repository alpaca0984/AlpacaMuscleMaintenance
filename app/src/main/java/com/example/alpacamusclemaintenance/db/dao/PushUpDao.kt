package com.example.alpacamusclemaintenance.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.alpacamusclemaintenance.db.entity.PushUp
import io.reactivex.Observable

@Dao
interface PushUpDao {

  @Insert
  fun insert(pushUp: PushUp)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertAll(pushUps: List<PushUp>)

  @Query("SELECT * FROM push_ups WHERE done_at >= DATE('now', 'start of day', '-10 days') ORDER BY done_at DESC")
  fun getPushUps(): Observable<List<PushUp>>

  @Query("DELETE FROM push_ups")
  fun deleteAll()
}
