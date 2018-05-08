package com.trivago.rta.files;

import com.trivago.rta.exceptions.filesystem.FileCreationException;
import com.trivago.rta.exceptions.filesystem.MissingFileException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FileIOTest {
    @Rule
    public final TemporaryFolder testFolder = new TemporaryFolder();
    private final FileIO fileIO = new FileIO();

    @Test(expected = FileCreationException.class)
    public void writeToInvalidFileTest() throws Exception {
        fileIO.writeContentToFile(null, "");
    }

    @Test
    public void fileReadWriteTest() throws Exception {
        String testString = "This is a test!";
        String path = testFolder.getRoot().getPath().concat("/test.tmp");
        fileIO.writeContentToFile(testString, path);
        assertThat(fileIO.readContentFromFile(path), is(testString));
    }

    @Test(expected = MissingFileException.class)
    public void readFromMissingFileTest() throws Exception {
        String wrongPath = testFolder.getRoot().getPath().concat("/missing.tmp");
        fileIO.readContentFromFile(wrongPath);
    }
}
