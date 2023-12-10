package edu.project4.render;

import edu.project4.Pixel;
import java.util.concurrent.ThreadLocalRandom;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class OneTreadRenderer implements Renderer {
    @Override
    public Pixel[][] render(
        int n, int eqCount, int iter, int xRes, int yRes, Coefficient[] coefficients,
        Pixel[][] pixels, int symmetry, String function
    ) {
        for (int i = 0; i < n; i++) {
            double newX = rand(X_MIN, X_MAX);
            double newY = rand(Y_MIN, Y_MAX);

            for (int step = -20; step < iter; step++) {
                int index = ThreadLocalRandom.current().nextInt(eqCount);
                double x = coefficients[index].a * newX + coefficients[index].b * newY + coefficients[index].c;
                double y = coefficients[index].d * newX + coefficients[index].e * newY + coefficients[index].f;

                Point newXY = NonlinearTransformation.transformXY(function, x, y);
                newX = newXY.x();
                newY = newXY.y();

                double theta = 0;
                for (int j = 0; j < symmetry; j++) {
                    theta += ((2 * Math.PI) / (symmetry));

                    double x_rot = newX * cos(theta) - newY * sin(theta);
                    double y_rot = newX * sin(theta) + newY * cos(theta);

                    if (x_rot >= X_MIN && x_rot <= X_MAX && y_rot >= Y_MIN && y_rot <= Y_MAX) {
                        int x1 = xRes - (int) (((X_MAX - x_rot) / (X_MAX - X_MIN)) * xRes);
                        int y1 = yRes - (int) (((Y_MAX - y_rot) / (Y_MAX - Y_MIN)) * yRes);

                        if (x1 > -1 && x1 < xRes && y1 > -1 && y1 < yRes) {
                            if (pixels[x1][y1].counter == 0) {
                                pixels[x1][y1].red = coefficients[index].red;
                                pixels[x1][y1].green = coefficients[index].green;
                                pixels[x1][y1].blue = coefficients[index].blue;
                            } else {
                                pixels[x1][y1].red = (pixels[x1][y1].red + coefficients[index].red) / 2;
                                pixels[x1][y1].green = (pixels[x1][y1].green + coefficients[index].green) / 2;
                                pixels[x1][y1].blue = (pixels[x1][y1].blue + coefficients[index].blue) / 2;
                            }
                            pixels[x1][y1].counter++;
                        }
                    }
                }
            }
        }
        return pixels;
    }
}
