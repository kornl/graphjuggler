import java.io.*;

public class SaveGraphs {
    String fileName = "fastsavegraph.graph";
    
    // Default Settings:
    public int[][] e1 = {{0}};
    public int[][] k1 = {{1,1}}; //k für Koordinaten
    public String[] b1 = {""};
    
    public SaveGraphs() {
    }
    
    public static void main(String args[]) {

    }

    /**
     * Schreibt ein Zeilenende
     * @param out Zu benutzender FileWriter
     * @throws IOExeption 
     */

    public static void newLine(FileWriter out) throws IOException {
	out.write(13);
	out.write(10);
    }
    
    /**
     * Liefert aus einer Zuordnung in einem Strings den Teil hinter
     * dem Zuordnungsoperator (=)
     * @param str Zu betrachtender StringBuffer
     */

    public static String getValue(StringBuffer str) {
	int i = 0;
	for (; i < str.length() && str.charAt(i) != '='; i++);
	try {
	    return str.substring(i + 1, str.length());
	} catch (StringIndexOutOfBoundsException e) {
	} // Kann eigentlich nicht vorkommen
	return "";
    }
    
    /**
     * Liefert aus einer Zuordnung in einem Strings den Teil vor
     * dem Zuordnungsoperator (=)
     * @param str Zu betrachtender StringBuffer
     */

    public static String getWhat(StringBuffer str) {
	int i = 0;
	for (; i < str.length() && str.charAt(i) != '='; i++);
	try {
	    return str.substring(0, i);
	} catch (StringIndexOutOfBoundsException e) {
	} // Kann eigentlich nicht vorkommen
	return "";
    }
    

    /**
     * Schreibt die Einstellungen in die Datei filename
     */

    int v1;

    public void write() {
	File textFile;
	FileWriter out;
	v1 = e1.length;
	try {
	    out = new FileWriter(fileName);
	    try {
		out.write("# Ein gespeicherter Graph");		
		newLine(out);
		out.write("v1=");
		out.write(new Integer(v1).toString());
		newLine(out);
		for(int i=0; i<v1; i++) {
		    out.write(b1[i]);
		    newLine(out);
		    out.write(new Integer(k1[i][0]).toString());
		    newLine(out);
		    out.write(new Integer(k1[i][1]).toString());
		    newLine(out);
		    for(int j=0; j<v1; j++) {
			out.write(new Integer(e1[i][j]).toString());
			newLine(out);	
		    }
		}
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	    out.close();
	} catch (IOException ex) {
	    ex.printStackTrace();
	}
    }
    
    /**
     * Liest die Einstellungen aus der Datei filename
     */

    public void read() {
	BufferedReader in = null;
	String line;
	StringBuffer data;
	try {
	    in = new BufferedReader(new FileReader(fileName));
	} catch (FileNotFoundException e) {
	    System.err.println("File not found.");
	}
	if (in != null) {
	    try {
		while (true) {
		    line = in.readLine();
		    if (line == null)
			break;
		    data = new StringBuffer(line);
		    if (getWhat(data).equals("v1")) {
			v1 = Integer.parseInt(getValue(data));
			e1 = new int[v1][v1];
			k1 = new int[v1][2];
			b1 = new String[v1];
			for(int i=0; i<v1; i++) {
			    b1[i] = in.readLine();
			    line = in.readLine();
			    k1[i][0]=Integer.parseInt(line);
			    line = in.readLine();
			    k1[i][1]=Integer.parseInt(line);
			    for(int j=0; j<v1; j++) {
				line = in.readLine();
				e1[i][j]=Integer.parseInt(line);
			    }
			}
		    }
		}
	    } catch (IOException e) {
		System.err.println("End of File?");
	    }
	    
	}
    }
    
}
