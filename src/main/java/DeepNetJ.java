/*
 * Copyright (c) 2018, Herman Bergwerf. All rights reserved.
 * Use of this source code is governed by a MIT-style license
 * that can be found in the LICENSE file.
 */

import java.io.File;

import net.imagej.ImageJ;

import org.scijava.command.Command;
import org.scijava.plugin.Plugin;
import org.scijava.plugin.Parameter;

@Plugin(type=Command.class, headless=true, menuPath="DeepNetJ>Predict")
public class DeepNetJ implements Command {
	@Parameter(label="Keras model and weights (.h5)")
  private File kerasInput;

  @Parameter(label="2D patching method", choices={
    "Crop edges", // Do not process half paches
    "Clamp edges", // Fill half patches with clamped edges
    "Mirror edges" // Fill half patches with mirrored content
  })
  private String patchingMethod = "Mirror edges";

  @Parameter(label="Patch input overlap")
  private String patchOverlapIn = "0, 0";

  @Parameter(label="Patch output overlap")
  private String patchOverlapOut = "0, 0";

  // This method is executed when the user pressed OK. This means model
  // parameters are selected, and predicting can begin.
	@Override
	public void run() {
		// Import Keras model and weights into DL4J.
	}
}
