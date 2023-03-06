package sg.edu.nus.iss.refactorproject12.controller;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.iss.refactorproject12.exception.RandNoException;
import sg.edu.nus.iss.refactorproject12.model.Generate;

@Controller
// @RequestMapping(path="/")
@RequestMapping(path="/")
public class AppController {

    @GetMapping(path="/form")
    public String showForm(Model m){
        Generate g = new Generate();
        m.addAttribute("generatorObj", g);
        return "form";
    }

    // @GetMapping(path="/result")
    // public String generator(@RequestParam int value, Model m ){
    //     int x = value*2;
    //     m.addAttribute("newNum", x);
    //     return "result";
    // }

    @GetMapping(path="/result")
    public String generator(@RequestParam int value, Model m ){
        this.randgenerate(m, value);
        // m.addAttribute("newNum", x);
        return "result";
    }

    private void randgenerate(Model m, int value){
        int maxGenNo = 31;
        Integer[] numbers = new Integer[value];
    
        if(value < 1 || value > maxGenNo){
            throw new RandNoException();
        }

        int elem = 0;
        Random rand = new Random();

        //Use Set to avoid duplicates
        Set<Integer> uniqueNum = new LinkedHashSet<>();
        while (uniqueNum.size() < value){
            uniqueNum.add(rand.nextInt(maxGenNo));
        }
        
        Object[] uniqueNumArray = uniqueNum.toArray();
        String[] fileName = new String[value];
        for(elem=0; elem < value; elem++){
            System.out.println(">>>>>>>" + numbers[elem]);
            fileName[elem] = "number"+uniqueNumArray[elem]+".jpg";
            System.out.println(fileName[elem]);
        }



        //**Array allows for duplicate values. 
        // for(elem = 0; elem < value; elem++){
        //     numbers[elem] = rand.nextInt(maxGenNo);
        // }

        //**For arrays approach. Will have duplicates */
        // for(elem=0; elem < value; elem++){
        //     System.out.println(">>>>>>>" + numbers[elem]);
        //     fileName[elem] = "number"+numbers[elem]+".jpg";
        //     System.out.println(fileName[elem]);
        // }
    
        int x = 3*value;
        m.addAttribute("newNum", x);
        m.addAttribute("val", value);
        m.addAttribute("randNums", numbers);
        m.addAttribute("imgFiles", fileName);
            
    }
    
}







        //Attempt using try loop
        // try{
        //     if(value >= 1 && value <= maxGenNo){
        //     int x = 3*value;
        //     m.addAttribute("newNum", x);
        //     }
        // }
        // catch(RuntimeException e){
        //     e.printStackTrace();
        // }

        //Attempt using while loop
        // while(!(value >= 1 && value <= maxGenNo)){
        //     System.out.println("Your input is out of range.");
        //     }
        // int x = 3*value;
        // m.addAttribute("newNum", x);
