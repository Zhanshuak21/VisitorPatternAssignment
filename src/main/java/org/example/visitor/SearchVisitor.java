package org.example.visitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public final class SearchVisitor implements Visitor {
    private final String query;
    private final List<String> matchingPaths = new ArrayList<>();
    private final List<String> directoryPath = new ArrayList<>();

    public SearchVisitor(String query) {
        this.query = Objects.requireNonNull(query, "query").toLowerCase(Locale.ROOT);
    }

    @Override
    public void visitFile(File file) {
        if (file.getName().toLowerCase(Locale.ROOT).contains(query)) {
            matchingPaths.add(String.join("/", directoryPath) + "/" + file.getName());
        }
    }

    @Override
    public void visitDirectory(Directory directory) {
        directoryPath.add(directory.getName());
        try {
            for (Element child : directory.getChildren()) {
                child.accept(this);
            }
        }
        finally {
            directoryPath.remove(directoryPath.size() - 1);
        }
    }

    public List<String> getMatchingPaths() {
        return Collections.unmodifiableList(matchingPaths);
    }
}
