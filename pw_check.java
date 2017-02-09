import java.lang.*;
import java.io.*;
import java.util.*;

public class pw_check{
	
	public dlb dictionary,allpw
	public File all_passwords;
	public final String alphabet = "abcdefghijklmnopqrstuvwxyz";
	public final String numbers = "0123456789";
	public final String symbols = "!@$^_*";
	public final String allChars = "abcdefghijklmnopqrstuvwxyz0123456789!@$^_*";
	
	public pw_check(String[] args){
		if(args.length() != 3){
			System.out.println("You must use either the [-check or -find] flags");
			System.exit(1);
		}
		if(args[2].equals("-find")){
			File dictionary = new File("dictionary.txt");
			Scanner fscan = new Scanner(dictionary);
			dictionary = new dlb();
			
			//adding all words to the dictionary that are less than 5 chars long
			while(fscan.hasNextLine()){
				StringBuilder s = fscan.nextLine();
				if(s.length() <= 5)
					dictionary.put(s,0);
			}
			
			//now create the file all_passwords.txt
			all_passwords = new File("all_passwords.txt");
			PrintWriter toFile = new PrintWriter(all_passwords);
			
			for(int a = 0;a<allChars.length();a++){
				for(int b = 0;b<allChars.length();b++){
					for(int c = 0;c<allChars.length();c++){
						for(int d = 0;d<allChars.length();d++){
							for(int e = 0;e<allChars.length();e++){
								char password[5];
								password[0] = allChars[a];
								password[1] = allChars[b];
								password[2] = allChars[c];
								password[3] = allChars[d];
								password[4] = allChars[e];
							}
						}
					}
				}
				
			}
			
			
		}
		else if(args[2].equals("-check") && all_passwords.exists()){
			
		}
		else if(args[2].equals("-check")){
			System.out.println("You must use the -find flag before you use the -check flag");
			System.exit(1);
		}
		else{
			System.out.println("You must enter a valid flag, either '-find' or '-check'");
			System.exit(1);
		}
	}
	
	
	public static void main(String[] args) throws IOException{	
		pw_check pw = new pw_check(args);
	}
}