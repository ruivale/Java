/**
 * <p>
 * Classname: jdk9examples.date.yearspliterator.YearSpliterator
 * </p>
 * <p>
 * <p>
 * Copyright: Copyright (c) 2016 Efacec Engenharia e Sistemas, S.A.
 * <br>
 * This software is the confidential and proprietary information of EFACEC Eng. Sistemas.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>
 * Company: EFACEC Eng. Sistemas
 * <br>
 * Rua Eng.º Frederico Ulrich ? Ap. 3078
 * <br>
 * 4471-907 Moreira da Maia
 * <br>
 * PORTUGAL
 * <br>
 * Tel: +351 22 940 2000
 * <br>
 * Fax: +351 22 948 5428
 * <br>
 * Web: www.efacec.pt
 * <br>
 * Email: te@efacec.pt
 * </p>
 */
package jdk9examples.date.yearspliterator;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Objects;
import java.util.Spliterator;
import static java.util.Spliterator.DISTINCT;
import static java.util.Spliterator.IMMUTABLE;
import static java.util.Spliterator.NONNULL;
import static java.util.Spliterator.ORDERED;
import static java.util.Spliterator.SORTED;
import java.util.function.Consumer;



/**
 * Takes a start date and iterates backwards one year at a time.
 *
 * During 2017, we covered lots of interesting topics during our "Heinz's Happy Hour Season 01".
 * One of these was the Spliterator, which we looked at in detail in episode 2.
 * Since then, I've often used the knowledge gained during this episode to create a small custom
 * Spliterator, and use that to make a Stream. I now consider the ability to write a Spliterator
 * as essential knowledge for a professional Java programmer.
 *
 * I was curious as to how often the 1st of January falls on a Monday. I first thought of writing
 * a simple loop going back in time, but then was wondering whether it wouldn't be better to use
 * the Stream API.
 *
 * The YearSpliterator is an infinite sized Spliterator, meaning it does not stop once it reaches
 * the Julian calendar or BC. It just keeps on going backwards. The condition of when to stop is
 * the responsibility of whomsoever uses the stream. We thus do not set the SIZED characteristic.
 */
public class YearSpliterator implements Spliterator<LocalDate> {

  private LocalDate date;

  public YearSpliterator(LocalDate startDate) {
    this.date = startDate;
  }

  public long estimateSize() {
    return Long.MAX_VALUE;
  }

  public boolean tryAdvance(Consumer<? super LocalDate> action) {
    Objects.requireNonNull(action);
    action.accept(date);
    date = date.minusYears(1);
    return true;
  }

  public Spliterator<LocalDate> trySplit() {
    return null;
  }

  public int characteristics() {
    return DISTINCT | IMMUTABLE | NONNULL | ORDERED | SORTED;
  }

  public Comparator<? super LocalDate> getComparator() {
    return Comparator.reverseOrder();
  }
}
