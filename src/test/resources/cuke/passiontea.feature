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
Feature: Shopping on Website
  As a person, I want to browse items on a website. So that I can purchase the items that I want.
  
  Background: 
  Given The correct web address

  @tag1
  Scenario: Browse the items available on the website
    When I I navigate to the 'Menu' Page
    Then I can browse a list of available items

  @tag2
  Scenario: Add an item to checkout
  	When I click the checkout button
  	Then I am taken to the checkout page
