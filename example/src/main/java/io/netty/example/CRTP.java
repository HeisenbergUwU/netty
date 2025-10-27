package io.netty.example;

/**
 * Curiously Recurring Template Pattern
 * - Fluent Builder 或可链式调用的基类
 */
public class CRTP {
    abstract static class Animal<T extends Animal<T>> {
        private String name;

        // 返回 T，而不是Animal类型，确保链式调用保持子类类型。
        public T setName(String name) {
            this.name = name;
            return self();
        }

        public String getName() {
            return name;
        }

        protected abstract T self();
    }

    static class Dog extends Animal<Dog> {
        private String breed;

        public Dog(String s) {
            this.breed = s;
        }

        public Dog setBreed(String breed) {
            this.breed = breed;
            return this;
        }

        public String getBreed() {
            return breed;
        }

        @Override
        protected Dog self() {
            return this;
        }
    }
//    ❌ 问题来了：如果不在 Animal<T> 进行限定也是可以工作，但是编译器不会进行约束性检测了。
//    你可以写 class Dog extends Animal<String>，这显然荒谬，但编译器不阻止！
//    setName() 返回的是 T，但 T 可以是任意类型，完全失去类型关联。
//    无法保证 self() 返回的是当前对象（甚至类型都不对）。
//    👉 这不是 CRTP，只是普通泛型，且极易误用。
    public static void main(String[] args) {
        Dog dog = new Dog("wang!");

        System.out.println(dog.getBreed());

        dog.setBreed("ABC").setBreed("EFG");
        System.out.println(dog.getBreed());
    }
}
