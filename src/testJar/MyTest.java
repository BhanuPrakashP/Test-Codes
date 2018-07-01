package testJar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class MyTest {

	public static void main(String[] args) throws FileNotFoundException {
		
		String path = "C:\\ocmp_dependencies\\ocmp_4.5.0";
		System.err.println("started");
		File file = new File(path);
		
	
		getAllJarsList(file);
		
		
	}

	private static void getAllJarsList(File file) {
		if(file.isFile()){
			
			if(file.getName().endsWith(".jar")){
				System.out.println("<classpathentry kind=\"lib\" path=\""+file.getAbsolutePath()+"\"/>");
				//	<classpathentry kind="lib" path="C:/Users/rmv/.m2/smsc-repository/commons-collections/commons-collections/3.2.1/commons-collections-3.2.1.jar"/>

			}
		}else if(file.isDirectory()){
			
			for(File f : file.listFiles()){
				
				getAllJarsList(f);
			}
		}
		
	}

}
