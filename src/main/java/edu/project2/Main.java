package edu.project2;

import java.util.List;
import java.util.Scanner;

public final class Main {

    private Main() {
    }

    @SuppressWarnings("checkstyle:RegexpSinglelineJava")
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ширину лабиринта:");
        int width = scanner.nextInt();
        System.out.println("Введите высоту лабиринта:");
        int height = scanner.nextInt();

        Maze maze = Generator.generateMaze(width, height);
        System.out.println(Renderer.render(maze));

        System.out.println("Введите стартовые координаты - х y");
        int startX = scanner.nextInt();
        int startY = scanner.nextInt();

        System.out.println("Введите конечные координаты - х y");
        int endX = scanner.nextInt();
        int endY = scanner.nextInt();

        List<Coordinate> path = Solver.solveMaze(maze, new Coordinate(startX, startY), new Coordinate(endX, endY));

        System.out.println(Renderer.render(maze, path));
    }
}
