package com.storiqa.storiqawallet.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.storiqa.storiqawallet.data.db.entity.TransactionEntity
import com.storiqa.storiqawallet.data.db.entity.TransactionWithAddresses
import io.reactivex.Flowable

@Dao
interface TransactionDao {
    @Query("SELECT * FROM Transactions")
    fun loadTransactions(): Flowable<List<TransactionEntity>>

    @Query("SELECT * FROM Transactions")
    fun loadTransactionsWithAddresses(): Flowable<List<TransactionWithAddresses>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(transaction: TransactionEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(transactions: List<TransactionEntity>)

    @Query("DELETE FROM Transactions")
    fun deleteAll()
}