
import io.javalin.Javalin;
import org.javacodegeeks.javalin.costumer.CostumerController;

/**
 *
 * https://examples.javacodegeeks.com/javalin-rest-api/
 * 
 * You can access the Javalin RESTAPI routes example, customers, customers/:id using the following urls: 
 *    http://localhost:7000/example
 *    http://localhost:7000/customers
 *    http://localhost:7000/customers/1
 * 
 * The output for the above urls is shown below:
 *    Example Route Output
 *        This is a Javalin Example
 * 
 *    Costumers Route Output
 *        ["George Smith","Tania Rogers","Carol Smith"]
 * 
 *    Costumers/:id Route Output
 *        {"id":1,"name":"Tania Rogers","ssn":231872636}
 * 
 *
 * @author 
 */
public class JavalinExample {

  public static void main(String[] args) {

    System.out.println("Javalin example: ");

    Javalin example = Javalin.create()
      .port(7000)
      .start();

    example.get("/example", ctx -> ctx.html("Ths is a Javalin Example"));
    example.get("/example2", ctx -> ctx.html("Ths is a Javalin Example II"));

    example.get("/costumers", CostumerController.fetchAllCustomeNames);
    
    example.get("/costumers/:id", CostumerController.fetchById);

  }
}
