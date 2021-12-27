package com.tutorial.rentathingg

data class Item(
    val title: String? = null,
    val category: String? = null,
    val detail: String? = null,
    val description: String? = null,
    val phoneNum: String? = null,
    val value: String? = null,
    val author: String?,
    val authorId: String?,
    val imageUri: String? = null
) {
    // Null default values create a no-argument default constructor, which is needed
    // for deserialization from a DataSnapshot.
}