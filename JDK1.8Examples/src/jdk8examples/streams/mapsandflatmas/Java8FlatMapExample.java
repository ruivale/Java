/**
 * <p>
 * Classname:  jdk8examples.streams.mapsandflatmas.Java8FlatMapExample
 * </p>
 *
 * <p>Copyright: Copyright (c) 2019 EFACEC SE
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

package jdk8examples.streams.mapsandflatmas;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Java8FlatMapExample {

    public static void main(String[] args) {

        List<String> haiList = new ArrayList<>();

        haiList.add("hello");
        haiList.add("hai");
        haiList.add("hehe");
        haiList.add("hi");

        System.out.println("Output with nested List of List<String>");

        List<String> welcomeList = new ArrayList<>();

        welcomeList.add("You got it");
        welcomeList.add("Don't mention it");
        welcomeList.add("No worries.");
        welcomeList.add("Not a problem");

        List<List<String>> nestedList = Arrays.asList(haiList, welcomeList);

        nestedList.stream().flatMap(
          list ->  list.stream()).map(
            value -> value.toUpperCase()).forEach(value -> System.out.println(value));



    }
}