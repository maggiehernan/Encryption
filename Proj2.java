import java.util.Scanner;
/** 
 *The Proj2 program displays a list of menu
 *option and gets user input.
 *Based off of user input, the program gets
 *three types of encryption (shift, mirror, jump).
 *
 * 
 *@author Margaret (Maggie) Hernan, JHED
 *@since  2020-07-12
 */
 
public class Proj2 {

   /** How many letters in the alphabet. */
   private static final int ALPHA = 26;
    
    /** Validate the format of a messge - must start with a
     capital letter and end with '.', '?' or '!'.
     *  @param message the string to validate
     *  @return true if valid, false otherwise
     */
   public static boolean validateMessage(String message) {
     
      int firstIndexMsg;
      char firstCharMsg;
      boolean isValid = false;
      boolean r = Boolean.valueOf(message);
      
      firstCharMsg = message.charAt(0);
      boolean startValid = Character.isUpperCase(firstCharMsg);
      boolean periodValid = message.endsWith(".");
      boolean pointValid = message.endsWith("!");
      boolean markValid = message.endsWith("?");
   
      if (startValid && (periodValid || pointValid || markValid)) {
         r = true;
      }
      else {
         r = false;
      }
      return r;
   }
    
    /** Do shift encryption gets shifted string using.
     * an int from user input
     *  @param message the string to encrypt
     *  @param shiftValue to amount to shift - a positive value
     means shift right and a negative value means shift left:
     'A' + 6 == 'G', 'a' + -6 == 'u'
     *  @return a new string that is the encrypted version of message.
     */
   public static String shiftEncrypt(String message, int shiftValue) {
      String sencrypt;
      String alphabet = "abcdefghijklmnopqrstuvwxyz"
                        + "abcdefghijklmnopqrstuvwxyz"; 
      String alphabetCaps = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                        + "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
      int i;
      char msgCharacter;
      char newCharacter;
      int characterVal;
      int characterPosition;
   
      sencrypt = "";
      for (i = 0; i < message.length(); i++) {
         msgCharacter = message.charAt(i); 
            
         if (shiftValue > 0) {
            if ((msgCharacter >= 'a') && (msgCharacter <= 'z')) {
               characterPosition = alphabet.indexOf(msgCharacter);
               characterVal = characterPosition + shiftValue;
               newCharacter = alphabet.charAt(characterVal);
               sencrypt = sencrypt + newCharacter;
            }
            
            else if ((msgCharacter >= 'A') && (msgCharacter <= 'Z')) {
               characterPosition = alphabetCaps.indexOf(msgCharacter);
               characterVal = characterPosition + shiftValue;
               newCharacter = alphabetCaps.charAt(characterVal);
               sencrypt = sencrypt + newCharacter;
            }
            
            else {
               newCharacter = msgCharacter;
               sencrypt = sencrypt + newCharacter;
            } 
         }
           
         else if (shiftValue < 0) {
            if ((msgCharacter >= 'a') && (msgCharacter <= 'z')) {
               characterPosition = alphabet.lastIndexOf(msgCharacter);
               characterVal = characterPosition + shiftValue;
               newCharacter = alphabet.charAt(characterVal);
               sencrypt = sencrypt + newCharacter;
            }
            
            else if ((msgCharacter >= 'A') && (msgCharacter <= 'Z')) {
               characterPosition = alphabetCaps.lastIndexOf(msgCharacter);
               characterVal = characterPosition + shiftValue;
               newCharacter = alphabetCaps.charAt(characterVal);
               sencrypt = sencrypt + newCharacter;
            }
            
            else {
               newCharacter = msgCharacter;
               sencrypt = sencrypt + newCharacter;
            }
         }
      }    
      return sencrypt;   
   }
    
    /** Do mirror encryption gets the current string backwards.
     *  @param message the string to encrypt
     *  @return a new string that is the encrypted version of message.
     */
   public static String mirrorEncrypt(String message) {
      String mencrypt = "";
        
      String mirror = "";
      char backwards;
   
      for (int j = message.length() - 1; j >= 0; j--) { 
         backwards = message.charAt(j);
         mencrypt = mencrypt + backwards;
      }
   
      return mencrypt;
   }
    
    /** Do jump encryption gets a new string based off the user input.
     *  @param message the string to encrypt
     *  @param jumpValue the interval size to use when jumping; value
     should be at most the square root of the message length
     *  @return a new string that is the encrypted version of message.
     */
   public static String jumpEncrypt(String message, int jumpValue) {
      String jencrypt = "";
        
      String repeated = "";
      String newMessage = "";
      char newLine = '\n';
      int i;
      int row;
      char str;
      char col;
      int num;
   
      for (i = 0; i < jumpValue; ++i) {  
         for (str = 0; str < message.length(); str++) {
            repeated += message.charAt(str); 
         }
      } 
      for (col = 0; col < repeated.length(); col += jumpValue) {  
         jencrypt += repeated.charAt(col);
      }
      return jencrypt;
   }

   /** Main method, execution begins here.
    *  @param args not used
    */
   public static void main(String[] args) {
       
      Scanner kb = new Scanner(System.in);
      String message = "This is the original message.";
      char menuOption;
      int shiftVal;
      int jumpVal;
      
      menuOption = 'd';
      
      while ((menuOption == 'd') || (menuOption == 'r') ||
             (menuOption == 's') || (menuOption == 'm') ||
             (menuOption == 'j')) {
         System.out.println("Encryption menu options:");
         System.out.println("d - display current message");
         System.out.println("r - read new message");
         System.out.println("s - shift encrypt"); 
         System.out.println("m - mirror encrypt");
         System.out.println("j - jump encrypt");
         System.out.println("q - quit the program");
         System.out.print("Enter option letter -> ");
         menuOption = kb.next().charAt(0);
      
         if (menuOption == 'd') {
            System.out.println("Current message: " + message);
            System.out.println("");
         }
         
         else if (menuOption == 'r') {
            System.out.print("Enter new message: ");
            kb.nextLine();
            message = kb.nextLine();
            validateMessage(message); 
            if (validateMessage(message)) {
               message = message.replaceAll(message, message);
            }
            else {
               System.out.println("Error");
            }
            System.out.println("");
         }
         
         else if (menuOption == 's') {
            System.out.print("Enter shift value -25 - 25: ");
            shiftVal = kb.nextInt();
            System.out.println("Current message: " + message);
            System.out.println("Shift encryption: " + 
                                shiftEncrypt(message, shiftVal));
            System.out.println("");
            message = message.replaceAll(message, 
                                         shiftEncrypt(message, shiftVal));
         }
         
         else if (menuOption == 'm') {
            System.out.println("Current message: " + message);
            System.out.println("Mirror encryption: " + mirrorEncrypt(message));
            System.out.println("");
            message = message.replaceAll(message, mirrorEncrypt(message));
         }
         
         else if (menuOption == 'j') {
            System.out.print("Enter a jump value between 2-5-> ");
            jumpVal = kb.nextInt();
            System.out.println("Current message: " + message);
            System.out.println("Jump encryption: " + 
                                jumpEncrypt(message, jumpVal));
            System.out.println("");
         }
         
         else if (menuOption == 'q') {
            System.out.print("Goodbye!");
         }   
         
         else {
            System.out.println("Error");
         }
      }
       
   }

  
}
