package org.example.visitor;

import java.util.Objects;
public final class File implements Element {
    private final String name;
    private final long sizeBytes;

    public File(String name, long sizeBytes) {
        this.name = Objects.requireNonNull(name, "name");
        if (name.isBlank()) {
            throw new IllegalArgumentException("File name cannot be blank");
        }
        if (sizeBytes < 0) {
            throw new IllegalArgumentException("File size cannot be negative");
        }
        this.sizeBytes = sizeBytes;
    }

    public String getName() {
        return name;
    }

    public long getSizeBytes() {
        return sizeBytes;
    }

    @Override
    public void accept(Visitor visitor) {
        Objects.requireNonNull(visitor, "visitor").visitFile(this);
    }
}
