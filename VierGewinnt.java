import java.io.*;// diese Zeile muss ganz am Anfang des files stehen

/** *********************************************
 * Vorname , Nachname:   Alexander Christoph   * 
 * Studiengang: Medieninformatik               * 
 * Martikelnummer: 634389                      * 
 * Ort: FH Duesseldorf                         * 
 * @version 1.0  			   			       *
 * @author Alex	            	               *
 ********************************************* */

/**
 * ******************************************************** <h2>Vier Gewinnt</h2>
 * <h3>Abschlussaufgabe OOP1 - Teil 1</h3>
 * 
 * <h4>Aufgabenstellung</h4> Das Spiel soll folgendermaßenen ablaufen: Zuerst
 * wird ein Begrueßungsbildschirm gezeigt. Der Benutzer kann hier Hilfe anzeigen
 * lassen, die sowohl das Spiel selber beschreibt, als auch die Benutzung des
 * Programms beschreibt. Außerdem kann das Spiel gestartet oder das Programm
 * verlassen werden. Beim Start des Spiels wird das leere Spielfeld angezeigt.
 * Im Ablauf des Spieles geben abwechselnd beide Spieler als Spielzug die Spalte
 * an, in die ein Spieler seinen Spielstein wirft. Bei einer ungueltigen Eingabe
 * muss der Spieler noch einmal ziehen. Nach jedem Spielzug wird jeweils das
 * Spielfeld neu dargestellt. Nach jedem Spielzug wird auï¬erdem das Spielfeld
 * gecheckt, ob der Spieler nun gewonnen hat. Wenn ja, wird angezeigt, wer
 * gewonnen hat. Danach kann mit einer beliebigen Eingabe zum
 * Begrueßungsbildschirm zurueckgekehrt werden. Wenn kein Spieler gewonnen hat,
 * wird der jeweils andere Spieler nach seinem Spielzug gefragt.
 * 
 * <h4>Bewertung</h4> Sie muessen Ihre Loesung des 1. Teils der Abschlussaufgabe
 * den Betreuern im Praktikum sowohl zeigen als auch erlautern koennen. Dabei
 * muessen Sie folgende Anforderungen erfuellen: - Sie haben Funktionen,
 * Prozeduren und Variable sinnvoll erkannt, aufgeteilt, definiert, benannt und
 * beschrieben o Zur Dokumentation koennen Sie die von javadoc erzeugten
 * .html-files zeigen o Sie haben keine static Variablen verwendet - Sie haben
 * den Ablauf innerhalb jeder Funktion oder Prozedur sinnvoll beschrieben - Sie
 * kËnnen die Aufgabe, Funktionsweise und Ablauf von jeder Funktion, Prozedur
 * und jeder Variable erlauutern. - Das Spiel lauft so ab, wie gefordert Wenn
 * das alles OK ist, bekommen Sie das Testat fuer den 1. Teil der
 * Abschlussaufgabe.
 * 
 * 
 * @author Alexander Christoph
 * @version 1.0
 ******************************************************** */
public class VierGewinnt { // Anfang class VierGewinnt

	/**
	 * ******************************************************** In der Prozedur
	 * (Methode ohne Rueckgabewert -viod) <b>hilfe()</b> ist nur eine einfache
	 * System.out.println() Anweisung, die den Spieler Hilfestellungen zum Spiel
	 * anzeigt.
	 * 
	 ******************************************************** */

	public static void hilfe() {

		System.out
				.println("HILFE"
						+ "\n"
						+ "Vier Gewinnt - Spielbeschreibung und Spielregeln"
						+ "\n"
						+ "Spielbeschreibung und Reglen:"
						+ "\n"
						+ "Das Spielbrett besteht aus sieben Spalten (senkrecht) und sechs Reihen (waagerecht). "
						+ "\n"
						+ "Jeder Spieler besitzt 21 gleichfarbige Spielsteine (hier: Spieler 1 kenntlich durch X und Spieler 2 durch O)."
						+ "\n"
						+ " Beide Spieler lassen abwechselnd  Spielsteine  in eine Spalte fallen (durch Eingabe der gewuenschten Spalte, Bsp. 3Ã¬+ Enter), "
						+ "\n"
						+ "dieser besetzt den untersten freien Platz der Spalte. Gewinner ist der Spieler, der es als erster schafft, "
						+ "\n"
						+ "vier seiner Spielsteine waagerecht, senkrecht oder diagonal in eine Linie zu bringen. Das Spiel endet unentschieden, "
						+ "\n"
						+ "wenn das Spielbrett komplett gefuellt ist, ohne dass ein Spieler eine Viererlinie gebildet hat.");

	}

	public static void ueberschrift(int n) { // Anfang ueberschrift()

		// Schleife, die Zahlen von 1 - n als einzeilig, mit Tabulator getrennt,
		// ausgibt
		for (int i = 1; i <= n; i++) {

			System.out.print(i + "   ");

		}
		System.out.println(); // Leerzeile einfuegen

	} // Ende ueberschrift()

	/**
	 * ******************************************************** Innerhalb dieser
	 * Methode <b>spielbrettleer</b> wird das leere Spielbrett erzeugt, mit
	 * Hilfe eines 2 dim. Arrays (spielfeld[][]) und samt "Geruest" dargestellt.
	 * Dies wird innerhalb 2 ineinander geschachtelte for-Schleife "entworfen".
	 * 
	 * @param String
	 *            name gibt den Spielernamen an, z.B. Spieler 1 char
	 *            spielsteinchen zeigt den passenden Spielstein zum Spieler an
	 *            (X oder O) int i gibt die moeglichen Spielzuege (i < 43)
	 * @return gibt mËglichen Spielzuege zurueck
	 ******************************************************** */

	public static int spielablauf(char spielfeld[][], String name,
			char spielsteinchen, int i) {

		String spalte = spielereingabe(); // Aufruf der Funktion
											// spielereingabe()
		int spaltennummer = gueltigeEingabe(spalte); // Aufruf der Funktion
														// gueltigeEingabe
		// String spalte_2 = spielereingabe(); // Aufruf der Funktion
		// spielereingabe()
		volleSpalte(spaltennummer, spielfeld); // Aufruf der Funktion
												// volleSpalte()
		spielfeld = spielzug(spielfeld, spaltennummer, spielsteinchen); // Aufruf
																		// der
																		// Funktion
																		// spielzug()
		i = gewonnen(spielfeld, spielsteinchen, name, i);
		spielbrettVoll(spielfeld); // Aufruf der Prozedur spielbrettVoll()

		return i;

	}

	public static char[][] spielbrettleer(char spielfeld[][]) { // Anfang
																// spielbrettleer()

		ueberschrift(7);

		for (char y = 0; y < spielfeld.length; y++) // y = Zeilen
		{
			for (char x = 0; x < spielfeld[y].length; x++) // x = Spalten
			{
				spielfeld[y][x] = (' ');
				System.out.print(spielfeld[y][x] + " | "); // zwischen den
															// Spalten
															// erscheinen | als
															// optische
															// Trennung, so zu
															// sagen als Geruest
			}
			System.out.println(""); // macht einen Zeilenumbruch nach jeder
									// Zeile, damit das gewuenschte Format 6x7
									// erscheint

		}
		return spielfeld;

	} // ende spielbrettleer()

	/**
	 * ******************************************************** Die Hilfsmethode
	 * <b>spielereingabe()</b> liest eine Zeile von der Konsole ein
	 * 
	 * @return gibt eingelesene Zeile als String zurueck
	 ******************************************************** */

	public static String spielereingabe() { // anfang Funktion spielereingabe()

		String eingabe = ""; // die eingegebenen Zeichen werden als String
								// eingelesen und als Integer zurueck gegeben
		try {
			BufferedReader Tast = new BufferedReader(new InputStreamReader(
					System.in));
			eingabe = Tast.readLine();
		}

		catch (Exception e) {

		}
		return eingabe;

	} // ende der Funktion spielereingabe()

	/**
	 * ******************************************************** Die Hilfsmethode
	 * <b>Integer_parseInt()</b> wandelt einen String in eine ganze Zahl um
	 * 
	 * @param s
	 *            String, der in ein int umgewandelt werden soll
	 * @return Zahlenwert des Strings s, wenn gueltig, sonst -1111
	 * 
	 *         Wenn der String keine ganze Zahl ist, z.B. Ã12aÃ¬ oder Ã12,2Ã¬
	 *         oder Ã12.2Ã¬ wird der Zahlenwert -1111 zurueckgegeben
	 ******************************************************** */

	public static int Integer_parseInt(String eingabe) { // Anfang
															// Integer_parseInt()

		// int asInteger(String eingabe)

		int i;
		try {
			i = Integer.parseInt(eingabe);
		} catch (Exception e) {
			i = -1111;
		}
		return i;
	} // ende Integer_parseInt()

	/**
	 * ******************************************************** In der Methode
	 * <b>spielzug()</b> wird die vom Spieler eingegebene Spaltennummer
	 * ausgewertet, und das entsprechende Spielsteinchen (X oder O) in die
	 * naechst moegliche, unterster, freie Stelle in der eingegebenen Spalte
	 * gesetzt.
	 * 
	 * @param
	 * @return gibt das bearbeitete Spielfeld zurueck
	 ******************************************************** */

	public static char[][] spielzug(char spielfeld[][], int spaltennummer,
			char spielsteinchen) { // Anfang spielzug()

		for (int y = 5; y >= 0; y--)
		// Schleife geht die Zeilen von unten nach oben durch, und Ueberprueft
		// so ob das Feld leer ist (=' ')
		// um in die gewÂ¸nschte Spalte das Spielsteinchen zu packen, an die
		// naechst moegliche, unterste, freie Stelle
		{
			if (spielfeld[y][spaltennummer] == ' ') // Ueberprueft die naechst
													// moegliche, freie,
													// unterste Stelle der
													// eingegebenen Spalte und
													// Ueberprueft ob
			// diese frei ist
			{
				spielfeld[y][spaltennummer] = spielsteinchen; // wenn die Zeile
																// frei ist,
																// wird das
																// Steinchen
																// gesetzt
				y = 0; // durch diese Abbruchbedingung wird die Schleife
						// abgebrochen, wenn das freie Feld ermittelt wurden ist
						// um das Steinchen zu setzen.
			}

		}
		return spielfeld; // Spielfeld wird bearbeitet zurueck gegeben
	} // ende der Funktion spielzug()

	/**
	 * ******************************************************** In der Methode
	 * <b>spielfeldBefuellen()</b> werden die zuvor gezeigten 0, durch die
	 * gesetzten Spielsteinchen X und O ersetzt und angezeigt.
	 * 
	 * @param char spielfeld[][], 2 dim. Array
	 * @return gibt bearbeitetes bzw. befuelltes Spielfeld zurÂ¸ck bennung
	 *         aendern!
	 ******************************************************** */

	public static char[][] spielfeldBefuellen(char spielfeld[][]) { // Anfang
																	// spielfeldBefuellen()
		ueberschrift(7);
		for (char y = 0; y < spielfeld.length; y++) // y = Zeilen
		{
			for (char x = 0; x < spielfeld[y].length; x++) // x = spalten
			{
				System.out.print(spielfeld[y][x] + " | "); // Geruest
			}
			System.out.println(" "); // Zeilenumbruch
		}
		return spielfeld; // gibt befuelltes Spielfeld zurueck

	} // ende spielfeldBefuellen()

	/**
	 * ******************************************************** In der Methode
	 * <b>gueltigeEingabe()</b> wird geprueft, ob die Eingabe des Spielers
	 * gueltig war, sprich ob die eingegebene Zahl zwischen 1 und 7 lag,
	 * entsprechend der Spaltennummer. Falls eine ungueltige
	 * 
	 * @param @ return
	 ******************************************************** */

	public static int gueltigeEingabe(String spalte) { // Anfang
														// gueltigeEingabe()

		// Aufruf der Methode Integer_parseInt()
		int spaltennummer = Integer_parseInt(spalte);

		spaltennummer = spaltennummer - 1; // von der eingegebenen Zahl wird 1
											// subtrahiert, da ein Array bei 0
											// anfaengt
											// damit die gewuenschte Spalte auch
											// "angewaehlt" wird

		while (0 > spaltennummer || spaltennummer >= 7) // hier werden die
														// Tasten definiert, die
														// "zugelassen sind",
														// sprich die Zahlen
														// zwischen 1 und 7
														// (Spaltenanzahl)
		// damit bei ungueltigen Eingabe das Spiel nicht direkt abgebrochen wird
		{
			System.out
					.println("Die Eingabe war ungueltig! Waehlen Sie bitte eine andere Spalte zwischen 1 und 7 aus!");
			// bei falscher Eingabe, wird der Spieler aufgefordert // eine neue
			// Spalte zwischen 1 und 7 zu wâ°hlen
			spalte = spielereingabe();
			// Aufruf der Methode spielereingabe()
			spaltennummer = Integer_parseInt(spalte);
			spaltennummer = spaltennummer - 1;// Die Eingabe (spaltennummer)
												// wird in einen Integer
												// umgewandelt
		}

		return spaltennummer; // gibt die gueltige Eingabe zurueck
	} // ende gueltigeEingabe()

	/**
	 * ******************************************************** In der Methode
	 * <b>gewonnen_vertikal()</b> wird einer der vier Moeglichkeiten das Spiel
	 * zu gewinnen abgeprueft. In der folgenden Methode wird geprueft ob es
	 * jemanden gelungen ist eine vertikale Viererlinie zu bauen. Zunaechst wird
	 * jede Zeile, die aus 6 "Felder" besteht, abgetastet ob in der gewuenschten
	 * Zeile, wo das neue Spielsteinchen hinein gesetzt werden soll, schon 3 der
	 * gleichen Spielsteine vorhanden sind. Wenn dies zu trifft, was in der
	 * if-Anweisung ueberprueft wird, hat der Spieler gewonnen und wird diesem
	 * mitgeteilt. Danach kehrt das Spiel automatisch zum Begrueßungsbildschirm
	 * zurueck.
	 * 
	 * @return
	 * @param spielfeld
	 *            , spielsteinchen, String name, int i (43 - max. Spielzuege)
	 *            ********************************************************
	 */

	public static int gewonnen_vertikal(char spielfeld[][],
			char spielsteinchen, String name, int i) {

		for (int z = 5; z >= 3; z--) // jede Zeile wird geprueft, Index wird
										// immer um 1 erniedrigt (d.h. von oben
										// nach unten wird abgetastet)
		{
			for (int s = 0; s < spielfeld[z].length; s++) // Spalten werden
															// geprueft,
			{
				if (spielfeld[z][s] == spielsteinchen
						&& spielfeld[z - 1][s] == spielsteinchen
						&& spielfeld[z - 2][s] == spielsteinchen
						&& spielfeld[z - 3][s] == spielsteinchen) {
					System.out.println(name + " hat gewonnen!");
					i = 43;
				}
			}
		}
		return i;

	}

	/**
	 * ******************************************************** In der Methode
	 * <b>gewonnen_waagerecht()</b> wird einer der vier Moeglichkeiten das
	 * Spiel zu gewinnen abgeprueft. In der folgenden Methode wird geprueft ob es
	 * jemanden gelungen ist eine waagerechte Viererlinie zu bauen. Zunaechst
	 * werden die Felder der Zeile der laenge nach (spielfeld.length) von 0
	 * abgetastet (immer um 1). Bei den Spalten wird dann geguckt von ob nach
	 * dem eingeworfenen Spielstein noch 3 nebenan sitzen, um abzuchecken, ob es
	 * dem Spieler gelungen ist, eine Viererlinie waagerecht zu bauen. Dies wird
	 * wieder innerhalb der if-Anweisung Ueberprueft.
	 * 
	 * @return
	 * @param spielfeld
	 *            , spielsteinchen, String name, int i (43 - max. Spielzuege)
	 *            ********************************************************
	 */

	public static int gewonnen_waagerecht(char spielfeld[][],
			char spielsteinchen, String name, int i) {

		for (int z = 0; z < spielfeld.length; z++) // geht durch die kompletten
													// Zeilen durch
		{
			for (int s = 0; s <= 3; s++) // geht Spalten durch, guckt ob schon 3
											// vorhandene Spielsteine der
											// gleichen Art in der drin sind
			{
				if (spielfeld[z][s] == spielsteinchen
						&& spielfeld[z][s + 1] == spielsteinchen
						&& spielfeld[z][s + 2] == spielsteinchen
						&& spielfeld[z][s + 3] == spielsteinchen) // Ueberpruefung
				{
					System.out.println(name + " hat gewonnen!");
					i = 43; // Abbruchbedingung
				}
			}
		}
		return i;
	}

	/**
	 * ******************************************************** In der Methode
	 * <b>gewonnen_diagonal_1()</b> wird einer der vier Moeglichkeiten das Spiel
	 * zu gewinnen abgeprueft. In der folgenden Methode wird geprueft ob es
	 * jemanden gelungen ist eine diagonale Viererlinie zu bilden, und zwar von
	 * rechts unten nach links oben. Dies wird mithilfe einer Art
	 * Steigungsdreieck abgeprueft, in dem zu erst 2 "Schritte" in den Zeilen
	 * und 3 "Schritte" in den Spalten abgegangen werden, und diese
	 * zusammengesetzt werden und zudem wird geschaut ob dort ein Spielstein
	 * liegt.
	 * 
	 * @return
	 * @param spielfeld
	 *            , spielsteinchen, String name, int i
	 *            ********************************************************
	 */

	public static int gewonnen_diagonal_1(char spielfeld[][],
			char spielsteinchen, String name, int i) {

		for (int z = 0; z <= 2; z++) // durch die Zeilen gehen
		{
			for (int s = 0; s <= 3; s++) // durch die Spalten gehen
			{
				if (spielfeld[z][s] == spielsteinchen
						&& spielfeld[z + 1][s + 1] == spielsteinchen
						&& spielfeld[z + 2][s + 2] == spielsteinchen
						&& spielfeld[z + 3][s + 3] == spielsteinchen) // Werte
																		// werden
																		// zusammengesetzt
																		// und
																		// geguckt
																		// ob
																		// dort
																		// ein
																		// Spielstein
																		// liegt
				{
					System.out.println(name + " hat gewonnen!"); // wenn dies
																	// zutrifft,
																	// hat der
																	// Spieler
																	// gewonnen
																	// und das
																	// SPiel
																	// wird
																	// abgebrochen,
																	// man kehrt
																	// zum
																	// Begrueungsbildschirm
																	// zurueck
					i = 43; // Abbruchbedingun
				}
			}
		}
		return i;
	}

	/**
	 * ******************************************************** In der Methode
	 * <b>gewonnen_diagonal_2()</b> wird einer der vier Moeglichkeiten das Spiel
	 * zu gewinnen abgeprueft. In der folgenden Methode wird geprueft ob es
	 * jemanden gelungen ist eine diagonale Viererlinie zu bilden, rechts oben
	 * nach links unten .
	 * 
	 * @param spielfeld
	 *            , spielsteinchen, String name, int i
	 *            ********************************************************
	 */

	public static int gewonnen_diagonal_2(char spielfeld[][],
			char spielsteinchen, String name, int i) {

		for (int z = 0; z <= 2; z++) // zeilen durch gehen
		{
			for (int s = 6; s >= 3; s--) // Spalten durchgehen hier; um einen
											// Index verringern
			{
				if (spielfeld[z][s] == spielsteinchen
						&& spielfeld[z + 1][s - 1] == spielsteinchen
						&& spielfeld[z + 2][s - 2] == spielsteinchen
						&& spielfeld[z + 3][s - 3] == spielsteinchen) {
					System.out.println(name + " hat gewonnen!"); // Spieler hat
																	// gewonnen,
																	// Spiel
																	// wird
																	// abgebrochen,
																	// man kehrt
																	// zum
																	// BegrÂ¸ï¬ungsbildschirm
																	// zurÂ¸ck
					i = 43;
				}
			}
		}
		return i;
	}

	public static int gewonnen(char spielfeld[][], char spielsteinchen,
			String name, int i) {

		i = gewonnen_vertikal(spielfeld, spielsteinchen, name, i); // Aufruf der
																	// Funktion
																	// gewonnen_vertikal()
		i = gewonnen_waagerecht(spielfeld, spielsteinchen, name, i); // Aufruf
																		// der
																		// Funktion
																		// gewonnen_waagerecht()
		i = gewonnen_diagonal_1(spielfeld, spielsteinchen, name, i); // Aufruf
																		// der
																		// Funktion
																		// gewonnen_diagonal_1()
		i = gewonnen_diagonal_2(spielfeld, spielsteinchen, name, i); // Aufruf
																		// der
																		// Funktion
																		// gewonnen_diagonal_2()

		return i;
	}

	/**
	 * ******************************************************** In der Methode
	 * <b>volleSpalte()</b> wird Ueberprueft ob in der vom Spieler ausgewaehlten
	 * Spalte noch Platz fuer sein Spielsteinchen ist. Mithilfe einem Vergleich
	 * (boolean) wird Ueberprueft ob in den Zeilen der gewuenschten Spalte noch
	 * ein Feld frei wird, indem auf =! ' ' (ungleich) geprueft wird, d.h. es
	 * wird geguckt ob alle Felder ungleich Leerzeichen und somit voll waeren.
	 * Dann wird mithilfe einer if-Anweisung die Bedingung ungleich Leerzeichen
	 * ueberprueft, wenn dies zu trifft, wird eine entsprechenende Mitteilung
	 * auf dem Bildschirm erscheinen, und der Spieler wird aufgefordert eine
	 * neue bzw. andere Spalte auszuwaehlen.
	 * 
	 * 
	 * @param spaltennummer
	 *            , spielfeld
	 * @return gibt die eingegebene spaltennummer zurueck
	 *         ********************************************************
	 */

	public static int volleSpalte(int spaltennummer, char spielfeld[][]) {
		boolean spaltevoll = spielfeld[0][spaltennummer] != ' '; // hier wird
																	// geprueft,
																	// ob die
																	// oberste
																	// Zeile der
																	// gewuenschten
																	// Spalte
																	// schon
																	// besetzt
																	// ist
		if (spaltevoll) // trifft dies zu, erfolgt die untenstehende Ausgabe
		{
			System.out
					.println("Die von dir gewaehlte Spalte ist voll. Waehle eine andere Spalte zwischen 1 und 7 aus!");
			String spalte = spielereingabe(); // hier werden die neu
												// eingegebenen "Zeichen"
												// entgegengenommen
			spaltennummer = gueltigeEingabe(spalte); // hier wird geprueft ob
														// die Eingabe korrekt
														// ist
			return spaltennummer; // die neue Spaltennummer wird zurueckgegeben
									// und erneut ueberprueft
		}
		return spaltennummer; // Spaltennummer wird zurueck gegeben
	}

	/**
	 * ******************************************************** In der Prozedur
	 * <b>spielbrettVoll</b> wird mittels einem Vergleich (boolean) und einer
	 * if-Anweisung die oberste Zeile der 7 Spalten abgeprueft, ob diese ungleich
	 * = ' ' sind, d.h. es wird geguckt ob die oberste Zeile voll ist. Wenn dies
	 * zutrifft, erscheint eine Ausgabe, und das Spiel wird beendet.
	 * 
	 * @param spielfeld
	 * @return ********************************************************
	 */

	public static void spielbrettVoll(char spielfeld[][]) { // anfang
															// spielbrettVoll()

		// for(int i = 0; i < spielfeld[0].length; spielfeld[0][i++])

		boolean vollesBrett = spielfeld[0][0] != ' ' && spielfeld[0][1] != ' '
				&& spielfeld[0][2] != ' ' && spielfeld[0][3] != ' '
				&& spielfeld[0][4] != ' ' && spielfeld[0][4] != ' '
				&& spielfeld[0][5] != ' ' && spielfeld[0][6] != ' ';
		if (vollesBrett) {
			System.out
					.println("Das Spielbrett ist voll! Das Spiel endet unentschieden!");
		}

	}

	/**
	 * ******************************************************** In der Haupt-
	 * bzw Mainmethode werden nun die zuvor beschriebenen Methoden aufgerufen
	 * und so eingesetzt das der Spielablauf entsteht. Zuerst wird das Spielfeld
	 * aufgerufen, indem ein 2 dim. Array erstellt wird, welche die Maßee 6x7
	 * hat. Die Schleife ist eine while-Schleife, welche abgebrochen wird,
	 * sobald der Spieler innerhalb der if-else-Anweisung, welche den
	 * Begrueßungsbildschirm darstellt, die Taste 2 + Enter gedrueckt hat. So
	 * kann der Spieler das Spiel verlassen. Wie schon erwâ°hnt wird innerhalb
	 * der if-else-Anweisung der Begrueßungsbildschirm "aktiviert". Der Benutzer
	 * hat die Chance das Spiel zu starten, zu verlassen oder sich die Hilfe
	 * anzeigen zu lassen, indem er die angegebenen Zahlen fuer die
	 * entsprechenden Anweisungen drueckt. Wenn der Benutzer die 1 + Enter
	 * eingibt, wird das Spiel gestartet. Mithilfe einer Modulo Rechnung wird
	 * entschieden das bei ungeraden Spielzuegen Spieler 1 und bei geraden
	 * Spieler 2 dran ist.
	 * ********************************************************
	 */

	public static void main(String args[]) { // anfang main()

		char spielfeld[][];
		spielfeld = new char[6][7];
		int j = 0;
		while (j != 2) // while-Schleife, die abgebrochen wird, sobald die Taste
						// 2 gedrueckt wird - so kann man das Spiel verlassen
		{ // Beginn while-Schleife
			System.out
					.println("Herzlich Willkommen zu Vier Gewinnt."
							+ "\n"
							+ "Bitte geben Sie eine 1 ein und bestaetigen Sie diese mit Enter um das Spiel zu beginnen."
							+ "\n"
							+ "Bitte geben Sie eine 2 ein und bestaetigen Sie diese mit Enter um das Spiel zu verlassen"
							+ "\n"
							+ "Bitte geben Sie eine 3 ein und bestaetigen Sie diese mit Enter um die HILFE aufzurufen"
							+ "\n" + "Viel Erfolg und vorallem viel Spass!");

			String eingabe = spielereingabe(); // Aufruf der Methode
												// spielereingabe()
			j = Integer.parseInt(eingabe); // wandelt den erzeugten String in
											// einen Integer(int) um zum
											// weiterverarbeiten

			if (j == 1) // Spiel starten
			{ // Spielzuege - Spielablauf
				spielfeld = spielbrettleer(spielfeld);
				for (int i = 0; 43 > i; i++) // Schleife, zaehlt hoch bis 43,
												// weil nur 42 moegliche
												// Spielzuege erlaubt sind
				{ // Beginn for-Schleife
					if (i % 2 == 0) // ungerader Zug - Spieler 1 ist dran
					{
						System.out
								.println("Spieler 1 ist am Zug, bitte geben Sie die gewuenschte Spalte zwischen 1 und 7 ein, und bestaetigen diese mit Enter.");
						i = spielablauf(spielfeld, "Spieler 1", 'X', i);
						// String spalte = spielereingabe(); // Aufruf der
						// Funktion spielereingabe()
						// int spaltennummer = gueltigeEingabe(spalte); //
						// Aufruf der Funktion gueltigeEingabe
						// volleSpalte(spaltennummer, spielfeld); // Aufruf der
						// Funktion volleSpalte()
						// spielfeld = spielzug(spielfeld, spaltennummer, 'X' );
						// // Aufruf der Funktion spielzug()
						// i = gewonnen (spielfeld, i);
						// spielbrettVoll (spielfeld); // Aufruf der Prozedur
						// spielbrettVoll()
					} else if (i % 2 != 0) // gerader Zug, Spieler 2 ist dran
					{
						System.out
								.println("Spieler 2 ist am Zug, bitte geben Sie die gewuenschte Spalte zwischen 1 und 7 ein, und bestaetigen diese mit Enter.");
						i = spielablauf(spielfeld, "Spieler 2", 'O', i);
						// String spalte = spielereingabe();
						// int spaltennummer = gueltigeEingabe(spalte);
						// volleSpalte(spaltennummer, spielfeld); // Aufruf der
						// Funktion volleSpalte()
						// spielfeld = spielzug(spielfeld, spaltennummer, 'O' );
						// i = gewonnen (spielfeld, i);
						// spielbrettVoll (spielfeld); // Aufruf der Funktion
						// spielbrettVoll()
					}
					spielfeld = spielfeldBefuellen(spielfeld);
				} // ende for-Schleife
			} // ende Spiel starten
			else if (j == 3) // HILFE aufrufen
			{
				hilfe();
			} else if (j != 1 && j != 3 && j != 2) {
				System.out.println("Ungueltige Eingabe!");
			}

		} // ende while-Schleife

	} // ende main()

} // ende Class