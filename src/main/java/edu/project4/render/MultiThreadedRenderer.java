package edu.project4.render;

import edu.project4.Pixel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class MultiThreadedRenderer implements Renderer {
    private Lock[][] locks;
    private Pixel[][] newPixels;

    private void locksInit(int xRes, int yRes) {
        locks = new ReentrantLock[xRes][yRes];
        for (int i = 0; i < xRes; i++) {
            for (int j = 0; j < yRes; j++) {
                locks[i][j] = new ReentrantLock();
            }
        }
    }

    @Override
    public Pixel[][] render(
        int n, int eqCount, int iter, int xRes, int yRes, Coefficient[] coefficients,
        Pixel[][] pixels, int symmetry, String function
    ) {
        locksInit(xRes, yRes);
        newPixels = pixels;

        try (ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime()
            .availableProcessors())) {
            for (int i = 0; i < n; i++) {
                executorService.submit(() -> processIteration(
                    eqCount,
                    iter,
                    xRes,
                    yRes,
                    coefficients,
                    symmetry,
                    function
                ));
            }
        }
        return newPixels;
    }

    private void processIteration(
        int eqCount, int iter, int xRes, int yRes, Coefficient[] coefficients, int symmetry, String func
    ) {
        double newX = rand(X_MIN, X_MAX);
        double newY = rand(Y_MIN, Y_MAX);

        for (int step = -20; step < iter; step++) {
            int i = ThreadLocalRandom.current().nextInt(eqCount);
            double x = coefficients[i].a * newX + coefficients[i].b * newY + coefficients[i].c;
            double y = coefficients[i].d * newX + coefficients[i].e * newY + coefficients[i].f;
            Point newXY = NonlinearTransformation.transformXY(func, x, y);
            newX = newXY.x();
            newY = newXY.y();

            double theta = 0;
            for (int j = 0; j < symmetry; j++) {
                theta += ((2 * Math.PI) / (symmetry));
                double xRot = newX * cos(theta) - newY * sin(theta);
                double yRot = newX * sin(theta) + newY * cos(theta);

                if (xRot >= X_MIN && xRot <= X_MAX && yRot >= Y_MIN && yRot <= Y_MAX) {
                    int x1 = xRes - (int) (((X_MAX - xRot) / (X_MAX - X_MIN)) * xRes);
                    int y1 = yRes - (int) (((Y_MAX - yRot) / (Y_MAX - Y_MIN)) * yRes);

                    if (x1 > -1 && x1 < xRes && y1 > -1 && y1 < yRes) {
                        locks[x1][y1].lock();
                        try {
                            if (newPixels[x1][y1].counter == 0) {
                                newPixels[x1][y1].red = coefficients[i].red;
                                newPixels[x1][y1].green = coefficients[i].green;
                                newPixels[x1][y1].blue = coefficients[i].blue;
                            } else {
                                newPixels[x1][y1].red = (newPixels[x1][y1].red + coefficients[i].red) / 2;
                                newPixels[x1][y1].green = (newPixels[x1][y1].green + coefficients[i].green) / 2;
                                newPixels[x1][y1].blue = (newPixels[x1][y1].blue + coefficients[i].blue) / 2;
                            }
                            newPixels[x1][y1].counter++;
                        } finally {
                            locks[x1][y1].unlock();
                        }
                    }
                }
            }
        }
    }
}
