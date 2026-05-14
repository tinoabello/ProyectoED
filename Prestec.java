package ProjecteBiblio.ProyectoED;

public class Prestec {
	private Usuari usuari;
	private Llibre llibre;
	private String dataPrestec; // ***MODIFICAT*** String simple, ex: "14/05/2026"
	private String dataRetorn; // ***MODIFICAT*** String simple, ex: "28/05/2026"

	public Prestec(Usuari usuari, Llibre llibre, String dataPrestec) {
		this.usuari = usuari;
		this.llibre = llibre;
		this.dataPrestec = dataPrestec;
		this.dataRetorn = calcularDataRetorn(dataPrestec); // ***MODIFICAT*** Càlcul manual sense imports de dates
	}

	// ***MODIFICAT*** Calcula data retorn (+14 dies) de forma manual amb Strings
	private String calcularDataRetorn(String data) {
		// Separem dia, mes i any
		String[] parts = data.split("/");
		int dia = Integer.parseInt(parts[0]);
		int mes = Integer.parseInt(parts[1]);
		int any = Integer.parseInt(parts[2]);

		// Dies que té cada mes
		int[] diesPerMes = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

		// Comprovar any de traspàs
		if ((any % 4 == 0 && any % 100 != 0) || (any % 400 == 0)) {
			diesPerMes[1] = 29;
		}

		// Sumem 14 dies
		dia = dia + 14;

		// Si ens passem dels dies del mes, passem al mes següent
		while (dia > diesPerMes[mes - 1]) {
			dia = dia - diesPerMes[mes - 1];
			mes++;
			// Si ens passem de desembre, passem a l'any següent
			if (mes > 12) {
				mes = 1;
				any++;
			}
		}

		// Retornem amb format dd/MM/yyyy
		String diaStr = (dia < 10) ? "0" + dia : "" + dia;
		String mesStr = (mes < 10) ? "0" + mes : "" + mes;
		return diaStr + "/" + mesStr + "/" + any;
	}

	public Usuari getUsuari() { return usuari; }
	public Llibre getLlibre() { return llibre; }
	public String getDataPrestec() { return dataPrestec; }
	public String getDataRetorn() { return dataRetorn; }

	@Override
	public String toString() {
		return "Prestec: " + usuari.getNom() + " - " + llibre.getTitol()
				+ " | Data prestec: " + dataPrestec
				+ " | Data retorn: " + dataRetorn;
	}
}