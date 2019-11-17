package com.devcircus.utils;

import java.io.File;
import java.io.IOException;

public enum JarPath {
    // Project Folder - Artifact ID - Version
    BASE("00-base", "jhipster-performance-bench-00-base", "0.0.1-SNAPSHOT");

    private String path;

    /**
     * Generate the experiment path
     *
     * @param artifactId 
     * @param version
     */
    JarPath(String projectFolder, String artifactId, String version) {
        try {
            path = new File("..").getAbsoluteFile().getCanonicalPath() + File.separator
                    + projectFolder + File.separator + "target" + File.separator + artifactId
                    + "-" + version + ".jar";
        } catch (IOException e) {
            throw new IllegalStateException("Cannot find benchmarks", e);
        }
    }

    /**
     * Return the project path
     *
     * @return
     */
    public String path() {
        return path;
    }

}
