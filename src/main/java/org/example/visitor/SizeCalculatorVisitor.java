package org.example.visitor;

public final class SizeCalculatorVisitor implements Visitor {
    private long totalSizeBytes;

    @Override
    public void visitFile(File file) {
        totalSizeBytes += file.getSizeBytes();
    }

    @Override
    public void visitDirectory(Directory directory) {
        for (Element child : directory.getChildren()) {
            child.accept(this);
        }
    }

    public long getTotalSizeBytes() {
        return totalSizeBytes;
    }
}
