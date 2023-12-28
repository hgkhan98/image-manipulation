import model.ImageDataBase;
import model.image.ImageImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for the model.
 */
public class TestImage {
  private ImageDataBase database;
  private ImageImpl image;

  @Before
  public void setUp() {
    this.database = new ImageDataBase();

    image = new ImageImpl(3, 2);
    image.setPixel(0, 0, 168, 168, 168);
    image.setPixel(0, 1, 133, 133, 133);
    image.setPixel(1, 0, 133, 133, 133);
    image.setPixel(1, 1, 0, 0, 0);
    image.setPixel(2, 0, 248, 177, 177);
    image.setPixel(2, 1, 246, 146, 150);
  }

  @Test
  public void testImageConstructor() {
    assertEquals(3, image.getWidth());
    assertEquals(2, image.getHeight());
  }

  @Test
  public void testGetRed() {
    assertEquals(0, image.getRedChannel(1, 1));
    assertEquals(246, image.getRedChannel(2, 1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalRed() {
    image.getRedChannel(3, 1);
    image.getRedChannel(0, -2);
  }

  @Test
  public void testGetGreen() {
    assertEquals(0, image.getGreenChannel(1, 1));
    assertEquals(146, image.getGreenChannel(2, 1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalGreen() {
    image.getGreenChannel(3, 1);
    image.getGreenChannel(0, -2);
  }

  @Test
  public void testGetBlue() {
    assertEquals(0, image.getBlueChannel(1, 1));
    assertEquals(150, image.getBlueChannel(2, 1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalBlue() {
    image.getBlueChannel(3, 1);
    image.getBlueChannel(0, -2);
  }

  @Test
  public void testSetPixel() {
    image.setPixel(0, 0, 1, 20, 255);
    assertEquals(1, image.getRedChannel(0, 0));
    assertEquals(20, image.getGreenChannel(0, 0));
    assertEquals(255, image.getRedChannel(0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalPixel() {
    image.setPixel(3, 1, 0, 5, 10);
    image.setPixel(0, -2, 0, 0, 0);
  }

  @Test
  public void testDatabase() {
    database.add("3x2", image);
    assertEquals(image, database.get("3x2"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalDatabase() {
    database.add(null, image);
    database.add("image", null);
  }
}
