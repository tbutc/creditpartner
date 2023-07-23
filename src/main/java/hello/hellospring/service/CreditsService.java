package hello.hellospring.service;

import hello.hellospring.domain.Classes;
import hello.hellospring.domain.Credit;
import hello.hellospring.domain.Subject;
import org.springframework.context.annotation.Configuration;
import hello.hellospring.repository.creditsRepository;

import java.util.List;


@Configuration
public class CreditsService {
    private final creditsRepository creditsRepository;

    public CreditsService(hello.hellospring.repository.creditsRepository creditsRepository) {
        this.creditsRepository = creditsRepository;
    }
    public List<Credit> showAll(int semester) {
        return creditsRepository.find(semester);
    }
    public List<Classes> showAll_class(int cid){
        return creditsRepository.find_class((cid));
    }
    public List<Subject> showAll_subject(int sid){
        return creditsRepository.find_subject((sid));
    }
}
