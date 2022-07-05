/**
 * <p>
 * Classname:  javastreams.map.multi.albums.Artist
 * </p>
 *
 * <p>Copyright: Copyright (c) 2021 EFACEC SE
 * <br>
 * This software is the confidential and proprietary information of EFACEC SE.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC SE
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
import java.util.Objects;

public class Artist {

    private final String name;
    private boolean associatedMajorLabels;
    private List<String> majorLabels;

    public Artist(String name, boolean associatedMajorLabels, List<String> majorLabels) {
        this.name = name;
        this.associatedMajorLabels = associatedMajorLabels;
        this.majorLabels = majorLabels;
    }
    public String getName() {
        return name;
    }

    public boolean isAssociatedMajorLabels() {
        return associatedMajorLabels;
    }

    public List<String> getMajorLabels() {
        return majorLabels;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Artist other = (Artist) obj;
        return Objects.equals(name, other.name);
    }

    public String toString() {
        return name;
    }
}