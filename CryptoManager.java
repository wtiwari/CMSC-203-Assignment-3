 /*
  * Class: CMSC203 CRN 3416
 Program: Assignment #3
 Instructor: Dr. Grinberg
 Summary of Description: encrypt and decrypt a phrase using two similar approaches, the Caesar and Belaso cyphers, each insecure by modern standards. 
 Due Date: 03/14/2022 
 Integrity Pledge: I pledge that I have completed the programming assignment independently.
 I have not copied the code from a student or any source.
 William Tiwari
  */
class CryptoManager {
	
	private static final char LOWER_BOUND = ' ';
	private static final char UPPER_BOUND = '_';
	private static final int RANGE = UPPER_BOUND - LOWER_BOUND + 1;

	/**
	 * This method determines if a string is within the allowable bounds of ASCII codes 
	 * according to the LOWER_BOUND and UPPER_BOUND characters
	 * @param plainText a string to be encrypted, if it is within the allowable bounds
	 * @return true if all characters are within the allowable bounds, false if any character is outside
	 */
	public static boolean stringInBounds (String plainText) {
		 //declare boolean variable 
		boolean bool = true;
		//For loop for input validation
	       for (int index = 0; index < plainText.length(); index++)
	       {
	    	   //If statement that sets bool to falce value if 
	    	   //plainText.charAt(index) >= LOWER_BOUND && plainText.charAt(index) <= UPPER_BOUND)
	           if (!(plainText.charAt(index) >= LOWER_BOUND && plainText.charAt(index) <= UPPER_BOUND))
	               bool = false;
	       }
	       //return boolean value in bool
	           return bool;
		//throw new RuntimeException("method not implemented");
	}

	/**
	 * Encrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in plainText is replaced by the character \"offset\" away from it 
	 * @param plainText an uppercase string to be encrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the encrypted string
	 */
	public static String encryptCaesar(String plainText, int key) {
		//make char array plainTextChar[] and set to plainText.length()
		char [] plainTextChar = new char[plainText.length()];
		//declare string variable to hold encryptedText
		String encryptedText = "";
		//For loop for input validation and encryption 
		for (int i = 0; i< plainText.length(); i++) {
			//put characters in array 
			plainTextChar[i] =  plainText.charAt(i);
			//add key to characters to encrypt 
			plainTextChar[i] += key;
			while(plainTextChar[i]>UPPER_BOUND) {
				plainTextChar[i] -= RANGE; 
			}
			//place chars in string
			encryptedText += plainTextChar[i];
		}
		//return encrypted string
		return encryptedText;
		
		//throw new RuntimeException("method not implemented");
	}
	
	/**
	 * Encrypts a string according the Bellaso Cipher.  Each character in plainText is offset 
	 * according to the ASCII value of the corresponding character in bellasoStr, which is repeated
	 * to correspond to the length of plainText
	 * @param plainText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the encrypted string
	 */
	public static String encryptBellaso(String plainText, String bellasoStr) {
		//holds the lengthened key from  bellasoStr which is the key passed to the method from CyrptoManagerTest. It holds the
		//Bellaso key.
		String key = bellasoStr;
		//Char array to use for character encryption
		char[] charencryptedText = new char[plainText.length()];
		//String object to hold encrypted String
		String encryptedText = "";
		//if statement to lengthen the 
		if (bellasoStr.length() < plainText.length()) {
			//declare ints to hold the values for the key's length
			int wholeNumber = plainText.length()/bellasoStr.length();
			int remainderNumber = bellasoStr.length() % plainText.length();
			//for loop for the whole number portion of key's length where the 
			//whole string from belasoStr is placed in it 
			for(int i  = 1; i < wholeNumber; i++) 
				key += bellasoStr;
			//for loop for remainder = of key's length where only part of the String 
			//from belasoStr is placed in it
			for(int i  = 0; i < remainderNumber-1; i++) 
				key += bellasoStr.charAt(i);
		}
		//for loop to encrypt plainText
		for(int i = 0; i < plainText.length(); i++) {
			charencryptedText[i] = (char) ((plainText.charAt(i)+key.charAt(i)));
			//plain text for wrap around if ASCII value falls above the Range
			while(charencryptedText[i]>UPPER_BOUND) {
				charencryptedText[i] -= RANGE; 
			}
			//place char array in a String object 
			encryptedText += charencryptedText[i];
		}
		//return encrypted String 
		return encryptedText;
		//throw new RuntimeException("method not implemented");
		
	}
	
	/**
	 * Decrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in encryptedText is replaced by the character \"offset\" characters before it.
	 * This is the inverse of the encryptCaesar method.
	 * @param encryptedText an encrypted string to be decrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the plain text string
	 */
	public static String decryptCaesar(String encryptedText, int key) {
		//make char array plainTextChar[] and set to plainText.length()
		char [] plainTextChar = new char[encryptedText.length()];
		String dencryptedText = "";
		//For loop for input validation
		for (int i = 0; i<encryptedText.length(); i++) {
			//put characters in arra
			int x = encryptedText.charAt(i) - key;
			
			while(x<LOWER_BOUND) {
				x += RANGE; 
			}
			//place decrypted chars in string
			dencryptedText += (char)x;
		}
		//return string 
		return dencryptedText;
		//throw new RuntimeException("method not implemented");
	}
	
	/**
	 * Decrypts a string according the Bellaso Cipher.  Each character in encryptedText is replaced by
	 * the character corresponding to the character in bellasoStr, which is repeated
	 * to correspond to the length of plainText.  This is the inverse of the encryptBellaso method.
	 * @param encryptedText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the decrypted string
	 */
	public static String decryptBellaso(String encryptedText, String bellasoStr) {
		//holds the lengthened key from  bellasoStr which is the key passed to the method from CyrptoManagerTest. It holds the
				//Bellaso key.
				String key = bellasoStr;
				//String object to hold decrypted String
				String decryptedText = "";
				//if statement to lengthen the key 
				if (bellasoStr.length() <  encryptedText.length()) {
					//declare ints to hold the values for the key's length
					int wholeNumber =  encryptedText.length()/bellasoStr.length();
					int remainderNumber = bellasoStr.length() %  encryptedText.length();
					//for loop for the whole number portion of key's length where the 
					//whole string from belasoStr is placed in it 
					for(int i  = 1; i < wholeNumber; i++) 
						key += bellasoStr;
					//for loop for remainder = of key's length where only part of the String 
					//from belasoStr is placed in it
					for(int i  = 0; i < remainderNumber-1; i++) 
						key += bellasoStr.charAt(i);
				}
				
				//for loop to decrypt encryptedText
				for(int i = 0; i <  encryptedText.length(); i++) {
					//to add the ascii values for decryption
					int x = encryptedText.charAt(i) - key.charAt(i);
					//plain text for wrap around if ASCII value falls bellow the Range
					while(x<LOWER_BOUND) {
						x += RANGE; 
					}
					 decryptedText += (char)x;
					//place int in the string
				}
				//return decrypted String 
				return  decryptedText;
		//throw new RuntimeException("method not implemented");
	}
}
