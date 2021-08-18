package com.avangarde.parkinglot.auxs.fileIO;

import java.nio.file.Paths;

public class Utils {
    public static String resourcesPath() {
        return Paths.get("src", "main", "resources").toFile().getAbsolutePath() + "/";
    }
}
