package com.mygdx.coreGame;

public class Parameters {
    
    private static float aspectRatio;
    private static float inverseAspectRatio;

    public static float getAspectRatio() {
        return aspectRatio;
    }
    public static void setAspectRatio(float width, float height) {
        aspectRatio = width/height;
        inverseAspectRatio = height/width;
    }
    public static float getInverseAspectRatio() {
        return inverseAspectRatio;
    }
}
