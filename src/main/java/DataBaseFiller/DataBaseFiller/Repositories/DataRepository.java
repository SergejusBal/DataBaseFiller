package DataBaseFiller.DataBaseFiller.Repositories;

import DataBaseFiller.DataBaseFiller.Models.Mail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class DataRepository {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;


    public String registerMail(Mail mail){

        if(mail == null || mail.getContent() == null || mail.getEmailTo() == null) return "Invalid data";

        String sql = "INSERT INTO mails (mail_to,content) VALUES (?,?);";
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,mail.getEmailTo());
            preparedStatement.setString(2,mail.getContent());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

        }catch (SQLException e) {

            System.out.println(e.getMessage());

            return "Database connection failed";
        }

        return "Mail was successfully added";

    }

    public String saveImage(byte[] imageInBytes) {

        if(imageInBytes == null) return "Invalid data";

        String sql = "INSERT INTO image (image) VALUES (?);";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBytes(1, imageInBytes);

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return "Database connection failed";
        }

        return "Image was successfully added";

    }


}
