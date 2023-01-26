package model;

import java.util.ArrayList;
import model.functionobjects.ITransformation;

/**
 * Represents the implementation of LayeredImageProcessingModel. Contains
 * functions which can alter and observe a layer of images, including adding
 * new layers/images, removing layers, and applying transformations to individual
 * layers.
 */
public class LayeredModel implements LayeredImageProcessingModel {
  private ArrayList<SimpleImageProcessingModel> layers;
  private ArrayList<Boolean> visibility;

  public LayeredModel() {
    this.layers = new ArrayList<SimpleImageProcessingModel>();
    this.visibility = new ArrayList<Boolean>();
  }

  @Override
  public void applyTransformationToLayer(ITransformation transformation, int layer)
      throws IllegalArgumentException {
    if (transformation == null) {
      throw new IllegalArgumentException("Transformation is null.");
    }

    if (layer < 0 || layer >= layers.size()) {
      throw new IllegalArgumentException("Layer does not point to a valid index.");
    }

    if (layers.get(layer).getImage().size() == 0)  {
      throw new IllegalArgumentException("This layer is currently blank.");
    }

    layers.get(layer).applyTransformation(transformation);
  }

  @Override
  public void addBlankLayer() {
    layers.add(new SimpleImageProcessingModel());
    visibility.add(false);
  }

  @Override
  public void addImageToLayer(SimpleImageProcessingModel img, int layer)
      throws IllegalArgumentException {
    if (img == null) {
      throw new IllegalArgumentException("Image is null.");
    }

    if (layer < 0 || layer >= layers.size()) {
      throw new IllegalArgumentException("Layer points to an invalid index.");
    }

    if (layers.get(layer).getImage().size() != 0) {
      throw new IllegalArgumentException("There is already an image stored in this layer.");
    }

    if (this.getHeightOfImages() != 0) {
      if (img.getHeight() != this.getHeightOfImages() ||
          img.getWidth() != this.getWidthOfImages()) {
        throw new IllegalArgumentException("Image to be added is of incorrect dimensions.");
      }
    }


    layers.set(layer, img);
    visibility.set(layer, true);
  }

  @Override
  public void addCopyOfLayer(int layer) throws IllegalArgumentException {
    if (layer < 0 || layer >= layers.size()) {
      throw new IllegalArgumentException("Layer points to an invalid index.");
    }

    if (layers.get(layer).getImage().size() == 0) {
      throw new IllegalArgumentException("Cannot copy a blank layer.");
    }

    SimpleImageProcessingModel copy = new SimpleImageProcessingModel(layers.get(layer).getImage());
    layers.add(copy);
    visibility.add(visibility.get(layer));
  }

  @Override
  public void removeLayer() {
    if (this.layers.size() > 0) {
      layers.remove(layers.size() - 1);
      visibility.remove(visibility.size() - 1);
    }
  }

  @Override
  public void switchVisibility(int layer) throws IllegalArgumentException {
    if (layer < 0 || layer >= layers.size()) {
      throw new IllegalArgumentException("Layer points to an invalid index.");
    }

    visibility.set(layer, !visibility.get(layer));
  }

  @Override
  public int getWidthOfImages() {
    for (SimpleImageProcessingModel img : layers) {
      if (img.getWidth() > 0) {
        return img.getWidth();
      }
    }

    return 0;
  }

  @Override
  public int getHeightOfImages() {
    for (SimpleImageProcessingModel img : layers) {
      if (img.getHeight() > 0) {
        return img.getHeight();
      }
    }

    return 0;
  }

  @Override
  public ArrayList<ArrayList<IPixel>> getImageAtLayer(int layer) throws IllegalArgumentException {
    if (layer < 0 || layer >= layers.size()) {
      throw new IllegalArgumentException("Layer points to an invalid index.");
    }

    return layers.get(layer).getImage();
  }

  @Override
  public boolean getStatusOfImageAtLayer(int layer) throws IllegalArgumentException {
    if (layer < 0 || layer >= layers.size()) {
      throw new IllegalArgumentException("Layer points to an invalid index.");
    }

    return visibility.get(layer);
  }

  @Override
  public int getNumberOfImages() {
    int num = 0;

    for (SimpleImageProcessingModel img : layers) {
      if (img.getWidth() > 0) {
        num++;
      }
    }

    return num;
  }

  @Override
  public int getNumberOfLayers() {
    return layers.size();
  }
}
