package com.lu.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author lu
 *
 * @description 实现一个猫狗队列，具备队列的功能，pollAll按进队列顺序弹出，pollDog/pollCat按该类的顺序弹出。
 *
 */
public class CatDogQueue {

    private Queue<ExtraQueue> catQueue;
    private Queue<ExtraQueue> dogQueue;
    private int count;
    
    public CatDogQueue() {
        catQueue = new LinkedList<ExtraQueue>();
        dogQueue = new LinkedList<ExtraQueue>();
        count = 0;
    }
    
    public void add(Pet pet) {
        if(pet.getType().equals("cat")) {
            catQueue.add(new ExtraQueue(pet, count));
            count = count + 1;
        } else if (pet.getType().equals("dog")) {
            dogQueue.add(new ExtraQueue(pet, count));
            count = count + 1;
        }
    }
    
    public Pet pollAll() {
        if(!catQueue.isEmpty() && !dogQueue.isEmpty()) {
            if(catQueue.peek().getCount() < dogQueue.peek().getCount()) {
                return catQueue.poll().getPet();
            } else {
                return dogQueue.poll().getPet();
            }
        } else if (catQueue.isEmpty() && !dogQueue.isEmpty()) {
            return dogQueue.poll().getPet();
        } else if (!catQueue.isEmpty() && dogQueue.isEmpty()) {
            return catQueue.poll().getPet();
        } else {
            throw new RuntimeException("queue is empty!");
        }
    }
    
    public Pet pollCat() {
        if(catQueue.isEmpty()) {
            throw new RuntimeException("cat queue is empty");
        }
        return catQueue.poll().getPet();
    }
    
    public Pet pollDog() {
        if(dogQueue.isEmpty()) {
            throw new RuntimeException("dog queue is empty");
        }
        return dogQueue.poll().getPet(); 
    }
    
    public static void main(String[] args) {
        CatDogQueue queue = new CatDogQueue();
        queue.add(new Pet("cat"));
        queue.add(new Pet("cat"));
        queue.add(new Pet("dog"));
        queue.add(new Pet("cat"));
        queue.add(new Pet("cat"));
        queue.add(new Pet("dog"));
        queue.add(new Pet("cat"));
        queue.add(new Pet("dog"));
        queue.add(new Pet("dog"));
        queue.add(new Pet("dog"));
        queue.add(new Pet("cat"));
        queue.add(new Pet("dog"));
        
        System.out.println(queue.pollAll().getType());
        System.out.println(queue.pollAll().getType());
        System.out.println(queue.pollAll().getType());
        System.out.println(queue.pollAll().getType());
        System.out.println(queue.pollAll().getType());
        System.out.println(queue.pollAll().getType());
        System.out.println(queue.pollAll().getType());
        System.out.println(queue.pollAll().getType());
        System.out.println(queue.pollAll().getType());
        System.out.println(queue.pollAll().getType());
        System.out.println(queue.pollAll().getType());
        System.out.println(queue.pollAll().getType());
    }

}


class ExtraQueue {
    
    private Pet pet;
    private int count;
    
    public ExtraQueue(Pet pet, int count) {
        this.pet = pet;
        this.count = count;
    }
    
    public String getType() {
        return pet.getType();
    }
    
    public int getCount() {
        return count;
    }
    
    public Pet getPet() {
        return pet;
    }
    
}


// 猫狗类已经提供。
class Pet{
    
    private String type;
    
    public Pet(String type) {
        this.type = type;
    }
    
    public String getType() {
        return this.type;
    }
}

class Cat extends Pet {
    
    public Cat() {
        super("cat");
    }
}

class Dog extends Pet {
    
    public Dog() {
        super("dog");
    }
}