package DataBaseFiller.DataBaseFiller.Services;

import DataBaseFiller.DataBaseFiller.Models.Mail;
import DataBaseFiller.DataBaseFiller.Repositories.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataService {

    @Autowired
    private DataRepository dataRepository;


    public String registerMail(Mail mail){
        RabbitMQService rabbitMQService = new RabbitMQService("Mail");
        rabbitMQService.sendObjectToQueue(mail);
        return "Mail was successfully added";
        //return dataRepository.registerMail(mail);
    }

    public String saveImage(byte[] imageInBytes) {
        RabbitMQService rabbitMQService = new RabbitMQService("Image");
        rabbitMQService.sendObjectToQueue(imageInBytes);
        return "Image was successfully added";
      //  return dataRepository.saveImage(imageInBytes);
    }

}
