package edu.umb.cs681.hw08;

import java.util.LinkedList;

public class PrimeGenerator {
  protected long from, to;
  protected LinkedList<Long> primes;

  public PrimeGenerator(long from, long to) {
    this.from = from;
    this.to = to;
    primes = new LinkedList<>();
  }

  public void generatePrimes() {
    for (long i = from; i < to; i++) {
      if (isPrime(i)) {
        primes.add(i);
      }
    }
  }

  public LinkedList<Long> getPrimes() {
    return primes;
  }


  protected boolean isPrime(long n) {
    if (n <= 1) {
      return false;
    }
    for (int i = 2; i <= n / 2; i++) {
      if ((n % i) == 0)
        return false;
    }
    return true;
  }
}


