package com.kuriotetsuya.data

fun String.convertNetworkString() : String {
    return "http://image.tmdb.org/t/p/w500$this"
}