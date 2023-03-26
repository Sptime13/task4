package com.example.task4.model;


import javafx.scene.image.Image;
import java.nio.file.Paths;


public class ConcreteAggregate implements Aggregate{
    private String filetopic;
    private Image bi;

    @Override
    public Iterator getIterator() {
        return new ImageIterator();
    }

    public ConcreteAggregate(String filetopic) {
        this.filetopic = filetopic;
    }
    private class ImageIterator implements Iterator{

        private int current = 0;

        private Image getImage(int iterator){
            String filename = Paths.get("src/main/resources/img/"+ filetopic + iterator +".png").toUri().toString();
            return new Image(filename);
        }

        @Override
        public boolean hasNext(int i) {
            return !getImage(current+i).isError();
        }

        @Override
        public Object next() {
            if(this.hasNext(1)) {
                return getImage(++current);
            }
            current = 1;
            return getImage(1);
        }

        public Object preview() {
            if(this.hasNext(-1)) {
                return getImage(--current);
            }
            current = 1;
            return getImage(1);
        }

    }
}
