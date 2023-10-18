import os


def modify_files(folder_path):
    # 获取文件夹内所有文件名
    file_list = os.listdir(folder_path)

    # 遍历文件夹内的文件
    for file_name in file_list:
        if file_name.endswith('.txt'):
            file_path = os.path.join(folder_path, file_name)
            modify_file(file_path)


def modify_file(file_path):
    # 读取文件内容
    with open(file_path, 'r') as file:
        lines = file.readlines()

    aim = ['2', '0', '2']
    modified_lines = []
    for line in lines:
        modified_lines.append(aim[int(line[0])] + line[1:])

    # 将修改后的内容写回文件
    with open(file_path, 'w') as file:
        file.writelines(modified_lines)


# 指定文件夹路径
folder_path = [r"F:\Temp\数据集\Keypractice\labels"]

# 调用函数修改文件夹内的txt文件
for e in folder_path:
    modify_files(e)
