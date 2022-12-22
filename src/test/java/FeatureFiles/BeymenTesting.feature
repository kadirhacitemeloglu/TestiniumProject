Feature: Beymen Website Testing
  Scenario: Adding and removing products

    Given Navigate to Beymen Website and Verify that you are on the homepage
    When Search for sort with ApachePOI and delete this word
    And Search for gomlek with ApachePOI and press enter button
    And Choose random product
    And Write the product informations to txt file
    And Add to cart product which choose
    And Compare prices
    And Add one more item to basket
    Then Delete all items from basket

