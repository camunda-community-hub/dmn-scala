package org.camunda.dmn

import org.camunda.dmn.DmnEngine._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class DmnEngineConfigurationTest extends AnyFlatSpec with Matchers {

  private val engine = new DmnEngine(
    configuration = Configuration(
      escapeNamesWithSpaces = true,
      escapeNamesWithDashes = true
    ))

  private def decisionWithSpaces =
    getClass.getResourceAsStream("/config/decision_with_spaces.dmn")

  private def decisionWithDash =
    getClass.getResourceAsStream("/config/decision_with_dash.dmn")

  "The DMN engine" should "evaluate a decision with spaces" in {

    val result =
      engine.eval(decisionWithSpaces, "greeting", Map("name" -> "DMN"))

    result.isRight should be(true)
    result.map(_.value should be("Hello DMN"))
  }

  it should "evaluate a decision with dash" in {

    val result = engine.eval(decisionWithDash, "greeting", Map("name" -> "DMN"))

    result.isRight should be(true)
    result.map(_.value should be("Hello DMN"))
  }

}
