package com.example.alpacamusclemaintenance.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.alpacamusclemaintenance.db.entity.PushUpEntity
import com.example.alpacamusclemaintenance.domain.pushup.PushUp

@Dao
interface PushUpDao {

    @Insert
    suspend fun insert(pushUpEntity: PushUpEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(pushUpEntities: List<PushUpEntity>)

    @Query(
        """
    SELECT *
    FROM push_ups
    WHERE done_at >= DATE('now', 'start of day', '-10 days')
    ORDER BY done_at DESC
    """
    )
    fun getPushUps(): LiveData<List<PushUpEntity>>

    @Query("DELETE FROM push_ups")
    fun deleteAll()
}

fun PushUpEntity.toPushUp(): PushUp = PushUp(
    id = id,
    count = count,
    doneAt = doneAt,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun List<PushUpEntity>.toPushUps(): List<PushUp> = map { it.toPushUp() }

fun PushUp.toPushUpEntity() = PushUpEntity(
    id = id,
    count = count,
    doneAt = doneAt,
    createdAt = createdAt,
    updatedAt = updatedAt
)
