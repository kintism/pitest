package org.pitest.functional.predicate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.pitest.functional.prelude.Prelude.or;

import org.junit.Test;

public class OrTest {

   @Test
  public void shouldBeFalseWhenGivenNoPredicates() {
    final Or<Object> testee = or();
    assertFalse(testee.apply(null));
  }

  @Test
  public void shouldBeTrueWhenGivenTrue() {
    final Or<Object> testee = or(True.all());
    assertTrue(testee.apply(null));
  }

  @Test
  public void shouldBeFalseWhenGivenFalse() {
    final Or<Object> testee = or(False.instance());
    assertFalse(testee.apply(null));
  }

  @Test
  public void shouldBeTrueWhenTrueOrTrue() {
    final Or<Object> testee = or(True.all(), True.all());
    assertTrue(testee.apply(null));
  }

  @Test
  public void shouldBeTrueWhenTrueOrFalse() {
    final Or<Object> testee = or(True.all(), False.instance());
    assertTrue(testee.apply(null));
  }

  @Test
  public void shouldBeFalseWhenFalseOrFalse() {
    final Or<Object> testee = or(False.instance(), False.instance());
    assertFalse(testee.apply(null));
  }

}
