package hello.hellospring.controller;

import hello.hellospring.domain.Classes;
import hello.hellospring.domain.Credit;
import hello.hellospring.domain.Member;
import hello.hellospring.domain.Subject;
import hello.hellospring.service.CreditsService;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CreditsController {

    @PostMapping("/credits")
    public String showSemesterForm(@RequestParam("dropdown") String semester) {
        int convertedSemester = convertSemester(semester);
        return "redirect:/credits/"+convertedSemester;
    }
    private final CreditsService creditsService;
    @Autowired
    public CreditsController(CreditsService creditsService) {
        this.creditsService = creditsService;
    }

    @GetMapping("/credits/{semester}") //이수내역 입력하기 페이지 출력
    public String showSemesterData(@PathVariable int semester, Model model) { //변수 -> semester : url에서 받아옴

        List<String> dropdownOptions = getDropdownOptions_semester();
        //드롭다운 데이터 받아오는 함수
        //dropdownOptions = {"1학년 1학기" , ... , "3학년 2학기"}
        List<String> dropdownOptions_subject = getDropdownOptions_subject();
        // 드롭다운 데이터 받아오는 함수2
        //dropdownOptions_subject = {"국어", "수학" , ... }

        model.addAttribute("dropdownOptions_semester", dropdownOptions);
        model.addAttribute("dropdownOptions_subject", dropdownOptions_subject);
        //model.addAttribute로 html에서 받아쓸 수 있게됨. 수정 필요

        List<Credit> credits = creditsService.showAll(semester); // credits = class_list 테이블에서 데이터 받아옴
        List<String> classNames = new ArrayList<>(); // class 이름 ex) '화법과 작문' ...
        List<String> subjects = new ArrayList<>(); // subject 이름 ex) '국어' ...

        for (Credit credit : credits) { //classNames, subjects 이름 받아오는 반복문
            int cid = credit.getCid();
            List<Classes> data = creditsService.showAll_class(cid);
            classNames.add((data.get(0).getName()));
            //classNames에 class 이름 받아옴. ex) '화법과 작문' ...

            List<Subject> data2 = creditsService.showAll_subject(data.get(0).getSid());
            subjects.add(data2.get(0).getName());
            //subjects에 subject 이름 받아옴. ex) '국어' ...
        }

        model.addAttribute("semester", semester);
        model.addAttribute("credits", credits);
        model.addAttribute("class", classNames);
        model.addAttribute("subjects", subjects);

        return "credits_selected";
    }


    @PostMapping("/credits/{semester}")
    public String getCreditForm(@RequestParam("dropdown_subject") String subject, @RequestParam("dropdown") String semester) {
        int convertedSemester = convertSemester(semester); // '1학년 1학기' -> '1'(int) 로 변환해주는 함수
        return "redirect:/credits/"+convertedSemester; // '1학년 1학기' -> credits/1 // '3학년 2학기' -> credits/6
    }


    // 드롭다운 데이터를 가져오는 메소드 (1학년 1학기, ... , 3학년 2학기)
    private List<String> getDropdownOptions_semester() {
        List<String> dropdownOptions = new ArrayList<>();

        // 1학년 1학기부터 3학년 2학기까지의 선택지 생성
        for (int year = 1; year <= 3; year++) {
            for (int semester = 1; semester <= 2; semester++) {
                String option = year + "학년 " + semester + "학기";
                dropdownOptions.add(option);
            }
        }
        return dropdownOptions;
    }

    // 드롭다운 데이터를 가져오는 메소드 (국어, 수학, 영어, ...)
    private List<String> getDropdownOptions_subject(){
        List<String> dropdownOptions = new ArrayList<>();
        List<Subject> subjects = creditsService.showAll_subject(-1);

        for(Subject subject : subjects){
            dropdownOptions.add(subject.getName());
        }

        return dropdownOptions;
    }

//    private List<String> getDropdownOptions_class(){
//        List<String> dropdownOptions = new ArrayList<>();
//        List<Classes> classes = creditsService.showAll_class(-1);
//
//        for(classes classes : classes){
//            dropdownOptions.add(subject.getName());
//        }
//
//        return dropdownOptions;
//    }

    private int convertSemester(String semester) {
        switch (semester) {
            case "1학년 1학기":
                return 1;
            case "1학년 2학기":
                return 2;
            case "2학년 1학기":
                return 3;
            case "2학년 2학기":
                return 4;
            case "3학년 1학기":
                return 5;
            case "3학년 2학기":
                return 6;
            default:
                throw new IllegalArgumentException("Invalid semester: " + semester);
        }
    }
}