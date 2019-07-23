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
 * Models a PriorityQueue of SupportTickets.
 *
 * @author Hunter Abraham
 */
public class HelpDesk implements HelpDeskInterface {

  protected SupportTicket[] array; // Array to store PriorityQueue
  protected int size; // Number of tickets in the array


  /**
   * HelpDesk DefaultConstructor.
   */
  public HelpDesk() {
  }

  /**
   * HelpDesk Constructor. Creates this desk's array and sets size equal to 0.
   *
   * @param size The size of the HelpDesk.
   */
  public HelpDesk(int size) {
    array = new SupportTicket[size];
    size = 0;
  }

  /**
   * Creates and adds a new SupportTicket to this HelpDesk.
   *
   * @param message names the client and describes their need for support.
   * @throws NullPointerException when the String message argument is null.
   */
  public void createNewTicket(String message) {
    if (message == null)
      throw new NullPointerException("Error: message argument cannot be null.");

    if (size >= array.length) // If size is greater than this array's length, resize array.
      array = Arrays.copyOf(array, size * 2);

    if (size == 0) { // If size is 0, make new ticket the root.
      array[0] = new SupportTicket(message); // Set the root to the new value.
      size++; // Increment size
      return; // Exit method
    }
    int idx = size++; // track current index of ticket being added and increment size
    array[idx] = new SupportTicket(message); // Create new support ticket at index
    propagateUp(idx);
  }


  /**
   * Propagates last element up to its correct position to the root of the PriorityQueue
   *
   * @param idx the index of the last added element
   */
  protected void propagateUp(int idx) {
    int parent = parentOf(idx);
    int child = idx;
    // If the index isn't the root and the child has a greater value than its parent
    if (idx != 0 && array[child].compareTo(array[parent]) > 0) { // swap child and parent
      swap(child, parent);
      propagateUp(parentOf(idx));
    } else { // If child isn't greater than 0 or is at root node, end loop
      return;
    }
  }

  /**
   * Given an index into the heap array, this method recursively swaps any SupportTickets
   * necessary to enforce the heap's order property between this index and it's children.
   *
   * @param idx
   */
  protected void propagateDown(int idx) {
    SupportTicket ticket = array[idx];
    int parent = idx;
    int child = leftChildOf(parent);
    // If the right child is further up the hierarchy, consider that the child
    if (child < size - 1 &&
        array[child].compareTo(array[rightChildOf(parent)]) < 0)
      child = rightChildOf(parent);
    // If the child is null or higher than the parent, break recursion
    if (child >= size || array[child] == null || ticket.compareTo(array[child]) >= 0)
      return;
    // Move child up and move down one level in the tree.
    if (array[parent].compareTo(array[child]) < 0) {
      swap(parent, child);
      propagateDown(child);
    }
  }


  /**
   * Returns the message within this HelpDesk that has the highest priority.
   * This method does not change the state of this HelpDesk.
   *
   * @return the message within the highest priority SupportTicket.
   * @throws IllegalStateException when called on a HelpDesk with zero SupportTickets.
   */
  public String checkNextTicket() {
    if (size == 0) // If size is 0 throw IllegalStateException
      throw new IllegalStateException("Error: this HelpDesk has zero SupportTickets.");

    return array[0].toString(); // Return item of highest priority
  }


  /**
   * Returns and removes the message within this HelpDesk that has the highest priority.
   *
   * @return the message within the highest priority SupportTicket (prior to its removal).
   * @throws IllegalStateException when called on a HelpDesk with zero SupportTickets.
   */
  public String closeNextTicket() {
    if (size == 0) // If the array is empty, throw IllegalStateException
      throw new IllegalStateException("Error: this HelpDesk has zero SupportTickets.");

    String s = array[0].toString(); // Store value at idx 0
    array[0] = null; // Remove item
    array[0] = array[size - 1];
    size--; // reduce size
    if (size != 0)
      propagateDown(0);
    return s; // return string
  }

  /**
   * Finds the parent of current node.
   *
   * @param index index of the current node.
   * @return the index of that node's parent.
   */
  protected static int parentOf(int index) {
    return (index - 1) / 2;
  }

  /**
   * Finds a node's left child.
   *
   * @param index the index of the node.
   * @return the index of the node's child.
   */
  protected static int leftChildOf(int index) {
    return index * 2 + 1;
  }


  /**
   * Finds a node's right child.
   *
   * @param index The index of the node.
   * @return The index of the node's right child.
   */
  protected static int rightChildOf(int index) {
    return index * 2 + 2;
  }

  /**
   * Swaps two elements in the HelpDesk instance's array
   *
   * @param indexA The index of the first element
   * @param indexB The index of the second element
   */
  protected void swap(int indexA, int indexB) {
    SupportTicket ticket = array[indexB]; // Create temp ticket to hold Index B's value
    array[indexB] = array[indexA]; // Put indexA's value in index B
    array[indexA] = ticket; // Put index B's value in index A
  }


}
