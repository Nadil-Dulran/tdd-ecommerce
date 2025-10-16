# 🧪 tdd-ecommerce  
**Test-Driven Development by building small, testable slices of an e-commerce platform**

---

## 📘 Overview
This project demonstrates **Test-Driven Development (TDD)** by incrementally building an e-commerce platform in **Java**.  
Each feature is developed following the **Red → Green → Refactor** cycle, supported by JUnit and Mockito for testing.

> 💡 The goal is to learn TDD by writing tests first, implementing minimal code to pass those tests, and refactoring confidently while keeping all tests green.

---

## ⚙️ Project Setup

### 🛠 Tech Stack
- **Language:** Java 17+
- **Build Tool:** Maven 3.9+
- **Testing Framework:** JUnit 5 (Jupiter)
- **Mocking Library:** Mockito 5

### 📁 Project Structure
tdd-ecommerce/
├─ pom.xml
├─ src/
│ ├─ main/java/com/example/ # Production code
│ └─ test/java/com/example/ # Unit tests
└─ README.md


### 💻 Basic Maven Commands
| Purpose | Command |
|----------|----------|
| Run all tests | `mvn test` |
| Run a single test class | `mvn -Dtest=ClassNameTest test` |
| Run quietly (short output) | `mvn -q test` |

---

## 🔁 TDD Cycle — Red → Green → Refactor

For every requirement (A–F):

1. **RED** — Write a test for the new behaviour and run it to see it fail.  
2. **GREEN** — Write the smallest amount of code to make the test pass.  
3. **REFACTOR** — Clean up the code (naming, duplication, structure) while ensuring all tests stay green.

> For each requirement, record three artifacts:  
> • Failing test + output  
> • Passing implementation + output  
> • Refactored code + passing output  

---

## 🧩 Requirements Overview

### 🅰️ **Requirement A — Product & Catalog**
Implement basic product and catalog management.

**Classes:**  
- `Product`: holds SKU, name, and price.  
- `Catalog`: stores and retrieves products by SKU.

**TDD Flow:**  
- **RED:** Test for adding and retrieving products.  
- **GREEN:** Implement minimal class definitions.  
- **REFACTOR:** Clean up constructors, add validations.

---

### 🅱️ **Requirement B — Shopping Cart (add/remove/total)**
Create a shopping cart that:
- Adds products and quantities.
- Removes items.
- Calculates total price.

**TDD Flow:**  
- **RED:** Test adding and totaling items.  
- **GREEN:** Implement simple map-based cart.  
- **REFACTOR:** Simplify loops and add safety checks.

---

### 🅲 **Requirement C — Inventory Reservation**
Integrate inventory validation before adding to cart.

**Rules:**  
- Check available stock via `inventory.getAvailable(sku)`.  
- If requested quantity > available, throw `IllegalStateException`.

**TDD Flow:**  
- **RED:** Write test mocking `InventoryService`.  
- **GREEN:** Call `inventory.getAvailable()` before adding.  
- **REFACTOR:** Add a Null Object for inventory (no null checks needed).

---

### 🅳 **Requirement D — Discount Rules**
Add flexible discount mechanisms using a **strategy interface**.

**Concept:**  
Define `DiscountRule` interface → Implement multiple discount types.

**Examples:**  
- **BulkDiscountRule:** 10% off when quantity ≥ 10.  
- **OrderDiscountRule:** 5% off when total ≥ 1000.

**TDD Flow:**  
- **RED:** Write tests for each rule type.  
- **GREEN:** Implement minimal logic to pass tests.  
- **REFACTOR:** Clean up with polymorphism and add docstrings.

---

### 🅴 **Requirement E — Checkout Flow + Payment Gateway**
Implement a simple checkout flow that:
1. Validates that all items are available in inventory.  
2. Computes total price after applying discounts.  
3. Charges the customer via `PaymentGateway.charge(amount, token)`.  
4. Creates an order only if the payment succeeds.

**Classes Introduced:**  
- `CheckoutService`  
- `PaymentGateway` (interface)  
- `Order`, `OrderRepository`, `CheckoutResult`

**TDD Flow:**  
- **RED:** Test both success and payment failure paths using mocked gateway.  
- **GREEN:** Implement validation, discount application, and order creation.  
- **REFACTOR:** Simplify and add overloads for cases without discounts.

---

### 🅵 **Requirement F — Order History & Persistence**
Add basic persistence and retrieval of past orders.

**Rules:**  
- `OrderRepository` supports saving and listing orders.  
- `OrderHistoryService` fetches orders by user or all orders.  
- Use `InMemoryOrderRepository` for testing.

**TDD Flow:**  
- **RED:** Test retrieval by user and all orders.  
- **GREEN:** Implement in-memory storage.  
- **REFACTOR:** Generalize repository interface and improve naming.

---

## 🧠 Learning Outcomes
- Practiced **writing tests first** before implementing code.  
- Gained **confidence refactoring** due to complete test coverage.  
- Designed **small, testable units** of a larger system.  
- Applied **mocking (Mockito)** to isolate dependencies.  
- Achieved clean, modular, and extensible architecture.

---

> “Write the test first. Code later. Refactor always.”  
