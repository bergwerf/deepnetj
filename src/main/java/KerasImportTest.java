/*
 * Copyright (c) 2018, Herman Bergwerf. All rights reserved.
 * Use of this source code is governed by a MIT-style license
 * that can be found in the LICENSE file.
 */

import java.util.Arrays;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.deeplearning4j.nn.graph.ComputationGraph;
import org.deeplearning4j.nn.modelimport.keras.KerasModelImport;

public class KerasImportTest {
  static void print(String msg) {
    System.out.println(msg);
  }

  public static void main(String[] args) {
    String path = "/tmp/model.h5";

    try {
      // Import Keras model and weights into DL4J.
      ComputationGraph g = KerasModelImport.importKerasModelAndWeights(path);

      // Log some information about the model to the console.
      INDArray in = g.input();
      print("Successfully imported Keras model.");
      print(String.format("Input shape: %s", Arrays.toString(in.shape())));
      print(String.format("Batch size: %s", g.batchSize()));
      print(String.format("Model summary: %s", g.summary()));
    } catch (Exception e) {
      print(String.format("Prediction failed: %s", e.getMessage()));
    }
  }
}
