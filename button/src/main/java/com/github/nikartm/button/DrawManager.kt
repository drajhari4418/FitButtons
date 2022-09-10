package com.github.nikartm.button

import android.util.AttributeSet
import android.view.View
import com.github.nikartm.button.drawer.ContainerDrawer
import com.github.nikartm.button.drawer.DividerDrawer
import com.github.nikartm.button.drawer.IconDrawer
import com.github.nikartm.button.drawer.TextDrawer
import com.github.nikartm.button.model.FButton
import com.github.nikartm.button.model.IconPosition

/**
 * The [FitButton] drawer manager
 * @author Ivan V on 25.03.2019.
 * @version 1.0
 */
internal class DrawManager constructor(view: FitButton, attrs : AttributeSet?) {

    private val controller = AttributeController(view, attrs)

    private val container = ContainerDrawer(view, controller.button)
    private val icon = IconDrawer(view, controller.button)
    private val divider = DividerDrawer(view, controller.button)
    private val text = TextDrawer(view, controller.button)

    companion object Create {
        fun init(view: FitButton, attrs : AttributeSet): DrawManager {
            return DrawManager(view, attrs)
        }
    }

    /**
     * Draw customized [FitButton] on [View]
     */
    fun drawButton() {
        container.draw()
        defineDrawingOrder()
    }

    /**
     * Resize button measure
     * @param width new button width
     * @param height new button height
     */
    fun changeMeasure(width: Int, height: Int) {
        updateViews()
    }

    /**
     * @return [FButton] with attrs or default values
     */
    fun getButton() : FButton {
        return controller.button
    }

    private fun defineDrawingOrder() {
        val icPosition: IconPosition = getButton().iconPosition
        if (icon.isReady()) {
            if (IconPosition.LEFT == icPosition || IconPosition.TOP == icPosition) {
                icon.draw()
                if (divider.isReady()) {
                    divider.draw()
                }
                if (text.isReady()) {
                    text.draw()
                }
            } else {
                if (text.isReady()) {
                    text.draw()
                }
                if (divider.isReady()) {
                    divider.draw()
                }
                icon.draw()
            }
        } else{
            if (text.isReady()) {
                text.draw()
            }
        }
    }

    /**
     * Update the button layouts and it elements
     */
    fun updateViews() {
        container.updateLayout()
        text.updateLayout()
        icon.updateLayout()
        divider.updateLayout()
    }

}
