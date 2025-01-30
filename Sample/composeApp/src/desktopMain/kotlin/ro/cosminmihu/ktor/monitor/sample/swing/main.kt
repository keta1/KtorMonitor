package ro.cosminmihu.ktor.monitor.sample.swing

import ro.cosminmihu.ktor.monitor.KtorMonitorPanel
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.BorderFactory
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.SwingConstants
import javax.swing.SwingUtilities

/**
 * Swing Sample how to use [KtorMonitorPanel].
 */
fun main() {
    SwingUtilities.invokeLater {
        val frame = JFrame("Ktor Monitor Sample")
        frame.minimumSize = Dimension(800, 600)
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE

        val panel = JPanel()
        panel.layout = BorderLayout()

        val text = JLabel("Sample how to use Ktor Monitor in Swing", SwingConstants.CENTER)
        text.border = BorderFactory.createEmptyBorder(10, 10, 10, 10)
        panel.add(text, BorderLayout.NORTH)

        panel.add(KtorMonitorPanel, BorderLayout.CENTER)

        frame.add(panel)
        frame.pack()
        frame.isVisible = true
    }
}