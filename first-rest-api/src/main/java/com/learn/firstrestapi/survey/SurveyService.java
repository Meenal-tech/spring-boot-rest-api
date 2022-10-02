package com.learn.firstrestapi.survey;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class SurveyService {

	private static List<Survey> surveys = new ArrayList<>();

	static {

		Question question1 = new Question("Question1", "Most Popular Cloud Platform Today",
				Arrays.asList("AWS", "Azure", "Google Cloud", "Oracle Cloud"), "AWS");
		Question question2 = new Question("Question2", "Fastest Growing Cloud Platform",
				Arrays.asList("AWS", "Azure", "Google Cloud", "Oracle Cloud"), "Google Cloud");
		Question question3 = new Question("Question3", "Most Popular DevOps Tool",
				Arrays.asList("Kubernetes", "Docker", "Terraform", "Azure DevOps"), "Kubernetes");

		List<Question> questions = new ArrayList<>(Arrays.asList(question1, question2, question3));

		Survey survey1 = new Survey("Survey1", "My Favorite Survey", "Description of the Survey", questions);
		Survey survey2 = new Survey("Survey2", "My Favorite Survey", "Description of the Survey", questions);

		surveys.add(survey1);
		surveys.add(survey2);
	}

	public List<Survey> retrieveAllSurveys() {
		return surveys;
	}

	public Survey retrieveSurveyById(String surveyId) {

		Survey survey2 = surveys.stream().filter(survey -> survey.getId().equalsIgnoreCase(surveyId)).findFirst().get();

		if (survey2.getTitle().isEmpty())
			return null;

		return survey2;
	}

	public List<Question> retrieveQuestionsBySurveyId(String surveyId) {

		Survey survey1 = surveys.stream().filter(survey -> survey.getId().equalsIgnoreCase(surveyId)).findFirst().get();

		List<Question> questions = survey1.getQuestions();
		System.out.println("the questions in the list : " + questions.size());
		return questions;
	}

	public List<Question> addQuestion(String surveyId, Question question) {

		List<Question> questions = retrieveQuestionsBySurveyId(surveyId);
		questions.add(question);

		return questions;
	}

	public String deletesurveyQuestion(String surveyId, String questionId) {

		List<Question> questions = retrieveQuestionsBySurveyId(surveyId);
		if (questions == null)
			return null;

		Predicate<? super Question> predicate = q -> q.getId().equalsIgnoreCase(questionId);

		questions.removeIf(predicate);
		return questionId;

	}

	public void updateSurveyQuestion(String surveyId, String questionId, Question question) {

		deletesurveyQuestion(surveyId, questionId);
		addQuestion(surveyId, question);

	}

}
