# Custom settings file
import json
import math
import random
from os import listdir, mkdir, path

import geopandas as gpd
import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
from geocube.api.core import make_geocube
from PIL import Image
from shapely.geometry import box, mapping

import settings

class projection_settings:
    """ This affects the rasterisation process """
    
    # Custom projection box lat(x) long(y) in degrees
    # Should be big enough to contain all the data
    # This one correspons to the whole of UK
    minx = -8.64999583313928
    miny = 49.9586951724897
    maxx = 1.76370560966352
    maxy = 60.8607823901619
    # How many pixels do I want per deg(lat|long), sign determines direction
    resolution = (-0.01, 0.01)
    
    def __init__(self, gdf):
        """ 
            Takes in geopandas data frame and calculates the combined
            shape boundaries
        """
        bounds = gdf['geometry'].bounds
        self.maxx, self.maxy = bounds[['maxx', 'maxy']].max()
        self.minx, self.miny = bounds[['minx', 'miny']].min()
        
    def bounds(self):
        return (self.minx,self. miny, self.maxx, self.maxy)
    
    def set_resolution(self, x, y):
        """ 
            Tries to adjust the projection settings so that of the 
            final projection produced corresponds to the resolution
            in parameters in pixels.
            Preserves projections original ratio, adds margins
        """
        ratio = x/y
        
        cur_x = self.maxx - self.minx
        cur_y = self.maxy - self.miny       
        cur_ratio = cur_x/cur_y
                
        # If the ratio is already very similar, return.
        if math.isclose(ratio, cur_ratio, rel_tol=0.001):
            return
                
        # We are interested in how much to add to get to the wanted ratio
        if ratio > cur_ratio: # Are we extending x-axis?
            x_add = (((cur_x/cur_ratio) * ratio) - cur_x) / 2
            self.maxx += x_add
            self.minx -= x_add
        else: # Must be y-axis then
            y_add = (((1/ratio)*cur_x) - cur_y) / 2
            self.maxy += y_add
            self.miny -= y_add

        cur_x = self.maxx - self.minx
        cur_y = self.maxy - self.miny
        pixx_per_deg = x / cur_x
        pixy_per_deg = y / cur_y
        self.resolution = (1 / pixx_per_deg, 1/pixy_per_deg)

def get_files(directory):
    return [path.join(directory, f) for f in listdir(directory) if path.isfile(path.join(directory, f))]

def convert_geo_json(source_folder):
    #noice
    random.seed(69)

    geo_jsons = get_files(source_folder)

    print('Reading {file_count} files...'.format(file_count = len(geo_jsons)))
    areas = [gpd.read_file(f) for f in geo_jsons]

    combinedArea = pd.concat([x for x in areas]).filter(['geometry', 'areachect', 'code'])
    combinedArea.set_geometry("geometry")
    combinedArea.reset_index(inplace= True)

    categorical_enums = {'code': combinedArea.code.drop_duplicates().values.tolist()}
    
    proj_set = projection_settings(combinedArea)
    print('resolution', settings.EXPORT_RESOLUTION)
    xres, yres = settings.EXPORT_RESOLUTION
    proj_set.set_resolution(xres, yres)
    
    if settings.DIAGNOSTICS:
        print('lat long boundaries', proj_set.bounds())

    out_grid = make_geocube(
        vector_data=combinedArea,
        resolution= proj_set.resolution,
        categorical_enums=categorical_enums,
        measurements = ['code'],
        output_crs="epsg:4326",
        geom=json.dumps(mapping(box(proj_set.minx, proj_set.miny, proj_set.maxx, proj_set.maxy))),
    )

    codes = np.array(out_grid.code.values)
    x_vals = np.array(out_grid.x)
    y_vals = np.array(out_grid.y)
    cat_labels = np.array(out_grid.code_categories.to_dict()['data'])
    # Shift the whole list by 1 position forward.
    # We want categories to be indexable with their respected values, nodata = 0 but
    # is the last category label in the list. 
    temp = cat_labels[len(cat_labels) - 1]
    for i in range(1, len(cat_labels)):
        cat_labels[-i] = cat_labels[-i -1]
    cat_labels[0] = temp

    if settings.DIAGNOSTICS:
        img = Image.new( 'RGB', (len(x_vals),len(y_vals)), "black") # Create a new black image
        pixels = img.load() # Create the pixel map
        for i in range(img.size[0]):    # For every pixel:
            for j in range(img.size[1]):
                code = codes[j][i]
                if(code == -1):
                    continue
                pixels[i,-j] = (int(code % 128 + 128), 100, 120)
        img.show()

    # output_data = []
    # for i in range(len(x_vals)):
    #     output_data.append([x_vals[i], y_vals[i], codes[i]])
    # print(output_data)
    # output_data_simple = []
    # for i in range(len(x_vals)):
    #     for j in range(len(y_vals)):
    #         output_data_simple.append((i, j, codes[j][i]))
    # output_data_simple

    # Codes have the range of [-1,.., len(codes) - 1], we want them to be convertable into unsigned shorts
    return (codes.astype(np.ushort) + 1, x_vals, y_vals, cat_labels)

if __name__ == "__main__":
    print(hello)
    # convert_geo_json(settings.DATA_DESTINATION_REGIONS, settings.PLOTS_DESTINATION_REGION)
    # convert_geo_json(settings.DATA_DESTINATION_LTLA, settings.PLOTS_DESTINATION_LTLA)
    # convert_geo_json(settings.DATA_DESTINATION_UTLA, settings.PLOTS_DESTINATION_UTLA)