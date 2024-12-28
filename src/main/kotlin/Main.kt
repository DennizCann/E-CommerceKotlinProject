package com.denizcan

fun main() {

    print("İsminizi girin: ")
    val userName: String = readLine()?.takeIf { it.isNotBlank() } ?: "Kullanıcı"
    println("Merhaba $userName!")

    val user = User(userName)
    var isRunning = true

    // Ürün listeleri
    val electronics = listOf(
        Product("Laptop", ProductCategory.ELECTRONICS, 1500.0, 2),
        Product("Smartphone", ProductCategory.ELECTRONICS, 800.0, 1),
        Product("Headphones", ProductCategory.ELECTRONICS, 150.0, 3),
        Product("Smartwatch", ProductCategory.ELECTRONICS, 200.0, 0),
        Product("Tablet", ProductCategory.ELECTRONICS, 600.0, 1)
    )

    val fashion = listOf(
        Product("T-Shirt", ProductCategory.FASHION, 20.0, 5),
        Product("Jeans", ProductCategory.FASHION, 50.0, 2),
        Product("Sneakers", ProductCategory.FASHION, 80.0, 1),
        Product("Jacket", ProductCategory.FASHION, 100.0, 0),
        Product("Sunglasses", ProductCategory.FASHION, 40.0, 3)
    )

    val home = listOf(
        Product("Sofa", ProductCategory.HOME, 300.0, 1),
        Product("Dining Table", ProductCategory.HOME, 250.0, 0),
        Product("Lamp", ProductCategory.HOME, 30.0, 4),
        Product("Curtains", ProductCategory.HOME, 25.0, 2),
        Product("Carpet", ProductCategory.HOME, 100.0, 3)
    )

    val sports = listOf(
        Product("Football", ProductCategory.SPORTS, 30.0, 2),
        Product("Basketball Shoes", ProductCategory.SPORTS, 120.0, 1),
        Product("Yoga Mat", ProductCategory.SPORTS, 25.0, 0),
        Product("Tennis Racket", ProductCategory.SPORTS, 80.0, 3),
        Product("Running Shorts", ProductCategory.SPORTS, 20.0, 5)
    )

    val books = listOf(
        Product("The Hobbit", ProductCategory.BOOKS, 15.0, 2),
        Product("1984", ProductCategory.BOOKS, 10.0, 1),
        Product("Clean Code", ProductCategory.BOOKS, 35.0, 0),
        Product("Kotlin in Action", ProductCategory.BOOKS, 40.0, 3),
        Product("The Art of War", ProductCategory.BOOKS, 12.0, 4)
    )

    val grocery = listOf(
        Product("Apple", ProductCategory.GROCERY, 3.0, 10),
        Product("Milk", ProductCategory.GROCERY, 2.5, 5),
        Product("Bread", ProductCategory.GROCERY, 1.0, 8),
        Product("Eggs (12-pack)", ProductCategory.GROCERY, 12.0, 2),
        Product("Cheese", ProductCategory.GROCERY, 20.0, 4)
    )

    while (isRunning) {
        println("\nNe yapmak istersiniz?")
        println("1. Bakiye Yükle")
        println("2. Bakiye Görüntüle")
        println("3. Ürün Al")
        println("4. Çıkış Yap")
        print("Seçiminiz: ")

        val choice = readLine()?.toIntOrNull()

        when (choice) {
            1 -> {
                print("Yüklemek istediğiniz bakiye miktarını girin: ")
                val amountToAdd = readLine()?.toDoubleOrNull()
                if (amountToAdd != null) user.addBalance(amountToAdd) else println("Geçersiz giriş.")
            }
            2 -> user.showBalance()
            3 -> {
                println("Hangi kategoriden ürün almak istersiniz? (Geri gitmek için '0' yazın)")
                ProductCategory.values().forEachIndexed { index, category ->
                    println("${index + 1}. ${category.name}")
                }
                print("Seçiminiz: ")

                val categoryChoice = readLine()?.toIntOrNull()
                if (categoryChoice == 0) continue

                if (categoryChoice != null && categoryChoice in 1..ProductCategory.values().size) {
                    val selectedCategory = ProductCategory.values()[categoryChoice - 1]
                    val products = when (selectedCategory) {
                        ProductCategory.ELECTRONICS -> electronics
                        ProductCategory.FASHION -> fashion
                        ProductCategory.HOME -> home
                        ProductCategory.SPORTS -> sports
                        ProductCategory.BOOKS -> books
                        ProductCategory.GROCERY -> grocery
                    }

                    println("Mevcut Ürünler:")
                    products.forEachIndexed { index, product ->
                        print("${index + 1}. ")
                        product.showProductInfo()
                    }

                    print("Satın almak istediğiniz ürünün numarasını girin (Geri gitmek için '0' yazın): ")
                    val productChoice = readLine()?.toIntOrNull()
                    if (productChoice == 0) continue

                    if (productChoice != null && productChoice in 1..products.size) {
                        val selectedProduct = products[productChoice - 1]

                        if (selectedProduct.quantity > 0) {
                            print("Bu ürünü satın almak istiyor musunuz? (Evet/Hayır): ")
                            val confirmation = readLine()?.lowercase()

                            if (confirmation == "evet") {
                                if (user.getBalance() >= selectedProduct.price) {
                                    selectedProduct.decreaseQuantity()
                                    user.deductBalance(selectedProduct.price)
                                    println("Ürün alındı! Güncel bakiyeniz: ${user.getBalance()} TL")
                                    if (selectedProduct.quantity == 0) {
                                        println("${selectedProduct.name} tükendi.")
                                    }
                                } else {
                                    println("Yetersiz bakiye! Lütfen bakiye yükleyin.")
                                }
                            } else {
                                println("Satın alma işlemi iptal edildi.")
                            }
                        } else {
                            println("Ürün tükendi.")
                        }
                    } else {
                        println("Geçersiz ürün seçimi.")
                    }
                } else {
                    println("Geçersiz kategori seçimi.")
                }
            }
            4 -> {
                println("Çıkış yapılıyor. Güle güle ${user.getName()}!")
                isRunning = false
            }
            else -> println("Geçersiz seçim.")
        }
    }
}
