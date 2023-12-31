
/**
 * A very simply Timer class that functions as a virtual stopwatch.
 * 
 * This can be used as a semi-accurate method of measuring the elapsed time
 * while some code is running. It can be used as a timer in a game, or to
 * track the efficiency of some procedure. Methods for stop, start and reset
 * allow easy control of Timer objects. Methods are included that return 
 * seconds (as an int), milliseconds (as a float) or a formatted String.
 * 
 * @author Jordan Cohen
 * @version v1.0.2
 */
public class Timer
{
    private long startTime;
    private long endTime;

    /**
     * Constructor does nothing... however, I avoided making this class static so
     * that multiple Timers could be used simultaneously.
     */
    public Timer ()
    {
        // nothing
    }

    /**
     * Start the Timer
     */
    public void startTimer()
    {
        startTime = System.nanoTime();
    }

    /**
     * Stop the Timer
     */
    public void endTimer ()
    {
        endTime = System.nanoTime();
    }

    /**
     * Reset the Timer
     */
    public void resetTimer ()
    {
        startTime = 0;
        endTime = 0;
    }

    /**
     * Returns elapsed time as a neatly formatted String. Most practical for
     * applications where output (rather than calculation) is the goal. The
     * exact format will depend on the time elapsed - either ms, sec, or min:sec.
     * 
     * @return String   neatly formatted display of time elapsed
     */
    public String toString ()
    {
        if ((endTime - startTime) < 1000000)
        {
            return (endTime - startTime) + "ns";
        }
        // Less than 1 second
        if ((endTime - startTime)/1000000000 < 1)
        {
            return getTimeInMilliseconds() + " ms";
        }
        else if ((endTime - startTime)/1000000000 < 60)
        {
            return getTimeInPreciseSeconds() + " sec";
        }
        
        int minutes = getTimeInSeconds() / 60;
        float seconds = getTimeInPreciseSeconds() - ((float)minutes * 60);
        return minutes + " min " + seconds + " sec";
    }
    
    /**
     * Return the elapsed time in seconds. This assumes that the timer has already
     * been started and stopped (but not reset). For very short durations, this will
     * return zero even though some time has elapsed.
     * 
     * @return int  The number of seconds elapsed, as an int.
     */
    public int getTimeInSeconds ()
    {
        return (int)((double)(endTime - startTime) / 1000000000.0);
    }
    
    /**
     * Return the elapsed time in seconds. This assumes that the timer has already
     * been started and stopped (but not reset). For very short durations, this will
     * return zero even though some time has elapsed.
     * 
     * @return float  The number of seconds elapsed, as an int.
     */
    public float getTimeInPreciseSeconds ()
    {
        return (float)((double)(endTime - startTime) / 1000000000.0);
    }
    

    /**
     * Return the elapsed time in seconds. This assumes that the timer has already
     * been started and stopped (but not reset). This will return the value as a 
     * float and is most useful for shorter durations where second is not accurate
     * enough.
     * 
     * @return float  The number of milliseconds elapsed, as a float.
     */
    public float getTimeInMilliseconds ()
    {
        return (float)((double)(endTime - startTime) / 1000000.0);
    }

}
