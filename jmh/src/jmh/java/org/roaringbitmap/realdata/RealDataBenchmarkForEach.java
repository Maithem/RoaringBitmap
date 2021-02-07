package org.roaringbitmap.realdata;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.roaringbitmap.realdata.state.RealDataBenchmarkState;
import org.roaringbitmap.realdata.wrapper.Bitmap;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class RealDataBenchmarkForEach {

  @Benchmark
  public int forEach(RealDataBenchmarkState bs) {
    MyConsumer consumer = new MyConsumer();
    for (int k = 0; k < bs.bitmaps.size(); ++k) {
      Bitmap bitmap = bs.bitmaps.get(k);
      bitmap.forEach(consumer);
    }
    return consumer.total;
  }

  private static class MyConsumer implements Consumer<Integer> {
    int total = 0;

    @Override
    public void accept(Integer value) {
      total += value;
    }
  }

}
