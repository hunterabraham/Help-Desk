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

/**
 * SupportTicket class. Models a ticket that will be added to the HelpDesk PriorityQueue
 *
 * @author Hunter Abraham
 */
public class SupportTicket implements Comparable<SupportTicket> {

  private String message; // String message contained withing SupportTicketClass


  /**
   * SupportTicket constructor
   *
   * @param message the message that this supportTicket will contain
   * @throws NullPointerException if the message is null
   */
  public SupportTicket(String message) {
    if (message == null) // If message is null, throw NullPointerException
      throw new NullPointerException();
    this.message = message;
  }


  /**
   * Compares two support tickets. First, it compares their length followed by their lexicographical
   * order.
   *
   * @param ticket The support ticket being compared to this ticket
   * @return Positive integer if higher in natural ordering, negative if lower, and 0 if equal
   */
  @Override
  public int compareTo(SupportTicket ticket) {
    // If this message is greater than ticket's string, return 1
    if (message.length() > ticket.toString().length()) {
      return 1;
    } else if (message.length() < ticket.toString().length()) { // If it's shorter, return -1
      return -1;
    } else { // If their lengths are equal, return lexicographic ordering
      return message.compareTo(ticket.toString());
    }
  }

  /**
   * SupportTicket's toString() method. Returns this ticket's message.
   *
   * @return this ticket's message
   */
  @Override
  public String toString() {
    return message;
  }
}
