package graduateprojectpackage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

public class LoginData {

		@DataProvider
		public static Iterator<Object> myData() {
			List<Object> list = new ArrayList<Object>();
			File file = new File("LoginData.txt");
			BufferedReader br;

			try {
				br = new BufferedReader(new FileReader(file));
				String s = br.readLine();
				while (s != null) {
					list.add(new TestLoginParametr(s));

					s = br.readLine();

				}
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ArrayIndexOutOfBoundsException e) {
				e.printStackTrace();
			}

			return list.iterator();
		}
	}



