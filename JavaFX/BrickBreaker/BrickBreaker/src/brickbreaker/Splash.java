/*
 * Copyright (c) 2008, 2011 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * This file is available and licensed under the following license:
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  - Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the distribution.
 *  - Neither the name of Oracle Corporation nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package brickbreaker;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class Splash extends Parent {

    private static final int STATE_SHOW_TITLE = 0;
    private static final int STATE_SHOW_STRIKE = 1;
    private static final int STATE_SUN = 2;

    private static final int SUN_AMPLITUDE_X = Config.SCREEN_WIDTH * 2 / 3;
    private static final int SUN_AMPLITUDE_Y = Config.SCREEN_WIDTH / 2;
    
    private ImageView background; 
    private ImageView brick;
    private ImageView brickShadow;
    private ImageView breaker;
    private ImageView breakerShadow;
    private Timeline timeline;
    private int state;
    private int stateArg;
    private ImageView strike;
    private ImageView strikeShadow;
    private ImageView pressanykey;
    private ImageView pressanykeyShadow;
    private ImageView sun;
    private ImageView[] NODES;
    private ImageView[] NODES_SHADOWS;

    private void initTimeline() {
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame kf = new KeyFrame(Config.ANIMATION_TIME, new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (state == STATE_SHOW_TITLE) {
                    stateArg++;
                    int center = Config.SCREEN_WIDTH / 2;
                    int offset = (int)(Math.cos(stateArg / 4.0) * (40 - stateArg) / 40 * center);
                    brick.setTranslateX(center - brick.getImage().getWidth() / 2 + offset);
                    breaker.setTranslateX(center - breaker.getImage().getWidth() / 2 - offset);
                    if (stateArg == 40) {
                        stateArg = 0;
                        state = STATE_SHOW_STRIKE;
                    }
                    return;
                }
                if (state == STATE_SHOW_STRIKE) {
                    if (stateArg == 0) {
                        strike.setTranslateX(breaker.getTranslateX() + brick.getImage().getWidth());
                        strike.setScaleX(0);
                        strike.setScaleY(0);
                        strike.setVisible(true);
                    }
                    stateArg++;
                    double coef = stateArg / 30f;
                    brick.setTranslateX(breaker.getTranslateX() +
                        (breaker.getImage().getWidth() - brick.getImage().getWidth()) / 2f * (1 - coef));
                    strike.setScaleX(coef);
                    strike.setScaleY(coef);
                    strike.setRotate((30 - stateArg) * 2);
                    if (stateArg == 30) {
                        stateArg = 0;
                        state = STATE_SUN;
                    }
                    return;
                }
                // Here state == STATE_SUN
                if (pressanykey.getOpacity() < 1) {
                    pressanykey.setOpacity(pressanykey.getOpacity() + 0.05f);
                }
                stateArg--;
                double x = SUN_AMPLITUDE_X * Math.cos(stateArg / 100.0);
                double y = SUN_AMPLITUDE_Y * Math.sin(stateArg / 100.0);
                if (y < 0) {
                    for (Node node : NODES_SHADOWS) {
                        // Workaround RT-1976
                        node.setTranslateX(-1000);
                    }
                    return;
                }
                double sunX = Config.SCREEN_WIDTH / 2 + x;
                double sunY = Config.SCREEN_HEIGHT / 2 - y;
                sun.setTranslateX(sunX - sun.getImage().getWidth() / 2);
                sun.setTranslateY(sunY - sun.getImage().getHeight() / 2);
                sun.setRotate(-stateArg);
                for (int i = 0; i < NODES.length; i++) {
                    NODES_SHADOWS[i].setOpacity(y / SUN_AMPLITUDE_Y / 2);
                    NODES_SHADOWS[i].setTranslateX(NODES[i].getTranslateX() +
                        (NODES[i].getTranslateX() + NODES[i].getImage().getWidth() / 2 - sunX) / 20);
                    NODES_SHADOWS[i].setTranslateY(NODES[i].getTranslateY() +
                        (NODES[i].getTranslateY() + NODES[i].getImage().getHeight() / 2 - sunY) / 20);
                }
            }
        });
        timeline.getKeyFrames().add(kf);
    }
    
    public void start() {
        background.requestFocus();
        timeline.play();
    }

    public void stop() {
        timeline.stop();
    }

    Splash() {
        state = STATE_SHOW_TITLE;
        stateArg = 0;
        initTimeline();
        background = new ImageView();
        background.setFocusTraversable(true);
        background.setImage(Config.getImages().get(Config.IMAGE_BACKGROUND));
        background.setFitWidth(Config.SCREEN_WIDTH);
        background.setFitHeight(Config.SCREEN_HEIGHT);
        background.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                Main.getMainFrame().startGame();
            }
        });
        background.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                Main.getMainFrame().startGame();
            }
        });
        brick = new ImageView();
        brick.setImage(Config.getImages().get(Config.IMAGE_SPLASH_BRICK));
        brick.setTranslateX(-1000);
        brick.setTranslateY(brick.getImage().getHeight());
        brickShadow = new ImageView();
        brickShadow.setImage(Config.getImages().get(Config.IMAGE_SPLASH_BRICKSHADOW));
        brickShadow.setTranslateX(-1000);
        breaker = new ImageView();
        breaker.setImage(Config.getImages().get(Config.IMAGE_SPLASH_BREAKER));
        breaker.setTranslateX(-1000);
        breaker.setTranslateY(brick.getTranslateY() + brick.getImage().getHeight() * 5 / 4);
        breakerShadow = new ImageView();
        breakerShadow.setImage(Config.getImages().get(Config.IMAGE_SPLASH_BREAKERSHADOW));
        breakerShadow.setTranslateX(-1000);
        strike = new ImageView();
        strike.setImage(Config.getImages().get(Config.IMAGE_SPLASH_STRIKE));
        strike.setTranslateY(brick.getTranslateY() -
            (strike.getImage().getHeight() - brick.getImage().getHeight()) / 2);
        strike.setVisible(false);
        pressanykey = new ImageView();
        pressanykey.setImage(Config.getImages().get(Config.IMAGE_SPLASH_PRESSANYKEY));
        pressanykey.setTranslateX((Config.SCREEN_WIDTH - pressanykey.getImage().getWidth()) / 2);
        double y = breaker.getTranslateY() + breaker.getImage().getHeight();
        pressanykey.setTranslateY(y + (Config.SCREEN_HEIGHT - y) / 2);
        pressanykey.setOpacity(0);
        strikeShadow = new ImageView();
        strikeShadow.setImage(Config.getImages().get(Config.IMAGE_SPLASH_STRIKESHADOW));
        strikeShadow.setTranslateX(-1000);
        pressanykeyShadow = new ImageView();
        pressanykeyShadow.setImage(Config.getImages().get(Config.IMAGE_SPLASH_PRESSANYKEYSHADOW));
        pressanykeyShadow.setTranslateX(-1000);
        sun = new ImageView();
        sun.setImage(Config.getImages().get(Config.IMAGE_SPLASH_SUN));
        sun.setTranslateX(-1000);
        NODES = new ImageView[] {brick, breaker, strike, pressanykey};
        NODES_SHADOWS = new ImageView[] {brickShadow, breakerShadow, strikeShadow, pressanykeyShadow};
        Group group = new Group();
        group.getChildren().add(background);
        group.getChildren().addAll(NODES_SHADOWS);
        group.getChildren().addAll(NODES);
        group.getChildren().add(sun);
        getChildren().add(group);
    }

}

