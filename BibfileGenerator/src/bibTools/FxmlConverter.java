/**
 * 
 */
package bibTools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author dgolla
 *
 * Klasse mit einem Tool um eine .fxml-Datei umzuwandeln. Zum einen von der Möglichkeit, dass der SceneBuilder die Datei lesen kann,
 * zum Gebrauch im BibFileGenerator
 * !! Beachte: die Property required muss trotzdem noch manuell in der entpsrechenden .fxml auf false gesetzt werden!! 
 *
 */
class FxmlConverter {

	/**
	 * Wenn TOENTRYTEXTFIELD true ist, wird von der SceneBuilder-Version zur BibFileGenerator-Version konvertiert.
	 * Entprechend andersherum
	 */
	static boolean TOENTRYTEXTFIELD = true;
	
	/**
	 * In BIBTYPE wird der der gewünschte Name der fxml, die umgewandelt werden soll geschrieben
	 */
	static String BIBTYPE = "unpublished";
	
	static String filepath = new File("").getAbsolutePath();

	public static void main(String[] args) {
		if (TOENTRYTEXTFIELD) {
			System.out.println("Starte FxmlConverter");
			String input = "";
			try (BufferedReader br = new BufferedReader(new FileReader(filepath + "/src/view/entries/forSceneBuilder/" + BIBTYPE + ".fxml"))) {
				String currentLine = "";
				int i = 1;
				while (currentLine != null) {
					if (i == 3)
						input += "<?import view.bibComponent.EntryTextField?>\n";
					currentLine = br.readLine();
					if (currentLine == null)
						break;
					input += currentLine + "\n";
					i++;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(input);
			input = input.replaceAll("<TextField", "<EntryTextField required=\"true\"");
			input = input.replaceAll("</TextField", "</EntryTextField");
			System.out.println(input);
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(filepath + "/src/view/entries/" + BIBTYPE + ".fxml"))) {
				bw.write(input);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("ToEntryTextField = " + TOENTRYTEXTFIELD);
		} else {
			System.out.println("Starte FxmlConverter");
			String input = "";
			try (BufferedReader br = new BufferedReader(new FileReader(filepath + "/src/view/entries/" + BIBTYPE + ".fxml"))) {
				String currentLine = "";
				while (currentLine != null) {
					currentLine = br.readLine();
					if (currentLine == null)
						break;
					input += currentLine + "\n";
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(input);
			input = input.replace("<?import view.bibComponent.EntryTextField?>\n", "");
			input = input.replaceAll("<EntryTextField required=\"true\"", "<TextField");
			input = input.replaceAll("<EntryTextField required=\"false\"", "<TextField");
			input = input.replaceAll("</EntryTextField", "</TextField");
			System.out.println(input);
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(filepath + "/src/view/entries/forSceneBuilder/" + BIBTYPE + ".fxml"))) {
				bw.write(input);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("ToEntryTextField = " + TOENTRYTEXTFIELD);
		}
	}

}
