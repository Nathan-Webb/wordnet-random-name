package org.kohsuke.randname;

/**
 * Generates pseudo random unique names that combines one adjective and one noun,
 * like "friendly tiger" or "good apple".
 *
 * There's about 1.5 million unique combinations, and if you keep requesting a new word
 * it will start to loop (but this code will generate all unique combinations before it starts
 * to loop.)
 *
 * @author Kohsuke Kawaguchi
 */
public class RandomNameGenerator {

    public RandomNameGenerator() {
    }

    public synchronized String next() {
        Dictionary d = Dictionary.INSTANCE;
        return d.word();
    }
}
