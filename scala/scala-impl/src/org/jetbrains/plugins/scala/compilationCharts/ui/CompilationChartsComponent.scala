package org.jetbrains.plugins.scala.compilationCharts.ui

import com.intellij.openapi.project.Project
import com.intellij.ui.components.{JBPanel, JBScrollPane}
import com.intellij.util.ui.{JBUI, UIUtil}
import javax.swing.{JViewport, ScrollPaneConstants}
import net.miginfocom.swing.MigLayout

class CompilationChartsComponent(project: Project)
  extends JBPanel(new MigLayout("gap rel 0, ins 0")) {

  locally {
    val panelColor = UIUtil.getBgFillColor(this)
    val diagramsComponent = new DiagramsComponent(
      project = project,
      panelColor = panelColor,
      defaultZoom = ActionPanel.defaultZoom
    )
    val diagramsScrollPane = new JBScrollPane(diagramsComponent)
    diagramsScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS)
    diagramsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER)
    diagramsScrollPane.setBorder(JBUI.Borders.empty)
    diagramsScrollPane.getViewport.setScrollMode(JViewport.SIMPLE_SCROLL_MODE)
    val actionPanel = new ActionPanel(diagramsComponent.setZoom, panelColor)

    val verticalLeftPanel = new VerticalLeftPanel(project,
      panelColor, actionPanel.getHeight, diagramsComponent.diagramsInfo)
    val verticalRightPanel = new VerticalRightPanel(
      panelColor, actionPanel.getHeight, diagramsComponent.diagramsInfo)

    // TODO layout problems
    add(verticalRightPanel, "dock east")
    add(verticalLeftPanel, "dock west")
    add(actionPanel, "al right, wrap")
    add(diagramsScrollPane, "grow, push, span")
  }
}
