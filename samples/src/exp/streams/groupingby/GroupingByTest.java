package exp.streams.groupingby;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.Test;


public class GroupingByTest {

  @Test
  public void groupByFavoriteLanguage() {

    List<Developer> team = Arrays.asList(
        new Developer(18, "java"),
        new Developer(20, "java"),
        new Developer(35, "javascript"),
        new Developer(50, "javascript"),
        new Developer(50, "logo"));

    Map<String, List<Developer>> developersByLanguages =
        team.
        stream().
        collect(Collectors.groupingBy(Developer::getFavoriteLanguage));

    System.out.println(developersByLanguages);

    /* output:
            {java=[Developer{age=18, favoriteLanguage=java}, Developer{age=20, favoriteLanguage=java}],
            logo=[Developer{age=50, favoriteLanguage=logo}],
            javascript=[Developer{age=35, favoriteLanguage=javascript}, Developer{age=50, favoriteLanguage=javascript}]
            }
     */
    Map<String, Double> favoriteLanguageByAverageAge =
        team.
        stream().
        collect(Collectors.groupingBy(Developer::getFavoriteLanguage,
            Collectors.averagingInt(Developer::getAge)));
    System.out.println(favoriteLanguageByAverageAge);
    /* output:
    		{java=19.0, logo=50.0, javascript=42.5}
     */
  }
}
