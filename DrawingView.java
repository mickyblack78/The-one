// Inside DrawingView.java - Add these variables
public enum ToolMode { BRUSH, SQUARE, CIRCLE, TRIANGLE, EYEDROPPER, BLEND }
private ToolMode currentMode = ToolMode.BRUSH;

// For shape previewing
private float startX, startY; 

// Method to switch tools
public void setToolMode(ToolMode mode) {
    this.currentMode = mode;
}

// Logic to draw a triangle inside a bounding box
private void drawTriangle(Canvas canvas, float x1, float y1, float x2, float y2, Paint paint) {
    Path path = new Path();
    path.moveTo((x1 + x2) / 2, y1); // Top Point
    path.lineTo(x1, y2);           // Bottom Left
    path.lineTo(x2, y2);           // Bottom Right
    path.close();
    canvas.drawPath(path, paint);
}private void commitShape(float sX, float sY, float eX, float eY) {
    float left = Math.min(sX, eX);
    float right = Math.max(sX, eX);
    float top = Math.min(sY, eY);
    float bottom = Math.max(sY, eY);

    switch (currentMode) {
        case SQUARE:
            drawCanvas.drawRect(left, top, right, bottom, drawPaint);
            break;
        case CIRCLE:
            drawCanvas.drawOval(left, top, right, bottom, drawPaint);
            break;
        case TRIANGLE:
            drawTriangle(drawCanvas, left, top, right, bottom, drawPaint);
            break;
    }
    invalidate();
  public void setBlendMode(boolean active) {
    if (active) {
        drawPaint.setMaskFilter(new BlurMaskFilter(15, BlurMaskFilter.Blur.NORMAL));
        drawPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
    } else {
        drawPaint.setMaskFilter(null);
        drawPaint.setXfermode(null);
    }
}
}
// Inside MainActivity.java
private int[] generatePalette() {
    int[] palette = new int[48];
    for (int i = 0; i < 48; i++) {
        // Distribute 48 colors across the 360-degree hue spectrum
        float hue = (i * 360f) / 48f;
        palette[i] = Color.HSVToColor(new float[]{hue, 0.8f, 0.9f});
    }
    return palette;
}

private int[] monochromaticShades = {
    0xFF000000, // Pure Black
    0xFF444444, // Dark Grey
    0xFF888888, // Medium Grey
    0xFFCCCCCC, // Light Grey
    0xFFFFFFFF  // Pure White
};
