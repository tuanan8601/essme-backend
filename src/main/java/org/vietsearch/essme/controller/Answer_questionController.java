package org.vietsearch.essme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.vietsearch.essme.model.answer_question.Question;
import org.vietsearch.essme.repository.Answer_questionRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class Answer_questionController {
    @Autowired
    private Answer_questionRepository questionRepository;

    @GetMapping("/{id}")
    public Question getQuestionbyId(@PathVariable("id") String id) {return questionRepository.findById(id).get();}

    @GetMapping
    public List<Question> getQuestions(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "20") int size, @RequestParam(value = "sort", defaultValue = "createdAt") String sortAttr, @RequestParam(value = "desc", defaultValue = "false") boolean desc) {
        Sort sort = Sort.by(sortAttr);
        if (desc)
            sort = sort.descending();

        Page<Question> questionPage = questionRepository.findAll(PageRequest.of(page, size, sort));
        return questionPage.getContent();
    }

    @GetMapping("/totalpages")
    public int getQuestionTotalPage(@RequestParam(value = "size", defaultValue = "20") int size) {
        Page<Question> questionPage = questionRepository.findAll(PageRequest.of(0,size));
        return questionPage.getTotalPages();
    }

    @GetMapping("/topic/{topic}")
    public List<Question> getQuestionsbyTopic(@PathVariable("topic") String topic,@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "20") int size, @RequestParam(value = "sort", defaultValue = "createdAt") String sortAttr, @RequestParam(value = "desc", defaultValue = "false") boolean desc) {
        Sort sort = Sort.by(sortAttr);
        if (desc)
            sort = sort.descending();

        Page<Question> questionPage = questionRepository.findByTopic(topic,PageRequest.of(page, size, sort));
        return questionPage.getContent();
    }

    @GetMapping("/topic/{topic}/totalpages")
    public int getQuestionbyTopicTotalPage(@PathVariable("topic") String topic, @RequestParam(value = "size", defaultValue = "20") int size) {
        Page<Question> questionPage = questionRepository.findByTopic(topic,PageRequest.of(0, size));
        return questionPage.getTotalPages();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Question addQuestion(@RequestHeader(value = "accept-language", required = false, defaultValue = "en") String lang, @Valid @RequestBody Question question) {
        System.out.println(lang);
        questionRepository.save(question);
        return questionRepository.save(question);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Question updateQuestion(@PathVariable("id") String id,@Valid @RequestBody Question question){
        if (questionRepository.existsById(id)) {
            question.set_id(id);
            questionRepository.save(question);
            return question;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Question not found", null);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteQuestion(@PathVariable("id") String id){
        if (questionRepository.existsById(id)) {
            questionRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Question not found", null);
        }
    }
}
