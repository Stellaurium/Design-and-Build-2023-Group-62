import os
import re


def get_set_from_path(path):
    file_list = os.listdir(path)
    ret_set = set()
    for file in file_list:
        # match_obj = re.match(r"^(.*?)\.\w{3,5}$", file.strip(), re.M | re.I)
        # ret_set.add(match_obj.group(1))
        ret_set.add(file[:-4])
    return ret_set


file_path1 = r"F:\Temp\数据集\Keypractice\originimages"
res_set1 = get_set_from_path(file_path1)
file_path2 = r"F:\Temp\数据集\Keypractice\labels"
res_set2 = get_set_from_path(file_path2)

for e in (res_set1 - res_set2):
    print(e)
