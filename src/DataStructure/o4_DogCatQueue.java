package DataStructure;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by yetmare on 18-12-23.
 * 猫狗队列
 * 题目：已知宠物、狗和猫的类分别为Pet，Dog,Cat
 * 实现一种猫狗队列的结构，要求如下：
 * 用户可以调用add方法将cat类或dog类的实例放入队列中;
 * 用户可以调用pollAll方法，将队列中所有的实例按照进队列的先后顺序依次弹出；
 * 用户可以调用pollDog方法，将队列中dog类的实例按照进队列的先后顺序依次弹出;
 * 用户可以调用pollCat方法，将队列中cat类的实例按照进队列的先后顺序依次弹出;
 * 用户可以调用isEmpty方法，检查队列中是否还有dog或cat的实例；
 * 用户可以调用isDogEmpty方法，检查队列中是否有dog类实例；
 * 用户可以调用isCatEmpty方法，检查队列中是否有cat类实例；
 */
public class o4_DogCatQueue {
    public static class Pet {
        private String type;
        public Pet(String type) {
            this.type = type;
        }
        public String getPetType() {
            return this.type;
        }
    }

    public static class Dog extends Pet {
        public Dog() {
            super("dog");
        }
    }

    public static class Cat extends Pet {
        public Cat() {
            super("cat");
        }
    }

    public static class PetEnterQueue {
        private Pet pet;
        private long count;
        public PetEnterQueue(Pet pet, long count) {
            this.pet = pet;
            this.count = count;
        }

        public Pet getPet() {
            return this.pet;
        }

        public long getPetNum() {
            return this.count;
        }

        public String getEneterType() {
            return this.pet.getPetType();
        }
    }

    // 由于题目中有要求入队/出队的先后顺序，所以这里需要给Pet封装一个时间
    public static class DogCatQueue {
        private Queue<PetEnterQueue> dogQueue;
        private Queue<PetEnterQueue> catQueue;
        private long count;

        public DogCatQueue() {
            this.dogQueue = new LinkedList<>();
            this.catQueue = new LinkedList<>();
            this.count = 0;
        }

        public void add(Pet pet) {
            if(pet == null) {
                return;
            }
            if(pet.getPetType().equals("dog")) {
                this.dogQueue.add(new PetEnterQueue(pet, this.count++));
            } else if(pet.getPetType().equals("cat")) {
                this.catQueue.add(new PetEnterQueue(pet, this.count++));
            } else {
                throw new RuntimeException("err, not cat or dog!");
            }
        }

        public Pet pollAll() {
            if(!this.dogQueue.isEmpty() && !this.catQueue.isEmpty()) {
                if(this.dogQueue.peek().getPetNum() < this.catQueue.peek().getPetNum()) {
                    return this.dogQueue.poll().getPet();
                } else {
                    return this.catQueue.poll().getPet();
                }
            } else if(!this.dogQueue.isEmpty()) {
                return this.dogQueue.poll().getPet();
            } else if(!this.catQueue.isEmpty()) {
                return this.catQueue.poll().getPet();
            } else {
                throw new RuntimeException("err, queue is empty!");
            }
        }

        public Dog pollDog() {
            if(!this.isDogQueueEmpty()) {
                return (Dog) dogQueue.poll().getPet();
            } else {
                throw new RuntimeException("Dog queue is empty!");
            }
        }

        public Cat pollCat() {
            if(!this.isCatQueueEmpty()) {
                return (Cat) catQueue.poll().getPet();
            } else {
                throw new RuntimeException("Cat queue is empty!");
            }
        }

        public boolean isEmpty() {
            if(this.dogQueue.isEmpty() && this.catQueue.isEmpty()) {
                return true;
            }
            return false;
        }

        public boolean isDogQueueEmpty() {
            return this.dogQueue.isEmpty();
        }

        public boolean isCatQueueEmpty() {
            return this.catQueue.isEmpty();
        }
    }

    public static void main(String[] args) {
        DogCatQueue test = new DogCatQueue();

        Pet dog1 = new Dog();
        Pet cat1 = new Cat();
        Pet dog2 = new Dog();
        Pet cat2 = new Cat();
        Pet dog3 = new Dog();
        Pet cat3 = new Cat();

        test.add(dog1);
        test.add(cat1);
        test.add(dog2);
        test.add(cat2);
        test.add(dog3);
        test.add(cat3);

        test.add(dog1);
        test.add(cat1);
        test.add(dog2);
        test.add(cat2);
        test.add(dog3);
        test.add(cat3);

        test.add(dog1);
        test.add(cat1);
        test.add(dog2);
        test.add(cat2);
        test.add(dog3);
        test.add(cat3);
        while (!test.isDogQueueEmpty()) {
            System.out.println(test.pollDog().getPetType());
        }
        while (!test.isEmpty()) {
            System.out.println(test.pollAll().getPetType());
        }
    }
}
