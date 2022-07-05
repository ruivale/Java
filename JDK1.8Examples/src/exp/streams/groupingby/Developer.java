package exp.streams.groupingby;


public class Developer {

  private int age;
  private String favoriteLanguage;

  public Developer(int age,
                   String favoriteLanguage) {
    this.age = age;
    this.favoriteLanguage = favoriteLanguage;
  }

  public int getAge() {
    return age;
  }

  public String getFavoriteLanguage() {
    return favoriteLanguage;
  }

  @Override
  public String toString() {
    return "Developer{" + "age=" + age + ", favoriteLanguage=" + favoriteLanguage + '}';
  }
}
