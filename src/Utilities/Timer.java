
package Utilities;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * The Timer class creates a timer object to determine the number of seconds that have
 * passed since the timer began.
 *
 * This class is immutable and does not support inheritance.
 * @since 1.0
 * @version 1.0
 * @author Khamilah Nixon
 */
public final class Timer {

    /**
     * The time recorded when the timer starts.
     */
    private long start;

    /**
     * The time recorded when the timer ended.
     */
    private long end;

    /**
     * The difference between the stop and start times.
     */
     private long elapsed;

    /**
     * Creates timer object
     */
    private Timer() {}

    /**
     * Starts the timer and records the current time in milliseconds.  It stores this value in {@link #start}
     */
    public void startTimer() {
        start = System.currentTimeMillis();
    }

    /**
     * Stops the timer and records the current time in milliseconds.  It stores this value in {@link #end}
     */
    public void stopTimer() {
        end = System.currentTimeMillis();
    }

    /**
     * Determines the amount of time in seconds that passed between the time the timer started and stopped
     */
    public void elapsed() {
        elapsed = (end - start) / 1000;
    }

    /**
     * Marks the timer's start time
     * @return Current time in milliseconds
     */
    public long getStart() {
        return start;
    }

    /**
     * Marks the timer's end time
     * @return Current time in milliseconds
     */
    public long getEnd() {
        return end;
    }

    /**
     * Returns the amount of time, in seconds, that has elapsed since the timer started and stopped
     * @return The difference between the stop and start times
     */
    public long getElapsed() {
        return elapsed;
    }

    @Contract(pure = true)
    @Override
    public @NotNull String toString() {
        return "Timer{" +
                "start=" + start +
                ", end=" + end +
                ", elapsed=" + elapsed +
                '}';
    }
}
