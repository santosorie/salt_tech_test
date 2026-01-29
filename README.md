Hi this is app for salt tech interview

to install this pleaase install android studio for for IDE and Compiler

then clone this repo into local by type this command in your termina
git clone https://github.com/santosorie/salt_tech_test.git

sync gradle for library match (jetpack compose, retrofit)
run ready to run from emulator or debuggable device



===============

this app has ui state to handle
loading, success fetch api, error while fetching

the app started as loading state
then following the result either success, or error

the loading and success state is following figma design provided
the error just show simple text in the center of screen

the success state has
add to cart feature, and sort feature

for add to cart feature
user tap plus or minus to adjust the quantity

user will be displayed how much item in their cart,
and how much price they should pay

user cannot add more than product stock nor user cant reduce qty to minus

if there are item in the cart, user displayed enabled checkout button with reset button

when user tap reset button then the cart will be reseted

when user tap checkout button then there are dialog about the cart that has been succesed with detail item count and price (mock)
after user dismiss the checkout dialog, the cart will be reseted

when user tap sort button there will be sort dialog with 4 options

the sort default (as the api sort / product id)
the price sort (highes / lowest)
the name sort (a - z)

after user select the sort option then tap apply

the product list will be refreshed and sorted as option user selected before

in sort dialog if user tap outside of the dialog it will dismiss the dialog
