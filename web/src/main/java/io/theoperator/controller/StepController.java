package io.theoperator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by andreas on 3/8/15.
 */
@Controller
@RequestMapping("/steps")
@SessionAttributes("stepMap")
public class StepController {

    @ModelAttribute("stepMap")
    public Map<String, String> createStepMap() {
        Map<String, String> stepMap = new HashMap<>();
        System.out.println("Created StepMap");
        return stepMap;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "steps/index";
    }

    @RequestMapping(value = "step1", method = RequestMethod.GET)
    public String step1() {
        return "steps/step1";
    }

    @RequestMapping(value = "step2", method = RequestMethod.GET)
    public String step2(@ModelAttribute("stepMap") Map stepMap, @RequestParam("key") String key1) {
        stepMap.put("key1", key1);
        return "steps/step2";
    }

    @RequestMapping(value = "step3", method = RequestMethod.GET)
    public String step3(@ModelAttribute("stepMap") Map stepMap, @RequestParam("key") String key2) {
        stepMap.put("key2", key2);
        return "steps/step3";
    }

    @RequestMapping(value = "end", method = RequestMethod.GET)
        public ModelAndView end(@ModelAttribute("stepMap") Map stepMap, @RequestParam("key") String key3, SessionStatus status) {
        stepMap.put("key3", key3);
        ModelAndView mav = new ModelAndView("steps/end");
        mav.addObject("key1", stepMap.get("key1"));
        mav.addObject("key2", stepMap.get("key2"));
        mav.addObject("key3", stepMap.get("key3"));

        status.setComplete();
        return mav;
    }
}
