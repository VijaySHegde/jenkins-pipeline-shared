import json 
with open('data.json') as file_object:
        # store file data in object
        data = json.load(file_object)
print(data)
