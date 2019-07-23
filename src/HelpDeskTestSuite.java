//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           HelpDesk
// Files:           SupportTicket.java, HelpDesk.java, HelpDeskTestSuite.java,
//                  HelpDeskInterface.java
// Course:          CS 300, Semester 2, 2019
//
// Author:          Hunter Abraham
// Email:           hjabraham@wisc.edu
// Lecturer's Name: Gary Dahl
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    (name of your pair programming partner)
// Partner Email:   (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here.  Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons:         (identify each person and describe their help in detail)
// Online Sources:  (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.Arrays;

/**
 * Tests for HelpDesk.java and SupportTicket.java classes
 *
 * @author Hunter Abraham
 */
public class HelpDeskTestSuite extends HelpDesk {


  /**
   * Tests the HelpDesk Class' checkNewTicket() method.
   *
   * @return true if tests pass, false otherwise.
   */
  public static boolean testHelpDeskCreateCheckNewTicket() {
    HelpDesk desk = new HelpDesk(10);
    desk.createNewTicket("HI"); // Add element to the PriorityQueue

    if (!desk.checkNextTicket().equals("HI")) { // check element, if incorrect return false
      System.out.println("Error: desk instance was supposed to return \"HI\" but instead returned: "
          + desk.checkNextTicket());
      return false;
    }
    desk.createNewTicket("Howdy"); // Create several more tickets
    desk.createNewTicket("as;ldkfhghp9usfvn");
    desk.createNewTicket("AK");
    // If the wrong ticket is in priority, return false.
    if (!desk.checkNextTicket().equals("as;ldkfhghp9usfvn")) {
      System.out.println("Error: desk was supposed to return \"as;ldkfhghp9usfvn\" but instead " +
          "returned: " + desk.checkNextTicket());
      return false;
    }

    desk.createNewTicket("1k"); // Repeat process again
    desk.createNewTicket("2k");
    if (!desk.checkNextTicket().equals("as;ldkfhghp9usfvn")) {
      System.out.println("Error: desk returned: " +
          desk.checkNextTicket());
      return false;
    }

    return true; // If all tests pass, return true
  }


  /**
   * tests the HelpDesk class' closeNextTicket() method
   *
   * @return true if tests pass, false otherwise
   */
  public static boolean testHelpDeskCloseNextTicket() {
    HelpDesk desk = new HelpDesk(10); // Create and fill new HelpDesk
    desk.createNewTicket("HI"); // Create a ticket and then close it
    String s = desk.closeNextTicket();
    if (!s.equals("HI")) { // If the wrong ticket was closed, return false
      System.out.println("Error: desk was supposed to remove \"HI\" but instead removed: " + s);
      return false;
    }
    desk.createNewTicket("Program"); // Add two more tickets to the array
    desk.createNewTicket("Computer");
    s = desk.closeNextTicket();
    if (!s.equals("Computer")) { // If the wrong ticket was closed, return false
      System.out.println("Error: desk was supposed to remove \"Program\" but instead removed: " +
          s);
      return false;
    }

    return true; // Otherwise, return true
  }

  /**
   * tests the HelpDesk class' closeNextTicket() method using an empty string
   *
   * @return true if the test passes, false otherwise
   */
  public static boolean testHelpDeskCloseNextTicketEmptyStr() {
    HelpDesk desk = new HelpDesk(10);
    desk.createNewTicket(""); // Create a ticket with an empty string
    desk.createNewTicket("asldkfa"); // Fill rest of queue
    desk.createNewTicket("laksdjfa;slkj");
    desk.closeNextTicket(); // Empty rest of queue
    desk.closeNextTicket();
    String s = desk.closeNextTicket();
    if (!s.equals("")) { // If the wrong ticket is returned, return false
      System.out.println("Error: desk should have returned an empty string, but instead returned: "
          + s);
      return false;
    }
    return true; // Otherwise, return true
  }


  /**
   * Tests that the HelpDesk class' createNewTicket() method throws a NullPointerException if
   * the message being passed as an argument is null.
   *
   * @return true if the test passes, false otherwise.
   */
  public static boolean testHelpDeskCreateNewTicketNullStr() {
    HelpDesk desk = new HelpDesk(10);
    try {
      desk.createNewTicket(null);
    } catch (NullPointerException e) { // If correct exception is caught, return true
      return true;
    } catch (Exception e) { // Otherwise, catch wrong type of exception and return false
      System.out.println("Error: Wrong type of exception thrown. Exception Type: "
          + e.getClass());
      return false;
    }

    // If no exception was thrown, return false.
    System.out.println("Error: No exception was thrown upon a null argument.");
    return false;
  }

  /**
   * tests that the HelpDeskClass' checkNextTicket() method throws an IllegalStateException if
   * called on an empty list
   *
   * @return true if the test passes, false otherwise
   */
  public static boolean testHelpDeskCheckNextTicketEmptyList() {
    HelpDesk desk = new HelpDesk(4); // Create help desk
    try {
      desk.checkNextTicket();
    } catch (IllegalStateException e) { // Check to see that it catches the correct Exception
      return true;
    } catch (Exception e) { // If it catches the wrong exception print error and return false.
      System.out.println("Wrong type of exception thrown. Exception thrown: " + e.getClass());
      return false;
    }
    // If no exception is thrown from the method, return false
    System.out.println("Error: no exception was thrown for an empty HelpDesk");
    return false;
  }


  /**
   * Checks that the HelpDesk class' closeNextTicket() method throws an IllegalStateException if
   * called on an empty array.
   *
   * @return true if the tests pass, false otherwise.
   */
  public static boolean testHelpDeskCloseNextTicketEmptyList() {
    HelpDesk desk = new HelpDesk(10); // Create a new helpDesk
    try { // try to call closeNextTicket() on an empty array
      desk.closeNextTicket();
    } catch (IllegalStateException e) { // Catch the IllegalStateException
      return true; // return true, test passed
    } catch (Exception e) { // If a different Exception is caught, return false
      System.out.println("Error: wrong type of exception thrown (" + e.getClass() + ")");
      return false;
    }
    // If no Exception is thrown, return false.
    System.out.println("Error: no exception was thrown");
    return false;
  }


  /**
   * Tests that the HelpDesk's closeNextTicket() method works if a large quantity of tickets
   * are added to it
   *
   * @return true if the tests pass, false otherwise
   */
  public static boolean testHelpDeskCloseNextTicketExtra() {
    // Create desk and add tickets to it
    HelpDesk desk = new HelpDesk(100);
    desk.createNewTicket("kla;sdjf;");
    desk.createNewTicket("kfldsfa");
    desk.createNewTicket("asldkjf");
    desk.createNewTicket(",xnv");
    desk.createNewTicket("asdlkjf");
    desk.createNewTicket("xznbv");
    desk.createNewTicket("asdlfj");
    desk.createNewTicket("zx.,mbnsf");
    desk.createNewTicket("asjfnlx");
    desk.createNewTicket("sfldjkbnls");
    desk.createNewTicket("sf al wkl v");
    desk.createNewTicket("aljnsdflj");
    desk.createNewTicket("aslnals");
    desk.createNewTicket("asljn");
    desk.createNewTicket("asjnalsv");
    desk.createNewTicket("asdflkja;");
    desk.createNewTicket("zx,cmns");
    desk.createNewTicket("asdfjknalsknlskfbhsdlfbuiknjvahblva");
    desk.createNewTicket("sdjnflasj");
    desk.createNewTicket("asdjfnasj");
    desk.createNewTicket("asdfjnas.");
    desk.createNewTicket("asjvnas");
    desk.createNewTicket("asdnlva");
    desk.createNewTicket("asdljnva");
    desk.createNewTicket("sldnva");
    desk.createNewTicket("kfldsfa");
    desk.createNewTicket("asdfjla");
    desk.createNewTicket("asdjjnf");
    desk.createNewTicket("kfldsfa");
    desk.createNewTicket("sadfjnl as");
    desk.createNewTicket("asdjfn");
    desk.createNewTicket("kfldsfa");
    desk.createNewTicket("asd;fja");
    desk.createNewTicket("kfldsfa");
    desk.createNewTicket("sadkjf");
    desk.createNewTicket("asdjfn");
    desk.createNewTicket("asdjf");
    desk.createNewTicket("asdkva ;sd");
    desk.createNewTicket("vasdljvnas;djnva");
    desk.createNewTicket("asdjkvansv");
    desk.createNewTicket("bsdfjknalsknlskfbhsdlfbuiknjvahblva");
    desk.createNewTicket("sdmnfa");
    desk.createNewTicket("erjkasfk");
    desk.createNewTicket("asd;fi;sofnjkasjkhsdfjkhf");
    desk.createNewTicket("asdjkfna");
    desk.createNewTicket("asdkjnas");
    desk.createNewTicket("asdfnjas");

    // find top two strings being removes
    String s = desk.closeNextTicket();
    String s2 = desk.closeNextTicket();
    // If either string is the wrong string, print error and return false
    if (!s.equals("bsdfjknalsknlskfbhsdlfbuiknjvahblva")) {
      System.out.println("Error: HelpDesk returned wrong string: " + s);
      return false;
    }

    if (!s2.equals("asdfjknalsknlskfbhsdlfbuiknjvahblva")) {
      System.out.println("Error: HelpDesk returned wrong string: " + s2);
      return false;
    }
    // If tests pass, return true
    return true;
  }

  /**
   * Tests that the HelpDesk class' checkNextTicket() method works if a large quantity of strings
   * are added to it.
   *
   * @return true if tests pass, false otherwise
   */
  public static boolean testHelpDeskCheckNextTicketExtra() {
    // Create HelpDesk and add elements
    HelpDesk desk = new HelpDesk(100);
    desk.createNewTicket("kla;sdjf;");
    desk.createNewTicket("kfldsfa");
    desk.createNewTicket("asldkjf");
    desk.createNewTicket(",xnv");
    desk.createNewTicket("asdlkjf");
    desk.createNewTicket("xznbv");
    desk.createNewTicket("asdlfj");
    desk.createNewTicket("zx.,mbnsf");
    desk.createNewTicket("asjfnlx");
    desk.createNewTicket("sfldjkbnls");
    desk.createNewTicket("sf al wkl v");
    desk.createNewTicket("aljnsdflj");
    desk.createNewTicket("aslnals");
    desk.createNewTicket("asljn");
    desk.createNewTicket("asjnalsv");
    desk.createNewTicket("asdflkja;");
    desk.createNewTicket("zx,cmns");
    desk.createNewTicket("asdfjknalsknlskfbhsdlfbuiknjvahblva");
    desk.createNewTicket("sdjnflasj");
    desk.createNewTicket("asdjfnasj");
    desk.createNewTicket("asdfjnas.");
    desk.createNewTicket("asjvnas");
    desk.createNewTicket("asdnlva");
    desk.createNewTicket("asdljnva");
    desk.createNewTicket("sldnva");
    desk.createNewTicket("kfldsfa");
    desk.createNewTicket("asdfjla");
    desk.createNewTicket("asdjjnf");
    desk.createNewTicket("kfldsfa");
    desk.createNewTicket("sadfjnl as");
    desk.createNewTicket("asdjfn");
    desk.createNewTicket("kfldsfa");
    desk.createNewTicket("asd;fja");
    desk.createNewTicket("kfldsfa");
    desk.createNewTicket("sadkjf");
    desk.createNewTicket("asdjfn");
    desk.createNewTicket("asdjf");
    desk.createNewTicket("asdkva ;sd");
    desk.createNewTicket("vasdljvnas;djnva");
    desk.createNewTicket("asdjkvansv");
    desk.createNewTicket("bsdfjknalsknlskfbhsdlfbuiknjvahblva");
    desk.createNewTicket("sdmnfa");
    desk.createNewTicket("erjkasfk");
    desk.createNewTicket("asd;fi;sofn");
    desk.createNewTicket("asdjkfna");
    desk.createNewTicket("asdkjnas");
    desk.createNewTicket("asdfnjas");

    String s = desk.checkNextTicket(); // Store value being peeked
    if (!s.equals("bsdfjknalsknlskfbhsdlfbuiknjvahblva")) { // If it's the wrong value,
      System.out.println("Error: HelpDesk returned wrong string: " + s); // Print error
      return false; // Return false
    }
    return true; // return true if the tests pass

  }

  /**
   * Tests HelpDesk class' propogateUp() method.
   *
   * @return true if the test passes, false otherwise
   */
  public static boolean testHelpDeskPropagateUp() {
    HelpDesk desk = new HelpDesk(5); // Create new helpDesk
    SupportTicket ticket = new SupportTicket("dhdjfis"); // Create ticket
    desk.array[0] = new SupportTicket("kdksdfddkd"); // Fill desk
    desk.array[1] = new SupportTicket("sdklf");
    desk.array[2] = new SupportTicket("");
    desk.array[3] = new SupportTicket("klsd");
    desk.array[4] = ticket;
    desk.size = 5;
    desk.propagateUp(4);
    if (!desk.array[1].equals(ticket)) { // If the ticket is not propagated correctly, return false
      System.out.println("Error: HelpDesk propagateUp() returned the wrong array: "
          + Arrays.toString(desk.array));
      return false;
    }
    ticket = new SupportTicket("aaaaaaaaaa"); // 10
    desk.array[0] = new SupportTicket("aaaaaaaa"); // Fill desk (8)
    desk.array[1] = new SupportTicket("aaa"); // 3
    desk.array[2] = new SupportTicket(""); // 0
    desk.array[3] = new SupportTicket("klsd"); // 4
    desk.array[4] = ticket;
    desk.size = 5;
    desk.propagateUp(4);
    if (!desk.array[0].equals(ticket)) { // If the ticket is not propagated correctly, return false
      System.out.println("Error: HelpDesk propagateUp() returned the wrong array: "
          + Arrays.toString(desk.array));
      return false;
    }
    return true; // Otherwise, return true

  }

  /**
   * Tests the HelpDesk class' PropagateDown() method.
   *
   * @return True if the tests pass, false otherwise
   */
  public static boolean testHelpDeskPropagateDown() {
    HelpDesk desk = new HelpDesk(7); // Create new desk and fill with elements
    SupportTicket ticket = new SupportTicket("skds"); // Create ticket to be tracked
    desk.array[0] = ticket; // Fill queue
    desk.array[1] = new SupportTicket("dkdkdk");
    desk.array[2] = new SupportTicket("dkdkdkdkd");
    desk.array[3] = new SupportTicket("d");
    desk.array[4] = new SupportTicket("dk");
    desk.array[5] = new SupportTicket("sks");
    desk.array[6] = new SupportTicket("dkdkd");
    desk.size = 7;
    desk.propagateDown(0); // Propagate tracked ticket down
    SupportTicket[] expected = new SupportTicket[7]; // Create array of expected values
    expected[0] = new SupportTicket("dkdkdkdkd");
    expected[1] = new SupportTicket("dkdkdk");
    expected[2] = new SupportTicket("dkdkd");
    expected[3] = new SupportTicket("d");
    expected[4] = new SupportTicket("dk");
    expected[5] = new SupportTicket("sks");
    expected[6] = new SupportTicket("skds");

    for (int i = 0; i < desk.array.length; i++) { // If any values don't match, return false
      if (!desk.array[i].toString().equals(expected[i].toString())) {
        System.out.println("Error: desk contained String: " + desk.array[i].toString() + ", But" +
            " the expected value was: " + expected[i].toString());
        System.out.println(Arrays.toString(desk.array));
        return false;
      }
    }

    // Create new target ticket and reset test to ensure the ticket is propagated correctly.
    ticket = new SupportTicket("aaa");
    desk.array[0] = ticket;
    desk.array[1] = new SupportTicket("aaaaaa");
    desk.array[2] = new SupportTicket("aaaaaaaa");
    desk.array[3] = new SupportTicket("aaaa");
    desk.array[4] = new SupportTicket("aaaaa");
    desk.array[5] = new SupportTicket("a");
    desk.array[6] = new SupportTicket("aa");
    desk.size = 7;
    desk.propagateDown(0);
    expected[0] = new SupportTicket("aaaaaaaa");
    expected[1] = new SupportTicket("aaaaaa");
    expected[2] = new SupportTicket("aaa");
    expected[3] = new SupportTicket("aaaa");
    expected[4] = new SupportTicket("aaaaa");
    expected[5] = new SupportTicket("a");
    expected[6] = new SupportTicket("aa");


    for (int i = 0; i < desk.array.length; i++) { // If any values don't match, return false
      if (!desk.array[i].toString().equals(expected[i].toString())) {
        System.out.println("Error: desk contained String: " + desk.array[i].toString() + ", But" +
            " the expected value was: " + expected[i].toString());
        System.out.println(Arrays.toString(desk.array));
        return false;
      }
    }
    return true; // Otherwise, return true
  }


  /**
   * Drives all tests for HelpDeskTestSuite.java
   *
   * @param args unused
   */
  public static void main(String[] args) {
    System.out.println("testHelpDeskCreateCheckNewTicket(): " + testHelpDeskCreateCheckNewTicket());
    System.out.println("testHelpDeskCloseNextTicket(): " + testHelpDeskCloseNextTicket());
    System.out.println("testHelpDeskCloseNextTicketEmptyStr(): "
        + testHelpDeskCloseNextTicketEmptyStr());
    System.out.println("testHelpDeskCreateNewTicketNullStr(): "
        + testHelpDeskCreateNewTicketNullStr());
    System.out.println("testHelpDeskCheckNextTicketEmptyList(): "
        + testHelpDeskCheckNextTicketEmptyList());
    System.out.println("testHelpDeskCloseNextTicketEmptyList(): "
        + testHelpDeskCloseNextTicketEmptyList());
    System.out.println("testHelpDeskCloseNextTicketExtra(): " + testHelpDeskCloseNextTicketExtra());
    System.out.println("testHelpDeskCheckNextTicketExtra(): " + testHelpDeskCheckNextTicketExtra());
    System.out.println("testHelpDeskPropagateUp(): " + testHelpDeskPropagateUp());
    System.out.println("testHelpDeskPropagateDown(): " + testHelpDeskPropagateDown());

  }
}
