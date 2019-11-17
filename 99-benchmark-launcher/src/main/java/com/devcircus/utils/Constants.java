package com.devcircus.utils;

import java.io.File;
import java.io.IOException;

public enum Constants {
    // Project Folder - Artifact ID - Version
    BASE("00-base", "jhipster-performance-bench-00-base", "0.0.1-SNAPSHOT"),
    INDEXER("01-spring-context-indexer", "jhipster-performance-bench-01-spring-context-indexer", "0.0.1-SNAPSHOT"),
    NOVERIFY("02-noverifyr", "jhipster-performance-bench-02-noverify", "0.0.1-SNAPSHOT");

    private String path;

    /**
     * Generate the experiment path
     *
     * @param artifactId 
     * @param version
     */
    Constants(String projectFolder, String artifactId, String version) {
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
