from ultralytics import YOLO
# from ultralytics.yolo.utils.benchmarks import benchmark
import cv2


model = YOLO(r"E:\Program\Python\mid_term\model\middle2.pt")  # load a pretrained model (recommended for training)



cap = cv2.VideoCapture(0)

while cap.isOpened():
    # Read a frame from the video
    success, frame = cap.read()
    if success:
        # # Run YOLOv8 inference on the frame
        # results = model(frame, classes=[0, 1, 2], conf=0.3)
        #
        # # Visualize the results on the frame
        # annotated_frame = results[0].plot()

        # Display the annotated frame
        cv2.imshow("YOLOv8 Inference", frame)

        # Break the loop if 'q' is pressed
        if cv2.waitKey(1) & 0xFF == ord("q"):
            break
    else:
        # Break the loop if the end of the video is reached
        break

# Release the video capture object and close the display window
cap.release()
cv2.destroyAllWindows()
