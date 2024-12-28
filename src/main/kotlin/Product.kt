package com.denizcan

class Product(
    val name: String,
    val category: ProductCategory,
    val price: Double,
    var quantity: Int
) {

    // Ürün bilgilerini gösteren fonksiyon
    fun showProductInfo() {
        val stockInfo = if (quantity > 0) "Adet: $quantity" else "Tükendi"
        println("Ürün Adı: $name, Kategori: ${category.name}, Fiyat: $price TL, $stockInfo")
    }

    // Üründen bir adet düşme fonksiyonu
    fun decreaseQuantity(): Boolean {
        return if (quantity > 0) {
            quantity -= 1
            true
        } else {
            false
        }
    }
}

enum class ProductCategory {
    ELECTRONICS,
    FASHION,
    HOME,
    SPORTS,
    BOOKS,
    GROCERY
}
