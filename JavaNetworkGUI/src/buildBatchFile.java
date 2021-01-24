import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class buildBatchFile {
	
	public void buildBatch(String ip, String subnet, String gateway) {
		/*
		 * Builds a Batch file and calls the batch file to be ran
		 */
		
		try {
			//Builds the file
			PrintWriter writer = new PrintWriter("Network.bat");
			//Writes the batch file
			writer.write("@ECHO OFF");
			writer.println(":: BatchGotAdmin");
			writer.println(":-------------------------------------");
			writer.println("REM  --> Check for permissions");
			writer.println(">nul 2>&1 \"%SYSTEMROOT%\\system32\\cacls.exe\" \"%SYSTEMROOT%\\system32\\config\\system\"");
			writer.println("");
			writer.println("REM --> If error flag set, we do not have admin.");
			writer.println("if '%errorlevel%' NEQ '0' (");
			writer.println("    echo Requesting administrative privileges...");
			writer.println("    goto UACPrompt");
			writer.println(") else ( goto gotAdmin )");
			writer.println("");
			writer.println(":UACPrompt");
			writer.println("	echo Set UAC = CreateObject^(\"Shell.Application\"^) > \"%temp%\\getadmin.vbs\"");
			writer.println("	set params = %*:\"=\"\"");
			writer.println("    echo UAC.ShellExecute \"cmd.exe\", \"/c %~s0 %params%\", \"\", \"runas\", 1 >> \"%temp%\\getadmin.vbs\"");
			writer.println("");
			writer.println("    \"%temp%\\getadmin.vbs\"");
			writer.println("    del \"%temp%\\getadmin.vbs\"");
			writer.println("    exit /B");
			writer.println("");
			writer.println(":gotAdmin");
			writer.println("    pushd \"%CD%\"");
			writer.println("    CD /D \"%~dp0\"");
			writer.println(":------------------");
			writer.println("");
			writer.println("ECHO Resetting IP Address and Subnet Mask ");
			writer.println("");
			writer.println(":SET /P _inputIP= \"Please input new IP Address: \"");
			writer.println(":SET /P _inputSM= \"Please input new Subnet Mask: \"");
			writer.println(":SET /P _inputGW= \"Please input new Gate Way: \"");
			writer.println("");
			writer.println("ECHO - - - - - - - - - - - -");
			writer.println(":ECHO The IP - %1");
			writer.println(":ECHO The Mask - %2");
			writer.println(":ECHO The Gateway - %3");
			writer.println("ECHO - - - - - - - - - - - -");
			writer.println("");
			
			//VERY IMPORTANT LINE - - the "Ethernet 2" is the Network settings that will be changed
			//This will need to be changed in order to change the proper Network settings on different computers
			writer.println("netsh int ipv4 set address name=\"Ethernet\" source=static address="+ip+" mask="+ subnet + " gateway="+gateway);
			
			writer.println("");
			writer.println("ECHO Here are the new settings for %computername%");
			writer.println("netsh int ip show config");
			writer.println("pause");
			
			writer.close();
			
			//ProcessBuilder run = new ProcessBuilder("Network.bat");
			try {
				//Runs the Batch File
				Runtime.getRuntime().exec(new String[] {"Network.bat", ip, subnet, gateway});
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
/*			try {
				//run.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
*/
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
