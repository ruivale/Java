/**
 * <p>
 * Classname: javastreams.map.multi.albums.Album
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
package javastreams.map.multi.albums;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class Album {

  private String albumName;
  private int albumCost;
  private List<Artist> artists;

  public Album(String albumName, int albumCost, List<Artist> artists) {
    this.albumName = albumName;
    this.albumCost = albumCost;
    this.artists = artists;
  }

  public void artistAlbumPairsToMajorLabels(Consumer<Pair<String, String>> consumer) {

    for (Artist artist : artists) {
      if (artist.isAssociatedMajorLabels()) {
        String concatLabels = artist.getMajorLabels()
          .stream()
          .collect(Collectors.joining(","));
        consumer.accept(new ImmutablePair<>(artist.getName() + ":" + albumName, concatLabels));
      }
    }
  }

  public String getAlbumName() {
    return albumName;
  }

  public int getAlbumCost() {
    return albumCost;
  }

  public List<Artist> getArtists() {
    return artists;
  }

  public String toString() {
    return albumName;
  }
}
