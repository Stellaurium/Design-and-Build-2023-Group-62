from ultralytics import YOLO

# load a model
model = YOLO(r"E:\Program\Python\mid_term\model\middle4.pt")

confidence = 0.3

# input images path
img_path = r"E:\Program\Python\mid_term\train\datasets\test\images"
results = model.predict(img_path, save=True, save_txt=True, classes=[0, 1, 2], conf=confidence)

