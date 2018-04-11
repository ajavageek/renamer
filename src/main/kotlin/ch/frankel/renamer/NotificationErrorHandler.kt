package ch.frankel.renamer

import com.vaadin.server.*
import com.vaadin.ui.*

class NotificationErrorHandler : ErrorHandler {

    override fun error(event: ErrorEvent) {
        val throwable = event.throwable
        throwable.printStackTrace()
        Notification.show(throwable.message)
    }
}