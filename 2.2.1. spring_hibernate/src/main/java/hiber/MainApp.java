package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;


public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User Terminator = new User("Terminator", "Jeleznii    ", "termjel@mail.ru");
      User Chelovek = new User("Chelovek  ", "Nechelovekov", "Chelnec@mail.ru");
      User Albert = new User("Albert    ", "neruskii    ", "albener@mail.ru");
      User Magomed = new User("Magomed   ", "Ivanov      ", "maganov@mail.ru");

      Car Traktor = new Car("Traktor", 133);
      Car Mig = new Car("Mig    ", 244);
      Car TopolM = new Car("TopolM ", 333);
      Car Priora = new Car("Priora ", 201);

      userService.add(Terminator.setCar(Traktor).setUser(Terminator));
      userService.add(Chelovek.setCar(Mig).setUser(Chelovek));
      userService.add(Albert.setCar(TopolM).setUser(Albert));
      userService.add(Magomed.setCar(Priora).setUser(Magomed));

      for (User user : userService.listUsers()) {
         System.out.println(user + " " + user.getCar());
      }

      System.out.println(userService.getUserByCar("Traktor", 13));

      try {
         User notFoundUser = userService.getUserByCar("TopolM ", 333);
      } catch (NoResultException e) {
         System.out.println("User not found");
      }

      context.close();
   }
}