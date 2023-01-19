package ru.aasmc.io.file;

import java.io.File;
import java.util.Date;

public class Examples {
    public static void main(String[] args) throws Exception {
        String workingDir = System.getProperty("user.dir");
        System.out.println("Current working directory is: " + workingDir);
        File example = new File(workingDir, "example.txt");
        System.out.println("Absolute path " + example.getAbsolutePath());
        System.out.println("Path " + example.getPath());
        System.out.println("Canonical File " + example.getCanonicalFile());
        System.out.println("Canonical Path " + example.getCanonicalPath());
        System.out.println("Name " + example.getName());
        System.out.println("Parent " + example.getParent());
        System.out.println("Parent File " + example.getParentFile());
        System.out.println("Is absolute " + example.isAbsolute());

        File workingDirectory = new File(workingDir);
        System.out.println("Exists " + workingDirectory.exists());
        System.out.println("Is directory " + workingDirectory.isDirectory());
        System.out.println("Is File " + workingDirectory.isFile());
        System.out.println("Is Hidden " + workingDirectory.isHidden());
        System.out.println("Last modified " + new Date(workingDirectory.lastModified()));
        System.out.println("Length " + workingDirectory.length());

        System.out.println("Dumping roots");
        dumpRoots();
    }

    private static void dumpRoots() {
        File[] roots = File.listRoots();
        for (File root : roots) {
            System.out.println("Root " + root);
        }
    }
}
























