package com.wildcodeschool.Spring02Ontheroad.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;


@Controller
public class DocController {

    // Array mit den Namen
    String [] doctorsName = {
        "Nummer 0 gibt es nicht, gilt fuer Dr. und auch bei der Apfelliste :-)",
        "William Hartnell",
        "Patrick Troughton",
        "Jon Pertwee",
        "Tom Baker",
        "Peter Davison",
        "Colin Baker",
        "Sylvester McCoy",
        "Paul McGann",
        "Christopher Eccleston",
        "David Tennant",
        "Matt Smith",
        "Peter Capaldi",
        "Jodie Whittaker",
        };

    @GetMapping("/doctor/{incarnation}")
    @ResponseBody
    public String doctor( @PathVariable ("incarnation") int incarnationNumber) {

        // For doctors 9 to 13 inclusive, return the details about the incarnation of the corresponding Doctor.
        // These details should be returned in JSON format, and include the doctor's number and name like this: 
        // {"number": 13, "name": "Jodie Whittaker"}.
               
        if (incarnationNumber >= 9 && incarnationNumber <= 13){
            return "{\"number\": " +
                    incarnationNumber +
                    " \"name\": \"" +
                    doctorsName[incarnationNumber] +
                    "\"}";
        }

        // For the other doctors (1 to 8) return a 303 status.
        
        if (incarnationNumber >= 1 && incarnationNumber <= 8){
            throw new ResponseStatusException(HttpStatus.SEE_OTHER, "303 See Other.");
        }
        
        // If the number is not valid, return a 404 status and display this informative message:
        // ``Impossible to retrieve the incarnation <incarnation number>"` .
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Impossible to retrieve the incarnation " + incarnationNumber);

        //  and for everything else it returns a status 404 with the agreed informative message.
        // und für alles andere gibt es einen Status 404 mit der vereinbarten informativen Nachricht zurück.

        // Eintrag in application.properties
        // https://www.baeldung.com/spring-response-status-exception
        // To change the default behavior, we can use a server.error.include-message property.
        // Let's set it to always and see what happens:

        // return doctorsName[incarnationNumber];
        // return null;
    }

    
}
