package model.functionobjects;

import model.IPixel;
import java.util.ArrayList;

/**
 * Represents an abstract Transformation class. Contains methods shared between the concrete
 * implementations of transformations, such as blur and sharpen.
 */
public abstract class ATransformation implements ITransformation {

  public abstract ArrayList<ArrayList<IPixel>> apply(ArrayList<ArrayList<IPixel>> image);

  protected ArrayList<ArrayList<IPixel>> createNewList(ArrayList<ArrayList<IPixel>> image) {
    ArrayList<ArrayList<IPixel>> newList = new ArrayList<>();

    for (int i = 0; i < image.size(); i++) {
      newList.add(new ArrayList<IPixel>());
    }

    return newList;
  }

  protected void checkForValidImage(ArrayList<ArrayList<IPixel>> image) {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null.");
    }

    if (image.size() < 1 || image.get(0).size() < 1) {
      throw new IllegalArgumentException("Image must contain pixels to be transformed.");
    }
  }

}
