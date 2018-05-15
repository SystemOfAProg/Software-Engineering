package application.logic.questionmanager.impl;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import application.logic.gamemodel.impl.questions.Answer;
import application.logic.gamemodel.impl.questions.Question;
import application.logic.gamemodel.impl.questions.QuestionCategory;

/**
 * Reading questions from JSON File and parse them to Question-Objects
 * 
 * @author fabian
 *
 */
public class QuestionReader {

	private Question[] questions;
	
	private String defaultFileLocation = "./resources/questions.json";
	private String fileLocation = "";
	
	public QuestionReader(String fileLocation) {
		if(fileLocation.trim().length() == 0) {
			this.fileLocation = this.defaultFileLocation;			
		} else {
			this.fileLocation = fileLocation;
		}
		this.refreshQuestions();
	}
	
	public Question[] getQuestion() {
		return this.questions;
	}
	
	// Reread questions from file and build question objects
	public boolean refreshQuestions() {
		try {
			String questionsJSON = this.readFile(this.fileLocation, StandardCharsets.UTF_8);
			buildQuestions(questionsJSON);
			return true;
		} catch (Exception e) {
			System.err.println("The questions could not be refreshed. Please check if the following file exists and if it is correct:");
			System.err.println("Working Directory =       " + System.getProperty("user.dir"));
			System.err.println("Requested File Location = " + this.fileLocation);
			e.printStackTrace(System.out);
			return false;
		}
	}
	
	private void buildQuestions(String file) {
		JsonObject jsonObject = new JsonParser().parse(file).getAsJsonObject();
		QuestionCategory[] categories = this.parseCategories(jsonObject);
		this.questions  = this.parseQuestions(jsonObject, categories);
	}
	
	private QuestionCategory[] parseCategories(JsonObject jsonObject) {
		JsonArray c = jsonObject.getAsJsonArray("categories");
		QuestionCategory[] qcs = new QuestionCategory[c.size()];
		for(int i=0; i<c.size(); i++) {
			JsonElement co = c.get(i);
			if(co.isJsonObject()) {
				String categoryName = ((JsonObject)co).get("name").getAsString();
				qcs[i] = new QuestionCategory(categoryName);
			}
		}
		return qcs;
	}
	
	private Question[] parseQuestions(JsonObject jsonObject, QuestionCategory[] categories) {
		JsonArray c = jsonObject.getAsJsonArray("questions");
		Question[] questions = new Question[c.size()];
		for(int i=0; i<c.size(); i++) {
			JsonElement co = c.get(i);
			if(co.isJsonObject()) {
				JsonObject cob = (JsonObject)co;
				String questionSentence = cob.get("question").getAsString();
				Answer[] answers = this.parseAnswers(cob.get("answers").getAsJsonArray());
				int correctAnswerIndex = cob.get("correct").getAsInt();
				int categoryIndex = cob.get("category").getAsInt();
				QuestionCategory category = categories[categoryIndex];
				questions[i] = new Question(questionSentence, category, answers, correctAnswerIndex);
			}
		}
		return questions;
	}
	
	private Answer[] parseAnswers(JsonArray ja) {
		Answer[] answers = new Answer[ja.size()];
		for(int i=0; i<ja.size(); i++) {
			String answerSentence = ja.get(i).getAsString();
			answers[i] = new Answer(answerSentence);
		}
		return answers;
	}
	
	private String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
	
}
