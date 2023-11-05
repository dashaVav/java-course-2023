package edu.project2;

import java.util.List;
import java.util.Scanner;

public final class Main {

    private Main() {
    }

    private final static int MIN_LEN_MAZE = 4;

    private static void isValidSizeOfMaze(int width, int height) {
        if (width < MIN_LEN_MAZE || height < MIN_LEN_MAZE) {
            throw new IllegalArgumentException("Размеры лабиринта должны быть больше 3");
        }
    }

    @SuppressWarnings("checkstyle:RegexpSinglelineJava")
    private static Boolean isValidCoordinates(int x, int y, Maze maze) {
        try {
            if (maze.grid()[y][x].getType().equals(Cell.Type.WALL)) {
                System.out.printf("%d %d - стена %n", x, y);
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            System.out.printf("Координаты %d %d выходят за границы лабиринта%n", x, y);
            return false;
        }
    }

    @SuppressWarnings("checkstyle:RegexpSinglelineJava")
    public static void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ширину лабиринта:");
        int width = scanner.nextInt();
        System.out.println("Введите высоту лабиринта:");
        int height = scanner.nextInt();
        try {
            isValidSizeOfMaze(width, height);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        Maze maze = Generator.generateMaze(width, height);
        System.out.println(Renderer.render(maze));

        System.out.println("Введите стартовые координаты - х y");
        int startX = scanner.nextInt();
        int startY = scanner.nextInt();

        System.out.println("Введите конечные координаты - х y");
        int endX = scanner.nextInt();
        int endY = scanner.nextInt();

        if (isValidCoordinates(startX, startY, maze) && isValidCoordinates(endX, endY, maze)) {
            List<Coordinate> path = Solver.solveMaze(maze, new Coordinate(startX, startY), new Coordinate(endX, endY));
            System.out.println(Renderer.render(maze, path));
        }
    }

    public static void main(String[] args) {
        start();
    }
}
