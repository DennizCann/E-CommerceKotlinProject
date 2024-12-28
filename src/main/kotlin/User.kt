package com.denizcan

class User(private var name: String, private var balance: Double = 0.0) {

    // Kullanıcının ismini alma
    fun getName(): String {
        return name
    }

    // Kullanıcının güncel bakiyesini alma
    fun getBalance(): Double {
        return balance
    }

    // Bakiyeyi görüntüleme
    fun showBalance() {
        println("Güncel bakiyeniz: $balance TL")
    }

    // Bakiye yükleme
    fun addBalance(amount: Double) {
        if (amount > 0) {
            balance += amount
            println("$amount TL bakiyenize eklendi. Güncel bakiyeniz: $balance TL")
        } else {
            println("Geçersiz bakiye miktarı. Lütfen pozitif bir değer girin.")
        }
    }

    // Bakiyeden düşme
    fun deductBalance(amount: Double) {
        if (amount > 0 && amount <= balance) {
            balance -= amount
        }
    }
}
