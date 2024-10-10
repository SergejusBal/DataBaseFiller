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
        return dataRepository.registerMail(mail);
    }

    public String saveImage(byte[] imageInBytes) {
        return dataRepository.saveImage(imageInBytes);
    }

}
