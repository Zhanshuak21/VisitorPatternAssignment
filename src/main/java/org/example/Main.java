package org.example;

import org.example.visitor.Directory;
import org.example.visitor.File;
import org.example.visitor.SearchVisitor;
import org.example.visitor.SizeCalculatorVisitor;

public class Main {
    public static void main(String[] args) {
        Directory root = new Directory("workspace");
        Directory documents = new Directory("documents");
        Directory photos = new Directory("photos");

        documents.add(new File("report.pdf", 1200));
        documents.add(new File("notes.txt", 300));
        photos.add(new File("trip.jpg", 2500));
        photos.add(new File("profile.png", 1800));
        root.add(documents);
        root.add(photos);
        root.add(new File("readme.md", 200));

        SizeCalculatorVisitor sizeVisitor = new SizeCalculatorVisitor();
        root.accept(sizeVisitor);
        System.out.println("Total file size: " + sizeVisitor.getTotalSizeBytes() + " bytes");

        SearchVisitor searchVisitor = new SearchVisitor("p");
        root.accept(searchVisitor);
        System.out.println("Files containing 'p':");
        searchVisitor.getMatchingPaths().forEach(path -> System.out.println("- " + path));
    }
}
