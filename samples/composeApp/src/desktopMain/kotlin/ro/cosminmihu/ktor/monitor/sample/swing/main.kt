package ro.cosminmihu.ktor.monitor.sample.swing

import ro.cosminmihu.ktor.monitor.KtorMonitorPanel
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.JFrame
import javax.swing.SwingUtilities

/**
 * Swing Sample how to use [KtorMonitorPanel].
 */
fun main() {
    SwingUtilities.invokeLater {
        val frame = JFrame("Ktor Monitor Sample")
        frame.minimumSize = Dimension(800, 600)
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE

        frame.add(KtorMonitorPanel, BorderLayout.CENTER)

        frame.pack()
        frame.isVisible = true
    }
}