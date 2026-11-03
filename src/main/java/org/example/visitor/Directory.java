package org.example.visitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class Directory implements Element {
    private final String name;
    private final List<Element> children = new ArrayList<>();

    public Directory(String name) {
        this.name = Objects.requireNonNull(name, "name");
        if (name.isBlank()) {
            throw new IllegalArgumentException("Directory name cannot be blank");
        }
    }

    public String getName() {
        return name;
    }

    public void add(Element element) {
        children.add(Objects.requireNonNull(element, "element"));
    }

    public List<Element> getChildren() {
        return Collections.unmodifiableList(children);
    }

    @Override
    public void accept(Visitor visitor) {
        Objects.requireNonNull(visitor, "visitor").visitDirectory(this);
    }
}
