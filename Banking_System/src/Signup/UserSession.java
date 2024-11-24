package Signup;

//UserSession.java
public class UserSession {
	
 private static int userId;
 private static int userPin;

 // Getter for userId
 public static int getUserId() {
     return userId;
 }

 // Setter for userId (if needed)
 public static void setUserId(int id) {
     userId = id;
 }

 // Getter for userPin
 public static int getUserPin() {
     return userPin;
 }

 // Setter for userPin
 public static void setUserPin(int pin) {
     userPin = pin;
 }
}
