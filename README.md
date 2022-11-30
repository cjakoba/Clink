# Final Project

Authors: Anshu Adhikari, Carlos Ortega, Christian Jakob

For this Project, we decided to create an app to selll pre-packaged moctail drinks that custoers can buy from the app with decription on what the drink is and what ingridients are used. 

Features 
Retailers(Admin) : Admin class extends JPanel and used UI componets. We have Arraylist of mocktails that the aadmin has present in the discovery button on the app. Lists the drinks in the menu text file. Admin is allowed to remove Drink, edit drink, edit discription and edit price , save menu. We set this to grid layout and styled all the button on the app. these methods allows the admin to edit current menu , change name, price and other things on item like price and decription. 


Customer: Jpanel for Firstname, lastname, address, email, phone number, setters for each of these private strings , text fields using jpanel, deliver to address button that lets the customer click on the deliver to adress and proceed to receive the recepit. We used the paint component in this class that needed to draw something on JPanel other than drawing the background color. This method already exists in a JPanel class so that we need to use the super declaration to add something to this method and takes Graphics objects as parameters.

Dicovery : we use the paint component in the design of our discovery aswell just like we do in the customer class. this class conntains all components that links to the GUI development for this project 


