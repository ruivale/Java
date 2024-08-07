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

import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bat extends Parent {

    public static final int DEFAULT_SIZE = 2;

    public static final int MAX_SIZE = 7;

    private static final Image LEFT = Config.getImages().get(Config.IMAGE_BAT_LEFT);
    private static final Image CENTER = Config.getImages().get(Config.IMAGE_BAT_CENTER);
    private static final Image RIGHT = Config.getImages().get(Config.IMAGE_BAT_RIGHT);

    private int size;
    private int width;
    private int height;

    private ImageView leftImageView;
    private ImageView centerImageView;
    private ImageView rightImageView;

    public int getSize() {
        return size;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void changeSize(int newSize) {
        this.size = newSize;
        width = size * 12 + 45;
        double rightWidth = RIGHT.getWidth() - Config.SHADOW_WIDTH;
        double centerWidth = width - LEFT.getWidth() - rightWidth;
        centerImageView.setViewport(new Rectangle2D(
            (CENTER.getWidth() - centerWidth) / 2, 0, centerWidth, CENTER.getHeight()));
        rightImageView.setTranslateX(width - rightWidth);
    }

    public Bat() {
        height = (int)CENTER.getHeight() - Config.SHADOW_HEIGHT; 
        Group group = new Group();
        leftImageView = new ImageView();
        leftImageView.setImage(LEFT);
        centerImageView = new ImageView();
        centerImageView.setImage(CENTER);
        centerImageView.setTranslateX(LEFT.getWidth());
        rightImageView = new ImageView();
        rightImageView.setImage(RIGHT);
        changeSize(DEFAULT_SIZE);
        group.getChildren().addAll(leftImageView, centerImageView, rightImageView);
        getChildren().add(group);
        setMouseTransparent(true);
    }

}

