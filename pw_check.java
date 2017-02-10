import java.lang.*;
import java.io.*;
import java.util.*;

public class pw_check{
	
	public dlb dictionary,allpw;
	public File all_passwords = new File("all_passwords.txt");
	public final String allChars = "abcdefghijklmnopqrstuvwxyz0123456789!@$^_*";
	public boolean isInvalid = false;
	public Scanner inScan = new Scanner(System.in);
	
	public pw_check(String[] args) throws IOException,FileNotFoundException
	{
		if(args.length != 1){
			System.out.println("You must use either the [-check or -find] flags");
			System.exit(1);
		}
		if(args[0].equals("-find")){
			File dic = new File("dictionary.txt");
			Scanner fscan = new Scanner(dic);
			dictionary = new dlb();
			
			//adding all words to the dictionary that are less than 5 chars long
			while(fscan.hasNextLine()){
				String s = fscan.nextLine();
				if(s.length() <= 5)
					dictionary.put(s,0);
			}
			
			//now create the file all_passwords.txt
			
			PrintWriter toFile = new PrintWriter(all_passwords);
			
			long time_init = System.nanoTime();
			for(int a = 0;a<allChars.length();a++){
				for(int b = 0;b<allChars.length();b++){
					for(int c = 0;c<allChars.length();c++){
						for(int d = 0;d<allChars.length();d++){
							for(int e = 0;e<allChars.length();e++){
								char[] password = new char[5];
								password[0] = allChars.charAt(a);
								password[1] = allChars.charAt(b);
								password[2] = allChars.charAt(c);
								password[3] = allChars.charAt(d);
								password[4] = allChars.charAt(e);
								
								//get the frequencies of chars, numbers and symbols
								int charCount = 0,numCount = 0,symCount = 0;
								int[] tester = {a,b,c,d,e};
								//count up the number of chars symbols and numbers to make sure the specifications for
								//passwords are upheld
								for(int i = 0;i<5;i++){
									if(tester[i]<= 25){
										//char is a character
										charCount++;
									}
									else if((tester[i] > 25) && (tester[i] <= 35)){
										//char is a number
										numCount++;
									}
									else{
										//char is a symbol
										
										symCount++;
									}
								}
								
								if(charCount > 3 || charCount == 0){
									//do nothing, pw has too many or too few symbols
								}
								else if(numCount > 2 || numCount == 0){
									//also do nothing, pw has too many or too few numbers
								}
								else if(symCount > 2 || symCount == 0){
									//also do nothing, this pw has too many or too few symbols
								}
								else{
									//if we get here we know that the password must have the proper nubmer of chars
									//all we have to do now is check to make sure that it has no words in the dlb 
									
									
									//first lets create a second copy of the char array that will contain symbol replacements
									char[] pw_replace = new char[5];
									for(int i = 0;i<5;i++){
										char z = password[i];
										if(z == '7'){
											pw_replace[i] = 't'; 
										}
										else if(z == '4'){
											pw_replace[i] = 'a';
										}
										else if(z == '0'){
											pw_replace[i] = 'o';
										}
										else if(z == '3'){
											pw_replace[i] = 'e';
										}
										else if(z == '$'){
											pw_replace[i] = 's';
										}
										else if(z == '1'){
											pw_replace[i] = 'i';
										}
										else{
											pw_replace[i] = z;
										}
									}
									//at this point we have created pw_replace, which has all of the symbol replacements 
									isInvalid = false;
									String thePW = new String(pw_replace);
									check_all_substrings(thePW);
									//System.out.println("The PW we checked is: " + thePW + " Which is " + isInvalid);
									if(isInvalid){
										//do nothing
									}
									else{
										//have to add the password and time to the file.
										long t = System.nanoTime() - time_init;
										
										toFile.print(password);
										toFile.print(',');
										toFile.println(t);
										
									}
								}
								
							}
						}
					}
				}
				
			}
			
			
		}
		else if(args[0].equals("-check") && all_passwords.exists()){
			//first we have to read in all of the passwords and values from the file and put them into the dlb
			Scanner scan = new Scanner(all_passwords);
			allpw = new dlb();
			
			System.out.println("Collecting all passwords now, this might take a moment\n\n");
			while(scan.hasNextLine()){
				
				String s = scan.nextLine(); //read all of the data from the line into the variable
				char[] pass = new char[5];
				pass[0] = s.charAt(0);
				pass[1] = s.charAt(1);
				pass[2] = s.charAt(2);
				pass[3] = s.charAt(3);
				pass[4] = s.charAt(4);
				
				//s now contains the time, which was put to the file as a long
				s = s.substring(6);
				
				long t = Long.valueOf(s).longValue();
				
				String test = new String(pass);
				
				//System.out.println("inserting " + test + " and " + t);
				allpw.put(test,t);
			}
			//at this point all of the password and time pairs are stored in the dlb
			String aPW = new String("");
			System.out.println("Please enter a password, enter '-' when you are finished");
			while(!aPW.equals('-')){
				
				aPW = inScan.nextLine();
				
				if(allpw.contains(aPW)){
					long t = allpw.gets(aPW);
					System.out.println("The password " + aPW + " was found in " + t + " nanoseconds\n");
				}
				else{
					System.out.println("The password " + aPW + "was not found\n");
				}
			}
		}
		else if(args[0].equals("-check")){
			System.out.println("You must use the -find flag before you use the -check flag");
			System.exit(1);
		}
		else{
			System.out.println("You must enter a valid flag, either '-find' or '-check'");
			System.exit(1);
		}
	}
	
	public void check_all_substrings(String word) {
        if (word.length() == 1) {
            if(dictionary.contains(word)){
            	isInvalid = true;
            }
            return;
        }else{
        	if(dictionary.contains(word)){
            	isInvalid = true;
            }
            check_all_substrings(word.substring(0, word.length()-1)); 
            check_all_substrings(word.substring(1, word.length())); 
        }

    }
	
	public static void main(String[] args) throws IOException{	
		pw_check pw = new pw_check(args);
	}
	
}