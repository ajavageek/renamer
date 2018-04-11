package ch.frankel.renamer

import com.vaadin.server.Sizeable.Unit.*
import com.vaadin.shared.ui.ContentMode.*
import com.vaadin.spring.annotation.*
import com.vaadin.ui.*
import com.vaadin.ui.Alignment.*
import com.vaadin.ui.Button.*
import org.vaadin.spring.events.EventBus

@SpringComponent
@UIScope
class MainScreen(eventBus: EventBus.UIEventBus) : CustomComponent() {

    private val title = Label("<strong>${RenamerUI.name}</strong>", HTML)
    private val grid = FilesGrid(eventBus)
    private val path = TextField("Path:").apply {
        addValueChangeListener {
            eventBus.publish(this@MainScreen, PathChangedEvent(it))
        }
        setWidth(100f, PERCENTAGE)
    }
    private val pattern = TextField("Pattern:").apply {
        addValueChangeListener {
            eventBus.publish(this@MainScreen, PatternChangedEvent(it))
        }
        setWidth(100f, PERCENTAGE)
    }
    private val replacement = TextField("Replace by:").apply {
        addValueChangeListener {
            eventBus.publish(this@MainScreen, ReplaceByChangedEvent(it))
        }
        setWidth(100f, PERCENTAGE)
    }
    private val rename = Button("Apply", ClickListener {
        eventBus.publish(this@MainScreen, RenameEvent())
    })

    init {
        val form = FormLayout(path, pattern, replacement).apply { setWidth(100f, PERCENTAGE) }
        compositionRoot = VerticalLayout(
                title,
                form,
                grid,
                rename
        ).apply {
            setSizeFull()
            setComponentAlignment(rename, BOTTOM_RIGHT)
        }
    }
}
