import java.io.IOException;

public class ExeTest {
	public static void main(String args[]) {
		try {
			String[] open = { "NotePad.exe", "C:\\1.txt" };
			Runtime.getRuntime().exec(open);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
