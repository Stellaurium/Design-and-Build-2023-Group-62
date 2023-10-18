from ultralytics import YOLO
import tkinter as tk
from PIL import Image, ImageTk
from selenium import webdriver
import time
import io
import numpy as np


# Initialize the window
def initialize_window():
    # Create a Tkinter window
    window = tk.Tk()
    window.title("Image Viewer")

    # Create a Label for displaying images
    image_label = tk.Label(window)
    image_label.pack()

    return window, image_label


# Render the image
def render_image(window, image_label, image_data):
    if image_data is not None:
        # Convert image data, a numpy.ndarray, to a PIL Image
        image = Image.fromarray(image_data)

        # Convert the PIL Image to a Tkinter PhotoImage
        tk_image = ImageTk.PhotoImage(image=image)

        # Set the Label's image
        image_label.config(image=tk_image)
        image_label.image = tk_image

    # Update the window
    window.update()


# Read and resize the image
def read_and_resize_image(file_path):
    resize_factor = 2
    try:
        # Open the image file
        img = Image.open(file_path)

        # Resize the image to half of its original size
        img = img.resize((img.width // resize_factor, img.height // resize_factor))

        # Convert the image to a numpy.ndarray
        img_array = np.array(img)

        return img_array
    except Exception as e:
        print(f"Error: {e}")
        return None


def get_screenshot(left, top, right, bottom):
    # Capture a screenshot, even if obscured
    # Take a screenshot and save it
    screenshot = browser.get_screenshot_as_png()  # Capture the screenshot as a PNG image
    img = Image.open(io.BytesIO(screenshot))  # Open the captured image using BytesIO
    # Modify the pixel coordinates here
    img = img.crop((left, top, right, bottom))  # Crop the image to the specified coordinates
    img_np = np.array(img)  # Convert the image to a numpy.ndarray
    return img_np  # Return the numpy array representing the cropped screenshot


# System Initialization ==================
# Define a list of categories
all_category = [1, 2, 3]

# Load a model
model = YOLO(r"E:\Program\Python\mid_term\model\middle3.pt")  # Load a YOLO model from the specified file

# Initialize the graphical user interface
window, image_label = initialize_window()

# Browser Initialization
# Using Chrome browser
browser = webdriver.Chrome()  # Initialize the Chrome web browser

# Specify the website URL for capturing screenshots
url = 'http://192.168.57.29/'

# Open the website in the browser
browser.get(url)

# Choose a directory to save screenshots
save_dir = 'F:\\Temp\\'

# Wait for the web page to load completely to prevent capturing images of unfinished content
time.sleep(2)

# Set the screenshot width and height
browser.set_window_size(1920, 1080)

# Define object categories
category = ["BOOK", "CUBE", "KEY"]

# Create an empty dictionary to store the count of each detected object
object_counts = {"BOOK": 0, "CUBE": 0, "KEY": 0}

# ============================================
count = 0
start_time = time.time()  # Record the start time
while True:
    # TODO: Debugging location
    bias = 113

    # Capture a screenshot of the defined region
    frame = get_screenshot(30, 208 - bias, 668, 671 - bias)
    # frame = get_screenshot(0, 0 - bias, 1000, 700 - bias)
    frame = frame[:, :, :3]  # Retain only the first 3 channels (R, G, B) and discard the Alpha channel

    # Run YOLOv8 inference on the frame
    results = model(frame, classes=all_category, conf=0.2)
    if results[0]:
        class_value = category[int(results[0].boxes.cls[0].item())]
        confidence_value = results[0].boxes.conf[0].item()

        # If the detected object is in the dictionary, increment its count
        if class_value in object_counts:
            object_counts[class_value] += 1

    # Visualize the results on the frame
    annotated_frame = results[0].plot()

    render_image(window, image_label, annotated_frame)
    count += 1

    # Calculate the time ratio
    elapsed_time = time.time() - start_time
    fps = count / elapsed_time

    # Output total frames and time ratio
    # print(f"Total Frames: {count}, FPS: {fps:.2f}")
    # time.sleep(1)

    if count % 20 == 0:
        # Get the name of the object with the maximum count
        max_object = max(object_counts, key=object_counts.get)

        # Write the name of the maximum count object to object.txt
        with open(save_dir + 'object.txt', 'w') as file:
            file.write(max_object)
