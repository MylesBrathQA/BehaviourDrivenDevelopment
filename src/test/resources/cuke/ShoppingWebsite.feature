#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Buying clothes from website
  I want to be able to browse this website and purchase items of clothing
  
  @tag1
  Scenario: Searching for a skirt
    Given I am on the correct website
    When I search for a skirt
    Then I will receive a list of items that match my description

  @tag2
  Scenario: Checking out an item
  	Given I have an item in my basket
  	When I proceed to checkout
  	And fill in my customer details
  	Then I should be able to purchase the item
