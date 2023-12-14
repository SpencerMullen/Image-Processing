# Image Processing

#### Script Commands Explanation:

Note that error handling has not been programmed to display a message, then continue waiting for
input due to the lack of a view. If a command is malformed, either in a premade script or the manual
user input, the program will simply throw an error and stop running.

* q - Entering 'q' will immediately stop and exit the program. q must be the first input of the
  command, however. Inputting q after the start of a valid command (ie create layer q will create
  a new layer, called "q") will not quit the program.

* create - You can create a new layer by entering 'create' followed by the word 'layer' followed by
  the desired name of the layer. You can not create a layer with an existing name.
  - ex: create layer dog

* load - You can load an image onto the current layer by entering 'load' followed by the filename.
  Regardless of which format the file is in (ppm, png, or jpeg), the image will be loaded onto the
  layer unless the layer is already occupied.
  - Note that load may only be used if a layer to load something to has already been created.
    and selected.
  - ex: create layer dog \n current dog \n load dogPic.jpeg


* save - You can save a single layer (the current layer) by entering 'save' followed by the filename
  you wish to save to.
  - ex: create layer dog \n current dog \n load dogPic.jpeg \n save newDogPic.png


* loadLayered - You can load a multilayered image by entering 'loadLayered' followed by the name
  of the text file you wish to load the images from. If the text file is malformed, an error will
  occur from ImageUtil.
  - ex: loadLayered layeredImageExample.txt


* saveAll - You can save an entire multilayered image by entering 'saveAll' followed by the text
  filename you wish to save to.
  - ex: loadLayered layeredImageExample.txt \n saveAll newLayeredImageExample.txt


* remove - This command removes the topmost layer of the model currently being worked on. If
  there are no layers, it does nothing.
  - ex: create layer dog \n remove


* blur - This command will apply the blur transformation to the current layer.
  - ex: create layer dog \n current dog \n load dogPic.jpeg \n blur


* sharpen - This command will apply the sharpen transformation to the current layer.
  - ex: create layer dog \n current dog \n load dogPic.jpeg \n sharpen


* greyscale - This command will apply the greyscale transformation to the current layer.
  - ex: create layer dog \n current dog \n load dogPic.jpeg \n greyscale


* sepia - This command will apply the sepia transformation to the current layer.
  - ex: create layer dog \n current dog \n load dogPic.jpeg \n sepia


* current - Entering 'current' followed by the name of the layer you wish to make the current
  layer will set that layer as the current layer.
  - ex: create layer dog \n current dog


* visible - Entering 'visible' followed by the name of the layer you wish to make visible will
  set that layer's visibility as visible (does nothing if it is already visible).
  - ex: create layer dog \n visible dog


* invisible - Entering 'invisible' followed by the name of the layer you wish to make invisible will
  set that layer's visibility as invisible (does nothing if it is already invisible). Note that
  visible and invisible must have a layer following the command, as they are not called on the
  current layer.
  - ex: create layer dog \n invisible dog

#### Graphical User Interface

* The commands for the graphical user interface work exactly like those of the text view. If there
are any commands that require a text input, a pop-up dialog will appear after you have selected the
correct command from the menu in the graphical user interface.

- ex: edit --> create --> pop-up dialog box asking for a layer name appears

* The graphical user interface has a different controller and view from the original simple text version.
This is because there were many drastic changes that would not align with the old one. For example, needed to add methods that converted BufferedImages to an array of bytes to be able to display the images
using a JLabel.

![Alt text](https://github.com/SpencerMullen/Image-Processing/blob/main/GUIScreenshot.PNG?raw=true)
