## Environment Setup
I'm using Linux (Ubuntu 20.04 LTS) for this environment setup. So, the following instructions are specific to installing and configuring it on a Linux Bash environment.

### Conda Installation
To set up your environment, you'll first need to install Conda if it's not already installed. You can do so with the following commands:

```bash
# Install Conda
sudo apt-get install conda
```

### Create and Activate Environment
After installing Conda, you can create a dedicated environment for YOLOv8. In this case, we're using Python 3.9. Here are the steps:

```bash
# Create a new Conda environment
conda create -n yolov8 python=3.9

# Activate the newly created environment
conda activate yolov8
```

### Required Libraries
The key libraries you'll need for YOLOv8 are `numpy`, `opencv-python`, `pillow`, `torch` (with CUDA support), and `ultralytics`. These dependencies can be easily installed within your Conda environment. Additionally, you can check the list of required libraries in the external file `library_list.txt`, which is my current environment configuration.

## How to Train
### Train the Model
To train your YOLOv8 model, you can open and edit the `train/train.py` file. This file contains instructions and comments that guide you through the training process. Follow the comments and modify the script to suit your specific training needs.

## How to Use
### Model Selection
To select and configure a model for your task, you can make the necessary modifications in the files provided. All the pre-trained models are available in the "models" folder. Simply refer to the instructions and comments within these files to help you choose the most suitable model for your specific use case.

### Predict Images in a Folder
If you want to predict images in a folder, you can utilize the `predict/predict.py` script. It will process the images and provide predictions.

### Predict Images through a Local Camera
To make predictions from a local camera feed, you can use the `task2/predict_via_local_camera.py` script. It will allow you to capture and process video from your local camera.

### Predict Images through a Web Camera
For making predictions from a web camera, you can use the `task2/predict_via_web_camera.py` script. It enables you to access and analyze video from a web camera source.

Feel free to add further details or specific installation instructions for Conda and other libraries as needed for your environment setup.