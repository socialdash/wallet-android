package com.storiqa.storiqawallet.repositories

import com.storiqa.storiqawallet.enums.TransactionType
import com.storiqa.storiqawallet.objects.Transaction
import io.reactivex.Observable
import io.reactivex.Observer
import java.util.*

class TransactionRepository {

    fun getTransactions(idOfSelectedBill : String, limit : Int?) : Observable<Array<Transaction>> {
        return Observable.create<Array<Transaction>> {
            emitter ->
//            TODO implement
            val transactions = arrayListOf<Transaction>()
            transactions.add(Transaction("","STQ", TransactionType.SEND, true, "0.00001", "250", "", "Aleksey V."))
            transactions.add(Transaction("","BTC", TransactionType.SEND, false, "0.0000001", "2", "", "Aleksey V."))
            transactions.add(Transaction("","ETH", TransactionType.RECEIVE, false, "0.0001", "4000", "0xe3f…78345hj", ""))
            transactions.add(Transaction("","STQ", TransactionType.SEND, false, "0.00001", "250", "", "Aleksey V."))
            transactions.add(Transaction("","BTC", TransactionType.RECEIVE, false, "0.00001", "250", "0xe3f…78345hj", ""))
            transactions.add(Transaction("","ETH", TransactionType.SEND, false, "0.00001", "250", "", "Aleksey V."))
            transactions.add(Transaction("","ETH1", TransactionType.SEND, false, "0.00001", "250", "", "Aleksey V."))
            transactions.add(Transaction("","ETH", TransactionType.RECEIVE, false, "0.0001", "4000", "0xe3f…78345hj", ""))
            transactions.add(Transaction("","ETH", TransactionType.RECEIVE, false, "0.0001", "4000", "0xe3f…78345hj", ""))
            transactions.add(Transaction("","ETH2", TransactionType.SEND, false, "0.00001", "250", "", "Aleksey V."))
            transactions.add(Transaction("","ETH3", TransactionType.SEND, false, "0.00001", "250", "", "Aleksey V."))
            transactions.add(Transaction("","ETH4", TransactionType.SEND, false, "0.00001", "250", "", "Aleksey V."))
            transactions.add(Transaction("","ETH", TransactionType.RECEIVE, false, "0.0001", "4000", "0xe3f…78345hj", ""))
            transactions.add(Transaction("","ETH5", TransactionType.SEND, false, "0.00001", "250", "", "Aleksey V."))
            transactions.add(Transaction("","ETH6", TransactionType.SEND, false, "0.00001", "250", "", "Aleksey V."))
            transactions.add(Transaction("","ETH7", TransactionType.SEND, false, "0.00001", "250", "", "Aleksey V."))
            transactions.add(Transaction("","ETH8", TransactionType.SEND, false, "0.00001", "250", "", "Aleksey V."))
            transactions.add(Transaction("","ETH", TransactionType.RECEIVE, false, "0.0001", "4000", "0xe3f…78345hj", ""))
            transactions.add(Transaction("","ETH9", TransactionType.SEND, false, "0.00001", "250", "", "Aleksey V."))
            transactions.add(Transaction("","ETH0", TransactionType.SEND, false, "0.00001", "250", "", "Aleksey V."))
            transactions.add(Transaction("","ETH1", TransactionType.SEND, false, "0.00001", "250", "", "Aleksey V."))
            transactions.add(Transaction("","ETH2", TransactionType.SEND, false, "0.00001", "250", "", "Aleksey V."))
            transactions.add(Transaction("","ETH3", TransactionType.SEND, false, "0.00001", "250", "", "Aleksey V."))
            transactions.add(Transaction("","ETH4", TransactionType.SEND, false, "0.00001", "250", "", "Aleksey V."))
            transactions.add(Transaction("","ETH5", TransactionType.SEND, false, "0.00001", "250", "", "Aleksey V."))
            transactions.add(Transaction("","ETH6", TransactionType.SEND, false, "0.00001", "250", "", "Aleksey V."))
            transactions.add(Transaction("","ETH7", TransactionType.SEND, false, "0.00001", "250", "", "Aleksey V."))
            transactions.add(Transaction("","ETH8", TransactionType.SEND, false, "0.00001", "250", "", "Aleksey V."))

            transactions.shuffle()

            if(limit != null) {
                emitter.onNext(transactions.slice(0 until limit).toTypedArray())
            } else {
                emitter.onNext(transactions.toTypedArray())
            }
            emitter.onComplete()

        }.onErrorReturnItem(arrayOf())
    }
}