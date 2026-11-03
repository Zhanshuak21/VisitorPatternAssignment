package org.example.visitor;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class VisitorTest {
    private Directory sampleTree() {
        Directory root = new Directory("workspace");
        Directory docs = new Directory("docs");
        docs.add(new File("report.pdf", 1200));
        docs.add(new File("notes.txt", 300));
        root.add(docs);
        root.add(new File("profile.png", 1800));
        return root;
    }

    @Test
    void sizeVisitorAddsFilesAtEveryDirectoryLevel() {
        SizeCalculatorVisitor visitor = new SizeCalculatorVisitor();

        sampleTree().accept(visitor);

        assertEquals(3300, visitor.getTotalSizeBytes());
    }

    @Test
    void searchVisitorFindsMatchingFilesAndReturnsPaths() {
        SearchVisitor visitor = new SearchVisitor("p");

        sampleTree().accept(visitor);

        assertEquals(List.of("workspace/docs/report.pdf",
                "workspace/profile.png"), visitor.getMatchingPaths());
    }

    @Test
    void searchIsCaseInsensitive() {
        SearchVisitor visitor = new SearchVisitor("REPORT");

        sampleTree().accept(visitor);

        assertEquals(List.of("workspace/docs/report.pdf"), visitor.getMatchingPaths());
    }

    @Test
    void fileRejectsNegativeSize() {
        assertThrows(IllegalArgumentException.class, () -> new File("bad.bin", -1));
    }

    @Test
    void directoryDoesNotExposeMutableChildCollection() {
        Directory directory = new Directory("docs");
        directory.add(new File("a.txt", 10));

        assertThrows(UnsupportedOperationException.class,
                () -> directory.getChildren().clear());
        assertEquals(1, directory.getChildren().size());
    }
}
