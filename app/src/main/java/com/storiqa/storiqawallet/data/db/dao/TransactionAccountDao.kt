package com.storiqa.storiqawallet.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.storiqa.storiqawallet.data.db.entity.TransactionAccountEntity
import io.reactivex.Flowable

@Dao
interface TransactionAccountDao {
    @Query("SELECT * FROM TransactionAccounts")
    fun loadTransactionAccounts(): Flowable<List<TransactionAccountEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(transactionAccount: TransactionAccountEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(transactionAccounts: List<TransactionAccountEntity>)

    @Query("DELETE FROM TransactionAccounts")
    fun deleteAll()
}