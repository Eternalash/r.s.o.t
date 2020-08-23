package com.designpattern.singleton;

public class SingleTon {
    /**
     * 类级内部类，用于缓存类实例 该类将在被调用时才会被装载，从而实现了延迟加载 同时由于instance采用静态初始化的方式，因此JVM能保证其线程安全性
     */
    private static class Instance {
        private static SingleTon instance = new SingleTon();
    }

    /**
         * 私有化构造方法，使外部无法通过构造方法构造除instance外的类实例 从而达到单例模式控制类实例数目的目的
         */
        private SingleTon() {
        }

    /**
     * 类实例的全局访问方法 添加static关键词使得外部可以通过类名直接调用该方法获取类实例
     *
     * @return 单例类实例
     */
    public static SingleTon getInstance() {
        return Instance.instance;
    }
}
