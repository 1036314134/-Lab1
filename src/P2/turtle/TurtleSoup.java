/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P2.turtle;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;


public class TurtleSoup {

    /**
     * Draw a square.
     * 
     * @param turtle the turtle context
     * @param sideLength length of each side
     */

    public static void drawSquare(Turtle turtle, int sideLength) {
    	for(int i = 1; i <= 4; i++) {
    		turtle.forward(sideLength);
    		turtle.turn(90);
    	}
    }

    /**
     * Determine inside angles of a regular polygon.
     * 
     * There is a simple formula for calculating the inside angles of a polygon;
     * you should derive it and use it here.
     * 
     * @param sides number of sides, where sides must be > 2
     * @return angle in degrees, where 0 <= angle < 360
     */
    public static double calculateRegularPolygonAngle(int sides) {
    	if(sides <= 2) {
    		throw new RuntimeException("sides <= 2");
    	}
    	
    	double angle = 0.0;
    	double side = (double)sides;
    	angle = 180*(side-2)/side;
    	//angle = (double) Math.round(angle * 100) / 100;
    	return angle;  
    }

    /**
     * Determine number of sides given the size of interior angles of a regular polygon.
     * 
     * There is a simple formula for this; you should derive it and use it here.
     * Make sure you *properly round* the answer before you return it (see java.lang.Math).
     * HINT: it is easier if you think about the exterior angles.
     * 
     * @param angle size of interior angles in degrees, where 0 < angle < 180
     * @return the integer number of sides
     */
    public static int calculatePolygonSidesFromAngle(double angle) {
    	try {
    		if(angle <= 0 || angle >= 180) {
    			throw new RuntimeException("angel is wrong");
    		}
 		}catch(Exception e){
 			System.out.println("angel is wrong") ;
 		}
    	
    	int sides = 0;
    	sides = (int)(360/(int)(180-angle));
        return sides;
    }

    /**
     * Given the number of sides, draw a regular polygon.
     * 
     * (0,0) is the lower-left corner of the polygon; use only right-hand turns to draw.
     * 
     * @param turtle the turtle context
     * @param sides number of sides of the polygon to draw
     * @param sideLength length of each side
     */
    public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
    	try {
    		if(sides <= 2){
        		throw new RuntimeException("sides <= 2");
        	}
            if(sideLength <= 0){
            	throw new RuntimeException("sideLength <= 0");
            }
    	}catch(Exception e){
 			System.out.println("input wrong") ;
 		}
    	
		for(int i = 1; i <= sides; i++){
			turtle.forward(sideLength);
			turtle.turn(180 - calculateRegularPolygonAngle(sides));
		} 
    }

    /**
     * Given the current direction, current location, and a target location, calculate the Bearing
     * towards the target point.
     * 
     * The return value is the angle input to turn() that would point the turtle in the direction of
     * the target point (targetX,targetY), given that the turtle is already at the point
     * (currentX,currentY) and is facing at angle currentBearing. The angle must be expressed in
     * degrees, where 0 <= angle < 360. 
     *
     * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
     * 
     * @param currentBearing current direction as clockwise from north
     * @param currentX current location x-coordinate
     * @param currentY current location y-coordinate
     * @param targetX target point x-coordinate
     * @param targetY target point y-coordinate
     * @return adjustment to Bearing (right turn amount) to get to target point,
     *         must be 0 <= angle < 360
     */
    public static double calculateBearingToPoint(double currentBearing, int currentX, int currentY,
                                                 int targetX, int targetY) {
    	double targetBearing = Math.atan2(targetX-currentX,targetY-currentY)/(Math.PI/180);
    	double angle = 0;
    	angle = targetBearing - currentBearing;
    	if(angle < 0) {
    		angle += 360;
    	}
       
        return angle;
    }

    /**
     * Given a sequence of points, calculate the Bearing adjustments needed to get from each point
     * to the next.
     * 
     * Assumes that the turtle starts at the first point given, facing up (i.e. 0 degrees).
     * For each subsequent point, assumes that the turtle is still facing in the direction it was
     * facing when it moved to the previous point.
     * You should use calculateBearingToPoint() to implement this function.
     * 
     * @param xCoords list of x-coordinates (must be same length as yCoords)
     * @param yCoords list of y-coordinates (must be same length as xCoords)
     * @return list of Bearing adjustments between points, of size 0 if (# of points) == 0,
     *         otherwise of size (# of points) - 1
     */
    public static List<Double> calculateBearings(List<Integer> xCoords, List<Integer> yCoords) {
    	List<Double> ans = new ArrayList<Double>();
    	try {
    		if(xCoords.size() != yCoords.size()) {
    			throw new RuntimeException("wrong");
    		}
 		}catch(Exception e){
 			System.out.println("input wrong") ;
 		}
    	
    	int n = xCoords.size();
    	if(n == 0 || n == 1) {
    		ans = null;
    		return ans;
    	}
    	double currentBearing = 0;
    	double angle = 0;
    	for(int i = 0; i < n - 1; i++) {
    		angle = calculateBearingToPoint(currentBearing, xCoords.get(i), yCoords.get(i),xCoords.get(i+1), yCoords.get(i+1));
    		currentBearing = Math.atan2(xCoords.get(i+1)-xCoords.get(i),yCoords.get(i+1)-yCoords.get(i))/(Math.PI/180);
    		ans.add(angle);
    	}
    	return ans;
    }
    
    /**
     * Given a set of points, compute the convex hull, the smallest convex set that contains all the points 
     * in a set of input points. The gift-wrapping algorithm is one simple approach to this problem, and 
     * there are other algorithms too.
     * 
     * @param points a set of points with xCoords and yCoords. It might be empty, contain only 1 point, two points or more.
     * @return minimal subset of the input points that form the vertices of the perimeter of the convex hull
     */
    public static double calculateBearingToPoint2(double currentBearing, double currentX, double currentY,
            double targetX, double targetY) {
    	
		double targetBearing = Math.atan2(targetX-currentX,targetY-currentY)/(Math.PI/180);
		double angle = 0;
		angle = targetBearing - currentBearing;
		if(angle < 0) {
			angle += 360;
		}
		
		return angle;
    }
  
    
    public static Set<Point> convexHull(Set<Point> points) {
    	Set<Point> ans = new HashSet<Point>();
    	
    	int n = points.size();
    	if(n <= 3) {
    		ans = points;
    	}else {
    		Point min = null;//找到最坐下方的点
    		for(Point i : points){
    			if(min == null){
    				min = i;
    			}
				if(min.x() > i.x()) {
					min = i;
				}else if(min.x() == i.x()){
					if(i.y() < min.y()) {
						min = i;
					}
				}
    		}
    		
    		ans.add(min); //该点加入答案
    		
    		Point now = min;
    		Point next = null;
    		double nowBearing;
    		double nextBearing;
    		double preBearing = 0;
    		double Length;
    		
    		while(true) {//边界漫游
    			nextBearing = 360;
    			Length = Double.MAX_VALUE;
    			for(Point i : points) {
    				if(i.equals(now)) {
    					continue;
    				}
    				nowBearing = calculateBearingToPoint2(preBearing,now.x(),now.y(),i.x(),i.y());//遍历点的偏转角
    				if(nextBearing > nowBearing) {//找到最小偏转角的点
    					Length = Math.pow(i.x()-now.x(), 2)+Math.pow(i.y()-now.y(), 2);
    					nextBearing = nowBearing; 
    					next = i;
    				}else if(nextBearing == nowBearing) {//角度相等则找距离大的点
    					if(Length < (Math.pow(i.x()-now.x(), 2)+Math.pow(i.y()-now.y(), 2))){
    						Length = Math.pow(i.x()-now.x(), 2)+Math.pow(i.y()-now.y(), 2);
    						next = i;
    					}
    				}
    			}
    			if(min.equals(next)) {//回到原点则结束
    				break;
    			}
    			ans.add(next);
    			now = next;
    			preBearing += nextBearing;
    		}
    	}
    	return ans;
    }
    
    /**
     * Draw your personal, custom art.
     * 
     * Many interesting images can be drawn using the simple implementation of a turtle.  For this
     * function, draw something interesting; the complexity can be as little or as much as you want.
     * 
     * @param turtle the turtle context
     */
    public static void drawPersonalArt(Turtle turtle){
    	turtle.turn(150);
    	for(int i = 1; i <= 3; i++) {
    		turtle.forward(60);
    		turtle.turn(120);
    	}
    	turtle.forward(20);
    	turtle.turn(300);
    	turtle.forward(20);
    	for(int i = 1; i <= 3; i++) {
    		turtle.turn(120);
    		turtle.forward(60);
    	}

    }

    /**
     * Main method.
     * 
     * This is the method that runs when you run "java TurtleSoup".
     * 
     * @param args unused
     */
    
    public static void main(String args[]){
        DrawableTurtle turtle = new DrawableTurtle();

        drawSquare(turtle, 40);
        drawRegularPolygon(turtle, 8, 40);
        drawPersonalArt(turtle);
        
        
        // draw the window
        turtle.draw();
        
    }

}
