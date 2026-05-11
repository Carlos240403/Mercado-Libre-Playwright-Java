@MercadoLibre
Feature: Product search in Mercado Libre

    To validate product search filters
    As a user
    I want to search and filter products correctly

    Background:
        Given The user navigates to Mercado Libre
        And The user selects Mexico country

Scenario: Search and filter PlayStation 5 products

    When The user searches for "playstation 5"
    And The user filters by condition "Nuevo"

# Nota sobre el filtro de ubicación:
    #And The user filters by location Local
        # The shipping location filter in Mercado Libre consistently triggers
        # captcha protection during automated execution. 
        # To keep the test flow stable and reproducible, the remaining filters 
        # and validations were completed successfully.

        
    #And The user sorts results by lowest price
    Then The user prints the first 5 product names and prices
    And The user sorts results by lowest price
