package control.error;

public enum Error {
	PARSING_ERROR {
		@Override
		public String getErrorMessage() {
			return "Während der Verarbeitung ist ein Fehler aufgetreten.";
		}
	}, URL_FORMAT_ERROR {
		@Override
		public String getErrorMessage() {
			return "Ein Fehler mit der URL Formatierung ist aufgetraten.";
		}
	}, URL_INVALID_ERROR {
		@Override
		public String getErrorMessage() {
			return "Die Aufgerufene URL ist nicht valide.";
		}
	}, NO_CONNECTION_ERROR {
		@Override
		public String getErrorMessage() {
			return "Keine Verbindung zur Datenbank möglich! Bitte überprüfen Sie Ihre Internetverbindung.";
		}
	}, ILLEGAL_ACCESS_ERROR {
		@Override
		public String getErrorMessage() {
			return "Ein Zugriffsfehler ist aufgetreten.";
		}
	}, FILE_NOT_FOUND_ERROR {
		@Override
		public String getErrorMessage() {
			return "Die Datei konnte nicht gefunden werden.";
		}
	}, FORMATTING_ERROR {
		@Override
		public String getErrorMessage() {
			return "Die Datei konnte nicht korrekt formatiert werden.";
		}
	}, VIEW_LOAD_ERROR {
		@Override
		public String getErrorMessage() {
			return "Ein Anzeigefehler ist aufgetreten.";
		}
	}, EXPORT_ERROR {
		@Override
		public String getErrorMessage() {
			return "Beim Export ist ein Fehler aufgetreten.";
		}
	}, INTERNAL_ERROR {
		@Override
		public String getErrorMessage() {
			return "Ein interner Fehler ist aufgetreten";
		}
	};
	
	public abstract String getErrorMessage();
}