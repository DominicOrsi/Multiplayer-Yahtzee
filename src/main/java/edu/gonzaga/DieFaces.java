package edu.gonzaga;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

public class DieFaces {
    private final ArrayList<ImageIcon> images;

    public DieFaces() throws IOException {
        images = new ArrayList<>();
        loadImages();
    }

    private void loadImages() throws IOException {
        BufferedImage currentFace;
        images.add(null);
        for (int i = 1; i <= 12; ++i) {
            String imagePath = getFileName(i);
            currentFace = ImageIO.read(new File(imagePath));
            images.add(new ImageIcon(currentFace.getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        }
    }

    private String getFileName(int face) {
        String currentPath = "src/media";
        if (face < 10) currentPath = currentPath + "/D6-0" + face + ".png";
        else currentPath = currentPath + "/D6-" + face + ".png";
        return currentPath;
    }

    public ImageIcon getFace(int face) {
        return images.get(face);
    }
}
