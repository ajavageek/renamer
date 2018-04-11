package ch.frankel.renamer

import ch.frankel.renamer.RenamerUI.Companion.name
import com.vaadin.annotations.*
import com.vaadin.server.*
import com.vaadin.spring.annotation.*
import com.vaadin.ui.*
import org.springframework.boot.*
import org.springframework.boot.autoconfigure.*
import org.springframework.context.annotation.*
import org.vaadin.spring.events.config.EventBusConfiguration

@SpringBootApplication
@Import(EventBusConfiguration::class)
class RenamerApp

fun main(args: Array<String>) {
    SpringApplication.run(RenamerApp::class.java)
}

@SpringUI
@Title(name)
class RenamerUI(private val screen: MainScreen) : UI() {

    override fun init(request: VaadinRequest) {
        content = screen
        errorHandler = NotificationErrorHandler()
    }

    companion object {
        const val name = "Batch File Renamer"
    }
}
