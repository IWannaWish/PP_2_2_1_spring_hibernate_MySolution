package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);
      UserService userService = context.getBean(UserService.class);

      Car car1 = new Car("Mercedes", 1);
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", car1));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("Honda", 2)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("Infinity", 3)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("Nissan", 4)));

      List<User> users = userService.getListUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar());
         System.out.println();
      }
      System.out.println(userService.getOwner("Mercedes",1));

      context.close();
   }
}
