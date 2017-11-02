package com.github.strangerintheq.java.opengl.core.mouse;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class Mouse2d extends MouseAdapter {

    private Integer dragStartX, dragStartY;
    private Double centerAtDragStartX, centerAtDragStartY;
    private int width;
    private int height;
    public double centerX, centerY;
    public double zoom = 1.5;

    public Mouse2d(double centerX, double centerY, double zoom, int width, int height) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.zoom = zoom;
        this.width = width;
        this.height = height;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        zoom *= e.getWheelRotation() < 0 ? 0.9 : 1.1;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        centerAtDragStartX = centerX;
        centerAtDragStartY = centerY;
        dragStartX = e.getX();
        dragStartY = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        centerAtDragStartX = centerAtDragStartY = null;
        dragStartX = dragStartY = null;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        centerX = centerAtDragStartX + 4 * zoom * (dragStartX - e.getX()) / width;
        centerY = centerAtDragStartY - 4 * zoom * (dragStartY - e.getY()) / height;
    }

    public void updateResolution(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
