package com.concurrency;

import sun.misc.Contended;

/**
 * Author Bryan.C <br>
 * Date: 2019-01-21 10:12
 */
@SuppressWarnings("unchecked")
public final class FalseSharing implements Runnable {
	public final static int NUM_THREADS = 4; // change
	public final static long ITERATIONS = 500L * 1000L * 1000L;
	private final int arrayIndex;

	private static VolatileLong3[] longs = new VolatileLong3[NUM_THREADS];

	static {
		for (int i = 0; i < longs.length; i++) {
			longs[i] = new VolatileLong3();
		}
	}

	public FalseSharing(final int arrayIndex) {
		this.arrayIndex = arrayIndex;
	}

	public static void main(final String[] args) throws Exception {
		final long start = System.nanoTime();
		runTest();
		System.out.println("duration = " + (System.nanoTime() - start));
	}

	private static void runTest() throws InterruptedException {
		Thread[] threads = new Thread[NUM_THREADS];

		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new FalseSharing(i));
		}

		for (Thread t : threads) {
			t.start();
		}

		for (Thread t : threads) {
			t.join();
		}
	}

	@Override
	public void run() {
		long i = ITERATIONS + 1;
		while (0 != --i) {
			longs[arrayIndex].value = i;
		}
	}


	public final static class VolatileLong {
		public volatile long value = 0L;
	}

	public final static class VolatileLong2 {
		volatile long p0, p1, p2, p3, p4, p5, p6;
		public volatile long value = 0L;
		volatile long q0, q1, q2, q3, q4, q5, q6;
	}


	/**
	 * jdk8新特性，Contended注解避免false sharing
	 * Restricted on user classpath
	 * Unlock: -XX:-RestrictContended
	 */
	@Contended
	public final static class VolatileLong3 {
		public volatile long value = 0L;
	}
}
