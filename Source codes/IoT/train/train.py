from ultralytics import YOLO

# input the model you want to use to train here
model = YOLO(r"E:\Program\Python\mid_term\model\remake1.pt")


def train():
    model.train(
        # if you want to change the dataset, edit the data.yaml
        # in data.yaml you can specify where the data comes from.
        data=r"E:\Program\Python\mid_term\train\datasets\data.yaml",
        epochs=50,
        imgsz=640,
        batch=2,
        lr0=0.02,
        lrf=0.02,
    )


if __name__ == "__main__":
    train()
