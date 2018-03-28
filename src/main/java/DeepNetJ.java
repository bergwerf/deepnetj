/*
 * Copyright (c) 2018, Herman Bergwerf. All rights reserved.
 * Use of this source code is governed by a MIT-style license
 * that can be found in the LICENSE file.
 */

import java.io.File;
import java.util.Arrays;

import net.imagej.ImageJ;

import org.scijava.log.LogService;
import org.scijava.command.Command;
import org.scijava.plugin.Plugin;
import org.scijava.plugin.Parameter;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.deeplearning4j.nn.graph.ComputationGraph;
import org.deeplearning4j.nn.modelimport.keras.KerasModelImport;

@Plugin(type=Command.class, headless=true, menuPath="DeepNetJ>Predict")
public class DeepNetJ implements Command {
  /// Assigned automatically by ImageJ.
	@Parameter
  private LogService log;

	@Parameter(label="Keras model and weights (.h5)")
  private File kerasInput;

  @Parameter(label="2D patching method", choices={
    "Crop edges", // Do not process half patches.
    "Clamp edges", // Fill half patches with clamped edges.
    "Mirror edges" // Fill half patches with mirrored content.
  })
  private String patchingMethod = "Mirror edges";

  /*@Parameter(label="Patch input overlap")
  private String patchOverlapIn = "0, 0";

  @Parameter(label="Patch output overlap")
  private String patchOverlapOut = "0, 0";*/

  /// This method is executed when the user pressed OK. This means model
  /// parameters are selected, and predicting can begin.
	@Override
	public void run() {
    try {
      // Import Keras model and weights into DL4J.
      String path = kerasInput.getAbsolutePath();
      ComputationGraph g = KerasModelImport.importKerasModelAndWeights(path);

      // Log some information about the model to the console.
      INDArray in = g.input();
      log.info("Successfully imported Keras model.");
      log.info(String.format("Input shape: %s", Arrays.toString(in.shape())));
      log.info(String.format("Batch size: %s", g.batchSize()));
      log.info(String.format("Model summary: %s", g.summary()));
    } catch (Exception e) {
      log.info(String.format("Prediction failed: %s", e.getMessage()));
    }
	}

  /// Launch ImageJ and run plugin. This can be used for debugging.
  public static void main(final String... args) throws Exception {
    final ImageJ ij = new ImageJ();
    ij.launch(args);
    ij.command().run(DeepNetJ.class, true);
  }
}
