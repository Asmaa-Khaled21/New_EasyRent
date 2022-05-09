package com.sakura.easyrent.control.utils

/** [APIUtils] this object will be used for any API util */
object APIUtils {

    // Method(GenerateBearerToken):
    fun generateBearerToken(token: String): String = "Bearer $token"
}