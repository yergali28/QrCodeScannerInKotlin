package yergalizhakhan.kz.qrcodescannerkotlin.domain

import java.security.AccessControlContext
import java.util.*
import kotlin.properties.Delegates

class History {
    var id:Int by Delegates.notNull()
    var date:String by Delegates.notNull()
    var context:String by Delegates.notNull()

    constructor(date: String, context: String) {
        this.date = date
        this.context = context
    }

    constructor(id: Int, date: String, context: String) {
        this.id = id
        this.date = date.toString()
        this.context = context
    }
}

