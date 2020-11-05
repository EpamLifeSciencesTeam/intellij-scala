package org.jetbrains.plugins.scala.compilationCharts.ui

import java.awt.{Color, Graphics, Graphics2D}

import com.intellij.ui.components.JBPanel
import org.jetbrains.plugins.scala.compilationCharts.ui.DiagramsComponent.DiagramsInfo

class VerticalRightPanel(color: Color,
                         getActionPanelHeight: => Int,
                         getDiagramsInfo: => DiagramsInfo)
  extends JBPanel {

  setBackground(color)

  override def paintComponent(g: Graphics): Unit =
    Utils.printVerticalPanelBorder(g.asInstanceOf[Graphics2D], getActionPanelHeight, getDiagramsInfo, Side.West)
}
