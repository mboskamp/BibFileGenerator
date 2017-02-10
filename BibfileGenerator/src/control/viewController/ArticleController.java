package control.viewController;

import org.jbibtex.BibTeXEntry;
import org.jbibtex.Key;

import control.json.JSONUtils;
import control.net.NetUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import model.Article;
import view.bibComponent.EntryTextField;

/**
 * Controller that handles the input of user data about an
 * {@link BibTeXEntry#TYPE_ARTICLE article}.
 * 
 * @author Miklas Boskamp
 */
public class ArticleController extends AbstractPrintEntryController {

	@FXML
	public Button doiSearchButton;
	
	@FXML
	public EntryTextField doi;
	
	@FXML
	public EntryTextField journal;

	@FXML
	public EntryTextField volume;

	@FXML
	public EntryTextField number;

	@FXML
	public EntryTextField pages;

	@Override
	public void initialize() {
		//Do nothing
	}

	@Override
	public Key getEntryType() {
		return BibTeXEntry.TYPE_ARTICLE;
	}
	
	@FXML
	public void searchDOI(){
		String response = NetUtils.fireDOIRequest(doi.getText()); //"10.1016/j.bbapap.2016.11.003"
		if(!response.equals(NetUtils.ERROR)){
			Article article = JSONUtils.parseJSONDOIResponse(response);
			author.setText(article.getAuthors());
			title.setText(article.getTitle());
			journal.setText(article.getJournal());
			year.setText(article.getYear());
			volume.setText(article.getVolume());
			number.setText(article.getNumber());
			pages.setText(article.getPages());
			month.setText(article.getMonth());
			note.setText(article.getNote());
		}else{
			//TODO ERROR
		}
	}
}