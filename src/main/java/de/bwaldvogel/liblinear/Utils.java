package de.bwaldvogel.liblinear;

import java.io.PrintStream;
import java.util.Random;

/**
 * Some commonly used methods
 */
public class Utils {

	private Object OUTPUT_MUTEX = new Object();
	private PrintStream DEBUG_OUTPUT = System.out;
	private static final long DEFAULT_RANDOM_SEED = 0L;
	private Random random = new Random(DEFAULT_RANDOM_SEED);

	public Random getRandom()
	{
		return random;
	}
	
	void info(String message) {
		synchronized (OUTPUT_MUTEX) {
			if (DEBUG_OUTPUT == null)
				return;
			DEBUG_OUTPUT.printf(message);
			DEBUG_OUTPUT.flush();
		}
	}

	void info(String format, Object... args) {
		synchronized (OUTPUT_MUTEX) {
			if (DEBUG_OUTPUT == null)
				return;
			DEBUG_OUTPUT.printf(format, args);
			DEBUG_OUTPUT.flush();
		}
	}

	public void disableDebugOutput() {
		setDebugOutput(null);
	}

	public void enableDebugOutput() {
		setDebugOutput(System.out);
	}

	public void setDebugOutput(PrintStream debugOutput) {
		synchronized (OUTPUT_MUTEX) {
			DEBUG_OUTPUT = debugOutput;
		}
	}

	/**
	 * Java5 'backport' of Arrays.copyOf
	 */
	public double[] copyOf(double[] original, int newLength) {
		double[] copy = new double[newLength];
		System.arraycopy(original, 0, copy, 0,
				Math.min(original.length, newLength));
		return copy;
	}

	/**
	 * Java5 'backport' of Arrays.copyOf
	 */
	public int[] copyOf(int[] original, int newLength) {
		int[] copy = new int[newLength];
		System.arraycopy(original, 0, copy, 0,
				Math.min(original.length, newLength));
		return copy;
	}

	void swap(double[] array, int idxA, int idxB) {
		double temp = array[idxA];
		array[idxA] = array[idxB];
		array[idxB] = temp;
	}

	void swap(int[] array, int idxA, int idxB) {
		int temp = array[idxA];
		array[idxA] = array[idxB];
		array[idxB] = temp;
	}

	void swap(IntArrayPointer array, int idxA, int idxB) {
		int temp = array.get(idxA);
		array.set(idxA, array.get(idxB));
		array.set(idxB, temp);
	}

	public void resetRandom() {
		random = new Random(DEFAULT_RANDOM_SEED);
	}
}
