package DrawingBoard;

import java.io.Serializable;

class Shape implements Serializable {
	private int 	shapeType;
	private int	startXPosition;
	private int 	startYPosition;
	
	Shape (int argShapeType, int argStartXPoistion, int argStartYPosition) {
		this.shapeType 			= argShapeType;
		this.startXPosition 	= argStartXPoistion;
		this.startYPosition 	= argStartYPosition;
	}
	
	public int getShapeType() {
		return shapeType;
	}

	public void setShapeType(int shapeType) {
		this.shapeType = shapeType;
	}

	public int getStartXPosition() {
		return startXPosition;
	}

	public void setStartXPosition(int startXPosition) {
		this.startXPosition = startXPosition;
	}

	public int getStartYPosition() {
		return startYPosition;
	}

	public void setStartYPosition(int startYPosition) {
		this.startYPosition = startYPosition;
	}
}

class Line extends Shape {
	private int	endXPosition;
	private int 	endYPosition;
	
	Line (int argStartXPosition, int argStartYPosition, int argEndXPosition, int argEndYPosition) {
		super (1, argStartXPosition, argStartYPosition);
		
		this.endXPosition = argEndXPosition;
		this.endYPosition = argEndYPosition;
	}
	
	public int getEndXPosition() {
		return endXPosition;
	}

	public void setEndXPosition(int endXPosition) {
		this.endXPosition = endXPosition;
	}

	public int getEndYPosition() {
		return endYPosition;
	}

	public void setEndYPosition(int endYPosition) {
		this.endYPosition = endYPosition;
	}

}

class Circle extends Shape {
	private int	radius;

	Circle (int argStartXPosition, int argStartYPosition, int argRadius) {
		super (2, argStartXPosition, argStartYPosition);
		
		this.radius = argRadius;
	}
	
	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
}

class Square extends Shape {
	private int	width;
	private int	height;
	
	Square (int argStartXPosition, int argStartYPosition, int argWidth, int argHeight) {
		super (3, argStartXPosition, argStartYPosition);
		
		this.width 		= argWidth;
		this.height 	= argHeight;
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
