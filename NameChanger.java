import java.util.*; //MAHMUT ALPEREN ÇAVUÞ 210408044
import java.io.*;
import java.nio.file.Files;


public class NameChanger {

	public static void main(String[] args) {
		Scanner scan= new Scanner(System.in);
		System.out.print("Please enter a file name:");
		String fileName=scan.nextLine();
		File f= new File(fileName);
		
		scan.close();
		ArrayList<File> files= getSubFoldersandFiles(f);
		

		
		
		
		for(File folders: files) {

			System.out.println(folders.getName()+" "
					+getFolderSize(folders));
		}
		Collections.reverse(files); 
		for(File folder: files) {

			
			String newFilePath = folder.getAbsolutePath().replace(folder.getName(), "") + clearTurkishChars(folder.getName());
			File newFile = new File(newFilePath);
			folder.renameTo(newFile); 
		

		}
		
			


	}





	public static String clearTurkishChars(String str) {

		String ret =str.toUpperCase(Locale.ENGLISH);

		char[] turkishChars = new char[] {0x131, 0x130, 0xFC, 0xDC, 0xF6, 0xD6, 0x15F, 0x15E, 0xE7, 0xC7, 0x11F, 0x11E};
		char[] englishChars = new char[] {'i', 'I', 'u', 'U', 'o', 'O', 's', 'S', 'c', 'C', 'g', 'G'};
		for (int i = 0; i < turkishChars.length; i++) {
			ret = ret.replaceAll(new String(new char[]{turkishChars[i]}), new String(new char[]{englishChars[i]}));
		}

		return ret;

	}
	public static long getFolderSize(File folder)
	{
		long length = 0;
		if(!folder.isDirectory()) {
			length+=folder.length();
		}

		else {
			File[] files = folder.listFiles();

			int count = files.length;


			for (int i = 0; i < count; i++) {
				if (files[i].isFile()) {
					length += files[i].length();
				}
				else {
					length += getFolderSize(files[i]);
				}
			}
		}

		return length;
	}


	static ArrayList<File> getSubFoldersandFiles(File f)  {
		Queue<File> q = new LinkedList<>();
		q.add(f);
		ArrayList<File> res = new ArrayList<>();

		while(!q.isEmpty()) {
			File cur = q.remove();
			if(cur.isDirectory()) {
				res.add(cur);
				for(File ch: cur.listFiles()) { 
					q.add(ch);
				}
			}
			else 
				res.add(cur);


		}
		
		return res;
	}

	
}
