package br.com.douglas.financaskt.extension

fun String.limitOf(characters: Int) : String {
    if(this.length > characters) {
        return "${this.substring(0, characters)} ..."
    }
    return this
}