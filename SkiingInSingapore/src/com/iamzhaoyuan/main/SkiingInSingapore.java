package com.iamzhaoyuan.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class is used to solve the RedMart hiring question.
 * <br><br>
 * <b>Skiing in Singapore - a coding diversion</b>
 * 
 * <p>Sometimes it's nice to take a break and code up a solution to a small, 
 * fun problem. Here is one some of our engineers enjoyed recently called 
 * <em>Skiing In Singapore</em>.</p>
 * 
 * <p>Well you can�t really ski in Singapore. But let�s say you hopped on a 
 * flight to the Niseko ski resort in Japan. Being a software engineer you can�t 
 * help but value efficiency, so naturally you want to ski as long as possible and 
 * as fast as possible without having to ride back up on the ski lift. So you take 
 * a look at the map of the mountain and try to find the longest ski run down.</p>
 * 
 * <p>In digital form the map looks like the number grid below.</p>
 * 
 * <table>
 * <tr><td>4</td><td>4</td></tr>
 * <tr><td>4</td><td>8</td><td>7</td><td>3</td></tr>
 * <tr><td>2</td><td>5</td><td>9</td><td>3</td></tr>
 * <tr><td>6</td><td>3</td><td>2</td><td>5</td></tr>
 * <tr><td>4</td><td>4</td><td>1</td><td>6</td></tr>
 * </table>
 * 
 * <p>The first line (4 4) indicates that this is a 4x4 map. 
 * Each number represents the elevation of that area of the mountain. 
 * From each area (i.e. box) in the grid you can go north, south, east, west 
 * - but only if the elevation of the area you are going into is less than the 
 * one you are in. I.e. you can only ski downhill. You can start anywhere on 
 * the map and you are looking for a starting point with the longest possible 
 * path down as measured by the number of boxes you visit. And if there are 
 * several paths down of the same length, you want to take the one with the 
 * steepest vertical drop, i.e. the largest difference between your starting 
 * elevation and your ending elevation.</p>
 * <p>On this particular map the longest path down is of length=5 and it�s 
 * highlighted in bold below: <b>9-5-3-2-1.</b></p>
 * 
 * <table>
 * <tr><td>4</td><td>4</td></tr>
 * <tr><td>4</td><td>8</td><td>7</td><td>3</td></tr>
 * <tr><td>2</td><td><b>5</b></td><td><b>9</b></td><td>3</td></tr>
 * <tr><td>6</td><td><b>3</b></td><td><b>2</b></td><td>5</td></tr>
 * <tr><td>4</td><td>4</td><td><b>1</b></td><td>6</td></tr>
 * </table> 
 * 
 * <p>There is another path that is also length five: 8-5-3-2-1. However the 
 * tie is broken by the first path being steeper, dropping from 9 to 1, a drop 
 * of 8, rather than just 8 to 1, a drop of 7.</p>
 * 
 * <p>Your challenge is to write a program in your favorite programming language 
 * to find the longest (and then steepest) path on this 
 * <a href="http://s3-ap-southeast-1.amazonaws.com/geeks.redmart.com/coding-problems/map.txt">map</a> 
 * specified in the format 
 * above. It�s 1000x1000 in size, and all the numbers on it are between 0 and 1500.</p>
 * 
 * <p>Send your code or a github link (and a resume if you like) to [?????? at 
 * redmart dot com], replacing �??????� with the concatenation of the length of the 
 * longest path with the largest drop, and the size of the drop. So in the simple 
 * example above length=5, drop=8, so the email address would be [58 at redmart dot 
 * com]. If your e-mail gets through - you got the right answer.</p>
 * 
 * From <a href="http://geeks.redmart.com/2015/01/07/skiing-in-singapore-a-coding-diversion/">
 * RedMart Blog</a>
 * 
 * @author Yuan
 */
public class SkiingInSingapore {

	/**
	 * Read input file, get 2D array
	 * @return node map
	 */
	private Node[][] getMap() {
		Scanner file = null;
		String path = "/Users/yuan/Documents/workspace/MyProjects/SkiingInSingapore/src/com/iamzhaoyuan/main/map.txt";
		Node[][] map = null;
		try {
			file = new Scanner(new File(path));
			int x = file.nextInt();
			int y = file.nextInt();
			map = new Node[x][y];
			for (int i = 0; i < x; i++) {
				for (int j = 0; j < y; j++) {
					map[i][j] = new Node(file.nextInt());
				}
			}
			long startTime = System.currentTimeMillis();
			for (int i = 0; i < x; i++) {
				for (int j = 0; j < y; j++) {
					Node left = null, right = null, up = null, down = null;
					if (i > 0) left = map[i - 1][j];
					if (i < x - 1) right = map[i + 1][j];
					if (j > 0) up = map[i][j - 1];
					if (j < y - 1) down = map[i][j + 1];
					map[i][j].setNeighbor(up, down, left, right);
				}
			}
			long endTime = System.currentTimeMillis();
			System.out.println("Set neighbors: " + (endTime - startTime) + "ms");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			file.close();
		}
		return map;
	}
	
	private Node[][] createTestMap() {
		Node[][] map = new Node[4][4];
//		4 8 7 3 
//		2 5 9 3 
//		6 3 2 5 
//		4 4 1 6 
		map[0][0] = new Node(4);
		map[0][1] = new Node(2);
		map[0][2] = new Node(6);
		map[0][3] = new Node(4);
		map[1][0] = new Node(8);
		map[1][1] = new Node(5);
		map[1][2] = new Node(3);
		map[1][3] = new Node(4);
		map[2][0] = new Node(7);
		map[2][1] = new Node(9);
		map[2][2] = new Node(2);
		map[2][3] = new Node(1);
		map[3][0] = new Node(3);
		map[3][1] = new Node(3);
		map[3][2] = new Node(5);
		map[3][3] = new Node(6);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				Node left = null, right = null, up = null, down = null;
				if (i > 0) left = map[i - 1][j];
				if (i < 3) right = map[i + 1][j];
				if (j > 0) up = map[i][j - 1];
				if (j < 3) down = map[i][j + 1];
				map[i][j].setNeighbor(up, down, left, right);
			}
		}
		return map;
	}
	
	public int[] getResult() {
		long startTime = System.currentTimeMillis();
		Node[][] map = getMap();
		long endTime = System.currentTimeMillis();
		System.out.println("Load map: " + (endTime - startTime) + "ms");
		startTime = System.currentTimeMillis();
		int[] result = new int[2];
		result[0] = 0;
		result[1] = 0;
		for (Node[] nodes : map) {
			for (Node node : nodes) {
				int tmpLen = node.calculateLength();
				if (tmpLen > result[0] || (tmpLen == result[0] && node.getDrop() > result[1])) {
					result[0] = tmpLen;
					result[1] = node.getDrop();
				}
			}
		}
		endTime = System.currentTimeMillis();
		System.out.println("Calculate path: " + (endTime - startTime) + "ms");
		return result;
	}

	/**
	 * Main method, program entrance
	 * @param args
	 */
	public static void main(String[] args) {
		SkiingInSingapore test = new SkiingInSingapore();
		long startTime = System.currentTimeMillis();
		int result[] = test.getResult();
		long endTime = System.currentTimeMillis();
		System.out.println("Length: " + result[0]);
		System.out.println("Drop: " + result[1]);
		System.out.println("Time used: " + ((endTime - startTime)) + "ms");
	}

}
