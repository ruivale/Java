package exp.streams.partitioningby;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.Test;


public class PartitioningByTest {

  @Test
  public void partitionByAge() {

    List<Developer> team = Arrays.asList(
        new Developer(18, "java"),
        new Developer(20, "java"),
        new Developer(35, "javascript"),
        new Developer(50, "javascript"),
        new Developer(50, "logo"));

    Map<Boolean, List<Developer>> youngerThan30 =
        team.
        stream().
        collect(Collectors.partitioningBy(d -> d.getAge() < 30));

    System.out.println("Developers younger than thirty are using: " + youngerThan30.get(true).stream().
        map(d -> d.getFavoriteLanguage()).
        collect(Collectors.toSet()));
    //Output: Developers younger than thirty are using: [java]

    
    System.out.println("Developers older than thirty are using: " + youngerThan30.get(false).stream().
        map(d -> d.getFavoriteLanguage()).
        collect(Collectors.toSet()));
    //Output: Developers older than thirty are using: [logo, javascript]
  }
}
