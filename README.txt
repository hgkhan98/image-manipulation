Hiba Khan
CS 5008 Summer 2023
Homework 10

This assignment adhered to an MVC design. These packages also contain sub-packages.

- Controller:
	Commands:
		ICommand: Interface for the commands.
		BlurCommand: Command to blur image.
		SharpenCommand: Command to sharpen image.
		GrayscaleCommand: Command to create a grayscale image using a color transformation matrix.
		SepiaCommand: Command to create a sepia image using a color transformation matrix.
		BrightenCommand: Command to brighten image.
		ValueComponentCommand: Command to create a greyscale image using the value component.
		IntensityComponentCommand: Command to create a greyscale image using the intensity component.
		LumaComponentCommand: Command to create a greyscale image using the luma component.
		RedComponentCommand: Command to create a greyscale image using the red component.
		GreenComponentCommand: Command to create a greyscale image using the green component.
		BlueComponentCommand: Command to create a greyscale image using the blue component.
		LoadCommand: Command to load a PPM, PNG or JPEG file.
		SaveCommand: Command to save an image as a PPM, PNG or JPEG file.
	IO:
		IImageLoader: Interface for strategy to load image.
		IImageSaver: Interface for strategy to save image.
		ImageLoader: Strategy to load a PNG or JPEG file.
		PNGImageSaver: Strategy to save an image as a PNG file.
		JPEGImageSaver: Strategy to save an image as a JPEG file.
		PPMImageLoader: Strategy to load a PPM file.
		PPMImageSaver: Strategy to save an image as a PPM file.
	IController: Interface for the controller.
	ControllerImpl: Implements the IController interface.

- Model:
	Transformations:
		Filter:
			BlurTransformation: Strategy to blur image called by the command.
			SharpenTransformation:Strategy to sharpen image called by the command.
			GrayscaleTransformation: Strategy to create a grayscale image using a color transformation matrix called by the command.
			SepiaTransformation: Strategy to create a sepia image using a color transformation matrix called by the command.
		ITransformation: Interface for the transformations.
		BrightenTransformation: Strategy to brighten image called by the command.
		ValueTransformation: Strategy to create a greyscale image using the value component called by the command.
		IntensityTransformation: Strategy to create a greyscale image using the intensity component called by the command.
		LumaTransformation: Strategy to create a greyscale image using the luma component called by the command.
		RedTransformation: Strategy to create a greyscale image using the red component called by the command.
		GreenTransformation: Strategy to create a greyscale image using the green component called by the command.
		BlueTransformation: Strategy to create a greyscale image using the blue component called by the command.
	Image:
		IImageState: Interface to represent the state of an image, providing read-only access.
		IImage: Interface that extends IImageState.
		ImageImpl: Implements the IImage interface.
		IPixelState: Interface to represent the state of a pixel, providing read-only access.
		IPixel: Interface that extends IPixelState.
		Pixel: Implements the IPixel interface.
	IImageDataBase: Interface to represent a model containing images.
	ImageDataBase: Implements the IImageDataBase interface.
	ConvertImage: Class to convert between IImageState and BufferedImage.
- View:
	ViewListener: Interface to recieve events from the View.
	View: Represents the main GUI interface.
	Canvas: Displays images.

How to use the program:

Command-line inputs:
	- "-file path-of-script-file": Opens the script file, executes it and shuts down.
	- "-text": Opens the program in interactive text mode so the user can type the script and execute it one line at a time.
	- " ": Opens the GUI.

Using the GUI:
- Load image: Click the "Load Image" button to load an image from your computer. Supported image formats are PPM, PNG and JPEG.
- Save image: Click the "Save Image" button to save the current image to your computer. Supported image formats are the same as loaded file.
- Image transformations: Transformations can be applied to the loaded image, including:
	Brighten: To brighten or darken the image, click the "Brighten" button and enter an integer value in the dialog box.
	Value-component: To create a grayscale image using the value component, click the "Value-component" button.
	Intensity-component: To create a grayscale image using the intensity component, click the "Intensity-component" button.
	Luma-component: To create a grayscale image using the luma component, click the "Luma-component" button.
	Red-component: To create a grayscale image using the red component, click the "Red-component" button.
	Green-component: To create a grayscale image using the green component, click the "Green-component" button.
	Blue-component: To create a grayscale image using the blue component, click the "Blue-component" button.
	Blur: To blur the image, click the "Blur" button.
	Sharpen: To sharpen the image, click the "Sharpen" button.
	Grayscale: To create a grayscale image, click the "Grayscale" button.
	Sepia: To create a sepia image, click the "Sepia" button.
- Keyboard shortcuts: Press the 'l' key to load a new image and the 's' key to save the image.

Design changes:

The view package was completely changed to implement the GUI. It consists of the interface ViewListener and the classes View and Canvas.
A new controller was added to work with the GUI. It includes all the event handling.

Completed parts:

- Loading and saving images in PPM, PNG and JPEG formats.
- Image transformations.
- Performing these actions by loading a script, in interactive text mode or GUI.

Image citation:
Panda. (2023, April 4). Reader’s Digest. https://www.rd.com/article/how-many-giant-pandas-are-left-in-the-world/