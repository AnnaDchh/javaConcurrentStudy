//package com.test.synchronize;
//
///**
// * 使用实现方式模拟火车站售票窗口，开启窗口售票，总票数为100张
// * java实现线程的同步机制：
// * 	（1）、同步代码块
// *		synchronized (同步监视器) {
// *			// 需要被同步的代码块（即为操作共享数据的代码）
// *		}
// *
// *		1、共享数据：多个线程共同操作的数据（变量）
// *		2、同步监视器：由一个对象来充当，哪一个线程获取此监视器，谁就执行大括号里被同步的代码，俗称：锁
// *		(注：要求所有的线程必须共用同一把锁，在实现的方式中可以使用this来充当同步监视器，在继承的方式中不可以)
// *
// * @author Anna.
// * @date 2019-08-26
// */
//public class SellTicketWindowsByImplementTest03 {
//    public static void main(String[] args) {
//        // 创建共享数据
//        Windows2 windows2 = new Windows2(100);
//        // 3、创建实现Runnable接口的子类对象
//        SellTicketThread2 scn = new SellTicketThread2(windows2);
//        // 4、创建一个Thread对象
//        Thread th = new Thread(scn);
//        th.setName("窗口1");
//        Thread th2 = new Thread(scn);
//        th2.setName("窗口2");
//        // 5、调用线程的start()方法：启动此线程，调用相应的run()方法
//        th.start();
//        th2.start();
//    }
//}
//
///**
// * 创建共享数据
// */
//class Windows2 {
//    private int ticket;
//
//    public Windows2(int ticket) {
//        this.ticket = ticket;
//    }
//
//    public int getTicket() {
//        return ticket;
//    }
//
//    public void setTicket(int ticket) {
//        this.ticket = ticket;
//    }
//
//    public void sell() {
//        if (ticket > 0) {
//            ticket--;
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName() + ":售出一张票，还剩" + ticket + "张票");
//        }
//    }
//}
//
///**
// * 1、创建一个实现Runnable接口的子类
// */
//class SellTicketThread2 implements Runnable {
//    private Windows2 windows2;
//
//    public SellTicketThread2(Windows2 windows2) {
//        this.windows2 = windows2;
//    }
//
//    public Windows2 getWindows2() {
//        return windows2;
//    }
//
//    public void setWindows2(Windows2 windows2) {
//        this.windows2 = windows2;
//    }
//
//    /**
//     * 2、实现重写Thread类的run()方法,实现子线程要完成的功能
//     */
//    @Override
//    public void run() {
//        // 子线程完成的功能
//        // 同步代码块
//        while (windows2.getTicket() > 0) {
//            synchronized (this) {
//               windows2.sell();
//            }
//        }
//    }
//}