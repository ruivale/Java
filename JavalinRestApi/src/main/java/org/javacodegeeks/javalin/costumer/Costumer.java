/**
 * <p>
 * Classname:  org.javacodegeeks.javalin.costumer.Customer
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

package org.javacodegeeks.javalin.costumer;


public class Costumer {
    public final int id;
    public final String name;
	public final int ssn;

    public Costumer(int id, String name, int ssn) {
        this.id = id;
        this.name = name;
		this.ssn = ssn;
    }
}