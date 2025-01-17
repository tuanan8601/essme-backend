package org.vietsearch.essme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.vietsearch.essme.model.corporate_title.Corporate;
import org.vietsearch.essme.repository.CorporateRepository;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/corporates")
public class CorporateTitleController {
    @Autowired
    private CorporateRepository corporateRepository;

    @GetMapping("/search")
    public List<Corporate> search(@RequestParam("name") String name) {
        TextCriteria criteria = TextCriteria.forDefaultLanguage().matchingPhrase(name);
        List<Corporate> list = corporateRepository.findBy(criteria);
        if (list.isEmpty())
            return corporateRepository.findByNameStartsWithIgnoreCase(name);
        return list;
    }

    @GetMapping
    public List<Corporate> getCorporates(@RequestParam(value = "limit", defaultValue = "-1") int limit) {
        if (limit == -1) {
            return corporateRepository.findAll();
        }
        return corporateRepository.findAll(PageRequest.of(0, limit)).getContent();
    }

    @GetMapping("/{id}")
    public Corporate findById(@PathVariable("id") String id) {
        return corporateRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Corporate not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Corporate create(@Valid @RequestBody Corporate corporate) {
        checkExistsByName(corporate.getName());
        return corporateRepository.save(corporate);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") String id) {
        if (corporateRepository.existsById(id)) {
            corporateRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Corporate not found");
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Corporate update(@PathVariable("id") String id, @Valid @RequestBody Corporate corporate) {
        corporateRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Corporate not found"));
        checkExistsByName(corporate.getName());
        corporate.set_id(id);
        return corporateRepository.save(corporate);
    }

    private void checkExistsByName(String name) {
        if (corporateRepository.findByNameIgnoreCase(name) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Corporate already exists");
        }
    }
}
