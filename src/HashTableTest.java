import java.util.Random;

public class HashTableTest {

    public static void main(String[] args) {
        MyHashTable<MyTestingClass, Student> table = new MyHashTable<>(11);

        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            int id = random.nextInt(100000);
            MyTestingClass key = new MyTestingClass(id);
            Student value = new Student("Student" + id, random.nextInt(100));
            table.put(key, value);
        }

        printBucketSizes(table);
    }

    private static void printBucketSizes(MyHashTable<MyTestingClass, Student> table) {
        for (int i = 0; i < table.getCapacity(); i++) {
            int bucketSize = 0;
            MyHashTable.HashNode<MyTestingClass, Student> head = table.getChainArray()[i];
            while (head != null) {
                bucketSize++;
                head = head.next;
            }
            System.out.println("Bucket " + i + " size: " + bucketSize);
        }
    }


}
