package com.tutorial.rentathingg

data class Item(
    val title: String? = null,
    val category: String? = null,
    val detail: String? = null,
    val description: String? = null,
    val phoneNum: String? = null,
    val author: String?
) {
    // Null default values create a no-argument default constructor, which is needed
    // for deserialization from a DataSnapshot.
}