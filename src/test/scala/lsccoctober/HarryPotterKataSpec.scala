package lsccoctober

import org.scalatest.FunSpec
import org.scalatest.matchers.ShouldMatchers

class HarryPotterKataSpec extends FunSpec with ShouldMatchers {

  describe("when a customer orders nothing") {
    it("it should cost nothing") {
      val order = new Order
      order.value should equal (0)
    }
  }

  describe("when a customer orders the first book") {
    describe("when he orders a single copy") {
      it("should have no discount") {
        val order = new Order(Map("first" -> 1))
        order.value should equal (8.0)
      }
    }
    describe("when he orders a second copy of same book") {
      it("should have no discount") {
        val order = new Order(Map("first" -> 2))
        order.value should equal (16.0)
      }
    }
  }

  describe("when a customer orders two different books") {
    it ("it should be discounted 5%") {
      val order = new Order(Map("first" -> 1, "second" -> 1))
      order.value should equal (16 * 0.95)
    }
    it("should apply the 5% discount only to one copy for each different book") {
      val order = new Order(Map("first" -> 1, "second" -> 2))
      order.value should equal (16 * 0.95 + 8)
    }
  }

  describe("when a customer orders three different books") {
    describe("if there's only a copy ordered for each book") {
      it("should be discounted at 10%") {
        val order = new Order(Map("first" -> 1, "second" -> 1, "third" -> 1))
        order.value should equal (24 * 0.9)
      }
    }
    describe("when a customer orders three different books and the forth the same") {
      it("should be discounted at 10% on three and none on the other") {
        val order = new Order(Map("first" -> 1, "second" -> 2, "third" -> 1))
        order.value should equal ((24 * 0.9) + 8)
      }
    }
  }

  describe("when a customer orders four different books") {
    describe("and one of each book") {
      it("should be discounted at 20%") {
        val order = new Order(Map("first" -> 1, "second" -> 1, "third" -> 1, "forth" ->1))
        order.value should equal (32 * 0.8)
      }
    }
  }

  describe("the final offer") {
    it("should do what's written on the kata") {
      val order = new Order(Map("first" -> 2, "second" -> 2, "third" -> 2, "forth" -> 1, "fifth" -> 1))
      order.value should equal (51.20)
    }
  }
}