package exp.design.pattern.facade;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>
 * Description: In this lesson, we will discuss about another structural pattern, i.e., the Facade
 * Pattern. But before we dig into the details of it, let us discuss a problem which will be solved
 * by this particular Pattern. Your company is a product based company and it has launched a product
 * in the market, named Schedule Server. It is a kind of server in itself, and it is used to manage
 * jobs. The jobs could be any kind of jobs like sending a list of emails, sms, reading or writing
 * files from a destination, or just simply transferring files from a source to the destination. The
 * product is used by the developers to manage such kind of jobs and able to concentrate more
 * towards their business goal. The server executes each job at their specified time and also
 * manages all underline issues like concurrency issue and security by itself. As a developer, one
 * just need to code only the relevant business requirements and a good amount of API calls is
 * provided to schedule a job according to their needs. Everything was going fine, until the clients
 * started complaining about starting and stopping the process of the server. They said, although
 * the server is working great, the initializing and the shutting down processes are very complex
 * and they want an easy way to do that. The server has exposed a complex interface to the clients
 * which looks a bit hectic to them. We need to provide an easy way to start and stop the server. A
 * complex interface to the client is already considered as a fault in the design of the current
 * system. But fortunately or unfortunately, we cannot start the designing and the coding from
 * scratch. We need a way to resolve this problem and make the interface easy to access. A Facade
 * Pattern can help us to resolve this design problem. But before that, let us see about the Facade
 * Pattern.
 *
 * The Facade Pattern makes a complex interface easier to use, using a Facade class. The Facade
 * Pattern provides a unified interface to a set of interface in a subsystem. Facade defines a
 * higher-level interface that makes the subsystem easier to use. The Facade unifies the complex
 * low-level interfaces of a subsystem in-order to provide a simple way to access that interface. It
 * just provides a layer to the complex interfaces of the sub-system which makes it easier to use.
 * The Facade do not encapsulate the subsystem classes or interfaces; it just provides a simplified
 * interface to their functionality. A client can access these classes directly. It still exposes
 * the full functionality of the system for the clients who may need it. A Facade is not just only
 * able to simplify an interface, but it also decouples a client from a subsystem. It adheres to the
 * Principle of Least Knowledge, which avoids tight coupling between the client and the subsystem.
 * This provides flexibility: suppose in the above problem, the company wants to add some more steps
 * to start or stop the Schedule Server, that have their own different interfaces. If you coded your
 * client code to the facade rather than the subsystem, your client code doesn?t need to be change,
 * just the facade required to be changed, that?s would be delivered with a new version to the
 * client.
 *
 * Clients communicate with the subsystem by sending requests to Facade, which forwards them to the
 * appropriate subsystem object(s). Although the subsystem objects perform the actual work, the
 * facade may have to do the work of its own to translate its interface to subsystem interfaces.
 * Clients that use the facade don?t have to access its subsystem objects directly. Please note
 * that, a Facade same as an Adapter can wrap multiple classes, but a facade is used to an interface
 * to simplify the use of the complex interface, whereas, an adapter is used to convert the
 * interface to an interface the client expects.
 *
 *
 * Use the Facade Pattern, when: ? You want to provide a simple interface to a complex subsystem.
 * Subsystems often get more complex as they evolve. Most patterns, when applied, result in more and
 * smaller classes. This makes the subsystem more reusable and easier to customize, but it also
 * becomes harder to use for clients that don?t need to customize it. A facade can provide a simple
 * default view of the subsystem that is good enough for most clients. Only clients needing more
 * customizability will need to look beyond the facade. ? There are many dependencies between
 * clients and the implementation classes of an abstraction. Introduce a facade to decouple the
 * subsystem from clients and other subsystems, thereby promoting subsystem independence and
 * portability. ? You can layer your subsystems. Use a facade to define an entry point to each
 * subsystem level. If subsystems are dependent, then you can simplify the dependencies between them
 * by making them communicate with each other solely through their facades.
 *
 *
 *
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class RunFacadeExample {

  /**
   * This class LOGGER
   */
  private static final Logger LOGGER
    = Logger.getLogger(RunFacadeExample.class.getName());

  /**
   * The RunFacadeExemple default constructor.
   */
  public RunFacadeExample() {
  }

  /**
   * Returns this class description in a friendly way.
   *
   * @return String description
   */
  public String toString() {
    return new StringBuffer("RunFacadeExemple").append("").toString();
  }

  public static void main(final String[] args) {

    ScheduleServer scheduleServer = new ScheduleServer();
    ScheduleServerFacade facadeServer = new ScheduleServerFacade(scheduleServer);
    facadeServer.startServer();

    System.out.println("Start working......");
    System.out.println("After work done.........");
    facadeServer.stopServer();
  }
}
