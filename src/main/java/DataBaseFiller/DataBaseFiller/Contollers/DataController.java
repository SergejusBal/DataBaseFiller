package DataBaseFiller.DataBaseFiller.Contollers;

import DataBaseFiller.DataBaseFiller.Models.Mail;
import DataBaseFiller.DataBaseFiller.Services.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:5500","http://localhost:7778/","http://127.0.0.1:7778/"})
@RequestMapping("/data")
public class DataController {
    @Autowired
    private DataService dataService;

    @PostMapping("/newMail")
    public ResponseEntity<String> registerMail(@RequestBody Mail mail) {

        String response = dataService.registerMail(mail);
        HttpStatus status = checkHttpStatus(response);

        if(status == HttpStatus.OK) return new ResponseEntity<>(response, status);
        else return new ResponseEntity<>(response, status);

    }

    @PostMapping("/image")
    public ResponseEntity<String> saveImage(@RequestBody byte[] imageInBytes) {

        String response = dataService.saveImage(imageInBytes);
        HttpStatus status = checkHttpStatus(response);

        if(status == HttpStatus.OK) return new ResponseEntity<>(response, status);
        else return new ResponseEntity<>(response, status);

    }


    private HttpStatus checkHttpStatus(String response){

        switch (response){
            case "Database connection failed":
                return HttpStatus.INTERNAL_SERVER_ERROR;
            case "Invalid data":
                return HttpStatus.BAD_REQUEST;
            case "Mail was successfully added","Image was successfully added":
                return HttpStatus.OK;
            default:
                return HttpStatus.NOT_IMPLEMENTED;
        }

    }






}
