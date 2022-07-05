/**
 * <p>
 * Classname: javastreams.map.multi.MultiMapSamples
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2021 EFACEC SE
 * <br>
 * This software is the confidential and proprietary information of EFACEC SE. You shall not
 * disclose such Confidential Information and shall use it only in accordance with the terms of the
 * license agreement you entered into EFACEC SE.
 * </p>
 * <p>
 * Company: EFACEC SE
 * <br>
 * Rua Eng.º Frederico Ulrich - Apartado 3081
 * <br>
 * 4471-907 Moreira da Maia
 * <br>
 * PORTUGAL
 * <br>
 * Tel.: +351 22 943 20 00
 * <br>
 * Fax: +351 22 943 20 50
 * <br>
 * Web: www.efacec.com
 * <br>
 * Email: mktransportes@efacec.com
 * </p>
 */
package javastreams.map.multi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static java.util.stream.Collectors.toList;
import javastreams.map.multi.albums.Album;
import javastreams.map.multi.albums.Artist;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

/**
 * <p>
 * Description:
 *
 * https://www.baeldung.com/java-mapmulti
 *
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 *
 * @since __DATE__
 */
public class MultiMapSamples {
  
    static Artist a1 = new Artist("Artist 1", false, Collections.EMPTY_LIST);
    static Artist a2 = new Artist("Artist 2", false, Collections.EMPTY_LIST);
    static Artist a3 = new Artist("Artist 3", true, Arrays.asList("EMI", "SONY"));
    static Artist a4 = new Artist("Artist 4", true, Arrays.asList("UNIVERSAL", "SONY"));

    static List<Album> albums = Arrays.asList(
      new Album("Album 1", 15, Arrays.asList(a1, a3)),
      new Album("Album 2", 15, Arrays.asList(a1, a2)),
      new Album("Album 3", 12, Arrays.asList(a2)),
      new Album("Album 4", 17, Arrays.asList(a2, a4)),
      new Album("Album 5", 16, Arrays.asList(a3, a4))
    );
  
  

  /**
   * In our lambda implementation of BiConsumer<T, Consumer<R>> mapper, we first select only even
   * integers, then we add to them the amount specified in percentage, cast the result into a
   * double, and finish invoking consumer.accept.
   *
   * As we saw before, the consumer is just a buffer that passes the return elements to the stream
   * pipeline. (As a side note, notice that we have to use a type witness <Double>mapMulti for the
   * return value because otherwise, the compiler cannot infer the right type of R in the method's
   * signature.)
   */
  public static void sample1() {
    List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
    double percentage = .01;
    List<Double> evenDoubles = integers.stream()
      .<Double>mapMulti((integer, consumer) -> {
        if (integer % 2 == 0) {
          consumer.accept((double) integer * (1 + percentage));
        }
      })
      .collect(toList());

    System.out.println("\nSample1:\n\t" + Arrays.toString(evenDoubles.toArray()));
  }

  /**
   * Notice that the if statement in the previous code sample plays the role of a Stream::filter,
   * and casting the integer into a double, the role of a Stream::map. Hence, we could use Stream's
   * filter and map to achieve the same result: However, the mapMulti implementation is more direct
   * since we don't need to invoke so many stream intermediate operations.
   *
   * Another advantage is that the mapMulti implementation is imperative, giving us more freedom to
   * do element transformations.
   */
  public static void sample2() {
    List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
    double percentage = .01;
    List<Double> evenDoubles = integers.stream()
      .filter(integer -> integer % 2 == 0)
      .<Double>map(integer -> ((double) integer * (1 + percentage)))
      .collect(toList());

    System.out.println("\nSample2:\n\t" + Arrays.toString(evenDoubles.toArray()));
  }

  /**
   * To support int, long, and double primitive types, we have mapMultiToDouble, mapMultiToInt, and
   * mapMultiToLong variations of mapMulti.
   *
   * For example, we can use mapMultiToDouble to find the sum of the previous List of doubles.
   */
  public static void sample3() {
    List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
    double percentage = .01;
    double sum = integers.stream()
      .mapMultiToDouble((integer, consumer) -> {
        if (integer % 2 == 0) {
          consumer.accept(integer * (1 + percentage));
        }
      })
      .sum();

    System.out.println("\nSample3:\n\tSum = " + sum);
  }

  /**
   * If we want to collect a list of artist-album name pairs, we can implement it using mapMulti.
   *
   * For each album in the stream, we iterate over the artists, create an Apache Commons
   * ImmutablePair of artist-album names, and invoke Consumer::accept. The implementation of
   * mapMulti accumulates the elements accepted by the consumer and passes them to the stream
   * pipeline. This has the effect of a one-to-many transformation where the results are accumulated
   * in the consumer but ultimately are flattened into a new stream.
   */
  public static void sampleArtistAlbumNamePairs() {
    List<Pair<String, String>> artistAlbum = albums.stream()
      .<Pair<String, String>>mapMulti((album, consumer) -> {
        for (Artist artist : album.getArtists()) {
          consumer.accept(new ImmutablePair<String, String>(artist.getName(), album.getAlbumName()));
        }
      })
      .collect(toList());

    System.out.println("\nsampleArtistAlbumNamePairs:\n\t" + Arrays.toString(artistAlbum.toArray()));
  }

  /**
   * If we want to collect a list of artist-album name pairs, we can implement it using mapMulti.
   *
   * For each album in the stream, we iterate over the artists, create an Apache Commons
   * ImmutablePair of artist-album names, and invoke Consumer::accept. The implementation of
   * mapMulti accumulates the elements accepted by the consumer and passes them to the stream
   * pipeline. This has the effect of a one-to-many transformation where the results are accumulated
   * in the consumer but ultimately are flattened into a new stream.
   */
  public static void sampleArtistAlbumNamePairsWithFlatMap() {
    List<Pair<String, String>> artistAlbum = albums.stream()
      .flatMap(album -> album.getArtists()
      .stream()
      .map(artist -> new ImmutablePair<String, String>(artist.getName(), album.getAlbumName())))
      .collect(toList());

    System.out.println("\nsampleArtistAlbumNamePairsWithFlatMap:\n\t" + Arrays.toString(artistAlbum.toArray()));
  }

  /**
   * As stated in the Java documentation: ?when replacing each stream element with a small (possibly
   * zero) number of elements. Using this method avoids the overhead of creating a new Stream
   * instance for every group of result elements, as required by flatMap?.
   *
   */
  public static void sampleArtistAlbumNamePairsMapMultiInsteadOfFlatMap() {
    int upperCost = 9;
    List<Pair<String, String>> artistAlbum = albums.stream()
      .<Pair<String, String>>mapMulti((album, consumer) -> {
        if (album.getAlbumCost() < upperCost) {
          for (Artist artist : album.getArtists()) {
            consumer.accept(new ImmutablePair<String, String>(artist.getName(), album.getAlbumName()));
          }
        }
      }).collect(toList());

    System.out.println("\nsampleArtistAlbumNamePairsMapMultiInsteadOfFlatMap:\n\t" + Arrays.toString(artistAlbum.toArray()));
  }

  
  /**
   * 
   */
  public static void givenAListOfAlbums_whenFlatMap_thenGetListOfArtistAlbumPairs() {
    List<Pair<String, String>> artistAlbum = albums.stream()
      .flatMap(album -> album.getArtists()
      .stream()
      .map(artist -> new ImmutablePair<String, String>(artist.getName(), album.getAlbumName())))
      .collect(toList());

    System.out.println(
        "\ngivenAListOfAlbums_whenFlatMap_thenGetListOfArtistAlbumPairs:\n\t" + 
          Arrays.toString(artistAlbum.toArray()));
  }
  

  /**
   * 
   */
  public static void givenAListOfAlbums_whenMapMulti_thenGetListOfPairsOfArtistAlbum() {
    List<Pair<String, String>> artistAlbum = albums.stream()
      .<Pair<String, String>>mapMulti((album, consumer) -> {
        for (Artist artist : album.getArtists()) {
          consumer.accept(new ImmutablePair<String, String>(artist.getName(), album.getAlbumName()));
        }
      })
      .collect(toList());

    System.out.println(
      "\ngivenAListOfAlbums_whenMapMulti_thenGetListOfPairsOfArtistAlbum:\n\t" + 
          Arrays.toString(artistAlbum.toArray()));
  }

  
  /**
   * 
   */
  public static void givenAListOfAlbums_whenFlatMap_thenGetListOfArtistAlbumjPairsBelowGivenCost() {
    int upperCost = 9;
    List<Pair<String, String>> artistAlbum = albums.stream()
      .flatMap(album -> album.getArtists()
      .stream()
      .filter(artist -> upperCost > album.getAlbumCost())
      .map(artist -> new ImmutablePair<String, String>(artist.getName(), album.getAlbumName())))
      .collect(toList());

    System.out.println(
      "\ngivenAListOfAlbums_whenFlatMap_thenGetListOfArtistAlbumjPairsBelowGivenCost:\n\t" + 
          Arrays.toString(artistAlbum.toArray()));
  }

  
  /**
   * 
   */
  public static void givenAListOfAlbums_whenMapMulti_thenGetListOfArtistAlbumPairsBelowGivenCost() {
    int upperCost = 9;
    List<Pair<String, String>> artistAlbum = albums.stream()
      .<Pair<String, String>>mapMulti((album, consumer) -> {
        if (album.getAlbumCost() < upperCost) {
          for (Artist artist : album.getArtists()) {
            consumer.accept(new ImmutablePair<String, String>(artist.getName(), album.getAlbumName()));
          }
        }
      })
      .collect(toList());

    System.out.println(
      "\ngivenAListOfAlbums_whenMapMulti_thenGetListOfArtistAlbumPairsBelowGivenCost:\n\t" + 
          Arrays.toString(artistAlbum.toArray()));
  }

  
  /**
   * 
   */
  public static void givenAListOfAlbums_whenMapMulti_thenGetPairsOfArtistMajorLabelsUsingMethodReference() {
    List<Pair<String, String>> artistLabels = albums.stream()
      .mapMulti(Album::artistAlbumPairsToMajorLabels)
      .collect(toList());

    System.out.println(
      "\ngivenAListOfAlbums_whenMapMulti_thenGetPairsOfArtistMajorLabelsUsingMethodReference:\n\t" + 
          Arrays.toString(artistLabels.toArray()));
  }

  
  
  
  public static void main(final String[] args) {
    MultiMapSamples.sample1();
    MultiMapSamples.sample2();
    MultiMapSamples.sample3();
    MultiMapSamples.sampleArtistAlbumNamePairs();
    MultiMapSamples.sampleArtistAlbumNamePairsWithFlatMap();
    MultiMapSamples.sampleArtistAlbumNamePairsMapMultiInsteadOfFlatMap();
    MultiMapSamples.givenAListOfAlbums_whenFlatMap_thenGetListOfArtistAlbumPairs();
    MultiMapSamples.givenAListOfAlbums_whenMapMulti_thenGetListOfPairsOfArtistAlbum();
    MultiMapSamples.givenAListOfAlbums_whenFlatMap_thenGetListOfArtistAlbumjPairsBelowGivenCost();
    MultiMapSamples.givenAListOfAlbums_whenMapMulti_thenGetListOfArtistAlbumPairsBelowGivenCost();
    MultiMapSamples.givenAListOfAlbums_whenMapMulti_thenGetPairsOfArtistMajorLabelsUsingMethodReference();

    System.out.println("\n\n");
  }
}
