package com.company;

import java.util.ArrayList;
import java.util.Scanner;

/*1.Implement the master class MobilePhone, that holds the ArrayList of Contacts, with the following attributes:
        Two fields, a String called myNumber and an ArrayList of type Contact called myContacts.
        A constructor that takes a String (the phone number) and initialises myNumber and instantiates myContacts.
        And seven methods, they are (their functions are in their names):
            addNewContact(), has one parameter of type Contact and returns a boolean. Returns true if the contact doesn't exists, or false if the contact already exists.
            updateContact(), has two parameters of type Contact (the old contact that will be updated with the new contact) and returns a boolean. Returns true if the contact exists and was updated successfully, or false if the contact doesn't exists.
            removeContact(), has one parameter of type Contact and returns a boolean. Returns true if the contact exists and was removed successfully, or false if the contact doesn't exists.
            findContact(), has one parameter of type Contact and returns an int. The returned value is it's position in the ArrayList, it will either be -1 (doesn't exists) or a value greater than or equal to 0 (does exists).
            findContact(), same as above, only it has one parameter of type String.
            queryContact(), has one parameter of type String and returns a Contact. Use the String to search for the name and then return the Contact. Return null otherwise.
            printContacts(), has no parameters and doesn't return anything. Print the contacts in the following format:
        Contact List:
        1. Bob -> 31415926
        2. Alice -> 16180339
        3. Tom -> 11235813
        4. Jane -> 23571113
*/
public class MobilePhone {
    private String myNumber;
    private ArrayList<Contact> myContacts;
    private static Scanner scanner = new Scanner(System.in);

    public MobilePhone(String myNumber){
        this.myNumber = myNumber;
        this.myContacts = new ArrayList<Contact>();
    }
    public boolean addNewContact(Contact contact){
        int position = findContact(contact);
        if(position>=0){
            //Contact already exists
            return false;
        }
        return myContacts.add(contact);
    }
    public boolean updateContact(Contact oldContact, Contact newContact){
        int position = findContact(oldContact);
        if(position>=0){
            myContacts.set(position,newContact);
            return true;
        }
        return false;
    }
    public boolean removeContact(Contact contact){
        int position = findContact(contact);
        if(position>=0){
            myContacts.remove(contact);
            return true;
        }
        return false;
    }
    private int findContact(Contact contact){
        if(myContacts.contains(contact)){
            return myContacts.indexOf(contact);
        }
        return -1;
    }
    private int findContact(String contactName){
        for(int i=0;i<myContacts.size();i++){
            if(myContacts.get(i).getName().equals(contactName)){
                return i;
            }
        }
        return -1;
    }
    public Contact queryContact(String contactName){
        int position = findContact(contactName);
        if(position>=0){
            return myContacts.get(position);
        }
        return null;
    }
    public void printContacts(){
        System.out.println("Contact List:\n");
        for(int i=0;i<myContacts.size();i++){
            System.out.println((i+1) + ". " + myContacts.get(i).getName() + " -> " + myContacts.get(i).getPhoneNumber());
        }
    }
    public void turnOnPhone() {
        boolean quit = false;
        System.out.println("The phone is turning on to the number ");
        while (!quit) {//while quit is true
            System.out.println("Welcome to the menu, select one option \n" +
                    "1 : To add a contact\n" +
                    "2 : To update a contact\n" +
                    "3 : To remove a contact\n" +
                    "4 : To query a contact\n" +
                    "5 : To print contact list\n" +
                    "6 : To quit!\n");
            int value = scanner.nextInt();
            scanner.nextLine();
            switch (value) {
                case 1:
                    System.out.println("Enter a new contact name:");
                    System.out.print("Edit contact's name :");
                    String name = scanner.nextLine();
                    System.out.print("Edit contact's number :");
                    String phoneNumber = scanner.nextLine();
                    if(findContact(name) >= 0){
                        System.out.println("contact already exist");
                        break;
                    }
                    Contact contact = Contact.createContact(name, phoneNumber);
                    addNewContact(contact);
                    break;
                case 2:
                    System.out.println("Enter old contact name:");
                    String oldContact = scanner.nextLine();
                    if(queryContact(oldContact) == null){
                        System.out.println("contact doesn't exist");
                        break;
                    }
                    System.out.print("Edit contact's name :");
                    name = scanner.nextLine();
                    System.out.print("Edit contact's number :");
                    phoneNumber = scanner.nextLine();
                    contact = Contact.createContact(name, phoneNumber);
                    updateContact(this.myContacts.get(findContact(oldContact)), contact);
                    break;
                case 3:
                    System.out.println("Enter contact to delete :");
                    oldContact = scanner.nextLine();
                    if (queryContact(oldContact) == null) {
                        System.out.println(oldContact + " doesn't exist");
                        break;
                    }
                    removeContact(this.myContacts.get(findContact(oldContact)));
                    break;
                case 4:
                    System.out.println("Enter contact to query :");
                    oldContact = scanner.nextLine();
                    if (queryContact(oldContact) == null) {
                        System.out.println(oldContact + " doesn't exist");
                        break;
                    }
                    queryContact(oldContact);
                    break;
                case 5:
                    printContacts();
                    break;
                case 6:
                    quit = true;
                    break;
                case 7:
                    System.out.println(this.myContacts.size());
                    break;
                case 8:
                    System.out.print("Edit contact's name :");
                    name = scanner.nextLine();
                    System.out.println(this.myContacts.get(findContact(name)));
                    break;
                case 9:
                    System.out.print("Edit contact's name :");
                    name = scanner.nextLine();
                    System.out.println(findContact(name));
                    System.out.println(queryContact(name));
                    break;

            }
        }
    }
}
