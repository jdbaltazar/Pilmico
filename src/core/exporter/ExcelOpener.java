package core.exporter;

import java.io.File;
import java.io.IOException;

public class ExcelOpener {
	public ExcelOpener() {
	}

	public void openTable(File file) throws IOException {
		Runtime run = Runtime.getRuntime();
		// I make the assumption that the client has Excel and
		// the file type .XLS is associated with Excel

		// This is a simple check to find out the operating system
		String lcOSName = System.getProperty("os.name").toLowerCase();
		boolean MAC_OS_X = lcOSName.startsWith("mac os x");
		if (MAC_OS_X) {
			run.exec("open " + file);
		} else {
			run.exec("cmd.exe /c start " + file);
		}
		System.out.println(file + " opened");
	}
}
