package com.learn.firstrestapi.survey;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class SurveyResource {

	@Autowired
	SurveyService service;
	private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	// below four methods are for GET requests.

	@RequestMapping("/surveys")
	public List<Survey> retrieveAllSurveys() {
		return service.retrieveAllSurveys();
	}

	@RequestMapping("/surveys/{surveyId}")
	public Survey retrieveSurveyById(@PathVariable String surveyId) {

		Survey survey = service.retrieveSurveyById(surveyId);

		if (survey == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);

//		 throwing the error code, 
//		so that the web displayes the error according to the status code.

		return survey;
	}

	@RequestMapping(value = "/surveys/{surveyId}/questions", method = RequestMethod.GET)
	public List<Question> retieveQuestionsById(@PathVariable String surveyId) {

//		if (service.retrieveQuestionsBySurveyId(surveyId).isEmpty())
//			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);

		return service.retrieveQuestionsBySurveyId(surveyId);
	}

	@RequestMapping("/surveys/{surveyId}/questions/{questionId}")
	public Question retrieveQuestionByQuestionId(@PathVariable String surveyId, @PathVariable String questionId) {

		List<Question> questions = service.retrieveQuestionsBySurveyId(surveyId);

		if (questions.isEmpty())
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);

		return questions.stream().filter(q -> q.getId().equalsIgnoreCase(questionId)).findFirst().get();
	}

	// below method is for POST requests.
	@RequestMapping(value = "/surveys/{surveyId}/questions", method = RequestMethod.POST)
	public ResponseEntity<Object> addQuestion(@PathVariable String surveyId, @RequestBody Question question) {

		service.addQuestion(surveyId, question);
		return ResponseEntity.created(null).build();

	}

	// deleting the question.
	@RequestMapping(value = "/surveys/{surveyId}/questions/{questionId}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deletesurveyQuestion(@PathVariable String surveyId,
			@PathVariable String questionId) {

		String deleted = service.deletesurveyQuestion(surveyId, questionId);
		if (deleted == null) {
			LOGGER.info("The error is thrown");
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
		LOGGER.info("deleted executed");
		return ResponseEntity.noContent().build();

	}

	// update the Question, using PUT request.

	@RequestMapping(value = "/surveys/{surveyId}/questions/{questionId}", method = RequestMethod.PUT)
	public void updateSurveyQuestion(@PathVariable String surveyId, @PathVariable String questionId,
			@RequestBody Question question) {

		service.updateSurveyQuestion(surveyId, questionId, question);

	}

}
