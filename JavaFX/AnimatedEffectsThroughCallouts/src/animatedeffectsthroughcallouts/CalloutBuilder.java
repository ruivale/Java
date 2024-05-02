/**
 * <p>
 * Classname:  animatedeffectsthroughcallouts.CalloutBuilder
 * </p>
 *
 * <p>Copyright: Copyright (c) 2018 EFACEC SE
 * <br>
 * This software is the confidential and proprietary information of EFACEC SE.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC SE
 * <br>
 * Rua Eng.? Frederico Ulrich - Apartado 3081
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

package animatedeffectsthroughcallouts;



/*
 * Copyright (c) 2018. Carl Dea
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.lang.reflect.InvocationTargetException;

/**
 * A callout builder to create a Callout instance.
 * An example of building a Callout using the CalloutBuilder.:
 * <pre>
 *     Callout callout = CalloutBuilder.create()
 *                 .headPoint(600, 550)
 *                 .leaderLineToPoint(400, 300)
 *                 .endLeaderLineLeft()
 *                 .mainTitle("STONEY CREEK")
 *                 .subTitle("Pasadena, MD")
 *                 .pause(5000)
 *                 .build();
 * </pre>
 */
public class CalloutBuilder {

    private Callout callout = new Callout();

    private CalloutBuilder() {}

    private CalloutBuilder(Callout callout) {
        this.callout = callout;
    }

    public static CalloutBuilder create() {
        return new CalloutBuilder();
    }
    public static CalloutBuilder create(Class<? extends Callout> clazz) {
        try {
            return new CalloutBuilder(clazz.getDeclaredConstructor().newInstance());
        } catch (InstantiationException |
                IllegalAccessException |
                InvocationTargetException |
                NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public CalloutBuilder headPoint(double x, double y) {
        callout.setHeadPoint(x, y);
        return this;
    }

    public CalloutBuilder leaderLineToPoint(double x, double y) {
        callout.setLeaderLineToPoint(x, y);
        return this;
    }

    public CalloutBuilder endLeaderLineRight(double length) {
        callout.setEndLeaderLineLength(length);
        callout.setEndLeaderLineDirection(Callout.RIGHT);
        return this;
    }
    public CalloutBuilder endLeaderLineRight() {
        callout.setEndLeaderLineLength(75);
        callout.setEndLeaderLineDirection(Callout.RIGHT);
        return this;
    }

    public CalloutBuilder endLeaderLineLeft(double length) {
        callout.setEndLeaderLineLength(length);
        callout.setEndLeaderLineDirection(Callout.LEFT);
        return this;
    }

    public CalloutBuilder endLeaderLineLeft() {
        callout.setEndLeaderLineLength(75);
        callout.setEndLeaderLineDirection(Callout.LEFT);
        return this;
    }

    public CalloutBuilder endLeaderLineLength(double length) {
        callout.setEndLeaderLineLength(length);
        return this;
    }

    public CalloutBuilder endLeaderLineDirection(int direction) {
        callout.setEndLeaderLineDirection(direction);
        return this;
    }

    public CalloutBuilder mainTitle(String title) {
        callout.setMainTitleText(title);
        return this;
    }

    public CalloutBuilder subTitle(String title) {
        callout.setSubTitleText(title);
        return this;
    }
    public CalloutBuilder pause(long inMillis) {
        callout.setPauseTime(inMillis);
        return this;
    }


    public Callout build() {
        callout.build();
        return callout;
    }
}