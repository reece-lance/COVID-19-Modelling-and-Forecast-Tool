import settings
import rasterize
import os
import codecs
import numpy as np
from datetime import datetime

def export_map(data, dump_destination, map_file_name, comment, metadata = None):
    if os.path.exists(dump_destination) is False:
        os.makedirs(dump_destination)
    # categories are stored as [y][x][categories]
    # (categories, x_vals, y_vals) all numpy arraus
    categories, x_vals, y_vals, cat_labels = data
    categories = np.asarray(categories, dtype=int)

    output_categories = []
    for i in range(len(x_vals)):
        for j in range(len(y_vals)):
            output_categories.append(categories[j][i])
    if settings.DIAGNOSTICS:
        print('Writing {entry_count} entries...'.format(entry_count = len(output_categories)))

    destination_file_path = os.path.join(dump_destination, map_file_name);
    encoding = 'utf-8'
    with codecs.open(destination_file_path, 'w', 'utf-8') as output_file:
        output_file.write(comment)
        output_file.write('\n')
        output_file.write(str(len(cat_labels)))
        output_file.write('\n')
        output_file.write(str(len(output_categories)))
        output_file.write('\n')
        output_file.write(str(len(x_vals)))
        output_file.write('\n')
        output_file.write(str(len(y_vals)))
        output_file.write('\n')
        output_file.write('\n'.join(cat_labels))
        output_file.write('\n')
        output_file.write(str('\n'.join([str(c) for c in output_categories])))
        if metadata is not None:
            output_file.write('\n')
            output_file.write('\n'.join([str(m) for m in metadata]))            

def export_all():
    message = 'Map created on {date}'.format(date=str(datetime.now().strftime("%d/%m/%Y %H:%M:%S")))
    data = rasterize.convert_geo_json(settings.DATA_DESTINATION_REGIONS)
    filename = settings.MAP_FILE_NAME_FORMAT.format(description = 'regions')
    export_map(
        data= data,
        dump_destination= settings.MAPS_DESTINATION,
        map_file_name= filename,
        comment= message
    )
    export_map(
        data= data,
        dump_destination= settings.MAPS_DESTINATION_PROJECT,
        map_file_name= filename,
        comment= message
    )

    message = 'Map created on {date}'.format(date=str(datetime.now().strftime("%d/%m/%Y %H:%M:%S")))
    data = rasterize.convert_geo_json(settings.DATA_DESTINATION_UTLA)
    filename = settings.MAP_FILE_NAME_FORMAT.format(description = 'utla')
    export_map(
        data= data,
        dump_destination= settings.MAPS_DESTINATION,
        map_file_name= filename,
        comment= message
    )
    export_map(
        data= data,
        dump_destination= settings.MAPS_DESTINATION_PROJECT,
        map_file_name= filename,
        comment= message
    )
    
    message = 'Map created on {date}'.format(date=str(datetime.now().strftime("%d/%m/%Y %H:%M:%S")))
    data = rasterize.convert_geo_json(settings.DATA_DESTINATION_LTLA)
    filename = settings.MAP_FILE_NAME_FORMAT.format(description = 'ltla')
    export_map(
        data= data,
        dump_destination= settings.MAPS_DESTINATION,
        map_file_name= filename,
        comment= message
    )
    export_map(
        data= data,
        dump_destination= settings.MAPS_DESTINATION_PROJECT,
        map_file_name= filename,
        comment= message
    )

if __name__ == '__main__':
    export_all()