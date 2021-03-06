package org.jetbrains.plugins.scala.testingSupport.scalatest.staticStringTest

import org.jetbrains.plugins.scala.testingSupport.scalatest.ScalaTestTestCase

trait FlatSpecStaticStringTest extends ScalaTestTestCase {

  val flatSpecClassName = "FlatSpecStringTest"
  val flatSpecFileName = flatSpecClassName + ".scala"

  addSourceFile(flatSpecFileName,
    s"""import org.scalatest._
       |
       |class $flatSpecClassName extends FlatSpec with GivenWhenThen {
       |
       | val nameFragment = "work with consts"
       | val sumConst = " of consts"
       | "Static strings" should "accept" + " sums" in {
       | }
       |
       | it should nameFragment in {
       | }
       |
       | it should "work with sums" + sumConst in {
       | }
       |
       | it should "not accept this: " + System.currentTimeMillis() in {
       | }
       |}
    """.stripMargin.trim()
  )

  def testFlatSpecSum(): Unit = {
    assertConfigAndSettings(createTestCaretLocation(6, 7, flatSpecFileName), flatSpecClassName,
      "Static strings should accept sums")
  }

  def testFlatSpecVal(): Unit = {
    assertConfigAndSettings(createTestCaretLocation(9, 7, flatSpecFileName), flatSpecClassName,
      "Static strings should work with consts")
  }

  def testFlatSpecValSum(): Unit = {
    assertConfigAndSettings(createTestCaretLocation(12, 7, flatSpecFileName), flatSpecClassName,
      "Static strings should work with sums of consts")
  }

  def testFlatSpecNonConst(): Unit = {
    assertConfigAndSettings(createTestCaretLocation(15, 7, flatSpecFileName), flatSpecClassName,
      "Static strings should accept sums",
      "Static strings should work with consts",
      "Static strings should work with sums of consts")
  }
}
