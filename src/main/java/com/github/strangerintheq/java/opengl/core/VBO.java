package com.github.strangerintheq.java.opengl.core;

import com.jogamp.common.nio.Buffers;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.media.opengl.GL2;

public class VBO {

    private final GL2 gl;
    private int[] id = new int[1];
    private int size;

    public VBO(GL2 gl, float[] vertices) {
        this.gl = gl;
        size = vertices.length;
        createVBO();
        bind();
        fill(vertices);
    }

    private void createVBO() {
        gl.glGenBuffers(1, id, 0);
    }

    public void bind() {
        gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, id[0]);
    }

    public void fill(float[] quadVertices) {
        gl.glBufferData(
            GL2.GL_ARRAY_BUFFER,
            quadVertices.length * 4,
            asFloatBuffer(quadVertices),
            GL2.GL_STREAM_DRAW
        );
    }

    private Buffer asFloatBuffer(float[] quadVertices) {
        return Buffers.newDirectFloatBuffer(quadVertices).rewind();
    }

    public void enable() {
        bind();
        gl.glEnableClientState(GL2.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL2.GL_FLOAT, 0, 0);
    }

    public int size() {
        return size;
    }

    public void disable (){
        gl.glDisableClientState(GL2.GL_VERTEX_ARRAY);
    }

    public void delete() {
        gl.glDeleteBuffers(1, id, 0);
    }

    public void drawTriangleStrip() {
    }
}
