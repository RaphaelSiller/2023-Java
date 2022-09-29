import java.io.*;

public class FileCopy {
	public static void main(String[] args) {
		if (args == null || args.length != 2)
			System.out.println("java FileCopy inputfile outputfile");
		else {
			FileInputStream in;
			try {
				in = new FileInputStream(args[0]);
				FileOutputStream out = new FileOutputStream(args[1]);
				byte[] buf = new byte[4096];
				int len = 0;
				try {
					while ((len = in.read(buf)) > 0)
						out.write(buf, 0, len);
					in.close();
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}
	}
}