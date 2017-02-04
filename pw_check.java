import java.lang.*;
import java.io.*;
import java.util.*;

public class pw_check{
	
	public DlbDictionary dictionaryWords,thePasswords;
	public File all_passwords;
	public final String alphabet = "abcdefghijklmnopqrstuvwxyz";
	public final String numbers = "0123456789";
	public final String symbols = "!@$^_*";
	
	public pw_check(String[] args){
		if(args.length != 3){
			System.out.println("You must use either the [-check or -find] flags");
			System.exit(1);
		}
		if(args[2].equals("-find")){
			//do what it says for find
			//enumerate all various passwords
			File dictionary = new File("dictionary.txt");
			Scanner fscan = new Scanner(dictionary);
			while(fscan.hasNextLine()){
				StringBuilder s = fscan.nextLine();
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