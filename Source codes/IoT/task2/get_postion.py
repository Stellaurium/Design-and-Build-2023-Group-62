from pynput.mouse import Listener

# 定义一个回调函数，在鼠标点击事件发生时调用
def on_click(x, y, button, pressed):
    if pressed:
        print(f"Mouse clicked at ({x}, {y})")

# 创建鼠标点击事件监听器
with Listener(on_click=on_click) as listener:
    listener.join()
