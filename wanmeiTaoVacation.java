/* 
 * This program determines if a user is eligible to rent a vaction based on his/her age, the country he/she wants to visit
 * and if he/she have a valid passport. Then, the program calculate the cost of the rental depending on the number of days 
 * and people plus applicable discount. Finally, the program also calculate if the user needs to pay a deposit.
 * 
 * ASSUMPTIONS:
 * - the user enters only valid data
 * - since this is an online vacation form, the user can't answer with cash when paying. So the c of cash won't collide with the c of credit
 * 
 * BONUS:
 * - the program won't crash if he/she answers with a String instead of an int, char or double
 * - the program does not accept negative numbers
 * - if the user answer United States, US, USA, united states, us or usa the program will understand it's the United States.
 *   Same thing with France and Canada
 * 
 * Author: Wan Mei TAO, #2235016
 * 
 * Class 420-110, section 02
 */

//import a Scanner Object
import java.util.Scanner;

//public class called wanmeiTaoVacation
public class wanmeiTaoVacation
{
  //main method
  public static void main (String[] args)
  {
    //Welcoming message to the user
    System.out.println("Welcome to VacationStay! We will ask you a few questions");
    System.out.println("to make sure you're eligible and to calculate the cost of your vacation!");
    
    //new Scanner called reader
    Scanner reader = new Scanner (System.in);
    
    //initializing variables necessary to verify user's eligibility
    int age = 0;
    String userCountry, userValidPassport;
    char country, validPassport;
    
    //Question #1
    System.out.println("How old are you?");
    //BONUS: making sure the user enters an int
    while (reader.hasNextInt() == false)
    {  
      System.out.println("Please answer with an integer only"); 
      reader.next();
      reader.nextLine();    
    }   
    //assigning the value to age when an int has been entered
    age = reader.nextInt();
    reader.nextLine();
    
    //BONUS: making sure the user only enters a positive number
    if (age > 0)
    {
      //Question #2
      System.out.println("What country will you be visiting?");
      userCountry = reader.next();
      reader.nextLine();    
      //BONUS: converting the user's answer to a single lower case char
      userCountry = toLowercase(userCountry);
      country = userCountry.charAt(0);    
      //BONUS: making sure the user only answers with one of three options of country
      while (!(country == 'c' || country == 'u' || country == 'f'))
      {
        System.out.println("Sorry, we only have Canada, France or USA.");
        System.out.println("Please answer again with one of those three country.");
        System.out.println("What country will you be visiting?");
        userCountry = reader.next();
        reader.nextLine();
        //BONUS: converting the user's answer to a single lower case char
        userCountry = toLowercase(userCountry);
        country = userCountry.charAt(0);    
      }
      
      //Question #3
      System.out.println("Do you have a valid Canadian Passport?");
      userValidPassport = reader.next();
      reader.nextLine();
      //BONUS: converting the user's answer to a single lower case char
      userValidPassport = toLowercase(userValidPassport);
      validPassport = userValidPassport.charAt(0);    
      //BONUS: making sure the user only answers with yes or no
      while (!(validPassport == 'y' || validPassport == 'n'))
      {
        System.out.println("Sorry, please only answer by yes or no.");
        System.out.println("Do you have a valid Canadian Passport?");
        userValidPassport = reader.next();
        reader.nextLine();
        //BONUS: converting the user's answer to a single lower case char
        userValidPassport = toLowercase(userValidPassport);
        validPassport = userValidPassport.charAt(0);
      }    
      
      //calling isEligible method
      boolean eligibility = isEligible(age, country, validPassport);
      
      //the user is eligible, now it's time to calculate the cost
      if (eligibility == true)
      {
        //initializing variables necessary to calculate the rental cost
        int numDays, numPeople;
        String userRoomType;
        char roomType;
        
        //Question #4
        System.out.println("How many days will you be staying?");
        //BONUS: Making sure the user answers with integers without the program crashing
        while (reader.hasNextInt() == false)
        {  
          System.out.println("Please answer with an integer only"); 
          reader.next();
          reader.nextLine();    
        }   
        //assigning the value to numDays when an int has been entered
        numDays = reader.nextInt();
        reader.nextLine();
        
        //BONUS: making sure the user only enters a positive number
        if (numDays > 0)
        {
          //Question #5
          System.out.println("How many people in your party?");
          //BONUS: Making sure the user answers with integers without the program crashing
          while (reader.hasNextInt() == false)
          {  
            System.out.println("Please answer with an integer only"); 
            reader.next();
            reader.nextLine();    
          }   
          //assigning the value to numPeople when an int has been entered
          numPeople = reader.nextInt();
          reader.nextLine();
          
          //BONUS: making sure the user only enters a positive number
          if (numPeople > 0)
          {
            //Question #6
            System.out.println("What kind of room would you like?");
            userRoomType = reader.next();
            reader.nextLine();
            //BONUS: converting the user's answer to a single lower case char
            userRoomType = toLowercase(userRoomType);
            roomType = userRoomType.charAt(0);
            //BONUS: making sure the user only answers with one of the three types of rooms
            while (!(roomType == 'd' || roomType == 'k' || roomType == 'p'))
            {
              System.out.println("Sorry, we only have double, kingsize or penthouse.");
              System.out.println("Please answer again with one of those three room type.");
              System.out.println("What kind of room would you like?");
              userRoomType = reader.next();
              reader.nextLine();
              //BONUS: converting the user's answer to a single lower case char
              userRoomType = toLowercase(userRoomType);
              roomType = userRoomType.charAt(0);
            }
            
            //calling the calculateCost method
            double rentalCost;
            rentalCost = calculateCost(roomType, numDays, numPeople);
            //Printing the cost with only two decimals
            System.out.println("The cost is $" + String.format("%.2f", rentalCost));
            
            //initializing variables necessary to calculate the deposit
            String userPayType;
            char payType;
            
            //Question #7
            System.out.println("How will you be paying for the rental?");
            userPayType = reader.next();
            reader.nextLine();
            //BONUS: converting the user's answer to a single lower case char
            userPayType = toLowercase(userPayType);
            payType = userPayType.charAt(0);
            //BONUS: making sure the user only answers with debit or credit
            while (!(payType == 'd' || payType == 'c'))
            {
              System.out.println("Sorry, we only accept debit or credit.");
              System.out.println("Please answer again with one of those two payment method.");
              System.out.println("How will you be paying for the rental?");
              userPayType = reader.next();
              reader.nextLine();
              //BONUS: converting the user's answer to a single lower case char
              userPayType = toLowercase(userPayType);
              payType = userPayType.charAt(0);
            }
            
            //calling the calculateDeposit method
            double deposit;   
            deposit = calculateDeposit(rentalCost, payType);
            
            //Printing all the informations entered by the user as well as the cost of the rental
            System.out.println("The cost of the vacation package is $" + String.format("%.2f", rentalCost) + ".");
            System.out.println("The number of people staying is " + numPeople + ".");
            System.out.println("The duration of your stay is " + numDays + " days.");
            
            //Printing how much deposit the user needs to pay
            if (deposit == 0)
            {
              System.out.println("You are not required to pay a deposit.");
            }
            else
            {
              //Printing the amount with two decimals
              System.out.println("Please pay a deposit of $" + String.format("%.2f", deposit) + ".");
              if (deposit > rentalCost)
              {
                //In case the deposit is higher than the rental cost itself, the extra will be refunded
                System.out.println("Don't worry! Although we charge a minimum of $100 for deposit,");
                System.out.println("we will refund you the extra $" + String.format("%.2f", (deposit - rentalCost)) + " at the end of your vacation.");
              }
            }
            
            //Courtesy message to the user
            System.out.println("Enjoy your stay!");
            System.out.println("Brought to you by: Wan Mei TAO");   
          }
          else
          {
            //BONUS: The user entered a negative number, the program should stop 
            System.out.println("Sorry, we don't accept negative numbers as answer.");
          }        
        }
        else
        {
          //BONUS: The user entered a negative number, the program should stop 
          System.out.println("Sorry, we don't accept negative numbers as answer.");
        }
      }
      else
      {
        //The user is not eligible, the program should stop     
        System.out.println("Ineligible for vacation rental.");     
      }
    }
    else
    {
      //BONUS: The user entered a negative number, the program should stop 
      System.out.println("Sorry, we don't accept negative numbers as answer.");
    }
    
    //Closing the Scanner
    reader.close(); 
  }
  
// method to see if user is eligible depending on his passport, age and the destination country
  public static boolean isEligible (int age, char country, char validPassport)
  {
    //initializing this variable that will be returned at the end of this method
    boolean eligibility;
    
    //does the user have a valid passport?
    if (validPassport == 'y')
    {
      //Conditions to go to Canada
      if (country == 'c' && age > 21)
      {
        eligibility = true;
      }
      //Conditions to go to the US
      else if (country == 'u' && age > 18)
      {
        eligibility = true; 
      }
      //Conditions to go to France
      else if (country == 'f' && age > 25)
      {
        eligibility = true; 
      }
      //Does not meet requirements
      else
      {
        eligibility = false; 
      }
    }
    //Does not have a valid passport, but can still be eligible if in Canada and over 21 years old
    else if  (country == 'c' && age > 21)
    {
      eligibility = true;
    }
    //No valid passport and age/destination country is not valid
    else
    {
      eligibility = false; 
    }
    
    //return if user is eligible or not
    return eligibility;
  }
  
//method to calculate the cost depending on the number of days and people and the type of room
  public static double calculateCost (char roomType, int numDays, int numPeople)
  {
    //initializing the variable that contains the total cost of the rental
    double roomCost;
    
    //initializing constant variable for the price of each room type
    final int doubleCost, kingCost, penthouseCost;
    doubleCost    = 15;
    kingCost      = 20;
    penthouseCost = 30;
    
    //cost for double room type
    if (roomType == 'd')
    {
      roomCost = doubleCost;
    }
    //cost for kingsize room type
    else if (roomType == 'k')
    {
      roomCost = kingCost;
    }
    //cost for penthouse room type
    else
    {
      roomCost = penthouseCost;
    }
    
    //room cost times the number of days of the stay
    roomCost = roomCost * numDays;
    
    //calculating the cost of folding beds if applicable
    if (numPeople > 2)
    {
      roomCost = roomCost + (numPeople - 2)*20;
    } 
    
    //calculating the discount of the client if applicable
    if (numDays > 21)
    {
      roomCost = roomCost*0.8;
    }
    else if (numDays > 14)
    {
      roomCost = roomCost*0.85;
    }
    else if (numDays > 7)
    {
      roomCost = roomCost*0.9;
    }      
    
    //return the total rental cost
    return roomCost;
  }
  
//method to calculate the deposit if required depending on the payment type
  public static double calculateDeposit (double rentalCost, char payType)
  {
    //initializing this variable that will be returned at the end of this method
    double deposit;
    
    //no deposit if by credit
    if (payType == 'c')
    {
      deposit = 0;
    }
    //20% deposit or $100 deposit whichever is greater if by debit
    else
    {
      deposit = rentalCost * 0.2;
      if (deposit < 100)
      {
        deposit = 100;
      }
    }   
    
    //return the deposit amount
    return deposit;
  }
  
//method to lower case a String
  public static String toLowercase(String word)
  {
    //convert every char of the String to lower case
    word = word.toLowerCase();
    
    //return the lowered case String version
    return word;
  }
}
